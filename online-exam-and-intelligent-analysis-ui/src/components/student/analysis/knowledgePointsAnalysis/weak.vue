<template>
   
    <!-- <h1>知识点掌握情况分析</h1> -->
    <div>
        <el-row class="card1-part">
            <el-icon style="margin-top: 3px;margin-right: 10px;"><DataAnalysis /></el-icon>
            <span style="font-weight: bold;">{{subjectDetail.subjectName}}</span>
        </el-row>
        <el-divider border-style="double" />
        <el-row >
            <span style="padding-left: 20px;font-size: 16px;color: #666;">
                通过您的考试数据，您可以点击下方按钮生成ai智能分析报告，帮助您更好地掌握知识点。
            </span>
            <el-divider border-style="double" />
        </el-row>
        
        <div style="margin-bottom: 80px;">
            <div style="text-align: center;text-align: left;">
                <el-button type="primary" style="margin-left: 3px;" @Click='getAnalysisData'>点击生成ai分析报告</el-button>
                <div v-loading="loading" style="font-family: Inter, 'Helvetica Neue', Helvetica, 'PingFang SC',
  'Hiragino Sans GB', 'Microsoft YaHei', '微软雅黑', Arial, sans-serif;line-height: 26px;font-size: 16px;background-color: #f0f2f5;padding: 10px;border-radius: 5px;color: #666;margin: 10px 10px;height:300px;width:900px;padding: 10px 10px;">
                <span style="margin:0px;">{{analysisData}}</span>
                </div>
            </div>
        </div>
    </div>

  </template>
  
  <script>
  import request from '@/utils/request'
  import { DataAnalysis } from '@element-plus/icons-vue'
  import { mapState } from 'vuex'
  import Schart5 from '@/components/charts/Schart5.vue'
  import Schart6 from '@/components/charts/Schart6.vue'
  export default {
    name: 'analysis',
    components: {
      DataAnalysis,
      Schart5,
      Schart6
    },
    data() {
      return {
        options: [],
        value: '',
        options2: [],
        value2: '',
        tableData: [],
        loading: false,
        analysisData: '',
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
      this.getSubjects()
      if (this.analysisSubject != null) {
        this.value = this.analysisSubject
        this.getKnowledgePoints()
        this.analysis()
        // this.getExams()
      }
      // if (this.analysisScoreId != null) {
      //   this.value2 = this.analysisScoreId
      // }
    },
    computed: {
      ...mapState(['analysisSubject','analysisScoreId'])
    },
    watch: {
      analysisSubject: function (newVal, oldVal) {
        this.getKnowledgePoints
        this.analysis()
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
            
            })
        },
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
        this.getKnowledgePoints()
        // this.getExams()
      },
      getKnowledgePoints() {
       
        let studentId = localStorage.getItem('studentId')
        request.get(`/api/analysis/getSubjectKnowledgePoint/${this.analysisSubject}/${studentId}`).then((res) => {
          if(res.code === 200){
            console.log("知识点列表",res.data)
            this.tableData = res.data
            // this.getAnalysisData()
          }else{
            alert(res.msg)
          }
        })
      },
        getAnalysisData() {
          this.loading = true
          request.post('/api/login/examAnalysis3',this.tableData).then((res) => {
            if(res.code === 200){
              console.log(res.data)
              this.analysisData = res.data.message
              this.loading = false
            }else{
              alert("请求失败，请稍后再试")
              // this.getAnalysisData()
              this.loading = false
            }
          })
        },
      // getExams() {
      //   let studentId = localStorage.getItem('studentId')
      //   request.get(`/api/exam/queryScoreByStudentIdAndSubjectId/${studentId}/${this.analysisSubject}`).then((res) => {
          
      //     if(res.code === 200){
      //       console.log("考试列表",res.data)
      //       this.options2 = res.data
      //     }else{
      //       alert(res.msg)
      //     }
      //   })
      // },
      // selectExam(scoreId,scoreName,score) {
      //   this.score = score;
      //   this.$store.commit('setAnalysisScoreId', scoreId)  
      //   this.value2 = scoreName;
        
      // },
      
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
    height: 550px;
    left: 10px;
    position: relative;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease, border-color 0.3s ease; /* 添加过渡效果，使变化更平滑 */
    /* background-color: #fff; */
  
  }
  .card2 {
    width: 1175px;
    height: 550px;
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