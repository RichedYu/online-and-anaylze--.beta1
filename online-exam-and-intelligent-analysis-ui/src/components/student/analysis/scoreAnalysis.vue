<template>
    <el-container>
     
      <el-main>
        <!-- <h1>成绩分析</h1> -->
        <el-card class="mb-20">
           
          <el-row class="card1">
            <el-col :span="12">
             <el-row class="card1-part">
              <el-icon style="margin-top: 3px;margin-right: 10px;"><DataAnalysis /></el-icon>
              <span style="font-weight: bold;">{{subjectDetail.subjectName}}</span>
             </el-row>
              <el-row class="card1-part">
                <el-col span="6" class="card1-part-title">考试次数<br/>{{subjectDetail.examCount}}</el-col>
                <el-col span="6" class="card1-part-title">历史最高分<br/>{{ subjectDetail.maxScore }}</el-col>
                <el-col span="6" class="card1-part-title">历史最低分<br/>{{ subjectDetail.minScore }}</el-col>
                <el-col span="6" class="card1-part-title">平均分<br/>{{ subjectDetail.avgScore }}</el-col>
                <el-col span="6" class="card1-part-title">评分等级<br/>{{ subjectDetail.level }}</el-col>
              </el-row>
            </el-col>
            <el-col :span="12">
              <Schart2/>
            </el-col>
          </el-row>
          <el-row class="card1">
            <el-col :span="12">
              <Schart4/>
            </el-col>
            <el-col :span="12">
              <Schart1/>
            </el-col>
          </el-row>
        </el-card>
        <!-- <el-card class="mb-20">
          <el-row class="card2">
            <Schart3/>
          </el-row>
        </el-card> -->
          
      </el-main>
    </el-container>
  </template>
  
<script>
import request from '@/utils/request'
import { DataAnalysis } from '@element-plus/icons-vue'
  import Schart1 from '@/components/charts/Schart1.vue'
  import Schart2 from '@/components/charts/Schart2.vue'
  import Schart3 from '@/components/charts/Schart3.vue'
  import Schart4 from '@/components/charts/Schart4.vue'
  import { mapState } from 'vuex'
  export default {
    name: 'analysis',
    components: {
      Schart1,
      Schart2,
      Schart3,
      Schart4,
      DataAnalysis
    },
    data() {
      return {
        value: '',
        subjectDetail: {
          subjectName: '未选择科目',
          examCount: '*',
          maxScore: '*',
          minScore: '*',
          avgScore: '*',
          level: '*'
        },
        options: []
      }
    },
    created() {
      this.getSubjects()
      if (this.analysisSubject != null) {
        this.value = this.analysisSubject
        this.analysis() 
      }
    },
    computed: {
      ...mapState(['analysisSubject'])
    },
    watch: {
      analysisSubject: function (newVal, oldVal) {
        this.analysis()
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
      selectSubject(subjectId) {
        this.$store.commit('setAnalysisSubject', subjectId)       
      },
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
    height: 850px;
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
    width: 1175px;
    height: 50%;
    left: 18px;
    position: relative;
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