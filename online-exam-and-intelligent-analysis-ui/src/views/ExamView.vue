<template>
  <!-- 废弃方案 太过时了 -->
  <div id="app">
    <el-container>
      <el-header>答题界面</el-header>
      <el-main>
        <div v-if="loading">加载中...</div>
        <div v-else>
          <div id="end"><el-countdown title="距离考试结束时间" :value="endTime" @finish="submitAnswer" /></div>
          <div v-for="(questionGroup, index) in testQuestionVos" :key="index">
            <!-- 单选题 -->
            <div v-if="questionGroup.questionType === '1'" class="question-group">
              <h3 style="text-align: center;">题型：单选题（共 {{ questionGroup.questionNumByType }} 题 {{questionGroup.totalScoreByType}} 分）</h3>
              <div v-for="(question, qIndex) in multiChoices" :key="question.questionId">
                <div v-if="question.type === '1'">
                  <h4 class="question-title">{{ qIndex + 1 }}. {{ question.question }}</h4>
                  <el-radio-group v-model="answers[question.questionId]">
                    <el-radio :value="'1000'">A. {{ question.optionA }}</el-radio>
                    <el-radio :value="'0100'">B. {{question.optionB }}</el-radio>
                    <el-radio :value="'0010'">C. {{question.optionC }}</el-radio>
                    <el-radio :value="'0001'">D. {{question.optionD }}</el-radio>
                  </el-radio-group>
                </div>
              </div>
            </div>
            <!-- 多选题 -->
            <div v-if="questionGroup.questionType === '2'" class="question-group">
              <h3 style="text-align: center;">题型：多选题（共 {{ questionGroup.questionNumByType }} 题 {{questionGroup.totalScoreByType}} 分）</h3>
              <div v-for="(question, qIndex) in multiChoices" :key="question.questionId">
                <div v-if="question.type === '2'">
                  <h4 class="question-title">{{ qIndex + 1 }}. {{ question.question }}</h4>
                  <el-checkbox-group v-model="multiAnswers[question.questionId]">
                    <el-checkbox :value="8">A. {{question.optionA }}</el-checkbox>
                    <el-checkbox :value="4">B. {{question.optionB }}</el-checkbox>
                    <el-checkbox :value="2">C. {{question.optionC }}</el-checkbox>
                    <el-checkbox :value="1">D. {{question.optionD }}</el-checkbox>
                  </el-checkbox-group>
                </div>
              </div>
            </div>
            <!-- 判断题 -->
            <div v-if="questionGroup.questionType === '3'" class="question-group">
              <h3 style="text-align: center;">题型：判断题（共 {{ questionGroup.questionNumByType }} 题 {{questionGroup.totalScoreByType}} 分）</h3>
              <div v-for="(question, qIndex) in judges" :key="question.questionId">
                  <h4 class="question-title">{{ qIndex + 1 + multiChoices.length }}. {{ question.question }}</h4>
                  <el-radio-group v-model="answers[question.questionId]">
                    <el-radio :value="'1'">对</el-radio>
                    <el-radio :value="'0'">错</el-radio>
                    
                  </el-radio-group>             
              </div>
            </div>
          </div>
        
          <el-button type="primary" @click="submitAnswer" style="margin-bottom: 30px;">提交试卷</el-button>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import request from '@/utils/request';


