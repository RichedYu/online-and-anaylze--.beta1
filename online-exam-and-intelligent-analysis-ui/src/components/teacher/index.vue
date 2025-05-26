<template>
    <div class="teacher-dashboard">
      <!-- 欢迎信息 -->
      <el-card class="welcome-card">
        <div class="welcome-content">
          <el-avatar :size="60" :src="teacher.avatar" />
          <div class="welcome-text">
            <h2>欢迎回来，{{ teacher.name }}！</h2>
            <p>今天是 {{ currentDate }}，祝您工作愉快！</p>
          </div>
        </div>
      </el-card>
  
      <!-- 考试统计 -->
      <el-row :gutter="20" class="stats-row">
        <el-col :span="8">
          <el-card>
            <div class="stat-item">
              <el-icon><document /></el-icon>
              <div class="stat-text">
                <h3>已创建考试</h3>
                <p>{{ examStats.created }} 场</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card>
            <div class="stat-item">
              <el-icon><clock /></el-icon>
              <div class="stat-text">
                <h3>进行中的考试</h3>
                <p>{{ examStats.ongoing }} 场</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card>
            <div class="stat-item">
              <el-icon><finished /></el-icon>
              <div class="stat-text">
                <h3>已结束的考试</h3>
                <p>{{ examStats.finished }} 场</p>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
  
      <!-- 成绩概览 -->
      <el-card class="score-card">
        <h3>最近一次考试成绩概览</h3>
        <el-row :gutter="20">
          <el-col :span="8">
            <div class="score-item">
              <el-icon><star /></el-icon>
              <div class="score-text">
                <h4>平均分</h4>
                <p>{{ latestExamStats.average }} 分</p>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="score-item">
              <el-icon><trophy /></el-icon>
              <div class="score-text">
                <h4>最高分</h4>
                <p>{{ latestExamStats.highest }} 分</p>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="score-item">
              <el-icon><warning /></el-icon>
              <div class="score-text">
                <h4>最低分</h4>
                <p>{{ latestExamStats.lowest }} 分</p>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-card>
  
      <!-- 待办事项 -->
      <el-card class="todo-card">
        <h3>待办事项</h3>
        <el-table :data="todoList" style="width: 100%">
          <el-table-column prop="task" label="任务" />
          <el-table-column prop="deadline" label="截止时间" />
          <el-table-column label="操作">
            <template #default="{ row }">
              <el-button type="text" @click="handleTaskAction(row)">处理</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
  
      <!-- 快捷操作 -->
      <el-card class="quick-actions-card">
        <h3>快捷操作</h3>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-button type="primary" @click="handleCreateExam">
              <el-icon><document-add /></el-icon>
              创建考试
            </el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="success" @click="handleViewScores">
              <el-icon><data-analysis /></el-icon>
              查看成绩
            </el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="warning" @click="handleManageStudents">
              <el-icon><user /></el-icon>
              学生管理
            </el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="danger" @click="handleLogout">
              <el-icon><switch-button /></el-icon>
              退出登录
            </el-button>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </template>
  
  <script setup>
  import {
    Document,
    Clock,
    Finished,
    Star,
    Trophy,
    Warning,
    DocumentAdd,
    DataAnalysis,
    User,
    SwitchButton,
  } from '@element-plus/icons-vue';
  
  // 模拟数据
  const teacher = {
    name: '张老师',
    avatar: 'https://example.com/avatar.jpg',
  };
  
  const currentDate = new Date().toLocaleDateString();
  
  const examStats = {
    created: 10,
    ongoing: 2,
    finished: 8,
  };
  
  const latestExamStats = {
    average: 85,
    highest: 98,
    lowest: 60,
  };
  
  const todoList = [
    { task: '批改期中考试试卷', deadline: '2023-10-20' },
    { task: '发布期末考试成绩', deadline: '2023-12-25' },
  ];
  
  // 快捷操作处理函数
  const handleCreateExam = () => {
    console.log('创建考试');
  };
  
  const handleViewScores = () => {
    console.log('查看成绩');
  };
  
  const handleManageStudents = () => {
    console.log('学生管理');
  };
  
  const handleLogout = () => {
    console.log('退出登录');
  };
  
  const handleTaskAction = (row) => {
    console.log('处理任务:', row);
  };
  </script>
  
  <style scoped>
  .teacher-dashboard {
    padding: 20px;
  }
  
  .welcome-card {
    margin-bottom: 20px;
  }
  
  .welcome-content {
    display: flex;
    align-items: center;
  }
  
  .welcome-text {
    margin-left: 20px;
  }
  
  .stats-row {
    margin-bottom: 20px;
  }
  
  .stat-item {
    display: flex;
    align-items: center;
  }
  
  .stat-text {
    margin-left: 10px;
  }
  
  .score-card,
  .todo-card,
  .quick-actions-card {
    margin-bottom: 20px;
  }
  
  .score-item {
    display: flex;
    align-items: center;
  }
  
  .score-text {
    margin-left: 10px;
  }
  </style>