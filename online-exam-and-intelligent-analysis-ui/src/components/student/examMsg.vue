// 点击试卷后的缩略信息
<template>
  <div id="msg">
    <div class="title">
      <span>试卷列表</span>
      <span>/  {{examData.subject}}</span>
    </div>
    <div class="wrapper">
      <ul class="top">
        <li class="example">{{examData.subject}}</li>
        <li><i class="iconfont icon-pen-"></i></li>
        <li><i class="iconfont icon-share"></i></li>
        <li class="right">
          <div>
            <span class="count">总分</span>
            <span class="score">{{examData.totalScore}}</span>
          </div>
        </li>
      </ul>
      <ul class="bottom">
        <li>更新于{{examData.updateTime}}</li>
        <li>来自 {{examData.institute}}</li>
        <li class="btn">{{examData.description}}</li>
        <li class="start">{{ examData.examTime }}</li>
        <li class="right" id="startBtn"><el-button @click="toAnswer(examData.paperId)">开始答题</el-button></li>
        
      </ul>
      <ul class="info">
        <li @click="dialogVisible = true"><a href="javascript:;" style="position: relative;left: -430px;"><i class="iconfont icon-info"></i>考生须知</a></li>
      </ul>
    </div>
    <!--考生须知对话框-->
    <el-dialog
      title="考生须知"
      v-model="dialogVisible"
      width="30%">
      <div class="tips">{{examData.tips}}</div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">知道了</el-button>
      </span>
    </el-dialog>
    <div class="content">
      <el-divider border-style="dotted" />
      <div class="title">试题详情</div>
      <div class="time">{{examData.totalScore}}分 / {{examData.totalTime}}分钟</div>
      <el-divider border-style="double" />
      <div>
        <div v-if="single" class="titlei">单选题 (共{{single.questionNumByType}}题 共计{{single.totalScoreByType}}分)</div>
        <el-divider border-style="dashed" />
        <div v-if="more" class="titlei">多选题 (共{{more.questionNumByType}}题 共计{{more.totalScoreByType}}分)</div>
        <el-divider border-style="dashed" />
        <div v-if="judge" class="titlei">判断题 (共{{judge.questionNumByType}}题 共计{{judge.totalScoreByType}}分)</div>
        <el-divider border-style="dashed" />
        <div v-if="fill" class="titlei">填空题 (共{{fill.questionNumByType}}题 共计{{fill.totalScoreByType}}分)</div>
        <el-divider border-style="dashed" />
        <div v-if="short" class="titlei">简答题 (共{{short.questionNumByType}}题 共计{{short.totalScoreByType}}分)</div>
        <el-divider border-style="dashed" />
        <div v-if="problem" class="titlei">应用题 (共{{problem.questionNumByType}}题 共计{{problem.totalScoreByType}}分)</div>
        <el-divider border-style="double" />
      </div>
    </div>
    
  </div>
</template>

