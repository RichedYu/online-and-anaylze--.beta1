<template>
    <div class="admin-dashboard">
      <!-- 欢迎信息 -->
      <el-card class="welcome-card">
        <div class="welcome-content">
          <el-avatar :size="60" :src="admin.avatar" />
          <div class="welcome-text">
            <h2>欢迎回来，{{ admin.name }}！</h2>
            <p>今天是 {{ currentDate }}，祝您工作愉快！</p>
          </div>
        </div>
      </el-card>
  
      <!-- 系统概览 -->
      <el-row :gutter="20" class="stats-row">
        <el-col :span="6">
          <el-card>
            <div class="stat-item">
              <el-icon><user /></el-icon>
              <div class="stat-text">
                <h3>总用户数</h3>
                <p>{{ systemStats.totalUsers }} 人</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div class="stat-item">
              <el-icon><document /></el-icon>
              <div class="stat-text">
                <h3>总考试数</h3>
                <p>{{ systemStats.totalExams }} 场</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div class="stat-item">
              <el-icon><data-line /></el-icon>
              <div class="stat-text">
                <h3>活跃用户数</h3>
                <p>{{ systemStats.activeUsers }} 人</p>
              </div>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card>
            <div class="stat-item">
              <el-icon><warning /></el-icon>
              <div class="stat-text">
                <h3>异常日志</h3>
                <p>{{ systemStats.errorLogs }} 条</p>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
  
      <!-- 用户管理 -->
      <el-card class="user-management-card">
        <h3>最近注册的用户</h3>
        <el-table :data="recentUsers" style="width: 100%">
          <el-table-column prop="username" label="用户名" />
          <el-table-column prop="role" label="角色" />
          <el-table-column prop="registerTime" label="注册时间" />
          <el-table-column label="操作">
            <template #default="{ row }">
              <el-button type="text" @click="handleEditUser(row)">编辑</el-button>
              <el-button type="text" @click="handleDeleteUser(row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
  
      <!-- 考试管理 -->
      <el-card class="exam-management-card">
        <h3>最近创建的考试</h3>
        <el-table :data="recentExams" style="width: 100%">
          <el-table-column prop="name" label="考试名称" />
          <el-table-column prop="creator" label="创建人" />
          <el-table-column prop="createTime" label="创建时间" />
          <el-table-column label="操作">
            <template #default="{ row }">
              <el-button type="text" @click="handleEditExam(row)">编辑</el-button>
              <el-button type="text" @click="handleDeleteExam(row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
  
      <!-- 系统日志 -->
      <el-card class="system-logs-card">
        <h3>系统日志</h3>
        <el-table :data="systemLogs" style="width: 100%">
          <el-table-column prop="time" label="时间" />
          <el-table-column prop="action" label="操作" />
          <el-table-column prop="user" label="用户" />
          <el-table-column prop="details" label="详情" />
        </el-table>
      </el-card>
  
      <!-- 快捷操作 -->
      <el-card class="quick-actions-card">
        <h3>快捷操作</h3>
        <el-row :gutter="20">
          <el-col :span="6">
            <el-button type="primary" @click="handleAddUser">
              <el-icon><user-add /></el-icon>
              添加用户
            </el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="success" @click="handleCreateExam">
              <el-icon><document-add /></el-icon>
              创建考试
            </el-button>
          </el-col>
          <el-col :span="6">
            <el-button type="warning" @click="handleViewLogs">
              <el-icon><document /></el-icon>
              查看日志
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
    User,
    Document,
    DataLine,
    Warning,
    UserAdd,
    DocumentAdd,
    SwitchButton,
  } from '@element-plus/icons-vue';
  
  // 模拟数据
  const admin = {
    name: '管理员',
    avatar: 'https://example.com/admin-avatar.jpg',
  };
  
  const currentDate = new Date().toLocaleDateString();
  
  const systemStats = {
    totalUsers: 100,
    totalExams: 50,
    activeUsers: 80,
    errorLogs: 5,
  };
  
  const recentUsers = [
    { username: 'user1', role: '学生', registerTime: '2023-10-01' },
    { username: 'user2', role: '教师', registerTime: '2023-10-02' },
    { username: 'user3', role: '学生', registerTime: '2023-10-03' },
  ];
  
  const recentExams = [
    { name: '期中考试', creator: '张老师', createTime: '2023-10-10' },
    { name: '期末考试', creator: '李老师', createTime: '2023-12-01' },
  ];
  
  const systemLogs = [
    { time: '2023-10-15 10:00', action: '登录', user: 'admin', details: '成功登录系统' },
    { time: '2023-10-15 10:30', action: '创建考试', user: '张老师', details: '创建了期中考试' },
    { time: '2023-10-15 11:00', action: '删除用户', user: 'admin', details: '删除了用户 user4' },
  ];
  
  // 快捷操作处理函数
  const handleAddUser = () => {
    console.log('添加用户');
  };
  
  const handleCreateExam = () => {
    console.log('创建考试');
  };
  
  const handleViewLogs = () => {
    console.log('查看日志');
  };
  
  const handleLogout = () => {
    console.log('退出登录');
  };
  
  const handleEditUser = (row) => {
    console.log('编辑用户:', row);
  };
  
  const handleDeleteUser = (row) => {
    console.log('删除用户:', row);
  };
  
  const handleEditExam = (row) => {
    console.log('编辑考试:', row);
  };
  
  const handleDeleteExam = (row) => {
    console.log('删除考试:', row);
  };
  </script>
  
  <style scoped>
  .admin-dashboard {
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
  
  .user-management-card,
  .exam-management-card,
  .system-logs-card,
  .quick-actions-card {
    margin-bottom: 20px;
  }
  </style>