<template>
    <el-container>
     
      <el-main>
        <h1>考试分析</h1>
        <el-card class="mb-20">
            <el-row>
                <el-select 
            v-model="value"
            clearable
            placeholder="选择科目"
            style="width: 150px;float: left;top: -7px;position: relative;"
          >
            <el-option @click="selectSubject(item.value,item.label)"
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
          <el-select 
            v-model="value2"
            clearable
            placeholder="选择考试"
            style="width: 150px;float: left;top: -7px;position: relative;"
          >
            <el-option @click="selectExam(item.value,item.label,item.score)"
              v-for="item in options2"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
            </el-row>
          <el-row>
                你的本次考试成绩为：{{score}}分
          </el-row>
          
          <span style="font-weight: bold;">题型分析</span>

          <el-row class="card1">
            
            <el-col :span="12">
                
                <el-table :data="tableData" border style="width: 100%;">
                    <el-table-column prop="questionType" label="题型" width="150" />
                    <el-table-column prop="questionCount" label="题目数量"/>
                    <el-table-column prop="totalScore" label="题型总分" />
                    <el-table-column prop="userScore" label="获得分数" />
                    <el-table-column prop="scoreRate" label="得分率" />
                </el-table>
            </el-col>
            <el-col :span="12">
                智能评估<br/>
                <div v-loading="loading1" 
                style="font-size: 14px;line-height: 24px;padding: 20px;text-align:left;height: 84%">
                {{ analysis1 }}</div>
            </el-col>
          </el-row>
          <span style="font-weight: bold;">难度分析</span>
          <el-row class="card1">
            <el-col :span="12">
                <el-table :data="tableData2" border style="width: 100%;">
                    <el-table-column prop="questionType" label="难度分类" width="150" />
                    <el-table-column prop="questionCount" label="题目数量"/>
                    <el-table-column prop="totalScore" label="总分" />
                    <el-table-column prop="userScore" label="获得分数" />
                    <el-table-column prop="scoreRate" label="得分率" />
                </el-table>
            </el-col>
            <el-col :span="12">
                智能评估
                <div v-loading="loading2" 
                style="font-size: 14px;line-height: 24px;padding: 20px;text-align:left;height: 84%">
                {{ analysis2 }}</div>
            </el-col>
          </el-row>
          <span style="font-weight: bold;">章节薄弱点分析</span>
          <el-row class="card1">
            <el-col :span="12"><Schart5/></el-col>
              <el-col :span="12"><Schart6/></el-col>
              </el-row>
              <el-row class="card1">
                <el-col :span="24" >
                  <span style="font-weight: bold;text-align: center;width: 100%;">智能分析</span>
                  <div v-loading="loading" 
                      style="font-size: 14px;margin-bottom: 100px; line-height: 24px;padding: 20px;text-align:left;height: 80%;margin:10px 200px;border: 1px solid #e4e4e4;border-radius: 4px;background-color: #fff;">
                      {{ analysis }}</div>
                      
                </el-col>
              </el-row>
        </el-card>
          
      </el-main>
    </el-container>
  </template>
  