<script>
import request from '@/utils/request'
export default {
  data() {
    return {
      isJoinExam: false, //是否已经参加考试
      lastClickedTime: 0, // 上次点击时间
      dialogVisible: false, //对话框属性
      activeName: '0', //默认展开项
      examData: { //考试信息
        
      },
      single:{},
      more: {},
      judge: {},
      fill: {},
      short: {},
      problem: {}
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    //初始化页面数据
    init() {
      let examCode = this.$route.query.examCode //获取路由传递过来的试卷编号
      console.log(examCode)
      if(examCode) {
        request.get(`/api/exam/paperDetail/${examCode}`).then(res => {  //通过examCode请求试卷详细信息
        console.log(res.data)
        this.examData = { ...res.data}
        request.get(`/api/exam/testQuestionByType/${examCode}`).then(res => {  //通过paperId获取试题题目信息
          console.log(res.data)
          this.single = res.data.single
          this.more = res.data.more
          this.judge = res.data.judge
          this.fill = res.data.fill
          this.short = res.data.short
          this.problem = res.data.problem
        })
      })
      }else {
        this.$message.error("获取试卷编号失败")

      }
      
     
    },
    toAnswer(id) {
      const time = Date.now(); // 获取当前时间戳
 
      // 检查上一次点击的时间是否在一秒之前
      if (time - this.lastClickedTime <= 3000) {
        return;
      }
      this.lastClickedTime = time; // 更新上一次点击时间
      // 获取考试开始时间和结束时间
      let startTime = new Date(this.examData.examTime);
      let totalTimeMilliseconds = this.examData.totalTime * 60 * 1000; // 转换为毫秒
      let endTime = new Date(startTime.getTime() + totalTimeMilliseconds);
      
      // 获取当前时间
      let currentTime = new Date();
      
      // 检查当前时间是否在开始时间和结束时间之间
      if (currentTime >= startTime && currentTime <= endTime) {
        // 如果可以开始考试（且未超过结束时间），则跳转到答题页面，并传递考试代码
        this.queryIsJoinExam(id,endTime);// 判断学生是否已经参加过该考试
        
      } else if (currentTime < startTime) {
        // 如果不能开始考试（时间还早），则显示一条消息
        alert("考试尚未开始，请等待到 " + startTime.toLocaleString() + " 后再试。");
      } else {
        // 如果不能开始考试（已经超过结束时间），则显示另一条消息
        alert("考试已经结束，您无法再参加考试。");
      }
    },
    // 查询学生是否参加过该考试
    queryIsJoinExam(id,endTime) {
      // 查询数据库，判断该学生是否已经参加过该考试
      let studentId = localStorage.getItem("studentId");
      let paperId = this.$route.query.examCode;
      request.get(`/api/exam/queryScore/${studentId}/${paperId}`).then(res => {
        console.log("查询学生是否已经参加过该考试:",res)
        if (res.code === 200) {
          console.log("学生未参加过该考试")
          this.$router.push({ path: "/exam", query: { examCode: id,endTime:endTime.getTime(),subject:this.examData.subject} });
        }else if(res.code === 208){
          this.$message.error("您已经参加过该考试，无法再次参加")
          this.isJoinExam = true;
        }else {
          this.$message.error("查询学生是否已经参加过该考试失败")
          this.isJoinExam = true;
        }
        
      }).catch(err => {
        console.log(err)
      })

    }
  }
}
</script>

<style lang="less" scoped>
.tips {
  margin-bottom: 20px;
  text-align: center;
}
.top {
  padding-left: 10px;
}
.bottom {
  padding-left: 10px;
  .right{
    .el-button{
      color: #409EFF;
      border-color: #c6e2ff;
      background-color: #ecf5ff;
    }
  }
}
.right {
  margin-left: auto;
}

.content {
  margin-top: 20px;
  background-color: #fff;
  font-size: 16px;
  color: #88949b;
  font-weight: bold;
  text-align: left;
  padding-left: 20px;
}

.content .stitle {
  background-color: #0195ff;
}

#msg .content .title {
  font-size: 20px;
  margin: 0px;
  display: flex;
  align-items: center;
}

.content .header {
  padding: 10px 30px;
}
.wrapper .info {
  margin: 20px 0px 0px 20px;
  border-top: 1px solid #eee;
  padding: 20px 0px 10px 0px;
}
.wrapper .info a {
  color: #88949b;
  font-size: 14px;
}
.wrapper .info a:hover {
  color: #0195ff;
}
.wrapper .bottom .btn {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #88949b;
  border-radius: 4px;
} 
.wrapper .bottom {
  display: flex;
  margin-left: 20px;
  color: #999;
  font-size: 14px;
  align-items: center;
}
.wrapper .bottom li {
  margin-right: 14px;
}
#msg {
  background-color: #F9F9F9;
  width: 980px;
  margin: 0 auto;
}

.title {
  width: 200px;
}
#msg .title {
  margin: 20px;
}
#msg .wrapper {
  background-color: #fff;
  padding: 10px;
}
.wrapper .top {
  display: flex;
  margin: 20px;
  align-items: center;
}
.wrapper .top .right {
  margin-left: auto;
}
.wrapper .top .example {
  color: #333;
  font-size: 22px;
  font-weight: 700;
}
.wrapper .top li i {
  margin-left: 20px;
  color: #88949b;
}
.wrapper .right .count {
  margin-right: 60px;
  color: #fff;
  padding: 4px 10px;
  background-color: #88949b;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  border: 1px solid #88949b;
}
.wrapper .right .score {
  position: absolute;
  left: 53px;
  top: -2px;
  padding: 1px 12px;
  font-size: 20px;
  color: #88949b;
  border: 1px dashed #88949b;
  border-left: none;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  font-weight: bold;
}
.wrapper .right div {
  position: relative;
}
.time {
  float: right;
  margin-right: 30px;
  top: -25px;
  position: relative;
}

.start {
  left:200px;
  position: relative;
}

#startBtn {
  left: -10px;
  position: relative;
}

</style>
