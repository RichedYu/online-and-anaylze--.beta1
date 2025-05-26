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
                本门科目考试中所涉及的各章节知识点如下表所示：
            </span>
            <el-divider border-style="double" />
        </el-row>
        
        <div style="margin-bottom: 80px;">
          <div style="text-align: left;font-weight: bold;margin-bottom: 10px;">本科目考试知识点统计分析表</div>
        <el-table :data="tableData" stripe style="width: 90%;height: 500px;margin:0 auto;">
            <el-table-column prop="id" label="知识点编号" width="100" />
            <el-table-column prop="name" label="知识点名称" width="180" />
            <el-table-column prop="chapterName" label="所属章节" />
            <el-table-column prop="questionCount" label="累计答题数量" sortable width="135" />
            <el-table-column prop="wrongCount" label="错题数量" />
            <el-table-column prop="scoreRate" label="得分率" />
            <el-table-column prop="wrongRate" label="错题率" />
            <el-table-column prop="masteryLevel" label="掌握程度" />
        </el-table>
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
      // this.getSubjects()
      if (this.analysisSubject != null) {
        // this.value = this.analysisSubject
        this.analysis()
        
        
        // this.getExams()
      }
      // if (this.analysisScoreId != null) {
      //   this.value2 = this.analysisScoreId
      // }
    },
    computed: {
      ...mapState(['analysisSubject'])
    },
    watch: {
      analysisSubject: function (newVal, oldVal) {
        this.analysis()
        this.getKnowledgePoints
        
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
            this.getKnowledgePoints()
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
          // 设置连接超时时间为50秒
          request.defaults.timeout = 50000;
          request.post('/api/login/examAnalysis3',this.tableData).then((res) => {
            if(res.code === 200){
              console.log(res.data)
              this.analysisData = res.data.message
              this.loading = false
            }else{
              alert("请求失败")
              this.getAnalysis1()
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