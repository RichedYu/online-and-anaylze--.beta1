<template>
  <div style="height: auto;">
    <!--  引入导航菜单-->
    <el-menu id="aside"
        active-text-color="#E6E8EB"
        background-color="#606266"
        class="el-menu-vertical-demo"
        default-active="2"
        text-color="#fff"
        @open="handleOpen"
        @close="handleClose"
    >
    <div style="height: 50px;border: 1px solid #393b3e;">
      <span id="logo" style="">在线考试系统后台管理</span>
    </div>
      <router-link to="/teacher">
        <el-sub-menu index="1">
        <template #title>
          <el-icon><location /></el-icon>
          <span>首页</span>
        </template>
          <!-- <el-menu-item index="1-1">展示最近的考试信息</el-menu-item>
          <el-menu-item index="1-2">展示班级和学生概览</el-menu-item> -->
      </el-sub-menu>
      </router-link>
      <el-sub-menu index="2">
        <template #title>
          <el-icon><location /></el-icon>
          <span>班级管理</span>
        </template>
          
            <router-link to="/teacher/classInfo">
              <el-menu-item index="2-1">
              班级管理列表
            </el-menu-item>
            </router-link>
          
          <!-- <el-menu-item index="2-2">查看班级学生列表</el-menu-item> -->
      </el-sub-menu>
      <el-sub-menu index="3">
        <template #title>
          <el-icon><location /></el-icon>
          <span>学生管理</span>
        </template>
          <router-link to="/teacher/studentInfo">
            <el-menu-item index="3-1">学生管理列表</el-menu-item>
          </router-link>
          
          <!-- <el-menu-item index="3-3">查看学生考试记录</el-menu-item> -->
      </el-sub-menu>
      <!-- <el-sub-menu index="4">
        <template #title>
          <el-icon><location /></el-icon>
          <span>成绩分析</span>
        </template>
          <el-menu-item index="4-1">成绩趋势图</el-menu-item>
          <el-menu-item index="4-2">成绩分布图</el-menu-item>
          <el-menu-item index="4-3">成绩对比（班级间、学生间）</el-menu-item>
      </el-sub-menu> -->
      <el-sub-menu index="5">
        <template #title>
          <el-icon><location /></el-icon>
          <span>考试管理</span>
        </template>
          <router-link to="/teacher/organizeExam">
            <el-menu-item index="5-1">组织考试</el-menu-item>
          </router-link>
          <router-link to="/teacher/examList">
            <el-menu-item index="5-2">考试列表</el-menu-item>
          </router-link>
          <router-link to="/teacher/marking">
            <el-menu-item index="5-3">未批改试卷列表</el-menu-item>
            </router-link> 
          <router-link to="/teacher/marked">
            <el-menu-item index="5-4">已批改试卷列表</el-menu-item>
          </router-link>
      </el-sub-menu>
      <el-sub-menu index="6">
        <template #title>
          <el-icon><location /></el-icon>
          <span>题库管理</span>
        </template>
          <router-link to="/teacher/questions">
            <el-menu-item index="6-1">题库列表</el-menu-item>
          </router-link>
          <router-link to="/teacher/single">
            <el-menu-item index="6-2">单选题创编</el-menu-item>
          </router-link>
          <router-link to="/teacher/multiple">
            <el-menu-item index="6-3">多选题创编</el-menu-item>
          </router-link>
          <router-link to="/teacher/judge">
            <el-menu-item index="6-4">判断题创编</el-menu-item>
          </router-link>
          <router-link to="/teacher/fill">
            <el-menu-item index="6-5">填空题创编</el-menu-item>
          </router-link>
          <router-link to="/teacher/short">
            <el-menu-item index="6-6">简答题创编</el-menu-item>
          </router-link>
          <router-link to="/teacher/application">
            <el-menu-item index="6-7">应用题创编</el-menu-item>
          </router-link>
      </el-sub-menu>
      <el-sub-menu index="7">
        <template #title>
          <el-icon><location /></el-icon>
          <span>智能分析</span>
        </template>
      
        <router-link to="/teacher/personAnalysis">
          <el-menu-item index="7-1">个人薄弱知识点分析</el-menu-item>
        </router-link>
        <router-link to="/teacher/classAnalysis">
          <el-menu-item index="7-2">班级整体薄弱知识点分析</el-menu-item>
        </router-link>
      </el-sub-menu>
      
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>
#aside{
  width: 230px;
  height: 100%;
  position: fixed;
  top: 0;left: 0;
  z-index: 999;
  background-color: #303133;
  overflow: auto;
  box-shadow: 2px 0 6px rgba(0,0,0,0.3);
}

#logo{
  width: 100%;
  height: 60px;
  line-height: 41px;
  text-align: center;
  font-size: 16px;
  font-family: '楷体';
   font-style: italic;
   color: gray;
}
</style>