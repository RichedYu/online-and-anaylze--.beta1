<template>
    <div class="exam-list-container">
      <!-- 搜索和筛选 -->
      <div class="filter-container">
        <el-input
          v-model="key"
          placeholder="请输入试卷名称"
          class="search-input"
          @keyup.enter="search"
        >
          <template #append>
            <el-button :icon="Search" @click="search">搜索</el-button>
          </template>
        </el-input>
  
        <el-radio-group v-model="examStatus" @change="filterRecords">
          <el-radio-button label="全部"></el-radio-button>
          <el-radio-button label="未开始"></el-radio-button>
          <el-radio-button label="进行中"></el-radio-button>
          <el-radio-button label="已结束"></el-radio-button>
        </el-radio-group>
      </div>
  
      <!-- 考试列表 -->
      <el-table :data="pagination.records" v-loading="loading" stripe style="height: 100%;">
        <el-table-column prop="name" label="试卷名称" width="200"></el-table-column>
        <el-table-column prop="subjectName" label="所属课程" width="150"></el-table-column>
        <el-table-column prop="examTime" label="考试时间" width="180">
          <template #default="{ row }">
            {{ formatDate(row.examTime) }}
          </template>
        </el-table-column>
        <el-table-column prop="examDuration" label="考试时长（分钟）" width="120"></el-table-column>
        <el-table-column prop="totalScore" label="总分" width="100"></el-table-column>
        <el-table-column label="状态" width="120">
          <template #default="{ row }">
            <el-tag :type="getStatusTagType(row)">
              {{ getExamStatus(row) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="primary" size="small" @click="editExam(row)">编辑</el-button>
            <el-button type="danger" size="small" @click="deleteExam(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <!-- 分页 -->
      <el-pagination
        class="pagination"
        :current-page="pagination.current"
        :page-size="pagination.size"
        :total="pagination.total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes, prev, pager, next, jumper"
      ></el-pagination>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import { ElMessage, ElMessageBox } from 'element-plus';
  import { Search } from '@element-plus/icons-vue';
  import request from '@/utils/request';
  
  export default {
    components: {
      Search,
    },
    data() {
      return {
        loading: false,
        key: null, // 搜索关键字
        allExam: null, // 所有考试信息
        pagination: {
          current: 1, // 当前页
          total: null, // 记录条数
          size: 6, // 每页条数
          records: [], // 当前页数据
        },
        examStatus: '全部', // 当前筛选状态
        filteredRecords: {
          all: [],
          notStarted: [],
          inProgress: [],
          ended: [],
        },
      };
    },
    created() {
      this.getExamInfo();
    },
    methods: {
      // 获取考试信息
      getExamInfo() {
        this.loading = true;
        const studentId = localStorage.getItem('studentId');
        const status = this.examStatus === '全部' ? null : this.examStatus;
        request
          .get(
            `/api/exam/papers/${studentId}/${this.pagination.current}/${this.pagination.size}/${this.key}/${status}`
          )
          .then((res) => {
            this.pagination = res.data;
            this.categorizeRecords();
            this.loading = false;
          })
          .catch((error) => {
            console.log(error);
            this.loading = false;
          });
      },
  
      // 分类考试记录
      categorizeRecords() {
        const now = new Date();
        this.filteredRecords = {
          all: [...this.pagination.records],
          notStarted: [],
          inProgress: [],
          ended: [],
        };
  
        this.pagination.records.forEach((item) => {
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
  
      // 过滤考试记录
      filterRecords(status) {
        if (status === '全部') {
          this.pagination.records = this.filteredRecords.all;
        } else if (status === '未开始') {
          this.pagination.records = this.filteredRecords.notStarted;
        } else if (status === '进行中') {
          this.pagination.records = this.filteredRecords.inProgress;
        } else if (status === '已结束') {
          this.pagination.records = this.filteredRecords.ended;
        }
        this.pagination.current = 1;
      },
  
      // 格式化日期
      formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleString();
      },
  
      // 获取考试状态
      getExamStatus(row) {
        const now = new Date();
        const startTime = new Date(row.examTime);
        const endTime = new Date(startTime);
        endTime.setMinutes(startTime.getMinutes() + parseInt(row.examDuration));
  
        if (now < startTime) {
          return '未开始';
        } else if (now >= startTime && now <= endTime) {
          return '进行中';
        } else {
          return '已结束';
        }
      },
  
      // 获取状态标签类型
      getStatusTagType(row) {
        const status = this.getExamStatus(row);
        switch (status) {
          case '未开始':
            return 'warning';
          case '进行中':
            return 'success';
          case '已结束':
            return 'info';
          default:
            return '';
        }
      },
  
      // 编辑考试
      editExam(row) {
        ElMessageBox.confirm('确定要编辑该考试吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        })
          .then(() => {
            // 这里可以跳转到编辑页面
            ElMessage.success('编辑成功！');
          })
          .catch(() => {});
      },
  
      // 删除考试
      deleteExam(row) {
        ElMessageBox.confirm('确定要删除该考试吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        })
          .then(() => {
            // 这里可以调用 API 删除考试
            ElMessage.success('删除成功！');
            this.getExamInfo(); // 刷新列表
          })
          .catch(() => {});
      },
  
      // 改变每页条数
      handleSizeChange(val) {
        this.pagination.size = val;
        this.getExamInfo();
      },
  
      // 改变当前页码
      handleCurrentChange(val) {
        this.pagination.current = val;
        this.getExamInfo();
      },
  
      // 搜索考试
      search() {
        if(this.key == ''){
            this.key = null
        }
        this.getExamInfo();
      },
    },
  };
  </script>
  
  <style scoped>
  .exam-list-container {
    padding: 20px;
    background: #f5f7fa;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }
  
  .filter-container {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
  }
  
  .search-input {
    width: 300px;
    margin-right: 20px;
  }
  
  .pagination {
    margin-top: 20px;
    text-align: right;
  }
  </style>