<script>
import request from '@/utils/request'
import { DataAnalysis } from '@element-plus/icons-vue'
import Schart5 from '@/components/charts/Schart5.vue'
import Schart6 from '@/components/charts/Schart6.vue'
  import { mapState } from 'vuex'
  export default {
    name: 'analysis',
    components: {
      DataAnalysis,
      Schart5,
      Schart6
    },
    data() {
      return {
        score: null,
        options: [],
        value: '',
        options2: [],
        value2: '',
        tableData: [],
        tableData2: [],
        loading1: false,
        loading2: false,
        loading: false,
        analysis1: '',
        analysis2: '',
        analysis: ''
    }
    },
    created() {
      this.getSubjects()
      if (this.analysisSubject != null) {
        this.value = this.analysisSubject
        this.getExams()
      }
      if (this.analysisScoreId != null) {
        this.value2 = this.analysisScoreId
        this.selectExam(this.analysisScoreId,null,null)
        // this.getAnalysis3()
      }
    },
    computed: {
      ...mapState(['analysisSubject','analysisScoreId'])
    },
    watch: {
      analysisScoreId: function (newVal, oldVal) {
        this.selectExam(newVal,null,null)
      }
    },
    methods: {
      getSubjects() {
        let studentId = localStorage.getItem('studentId')
        request.get(`/api/analysis/getSubjects/${studentId}`).then((res) => {
          console.log(res.data)
          this.options = res.data
        })
      },
      selectSubject(subjectId,subjectName) {
        this.$store.commit('setAnalysisSubject', subjectId)  
        this.value = subjectName;
        this.getExams()
      },
      getExams() {
        let studentId = localStorage.getItem('studentId')
        request.get(`/api/exam/queryScoreByStudentIdAndSubjectId/${studentId}/${this.analysisSubject}`).then((res) => {
          
          if(res.code === 200){
            console.log("考试列表",res.data)
            this.options2 = res.data
          }else{
            alert(res.msg)
          }
        })
      },
      selectExam(scoreId,scoreName,score) {
        this.score = score;
        this.$store.commit('setAnalysisScoreId', scoreId)  
        request.get(`/api/analysis/getExamQuestionTypeAnalysis/${scoreId}`).then((res) => {
          if(res.code === 200){
            console.log(res.data)
            let data = res.data
            // scoreRate 保留两位小数
            let score = 0
            data.forEach(item => {
              score += item.userScore
              item.scoreRate = (item.userScore / item.totalScore * 100).toFixed(2)
            })
            this.score = score.toFixed(2)
            this.tableData = res.data
            // this.getAnalysis1()
          }else{
            alert(res.msg)
          }
        })
        request.get(`/api/analysis/getExamQuestionDifficulty/${scoreId}`).then((res) => {
          if(res.code === 200){
            console.log("tableData2",res.data)
            // scoreRate 保留两位小数
            let data = res.data
            data.forEach(item => {
              item.scoreRate = (item.userScore / item.totalScore * 100).toFixed(2)
            })
            this.tableData2 = res.data
            // this.getAnalysis2()
          }else{
            alert(res.msg)
          }
        })
      },
      getAnalysis1() {
        this.loading1 = true
        request.post('/api/login/examAnalysis1',this.tableData).then((res) => {
          if(res.code === 200){
            console.log(res.data)
            this.analysis1 = res.data.message
            this.loading1 = false
          }else{
            alert("请求失败")
            this.getAnalysis1()
          }
        })
      },
      getAnalysis2() {
        this.loading2 = true
        request.post('/api/login/examAnalysis2',this.tableData2).then((res) => {
          if(res.code === 200){
            console.log(res.data)
            this.analysis2 = res.data.message
            this.loading2 = false
          }else{
            alert("请求失败")
            this.getAnalysis2()
          }
        })
      },
      getAnalysis3() {
        this.loading = true
        request.get(`/api/login/examAnalysis4/${this.analysisScoreId}`).then((res) => {
          if(res.code === 200){
            console.log(res.data)
            this.analysis = res.data.message
            this.loading = false
          }else{
            alert("请求失败")
            this.getAnalysis3()
          }
        })
      }
    }
  }
</script>
  
  <style scoped>
  .el-main {
    /* background-color: #fff; */
    padding: 0px;
    /* background: url(@/assets/images/analy1.jpg); */
    
  }
  #analysis {
    font-size: 16px;
    width: 100%;
    height: 50px;
    font-weight: bold;
    background: #fff;
  }
  .mb-20 {
    display: flex;
    /* height: 950px; */
  }
  .el-card {
    margin: 0px;
    background-color: rgba(255, 255, 255, 0.7); /* 半透明背景 */

  }
  .el-col {
    border-radius: 4px;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease, border-color 0.3s ease; /* 添加过渡效果，使变化更平滑 */
  }
  .el-col:hover {
  /* 鼠标悬停时的样式 */
  background-color: #f0f0f0; /* 改变背景颜色 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
  /* 你可以在这里添加其他悬停时的样式，如阴影、字体颜色等 */
}
  .card1 {
    width: 1185px;
    height: 450px;
    left: 10px;
    position: relative;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease, border-color 0.3s ease; /* 添加过渡效果，使变化更平滑 */
    /* background-color: #fff; */

  }
  .card2 {
    width: 1175px;
    height: 80%;
    /* background-color: #fff; */

  }
  .card1-part {
    height: 20%;
    margin: 12px 12px;
    padding-top: 20px;
  }
  .card1-part-title {
    height: 100px;
    width: 120px;
    line-height: 40px;
    font-size: 14px;
    font-weight: bold;
    color: #333;
    text-align: center;
    margin: 10px 10px;
    box-shadow: 0 2px 3px 0 rgba(0, 0, 0, 0.3);
  }
  </style>