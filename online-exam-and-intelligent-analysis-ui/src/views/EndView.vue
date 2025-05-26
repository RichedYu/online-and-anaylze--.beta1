<template>
  <div class="exam-result-page">
    <el-container>
      <el-header>
        <h2>考试结束</h2>
      </el-header>
      <el-main>
        <el-card>
          <h3>考试信息</h3>
          <p>科目: {{ scoreResult.subject }}</p>
          <p>姓名: {{ scoreResult.studentName }}</p>
          <p>学号: {{ scoreResult.studentId }}</p>
          <p>分数: {{ scoreResult.score }}</p>
          <p>答题日期: {{formatDate(scoreResult.answerDate)}}</p>
          <p>用时: {{scoreResult.time}}分钟</p>
        </el-card>
        <el-button type="primary" @click="goBack">返回首页</el-button>
      </el-main>
    </el-container>
  </div>
  </template>
  
  <script>
  
  export default {
    name: 'ExamResultPage',
    data() {
      return {
        scoreResult: {
          studentName: '张三',
          studentId: '20210001',
          subject: '数学',
          score: 85,
          answerDate: '2023-10-01T10:00:00Z',
          time: 3600, // 用时，单位为分钟
        },
      };
    },
    created() {
      let userInfo = localStorage.getItem("userInfo");
      this.scoreResult.studentName = JSON.parse(userInfo).name;
      this.scoreResult.studentId = localStorage.getItem("studentId");
      this.scoreResult.subject = this.$route.query.subject;
      this.scoreResult.score = this.$route.query.score;
      this.scoreResult.answerDate = this.$route.query.answerDate;
      this.scoreResult.time = this.$route.query.time;
      
      console.log("scoreResult: ", this.scoreResult);
    },
    methods: {
      formatDate(dateStr) {
        const date = new Date(dateStr);
        return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()} ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`;
      },
      // 返回首页
      goBack() {
        this.$router.push('/student');
      },
    },
  };
  </script>
  
  <style scoped>
  /* 你可以在这里添加你的样式 */
  .exam-result-page {
    text-align: center;
    background-color: rgb(235.9, 245.3, 255);
  }

  .el-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
  }
  .el-main {
    width: 600px;
    
  }

  .el-header {
    margin-bottom: 20px;
  }

  .el-card {
    margin-bottom: 20px;
  }

  .el-button {
    margin-top: 20px;
  }
  </style>