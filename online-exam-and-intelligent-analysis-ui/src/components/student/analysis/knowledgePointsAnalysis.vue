<template>
  <el-container>
   
    <el-main>
      <h1>知识点掌握情况分析</h1>
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
        <!-- <el-select 
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
        </el-select> -->

        </el-row>
        <el-row class="card2">
          
          <el-table :data="tableData" border style="width: 90%;height: 500px;margin:0 auto;">
                    <el-table-column prop="id" label="知识点编号" width="100" />
                    <el-table-column prop="name" label="知识点名称" width="180" />
                    <el-table-column prop="chapterName" label="所属章节" />
                    <el-table-column prop="questionCount" label="累计答题数量" sortable width="135" />
                    <el-table-column prop="wrongCount" label="错题数量" />
                    <el-table-column prop="scoreRate" label="得分率" />
                    <el-table-column prop="wrongRate" label="错题率" />
                    <el-table-column prop="masteryLevel" label="掌握程度" />
                </el-table>
        </el-row>
        <el-row class="card1">
          <el-col :span="24" >
            <span style="font-weight: bold;text-align: center;width: 100%;">智能分析</span>
            <div v-loading="loading" 
                style="font-size: 14px;margin-bottom: 100px; line-height: 24px;padding: 20px;text-align:left;height: 80%;margin:10px 200px;border: 1px solid #e4e4e4;border-radius: 4px;background-color: #fff;">
                {{ analysisData }}</div>
                
          </el-col>
        </el-row>
        <!-- <el-row class="card1"> -->
          
          <!-- <el-col :span="12"> -->
            <!-- <span style="font-weight: bold;">各知识点所属章节占比（饼图）</span> -->
              <!-- <Schart5/> -->
          <!-- </el-col> -->
          <!-- <el-col :span="12"> -->
            <!-- <span style="font-weight: bold;">知识点所属章节得分率（雷达图）</span> -->
            <!-- <Schart6/> -->
          <!-- </el-col> -->
        <!-- </el-row> -->
        
        <!-- <el-row class="card1">
          <el-col :span="12">
            <span style="font-weight: bold;">知识点所属章节正确率趋势（折线图）</span>
            
          </el-col>
          <el-col :span="12">
             
          </el-col>
        </el-row> -->
        
      </el-card>
        
    </el-main>
  </el-container>
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
      analysisData: ''
      
  }
  },
  created() {
    this.getSubjects()
    if (this.analysisSubject != null) {
      this.value = this.analysisSubject
      this.getKnowledgePoints()
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
            alert("请求失败")
            this.getAnalysisData()
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