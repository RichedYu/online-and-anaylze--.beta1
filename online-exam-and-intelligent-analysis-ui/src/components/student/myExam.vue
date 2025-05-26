// 我的试卷页面
<template>
  <div id="myExam">
    <!-- <div class="title">我的试卷</div> -->
    <div class="wrapper">
      <ul class="top">
        <li class="order" @click="filterRecords('all')">
          <el-badge :value="sortByTime.all" class="item" type="primary" @click="get('all')">
            <span>全部</span>
          </el-badge></li>
      <li class="order" @click="filterRecords('notStarted')">
        <el-badge :value="sortByTime.notStarted" class="item" type="primary" @click="get('未开始')">
            <span>未开始</span>
          </el-badge>
      </li>
      <li class="order" @click="filterRecords('inProgress')">
        <el-badge :value="sortByTime.inProgress" class="item" type="primary" @click="get('进行中')">
            <span>进行中</span>
          </el-badge>
      </li>
      <li class="order" @click="filterRecords('ended')">
        <el-badge :value="sortByTime.ended" class="item" type="primary" @click="get('已结束')">
            <span>已结束</span>
          </el-badge>
      </li>
        <li class="search-li"><div class="icon"><input v-on:keyup.enter="search" type="text" placeholder="试卷名称" class="search" v-model="key"><i class="el-icon-search"></i></div></li>
        <li><el-button type="primary" @click="search" >搜索试卷</el-button></li>
      </ul>
      <ul class="paper" v-loading="loading">
        <li class="item" v-for="(item,index) in pagination.records" :key="index">
          <h4 @click="toExamMsg(item.id)">{{item.subjectName}}</h4>
          <p class="name">{{item.subjectName}}-{{item.name}}</p>
          <div class="info">
            <i class="el-icon-loading"></i><span>考试时间 : {{item.examTime.substr(0,16).replace('T',' ')}}</span>
            <i class="iconfont icon-icon-time">
            </i><span v-if="item.examDuration != null">限时 : {{item.examDuration}} 分钟</span><br>
            <i class="iconfont icon-fenshu"></i><span><el-icon><CircleCheck /></el-icon>满分{{item.totalScore}}分</span>
          </div>
        </li>
      </ul>
      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[6, 10, 20, 40]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>
  
<script>
  import request from '@/utils/request'
  import { CircleCheck } from '@element-plus/icons-vue'
export default {
  // name: 'myExam'
  components: {
   request,
   CircleCheck
  },
  data() {
    return {
      loading: false,
      key: null, //搜索关键字
      allExam: null, //所有考试信息
      pagination: { //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 6 //每页条数
      },
      currentTime: new Date(),
      filteredRecords: {
        all: [],
        notStarted: [],
        inProgress: [],
        ended: []
      },
      sortByTime:{},
      examStatus: ''
    }
  },
  created() {
    this.getExamInfo()
    this.sortExamByTime()
    this.loading = true
  },
  // watch: {
    
  // },
  methods: {
    //获取当前所有考试信息
    getExamInfo() {
      if(this.examStatus == ''){
        this.examStatus = null
      }
      let studentId = localStorage.getItem('studentId')
      request.get(`/api/exam/papers/${studentId}/${this.pagination.current}/${this.pagination.size}/${this.key}/${this.examStatus}`).then(res => {
        this.pagination = res.data
        this.loading = false
        console.log("获取的考试信息",this.pagination)
        this.categorizeRecords()
      }).catch(error => {
        console.log(error)
      })
    },
    //获取不同状态的考试数量
    sortExamByTime() {
      let studentId = localStorage.getItem('studentId')
      request.get(`/api/exam/sortByTime/${studentId}`).then(res => {
        console.log("获取的不同状态的考试数量",res.data)
        this.sortByTime = res.data
      }).catch(error =>
        console.log(error)
      )
    },
    categorizeRecords() {
      const now = new Date();
      this.filteredRecords = {
        all: [...this.pagination.records],
        notStarted: [],
        inProgress: [],
        ended: []
      };
 
      this.pagination.records.forEach(item => {
        const startTime = new Date(item.examTime);
        const endTime = new Date(startTime);
        endTime.setMinutes(startTime.getMinutes() + parseInt(item.examDuration));
 
        if (now < startTime) {
          this.filteredRecords.notStarted.push(item);
        } else if (now >= startTime && now <= endTime) {
          this.filteredRecords.inProgress.push(item);
        } else {
          this.filteredRecords.ended.push(item);
        }
      });
    },
    filterRecords(currenRecords) {
      if (currenRecords === 'all') {
        this.pagination.records = this.filteredRecords.all;
      } else if (currenRecords === 'notStarted') {
        this.pagination.records = this.filteredRecords.notStarted;
      } else if (currenRecords === 'inProgress') {
        this.pagination.records = this.filteredRecords.inProgress;
      } else if (currenRecords === 'ended') {
        this.pagination.records = this.filteredRecords.ended;
      }
      
      this.pagination.current = 1;
      
    },
   
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getExamInfo()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getExamInfo()
    },
    //搜索试卷
    search() {
      if(this.key == ''){
        this.key = null
      }
      this.getExamInfo()
    },
    get(status){
      this.key = null
      this.examStatus = status
      this.getExamInfo()
    },
    //跳转到试卷详情页
    toExamMsg(id) {
      this.$router.push({path: '/student/examMsg', query: {examCode: id}})
      console.log(id)
    }
  }
}
</script>

<style lang="less" scoped>

.el-icon{
  position: relative;
  top: 2px;
}
.pagination {
  padding: 20px 0px 30px 0px;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}
.paper {
  padding-left: 5px;
  h4 {
    cursor: pointer;
  }
}
.paper .item a {
  color: #000;
}
.wrapper .top .order {
  cursor: pointer;
}
.wrapper .top .order:hover {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.wrapper .top .order:visited {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.item .info i {
  margin-right: 5px;
  color: #0195ff;
}
.item .info span {
  margin-right: 14px;
}
.paper .item {
  width: 350px;
  border-radius: 4px;
  padding: 20px 30px;
  border: 1px solid #eee;
  box-shadow: 0 0 4px 2px rgba(217,222,234,0.3);
  transition: all 0.6s ease;
}
.paper .item:hover {
  box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
  transform: scale(1.03);
}
.paper .item .info {
  font-size: 14px;
  color: #88949b;
}
.paper .item .name {
  font-size: 14px;
  color: #88949b;
}
.paper * {
  margin: 20px 0;
}
.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.top .el-icon-search {
  position: absolute;
  right: 10px;
  top: 10px;
}
.top .icon {
  position: relative;
}
.wrapper .top {
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
}
#myExam .search-li {
  margin-left: auto;
}
.top .search-li {
  margin-left: auto;
}
.top li {
  display: flex;
  align-items: center;
}
.top .search {
  margin-left: auto;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #eee;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
  transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.top .search:hover {
  color: #0195ff;
  border-color: #0195ff;
}
.search:focus-visible {
  outline: none;
}
.wrapper .top {
  display: flex;
}
.wrapper .top li {
  margin: 20px;
}
#myExam {
  width: 980px;
  margin: 0 auto;
}
#myExam .title {
  margin: 20px;
}
#myExam .wrapper {
  background-color: #fff;
}
</style>
