<template>
    <div class="weakness-analysis-page">
      <!-- 顶部：选择课程 -->
      <el-card class="filter-card">
        <el-row>
          <span style="padding: 10px 5px; width: 80px;">考试科目:</span>
          <div v-for="(subject, index) in courseList" :key="index">
            <el-col
              :class="{ selected: selectedCourseIndex === index }"
              style="padding: 7px 7px; margin: 3px 8px; font-size: 13px; cursor: pointer"
              @click="selectCourse(subject.id, index)"
            >
              {{ subject.name }}
            </el-col>
          </div>
        </el-row>
      </el-card>
  
      <!-- 顶部下方：选择班级和学生 -->
      <el-card class="filter-card">
        <el-form :inline="true">
          <el-form-item label="选择班级" style="float: left; width: 220px;">
            <el-select
              v-model="selectedClass"
              placeholder="请选择班级"
              @change="handleClassChange"
            >
              <el-option
                v-for="classItem in classList"
                :key="classItem.id"
                :label="classItem.name"
                :value="classItem.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="选择学生" style="float: left; width: 220px;">
            <el-select
              v-model="selectedStudent"
              placeholder="请选择学生"
              @change="handleStudentChange"
            >
              <el-option
                v-for="student in studentList"
                :key="student.id"
                :label="`${student.name}（${student.studentId}）`"
                :value="student.studentId" 
              />
            </el-select>
          </el-form-item>
        </el-form>
      </el-card>
  
      <!-- 中部：柱状图展示得分率排名最后八位的知识点 -->
      <el-card class="chart-card">
        <div ref="chartRef" class="chart-container"></div>
      </el-card>
  
      <!-- 底部：表格展示知识点详情 -->
      <el-card class="list-card">
        <el-table :data="weaknessList" border stripe>
          <el-table-column prop="id" label="知识点编号" />
          <el-table-column prop="name" label="知识点名称" />
          <el-table-column prop="chapterName" label="所属章节" />
          <el-table-column prop="questionCount" label="累计答题数" />
          <el-table-column prop="wrongCount" label="错题数" />
          <el-table-column prop="scoreRate" label="得分率">
            <template #default="{ row }">
              {{ (row.scoreRate * 100).toFixed(2) }}%
            </template>
          </el-table-column>
          <el-table-column prop="wrongRate" label="错题率">
            <template #default="{ row }">
              {{ (row.wrongRate * 100).toFixed(2) }}%
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import * as echarts from 'echarts';
  import request from '@/utils/request';
  import { ElMessage } from 'element-plus';
  
  // 数据
  const selectedCourseIndex = ref(-1); // 当前选中的课程索引
  const selectedCourse = ref('');
  const selectedClass = ref('');
  const selectedStudent = ref('');
  const courseList = ref([]); // 科目列表
  const classList = ref([]); // 班级列表
  const studentList = ref([]); // 学生列表
  const weaknessList = ref([]); // 薄弱知识点列表
  const chartRef = ref(null); // 图表 DOM 引用
  
  // 获取科目列表
  const fetchSubjectList = async () => {
    try {
      const res = await request.get('/api/exam/subjectList');
      if (res.code === 200) {
        courseList.value = res.data;
      } else {
        ElMessage.error('获取科目列表失败');
      }
    } catch (error) {
      console.error('获取科目列表失败:', error);
      ElMessage.error('获取科目列表失败');
    }
  };
  
  // 处理课程选择
  const selectCourse = async (subjectId, index) => {
    selectedCourseIndex.value = index;
    selectedCourse.value = subjectId;
    selectedClass.value = ''; // 清空已选班级
    selectedStudent.value = ''; // 清空已选学生
    studentList.value = []; // 清空学生列表
    weaknessList.value = []; // 清空薄弱知识点列表
  
    try {
      const res = await request.get(`/api/exam/getClassBySubject/${subjectId}`);
      if (res.code === 200) {
        classList.value = res.data;
      } else {
        ElMessage.error('获取班级列表失败');
      }
    } catch (error) {
      console.error('获取班级列表失败:', error);
      ElMessage.error('获取班级列表失败');
    }
  };
  
  // 处理班级选择
  const handleClassChange = async (classId) => {
    selectedStudent.value = ''; // 清空已选学生
    weaknessList.value = []; // 清空薄弱知识点列表
  
    try {
      const res = await request.get(`/api/exam/getClassStudent/${classId}`);
      if (res.code === 200) {
        studentList.value = res.data;
      } else {
        ElMessage.error('获取学生列表失败');
      }
    } catch (error) {
      console.error('获取学生列表失败:', error);
      ElMessage.error('获取学生列表失败');
    }
  };
  
  // 处理学生选择
  const handleStudentChange = async (studentId) => {
    try {
      const res = await request.get(`/api/analysis/getWeakSubjectKnowledgePoint/${selectedCourse.value}/${studentId}`);
      if (res.code === 200) {
        weaknessList.value = res.data;
        renderChart(res.data);
        console.log("知识点信息：",weaknessList.value)
      } else {
        ElMessage.error('获取薄弱知识点数据失败');
      }
    } catch (error) {
      console.error('获取薄弱知识点数据失败:', error);
      ElMessage.error('获取薄弱知识点数据失败');
    }
  };
  
  // 渲染图表
  const renderChart = (data) => {
    if (!chartRef.value) return;
  
    const chart = echarts.init(chartRef.value);
    const option = {
      title: {
        text: '薄弱知识点分析（Top 8）',
        left: 'center',
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow',
        },
      },
      xAxis: {
        type: 'category',
        data: data.map((item) => item.name),
      },
      yAxis: {
        type: 'value',
        max: 100,
        min: 0,
        axisLabel: {
          formatter: '{value}%',
        },
      },
      series: [
        {
          name: '得分率',
          type: 'bar',
          data: data.map((item) => (item.scoreRate * 100).toFixed(2)),
          itemStyle: {
            color: '#409EFF', // 蓝色表示得分率
          },
        },
      ],
    };
    chart.setOption(option);
  };
  
  // 页面加载时获取科目列表
  onMounted(() => {
    fetchSubjectList();
  });
  </script>
  
  <style scoped>
  .weakness-analysis-page {
    padding: 20px;
  }
  
  .filter-card {
    margin-bottom: 20px;
  }
  
  .chart-card {
    margin-bottom: 20px;
  }
  
  .chart-container {
    width: 100%;
    height: 400px;
  }
  
  .list-card {
    margin-bottom: 20px;
  }
  
  /* 选中效果 */
  .selected {
    background-color: #409eff;
    color: white;
    border-radius: 4px;
  }
  </style>