<template>
    <div id="exam-view">
      <el-container id="exam-container">
        <el-header>答题界面</el-header>
        <el-container>
            <el-aside>
                <el-row style="font-weight: bold;">考试科目：{{subject}}</el-row>
                <el-row style="margin-top: 20px;font-size: 14px;">
                    <el-col :span="9">总分：{{examInfo.totalScore}}分</el-col>
                    <el-col :span="9">时长：{{examInfo.duration}}分钟</el-col>
                    
                </el-row>
                <hr/>
                    <!-- 循环渲染考试信息 -->
                    <div v-for="(item, num) in examInfo.questionByTypeVoList" :key="num">
                        <!-- 单选题 -->
                        <div v-if="item.questionType === '1'">
                            <div style="float: left;margin-left: 10px;">单选题</div><br/>
                            <el-row>
                              <div v-for="(question, index) in item.answers" :key="index">
                                <el-col class="exam-item" :span="4">
                                    <el-button type="default" circle="true" 
                                    :class="{ 'selected': selectedIndex === index,'inputed': answerSheet.answers[question.questionId] && selectedIndex !== index }"
                                    @click="selectButton(index,question.questionId)">
                                        {{index+1}}
                                    </el-button>
                                </el-col>
                              </div>
                            </el-row>
                        </div>
                        <!-- 多选题 -->
                        <div v-if="item.questionType === '2'">
                            <div style="float: left;margin-left: 10px;">多选题</div><br/>
                            <el-row>
                              <div v-for="(question, index) in item.answers" :key="index">
                                <el-col class="exam-item" :span="4">
                                    <el-button type="default" circle="true" 
                                    :class="{ 'selected': selectedIndex === index+sumQuestionNum(num-1) ,'inputed': answerSheet.answers[question.questionId]&&selectedIndex !== index+sumQuestionNum(num-1)}"
                                    @click="selectButton(index+sumQuestionNum(num-1),question.questionId)">
                                        {{sumQuestionNum(num-1)+index+1}}
                                    </el-button>
                                </el-col>
                              </div>
                            </el-row>
                        </div>
                        <!-- 判断题 -->
                        <div v-if="item.questionType === '3'">
                            <div style="float: left;margin-left: 10px;">判断题</div><br/>
                            <el-row>
                              <div v-for="(question, index) in item.answers" :key="index">
                                <el-col class="exam-item" :span="4">
                                    <el-button type="default" circle="true" 
                                    :class="{ 'selected': selectedIndex === index+sumQuestionNum(num-1),'inputed': answerSheet.answers[question.questionId]&&selectedIndex !== index+sumQuestionNum(num-1)}"
                                    @click="selectButton(index+sumQuestionNum(num-1),question.questionId)">
                                        {{index+sumQuestionNum(num-1)+1}}
                                    </el-button>
                                </el-col>
                              </div>
                            </el-row>
                        </div>
                        <!-- 填空题 -->
                        <div v-if="item.questionType === '4'">
                            <div style="float: left;margin-left: 10px;">填空题</div><br/>
                            <el-row>
                              <div v-for="(question, index) in item.answers" :key="index">
                                <el-col class="exam-item" :span="4">
                                    <el-button type="default" circle="true" 
                                    :class="{ 'selected': selectedIndex === index+sumQuestionNum(num-1),'inputed': answerSheet.answers[question.questionId]&&selectedIndex !== index+sumQuestionNum(num-1)}"
                                    @click="selectButton(index+sumQuestionNum(num-1),question.questionId)">
                                        {{index+sumQuestionNum(num-1)+1}}
                                    </el-button>
                                </el-col>
                              </div>
                            </el-row>
                        </div>
                        <!-- 简答题 -->
                        <div v-if="item.questionType === '5'">
                            <div style="float: left;margin-left: 10px;">简答题</div><br/>
                            <el-row>
                              <div v-for="(question, index) in item.answers" :key="index">
                                <el-col class="exam-item" :span="4">
                                    <el-button type="default" circle="true" 
                                    :class="{ 'selected': selectedIndex === index+sumQuestionNum(num-1),'inputed': answerSheet.answers[question.questionId]&&selectedIndex !== index+sumQuestionNum(num-1)}"
                                    @click="selectButton(index+sumQuestionNum(num-1),question.questionId)">
                                        {{index+sumQuestionNum(num-1)+1}}
                                    </el-button>
                                </el-col>
                              </div>
                            </el-row>
                        </div>
                        <!-- 编程题 -->
                        <div v-if="item.questionType === '6'">
                            <div style="float: left;margin-left: 10px;">应用题</div><br/>
                            <el-row>
                              <div v-for="(question, index) in item.answers" :key="index">
                                <el-col class="exam-item" :span="4">
                                    <el-button type="default" circle="true" 
                                    :class="{ 'selected': selectedIndex === index+sumQuestionNum(num-1),'inputed': answerSheet.answers[question.questionId]&&selectedIndex !== index+sumQuestionNum(num-1) }"
                                    @click="selectButton(index+sumQuestionNum(num-1),question.questionId)">
                                        {{index+sumQuestionNum(num-1)+1}}
                                    </el-button>
                                </el-col>
                              </div>
                            </el-row>
                        </div>
                    </div><hr/>
               <el-row style="margin: 20px 80px;"><el-button type="primary" @click="submitAnswer">提交答案</el-button></el-row>
            </el-aside>
            <el-main>
                <div style="float: left;width: 90%;height: 450px;position: relative;">
                    <div v-if="question.type === '1'">
                        <div class="question-title">
                            <div style="float: left;">{{selectedIndex+1}}. {{ question.question }}</div>
                        </div>
                        <div>
                            <el-radio-group v-model="answerSheet.answers[question.questionId]">
                            <el-radio :value="'A'">A. {{ question.optionA }}</el-radio>
                            <el-radio :value="'B'">B. {{question.optionB }}</el-radio>
                            <el-radio :value="'C'">C. {{question.optionC }}</el-radio>
                            <el-radio :value="'D'">D. {{question.optionD }}</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                    <div v-if="question.type === '2'">
                        <div class="question-title">
                            <div style="float: left;">{{selectedIndex+1}}. {{ question.question }}</div>
                        </div>
                        <div>
                            <el-checkbox-group v-model="answerSheet.answers[question.questionId]">
                                <el-checkbox :value="8">A. {{question.optionA }}</el-checkbox>
                                <el-checkbox :value="4">B. {{question.optionB }}</el-checkbox>
                                <el-checkbox :value="2">C. {{question.optionC }}</el-checkbox>
                                <el-checkbox :value="1">D. {{question.optionD }}</el-checkbox>
                            </el-checkbox-group>
                        </div>
                    </div>
                    <div v-if="question.type === '3'">
                        <div class="question-title">
                            <div style="float: left;">{{selectedIndex+1}}. {{ question.question }}</div>
                        </div>
                        <div>
                            <el-radio-group v-model="answerSheet.answers[question.questionId]" style="float: left;">
                            <el-radio :value="'1'">正确</el-radio>
                            <el-radio :value="'0'">错误</el-radio>
                            </el-radio-group>
                        </div>
                    </div>
                    <div v-if="question.type === '4'">
                        <div class="question-title">
                            <div style="float: left;">{{selectedIndex+1}}. {{ question.question }}</div>
                        </div>
                        <div v-for="n in question.answerCount" :key="n">
                            第{{n}}空：<el-input v-model="answerList[n-1]" placeholder="请输入答案" style="width: 200px;margin: 10px;"></el-input>
                        </div>
                    </div>  
                    <div v-if="question.type === '5'">
                        <div class="question-title">
                            <div style="float: left;">{{selectedIndex+1}}. {{ question.question }}</div>
                        </div>
                        <!-- 文本输入框 -->
                        <el-input type="textarea" v-model="answerSheet.answers[question.questionId]" placeholder="请输入答案" :rows="10" style="width: 95%;margin: 30px;"></el-input>
                    </div>
                    <div v-if="question.type === '6'">
                        <div class="question-title">
                            <div v-if="isImage(question.question)">
                                <div style="float: left;">{{selectedIndex+1}}. </div><br/>
                                <div v-for="(item, index) in imageUrls" :key="index">
                                    <img :src="imageUrls[index]" style="width: 80%;;max-height: 60%;"/>
                                </div>
                            </div>
                            <div v-else style="float: left;">{{selectedIndex+1}}. {{ question.question }}</div>
                        </div>
                        <el-upload style="float: left;margin-left: 30px;top:30px;position: relative;"
                            class="avatar-uploader"
                            action="/api/exam/file/upload"
                            :headers="headers"
                            :data="{'url': answerSheet.answers[question.questionId]}"
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload"
                        >
                            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                            {{uploadButton}} </el-upload>
                        <!-- 文本输入框 -->
                        <el-button type="default" @click="clear" style="float: left;margin-left: 10px;top: 25px;position: relative;">
                            重置答案
                        </el-button>
                        <el-input type="textarea" :disabled="textInputDisabled" v-model="answerSheet.answers[question.questionId]" placeholder="请输入答案" :rows="12" style="width: 95%;margin: 30px;"></el-input>       
                    </div>
                <div style="float: left;width: 100%;margin-bottom: 100px;margin-top: 50px;padding-bottom: 100px;">
                    <el-button size="large" @click="before" style="margin-left: 200px;">上一题</el-button>
                    <el-button size="large" @click="next" style="margin-left: 100px;">下一题</el-button>
                </div>
                </div>
                
                <div id="end"><el-countdown title="距离考试结束时间" :value="endTime" @finish="submitAnswer" /></div>
                <!-- 考试完成消息弹窗-->
                <el-dialog title="考试完成" v-model="dialogVisible" width="30%" :before-close="handleClose">
                <div style="margin: 20px;padding-bottom: 20px;">请等待考试结果，点击确定返回我的考试页面。</div>
                <el-button type="primary" @click="returnToMyExam">确定</el-button>
                </el-dialog>
            </el-main>
        </el-container>
      </el-container>
    </div>
  </template>
  
  <script>
  import request from '@/utils/request';
  import { Plus } from '@element-plus/icons-vue'

  
  export default {
    name: 'App',
    components: {
      Plus
    },
    data() {
      return {
        scoreId: null, // 成绩ID
        dialogVisible: false, // 考试完成消息弹窗
        textInputDisabled: false, // 文本输入框是否禁用
        answerList: [], // 答案列表
        answerSheet: {
            paperId: null, // 考试ID
            studentId: null, // 学生ID
            answers: {}, // 用户答案
            answerDate: null, // 考试日期
            time: null, // 用时
        }, // 答题卡
        imageUrls: [], // 图片链接
        uploadButton: '上传答案（PDF格式）', // 上传按钮文字
        headers: {
            'token': localStorage.getItem('token')
        },
        subject:'计算机科学与技术', // 科目名称
        examId: null, // 考试ID
        studentId: null, // 学生ID
        endTime: null, // 考试结束时间
        question:{
            questionId: null, // 题目ID
            questionType: '1', // 题目类型
            questionScore: null, // 题目分值
            question: null, // 题目内容
            questionItems: null, // 题目选项
            answer: null, // 题目答案
        },
        examInfo: {
            totalScore: 100, // 总分
            duration: 60, // 时长
            examName: '测试考试', // 考试名称
            examId: null, // 考试ID
            questionByTypeVoList: [ // 题目列表
                {
                    questionType: 1, // 题目类型
                    questionNums: 9, // 题目数量
                    totalScore: 10, // 题目分值
                    answers: [ // 题目详情
                        {questionId: 1},
                    ]
                },
                {
                    questionType: 2,
                    questionNums: 5,
                    questionScore: 20,
                    questionItems: [
                        {questionId: 6}
                    ]
                },
                {
                    questionType: 3,
                    questionNums: 3,
                    questionScore: 30,
                    questionItems: [
                        {questionId: 11}
                    ]
                },
                {
                    questionType: 4,
                    questionNums: 2,
                    questionScore: 40,
                    questionItems: [
                        {questionId: 14}
                    ]
                },
                {
                    questionType: 5,
                    questionNums: 1,
                    questionScore: 50,
                    questionItems: [
                        {questionId: 16}
                    ]
                },
                {
                    questionType: 6,
                    questionNums: 1,
                    questionScore: 60,
                    questionItems: [
                        {questionId: 17}
                    ]
                }
            ]
        }, // 考试信息
        loading: false, // 加载状态
        selectedIndex: 0  // 初始选中第一个按钮
      };
    },
    created() {
      this.subject = this.$route.query.subject;
      this.examId = this.$route.query.examCode;
      this.studentId = localStorage.getItem('studentId');
      this.answerSheet.answerDate = new Date();
      this.endTime = this.$route.query.endTime;
      this.init();
    },
    methods: {
        init(){
            if (!this.examId) {
                this.$message.error('考试ID不能为空');
                return;
            }
            request.get(`/api/exam/getExamInfo/${this.examId}`).then(res => {
                this.examInfo = res.data;
                console.log("examInfo",this.examInfo);
                this.getQuestion(this.getQuestionId());
            });
            
        },
        selectButton(index,questionId) {
            this.selectedIndex = index;
            this.getQuestion(questionId);
        },
        getQuestionId() {
            let questionByTypeVoList = this.examInfo.questionByTypeVoList;
            let sum = 0;
            for (let i = 0; i < questionByTypeVoList.length; i++) {
                for (let j = 0; j < questionByTypeVoList[i].answers.length; j++) {
                    if(this.selectedIndex === sum){
                        return questionByTypeVoList[i].answers[j].questionId;
                    }
                    sum++;
                }
            }

        },
        getQuestion(questionId) {
            if(this.question.type === '2'){
                if(Array.isArray(this.answerSheet.answers[this.question.questionId])){
                    let arr = this.answerSheet.answers[this.question.questionId];
                    let result = 0;
                    arr.forEach(element => {
                    result += element;
                    });
                    this.calAsnwer(result);
                }
            }
            if(this.question.type === '4'){
                this.splitAnswer()
            }
            request.get(`/api/exam/getQuestion/${questionId}`).then(res => {
                this.question = res.data;
                this.question.answer = [];
                if(this.question.type === '2'){
                    this.breakAnswer(this.answerSheet.answers[this.question.questionId]);
                }
                if(this.question.type === '4'){
                    this.partAnswer();
                }
                if(this.question.type === '6'){
                    let answer = this.answerSheet.answers[this.question.questionId];
                    if(answer && answer.startsWith('http://')){
                        this.textInputDisabled = true;
                    }else{
                        this.textInputDisabled = false;
                    }
                }
                console.log("question",this.question);
            });
        },
        sumQuestionNum(num) {
            let sum = 0;
            for (let i = 0; i <= num; i++) {
                sum += this.examInfo.questionByTypeVoList[i].questionNums;
            }
            return sum;
        },
        before() {
            if (this.selectedIndex > 0) {
                this.selectedIndex--;
                this.getQuestion(this.getQuestionId());
            }
        },
        next() {
            let num = this.sumQuestionNum(this.examInfo.questionByTypeVoList.length - 1);
            if (this.selectedIndex < num - 1) {
                this.selectedIndex++;
                this.getQuestion(this.getQuestionId());
            }
            // alert(this.selectedIndex)
        },
        isImage(question) {
            // 如果question去掉两边的空格，是以[开始，以]结束，则是图片链接，否则是文字
            question = question.trim();
            if (question.startsWith('[') && question.endsWith(']')) {
                this.imageUrls = []
                question = question.substring(1, question.length - 1);
                let imgArr = question.split(',');
                for (let i = 0; i < imgArr.length; i++) {
                    this.imageUrls.push(imgArr[i].trim());
                }
                return true;
            }
            return false;
        },
        beforeAvatarUpload(file) {
            // 判断是不是PDF文件
            const isPdf = file.type === 'application/pdf';
            if (!isPdf) {
                this.$message.error('请上传PDF文件');
                return false;
            }
            return true;
        },
        handleAvatarSuccess(res, file) {
            // 上传成功，弹出上传成功的提示，并将图片链接保存到答题卡中
            this.uploadButton = '上传成功,点击修改';
            this.textInputDisabled = true;
            this.$message.success('上传成功');
            let url = res.data.url;
            this.answerSheet.answers[this.question.questionId] = url;
            console.log("answerSheet",this.answerSheet);

        },
        clear(){
            this.answerSheet.answers[this.question.questionId] = "";
            this.textInputDisabled = false;
            this.uploadButton = '上传答案（PDF格式）';
        },
        calAsnwer(result) {
            if(result === 1){
                this.answerSheet.answers[this.question.questionId] = 'D';
            }else if(result === 2){
                this.answerSheet.answers[this.question.questionId] = 'C';
            }else if(result === 4){
                this.answerSheet.answers[this.question.questionId] = 'B';
            }else if(result === 8){
                this.answerSheet.answers[this.question.questionId] = 'A';
            }else if(result === 12){
                this.answerSheet.answers[this.question.questionId] = 'AB';
            }else if(result === 10){
                this.answerSheet.answers[this.question.questionId] = 'AC';
            }else if(result === 9){
                this.answerSheet.answers[this.question.questionId] = 'AD';
            }else if(result === 6){
                this.answerSheet.answers[this.question.questionId] = 'BC';
            }else if(result === 5){
                this.answerSheet.answers[this.question.questionId] = 'BD';
            }else if(result === 3){
                this.answerSheet.answers[this.question.questionId] = 'CD';
            }else if(result === 14){
                this.answerSheet.answers[this.question.questionId] = 'ABC';
            }else if(result === 11){
                this.answerSheet.answers[this.question.questionId] = 'ACD';
            }else if(result === 13){
                this.answerSheet.answers[this.question.questionId] = 'ABD';
            }else if(result === 7){
                this.answerSheet.answers[this.question.questionId] = 'BCD';
            }else if(result === 15){
                this.answerSheet.answers[this.question.questionId] = 'ABCD';
            }else{
                return false;
            }
            return true;
        },
        breakAnswer(answer) {
            if(answer === 'A'){
                this.answerSheet.answers[this.question.questionId] = [8,0,0,0];
            }else if(answer === 'B'){
                this.answerSheet.answers[this.question.questionId] = [0,4,0,0];
            }else if(answer === 'C'){
                this.answerSheet.answers[this.question.questionId] = [0,0,2,0];
            }else if(answer === 'D'){
                this.answerSheet.answers[this.question.questionId] = [0,0,0,1];
            }else if(answer === 'AB'){
                this.answerSheet.answers[this.question.questionId] = [8,4,0,0];
            }else if(answer === 'AC'){
                this.answerSheet.answers[this.question.questionId] = [8,0,2,0];
            }else if(answer === 'AD'){
                this.answerSheet.answers[this.question.questionId] = [8,0,0,1];
            }else if(answer === 'BC'){
                this.answerSheet.answers[this.question.questionId] = [0,4,2,0];
            }else if(answer === 'BD'){
                this.answerSheet.answers[this.question.questionId] = [0,4,0,1];
            }else if(answer === 'CD'){
                this.answerSheet.answers[this.question.questionId] = [0,0,2,1];
            }else if(answer === 'ABC'){
                this.answerSheet.answers[this.question.questionId] = [8,4,2,0];
            }else if(answer === 'ACD'){
                this.answerSheet.answers[this.question.questionId] = [8,0,2,1];
            }else if(answer === 'ABD'){
                this.answerSheet.answers[this.question.questionId] = [1,1,0,1];
            }else if(answer === 'BCD'){
                this.answerSheet.answers[this.question.questionId] = [0,1,1,1];
            }else if(answer === 'ABCD'){
                this.answerSheet.answers[this.question.questionId] = [1,1,1,1];
            }else{
                return false;
            }
            return true;
        },
        splitAnswer() {
            // 循环answerList，将答案凭借到answerSheet.answers中
            for(let i=0;i<this.answerList.length;i++){
                if(!this.answerList[i]){
                    this.answerList[i] = '';
                }
                if(i === 0){
                    this.answerSheet.answers[this.question.questionId] = this.answerList[i];
                }else{
                    this.answerSheet.answers[this.question.questionId] += ',' + this.answerList[i];
                }
                
            }
            this.answerList = [];
        },
        partAnswer(){
            //this.answerSheet.answers[this.question.questionId]按逗号分割，存入answerList
            let answer = this.answerSheet.answers[this.question.questionId];
            let arr = [];
            if(typeof answer === 'string'){
                arr = answer.split(',');
            }
            for(let i=0;i<arr.length;i++){
                this.answerList.push(arr[i]);
            }
            this.answerSheet.answers[this.question.questionId] = "";
        },
        returnToMyExam() {
            this.$router.push({ path: '/student' });
        },
        submitAnswer() {
            if(this.question.type === '2'){
                if(Array.isArray(this.answerSheet.answers[this.question.questionId])){
                    let arr = this.answerSheet.answers[this.question.questionId];
                    let result = 0;
                    arr.forEach(element => {
                    result += element;
                    });
                    this.calAsnwer(result);
                }
            }
            if(this.question.type === '4'){
                this.splitAnswer();
            }
            console.log("answerSheet",this.answerSheet);
            // 计算用时，精确到分钟
            let duration = (new Date() - this.answerSheet.answerDate) / 1000 / 60;
            this.answerSheet.time = duration
            this.answerSheet.studentId = this.studentId;
            this.answerSheet.paperId = this.examId;
            request.post('/api/exam/createScoreRecord', this.answerSheet).then(res => {
                console.log("submitAnswer",res);
                if(res.code === 200){
                    this.$message.success('提交成功');
                    this.dialogVisible = true;
                }else if(res.code === 204){
                    this.$message.error('重复提交')
                }else{
                    this.$message.error('提交失败');
                }
            });
        }
    }
  };
  </script>
  
  <style scoped>
  /* 样式可以根据需要进行调整 */
  #exam-view {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    text-align: left;
    color: #2c3e50;
    background-color: rgb(235.9, 245.3, 255);
    height: 100vh;
    overflow: hidden;
  }

  #exam-container {
    padding: 10px 20px;
   
  }
  .el-container {
    width: 100%;
    height: 100%;
    margin: 0 auto;
    display: flex;
  }
  
  .el-header {
    background-color: #fff;
    height: 30px;
    border-bottom: 0.5px solid #4c8696;
  }
  .el-aside {
    background-color: #fff;
    padding: 0;
    height: 100%;
    border-right: 1px solid #eee;
    width: 290px;
    padding-bottom: 30px;
  }
  .el-main {
    background-color: #fff;
    height: 100%;
    /* overflow: hidden; */
    flex: 1;
    padding-bottom: 50px;
  }
  #end{
    bottom: 10px;
    right: 35px;
    position:fixed;
  }
 .el-row {
    margin: 15px 5px;
  }
  .exam-item {
    margin: 5px 8px;
  }
  .selected {
  background-color: #42b983;
  color: white;
}
 .inputed {
    background-color: #81eabb;
    color: white;
}
.question-title {
    float: left;
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 20px;
    padding-left: 30px;
    width: 100%;
}
.el-radio{
    width: 100%;
    margin: 20px 10px;
    padding-left: 30px;
}
.el-checkbox {
    width: 100%;
    margin: 20px 10px;
    padding-left: 30px;
}
  </style>