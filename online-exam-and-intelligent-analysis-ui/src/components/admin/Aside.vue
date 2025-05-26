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
    
      <router-link to="/admin">
        <el-sub-menu index="1">
        <template #title>
          <el-icon><location /></el-icon>
          <span>首页</span>
        </template>
          
      </el-sub-menu>
      </router-link>
      <el-sub-menu index="2">
        <template #title>
          <el-icon><location /></el-icon>
          <span>属性管理</span>
        </template>
            <router-link to="/admin/institutionInfo">
              <el-menu-item index="2-1">
              管理学院信息
             </el-menu-item>
            </router-link>
            <router-link to="/admin/majorInfo">
              <el-menu-item index="2-2">
                管理专业信息
              </el-menu-item>
            </router-link>
            <router-link to="/admin/subjectInfo">
              <el-menu-item index="2-3">
              管理课程信息
              </el-menu-item>
            </router-link>  
            <router-link to="/admin/chapterInfo">
              <el-menu-item index="2-4">管理章节信息</el-menu-item>
            </router-link> 
            <router-link to="/admin/knowledgePoints">
              <el-menu-item index="2-5">管理知识点信息</el-menu-item>
            </router-link>
      </el-sub-menu>

      <el-sub-menu index="3">
        <template #title>
          <el-icon><location /></el-icon>
          <span>班级管理</span>
        </template>
          <router-link to="/admin/classInfo">
            <el-menu-item index="3-1">管理班级信息</el-menu-item>
          </router-link>
      </el-sub-menu>
      <el-sub-menu index="4">
        <template #title>
          <el-icon><location /></el-icon>
          <span>用户管理</span>
        </template>
          <router-link to="/admin/teacherInfo">
            <el-menu-item index="4-1">教师信息管理</el-menu-item>
          </router-link>
          <router-link to="/admin/studentInfo">
            <el-menu-item index="4-2">学生信息管理</el-menu-item>
          </router-link>
      </el-sub-menu>
      <el-sub-menu index="5">
        <template #title>
          <el-icon><location /></el-icon>
          <span>题库管理</span>
        </template>
          <router-link to="/admin/questionInfo">
            <el-menu-item index="5-1">查看题库信息</el-menu-item>
          </router-link>
          <router-link to="/admin/single">
            <el-menu-item index="5-2">单选题创编</el-menu-item>
          </router-link>
          <router-link to="/admin/multiple">
            <el-menu-item index="5-3">多选题创编</el-menu-item>
          </router-link>
          <router-link to="/admin/judge">
            <el-menu-item index="5-4">判断题创编</el-menu-item>
          </router-link>
          <router-link to="/admin/fill">
            <el-menu-item index="5-5">填空题创编</el-menu-item>
          </router-link>
          <router-link to="/admin/short">
            <el-menu-item index="5-6">简答题创编</el-menu-item>
          </router-link>
          <router-link to="/admin/application">
            <el-menu-item index="5-7">应用题创编</el-menu-item>
          </router-link>
      </el-sub-menu>
      <el-sub-menu index="6">
        <template #title>
          <el-icon><location /></el-icon>
          <span>考试管理</span>
        </template>
        <router-link to="/admin/organizeExam">
            <el-menu-item index="6-1">组织考试</el-menu-item>
          </router-link>
          <router-link to="/admin/examList">
            <el-menu-item index="6-2">考试列表</el-menu-item>
          </router-link>
          <router-link to="/admin/marking">
            <el-menu-item index="6-3">未批改试卷列表</el-menu-item>
            </router-link> 
          <router-link to="/admin/marked">
            <el-menu-item index="6-4">已批改试卷列表</el-menu-item>
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
  overflow-x: hidden;
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
/* 点击Item，更改背景颜色 */
.el-menu-item:active{
  background-color: #fff;
}
</style>