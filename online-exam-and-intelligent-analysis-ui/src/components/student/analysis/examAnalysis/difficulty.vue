<template>
    <div>
        <el-row class="card1-part">
            <el-icon style="margin-top: 3px;margin-right: 10px;"><DataAnalysis /></el-icon>
            <span style="font-weight: bold;">{{subjectDetail.subjectName}}</span>
            </el-row>
            <el-divider border-style="double" />
        <el-row>
                <span style="padding-left: 20px;font-size: 16px;color: #666;height: 30px;">
                    选择考试：
                </span>
                <div v-for="(exam, index) in options2" :key="index">
                    <el-col :class="{ 'selected': selectedIndex === index}" style="padding: 5px 7px;margin:8px 8px;font-size: 12px;" @Click="selectExam(exam.value,null,null,index)" >{{exam.label}}</el-col>
                </div>
                <el-divider border-style="double" />
            </el-row>
            
            <div>
              <div style="text-align: left;font-weight: bold;margin-bottom: 10px;">本次考试中各试题难度统计分析表</div>
                <el-table :data="tableData2" stripe style="width: 100%">
                    <el-table-column prop="questionType" label="难度分类" width="150" />
                    <el-table-column prop="questionCount" label="题目数量"/>
                    <el-table-column prop="totalScore" label="总分" />
                    <el-table-column prop="userScore" label="获得分数" />
                    <el-table-column prop="scoreRate" label="得分率" />
                </el-table>
            </div>
            <el-divider border-style="double" />
            <div style="text-align: center;text-align: left;">
                <el-button type="primary" style="margin-left: 3px;" @Click='getAnalysis2'>点击生成ai分析报告</el-button>
                <div v-loading="loading" style="font-family: Inter, 'Helvetica Neue', Helvetica, 'PingFang SC',
  'Hiragino Sans GB', 'Microsoft YaHei', '微软雅黑', Arial, sans-serif;line-height: 26px;font-size: 16px;background-color: #f0f2f5;padding: 10px;border-radius: 5px;color: #666;margin: 10px 10px;height:300px;width:900px;padding: 10px 10px;">
                <span style="margin:0px;">{{analysis2}}</span>
                </div>
            </div>
            
               <!-- <el-row>
                     你的本次考试成绩为：{{score}}分
               </el-row> -->
               <!-- <el-row class="card1"> -->
                 <!-- <el-col :span="12">
                     
                     <el-table :data="tableData" border style="width: 100%;">
                         <el-table-column prop="questionType" label="题型" width="150" />
                         <el-table-column prop="questionCount" label="题目数量"/>
                         <el-table-column prop="totalScore" label="题型总分" />
                         <el-table-column prop="userScore" label="获得分数" />
                         <el-table-column prop="scoreRate" label="得分率" />
                     </el-table>
                 </el-col> -->
                 <!-- <el-col :span="12">
                     智能评估<br/>
                     <div v-loading="loading1" 
                     style="font-size: 14px;line-height: 24px;padding: 20px;text-align:left;height: 84%">
                     {{ analysis1 }}</div>
                 </el-col> -->
               <!-- </el-row> -->
        <!-- <el-divider border-style="double" /> -->
        
    </div>
    
  </template>
  
<script>
import request from '@/utils/request'
import { DataAnalysis } from '@element-plus/icons-vue'

  import { mapState } from 'vuex'
  export default {
    name: 'questionType',
    components: {
      DataAnalysis
     
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
        loading: false,
        // analysis1: '在本次考试中，你在不同题型上的表现存在显著差异。优点方面，你在判断题和简答题上表现出色，分别取得了80%和83.33%的得分率，显示出你在理解和应用知识方面有较强的能力。简答题的高得分率也表明你能较好地表达和阐述自己的观点。 然而，单选题和多选题的得分率较低，分别只有18.75%和20%，这表明你在选择题上的答题策略和知识掌握可能存在问题。填空题和应用题的得分率也只有50%，显示出在具体知识点的掌握和综合应用方面还有提升空间。 为了在未来取得更好的成绩，你需要加强以下方面：首先，多练习选择题，提升对题型的熟悉度和解题技巧，尤其是对易混淆知识点的辨别能力。其次，多做填空题和应用题的训练，巩固基础知识并增强其灵活应用的能力。此外，复习时注重知识的全面覆盖和系统性，确保对各类型题目都有较好的准备。通过这些努力，你将在各个题型上取得更加均衡和优秀的成绩。',
        analysis2: '',
        selectedIndex: 0,  // 初始选中第一个按钮
        subjectDetail: {
            subjectName: '未选择科目',
            // examCount: '*',
            // maxScore: '*',
            // minScore: '*',
            // avgScore: '*',
            // level: '*'
        },
    }
    },
    created() {
    //   this.getSubjects()
      if (this.analysisSubject != null) {
        this.value = this.analysisSubject
        this.getExams()
        this.analysis()
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
      // analysisScoreId: function (newVal, oldVal) {
      //   this.selectExam(newVal,null,null)
      // },
      analysisSubject: function (newVal, oldVal) {
        if(this.analysisSubject != null){
            this.analysis()
        }
      }
    },
    methods: {
        analysis() {
            if (this.analysisSubject == null) {
                alert('请选择科目')
                return;
            }
            let studentId = localStorage.getItem('studentId')
            request.get(`/api/analysis/getSubjectDetail/${studentId}/${this.analysisSubject}`).then((res) => {
            console.log(res.data)
            this.subjectDetail = res.data
            this.getExams()
            })
        },
  
      getExams() {
        let studentId = localStorage.getItem('studentId')
        request.get(`/api/exam/queryScoreByStudentIdAndSubjectId/${studentId}/${this.analysisSubject}`).then((res) => {
          
          if(res.code === 200){
            console.log("考试列表",res.data)
            this.options2 = res.data
            this.selectExam(res.data[0].value,null,null,0)
          }else{
            alert(res.msg)
          }
        })
      },
      selectExam(scoreId,scoreName,score,index) {
        this.selectedIndex = index
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
        this.loading = true
        request.post('/api/login/examAnalysis1',this.tableData).then((res) => {
          if(res.code === 200){
            console.log(res.data)
            this.analysis1 = res.data.message
            this.loading = false
          }else{
            alert("请求失败，请稍后再试")
            // this.getAnalysis1()
            this.loading = false
          }
        })
      },
      getAnalysis2() {
        this.loading = true
        request.post('/api/login/examAnalysis2',this.tableData2).then((res) => {
          if(res.code === 200){
            console.log(res.data)
            this.analysis2 = res.data.message
            this.loading = false
          }else{
            alert("请求失败，请稍后再试")
            // this.getAnalysis2()
            this.loading = false
          }
        })
      }
      
    }
  }
</script>
  
  <style scoped>
  .selected {
  background-color: #6aa4f6;
  color: white;
}
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