export default {
  name: 'App',
  data() {
    return {
      subject: '', // 考试科目
      endTime: 0, // 考试结束时间
      score: 0, // 总分数
      testQuestionVos: [], // 存储试题信息
      multiChoices: [],// 存储单选和多选题信息
      judges:[],// 存储判断题信息
      answers: {}, // 存储用户答案
      multiAnswers: [], // 存储多选题答案
      scoreResult:{// 存储用户答题结果
        studentId: '',// 学号
        score: '',// 分数
        paperId: '',// 试卷编号
        answerDate: '',// 答题日期
        time: '',// 用时
        answer: '',// 答案
        result: {}// 结果,存储每个题目的对错情况
      },
      loading: true // 加载状态
    };
  },
  created() {
    this.endTime = this.$route.query.endTime;// 获取考试结束时间
    this.subject = this.$route.query.subject;// 获取考试科目
    console.log('examTime:',this.examTime);
    this.scoreResult.studentId = localStorage.getItem('studentId');// 获取学生学号
    this.scoreResult.paperId = this.$route.query.examCode;// 获取试卷编号
    this.scoreResult.answerDate = new Date();// 获取当前时间为考试开始时间
    console.log('this.scoreResult:',this.scoreResult);
    this.fetchQuestions();
    this.loading = false;
  },
  methods: {
    fetchQuestions() {
      let examCode = this.$route.query.examCode;
      if (examCode) {
        request.get(`/api/exam/getQuestionDetail/${examCode}`)
          .then(res => {
            console.log('Test questions:', res.data);
            this.testQuestionVos = res.data.testQuestionVos;
            this.multiChoices = res.data.multiChoices;
            this.judges = res.data.judges;
            this.loading = false;
            console.log('this.testQuestionVos:', this.testQuestionVos);
            console.log('this.multiChoices:', this.multiChoices);
            console.log('this.judges:', this.judges);
          })
        
          .catch(error => {
            console.error('Failed to fetch test questions:', error);
            this.loading = false;
          });
           
          } else {
            alert('examCode is required');
            this.loading = false;
      }
    },
    
    submitAnswer() {
      console.log('提交答案',this.answers);
      // 计算考试用时
      let endTime = Math.floor((new Date() - this.scoreResult.answerDate) / (1000*60));// 计算考试用时
      console.log('endTime:',endTime);

      this.score=0;// 防止多次提交时分数累加
      // 将多选题的答案转换为字符串
      for (let i = 0; i < this.multiChoices.length; i++) {
        if(this.multiChoices[i].type === '2'){
          if(Array.isArray(this.multiAnswers[this.multiChoices[i].questionId])){
            let arr = this.multiAnswers[this.multiChoices[i].questionId];
            let result = 0;
            arr.forEach(element => {
              result += element;
            });
            let is_ok = this.calAsnwer(result,i);
            if(!is_ok){
                alert('多选题需要选择两个以上答案，请检查第'+(i+1)+'题');
                return;
            }
          }
        }
      }
      //检查答案是否填写完整
      const answerKeysLength = Object.keys(this.answers).length;
      if(answerKeysLength === (this.multiChoices.length + this.judges.length)){
        console.log('答案填写完整')
      }else{
        console.log('answerKeysLength',answerKeysLength);
        console.log('this.multiChoices.length:',this.multiChoices.length);
        console.log('this.judges.length:',this.judges.length)
        alert('答案填写不完整，请检查');
        return;
      }
      
      //计算单选题和多选题的分数
      for (let i = 0; i < this.multiChoices.length; i++) {
        let question = this.multiChoices[i];
        let userAnswer = this.answers[question.questionId];
        if (userAnswer === question.answer) {
          this.scoreResult.result[question.questionId] = '1';
          this.score += question.score;
        } else {
          this.scoreResult.result[question.questionId] = '0';
        }
      }
      //计算判断题的分数
      for (let i = 0; i < this.judges.length; i++) {
        let question = this.judges[i];
        let userAnswer = this.answers[question.questionId];
        if (userAnswer === question.answer) {
          this.scoreResult.result[question.questionId] = '1';
          this.score += question.score;
        }else {
          this.scoreResult.result[question.questionId] = '0';
        }
      }
      console.log('总分数',this.score);
      this.scoreResult.score = this.score;// 设置分数
      this.scoreResult.time = endTime;// 设置用时
      this.scoreResult.answer = this.answers;// 设置答案
      console.log('this.scoreResult:',this.scoreResult);
      // 添加 axios.post 保存成绩到服务器
      request.post('/api/exam/saveScore', this.scoreResult).then(res => {
        console.log('提交答案成功:', res.data);
        if(res.code === 200){
          delete this.scoreResult.answer;
          this.scoreResult.subject = this.subject;
          this.$router.push({ path: '/endView', query: {score: this.scoreResult.score,answerDate: this.scoreResult.answerDate,subject: this.subject,time: this.scoreResult.time} });
          
        }
        
      }).catch(error => {
        console.error('提交答案失败:', error);
      })
      
    },
    calAsnwer(result,i) {
      if(result === 12){
              this.answers[this.multiChoices[i].questionId] = '1100';
      }else if(result === 10){
        this.answers[this.multiChoices[i].questionId] = '1010';
      }else if(result === 9){
        this.answers[this.multiChoices[i].questionId] = '1001';
      }else if(result === 6){
        this.answers[this.multiChoices[i].questionId] = '0110';
      }else if(result === 5){
        this.answers[this.multiChoices[i].questionId] = '0101';
      }else if(result === 3){
        this.answers[this.multiChoices[i].questionId] = '0011';
      }else if(result === 14){
        this.answers[this.multiChoices[i].questionId] = '1110';
      }else if(result === 11){
        this.answers[this.multiChoices[i].questionId] = '1011';
      }else if(result === 13){
        this.answers[this.multiChoices[i].questionId] = '1101';
      }else if(result === 7){
        this.answers[this.multiChoices[i].questionId] = '0111';
      }else if(result === 15){
        this.answers[this.multiChoices[i].questionId] = '1111';
      }else{
        return false;
      }
      return true;
    }
   
  }
};
</script>

<style>
/* 样式可以根据需要进行调整 */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  padding-top: 30px;
  background-color: rgb(235.9, 245.3, 255);
  
}
#end{
  bottom:10px;
  left:12px;
  position:fixed;
}
.el-radio-group {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.el-checkbox-group {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.el-radio {
  margin: 5px 230px;
  
}
.el-checkbox{
  margin: 5px 230px;

}
.question-group {
  margin: 20px 100px;
  background-color: #fff;
  padding-top: 20px;
  padding-bottom: 30px;
  text-align: left;
}

.question-title {
  margin: 25px 210px;
}

</style>