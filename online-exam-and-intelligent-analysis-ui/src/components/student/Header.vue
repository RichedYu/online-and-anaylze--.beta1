<template>
  <div class="header">
        <!-- 导航栏 -->
        <div class="nav">
            <el-row :gutter="20" class="header-row">
            <el-col :span="4">学生端{{ a }}</el-col>
            <el-col :span="4" class="grid-content"><router-link to="/student/"><div class="a">我的考试</div></router-link> </el-col>
            <el-col :span="4" class="grid-content"><router-link to="/student/scoreTable"><div class="a">成绩查询</div></router-link></el-col>
            <el-col :span="4" class="grid-content"><router-link to="/student/analysis"><div class="a">智能分析</div></router-link></el-col>
            <el-col :span="4" class="grid-content"><router-link to="/student/mistakes"><div class="a">错题本</div></router-link></el-col>
            <el-col :span="4" class="grid-content"><router-link to="/student/myInfo"><div class="a">我的信息</div></router-link></el-col>
            </el-row>
        </div>
        <!-- 个人信息 -->
        <div class="user">
            
            <el-dropdown placement="top-start" class="personal-dropdown">
                <b>{{user.name}}<el-icon class="icon-arrow"><ArrowDown /></el-icon></b>
                <template #dropdown>
                    <el-dropdown-menu>
                    <el-dropdown-item>修改密码</el-dropdown-item>
                    <el-dropdown-item @click="$router.push('/')">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </template>

​            </el-dropdown>
        </div>
  </div>
</template>

<script>
import { ArrowDown } from '@element-plus/icons-vue'
export default {
  name: "Header",
  components: {
    ArrowDown
  },
  data() {
    return {
      user: {
        name: '张三'
      }
    }
  },
  created() {
      // 从路由获取用户名
    //   this.user.name = this.$route.query.username;
    this.user.name = localStorage.getItem('studentName');
      console.log(this.user.name);
        
      }
  
}
</script>

<style scoped>
    .header {
        height: 52px;
        background-color: #fff;
        display: flex;
        box-shadow: 0 1px 16px rgba(74, 66, 230, 0.2); 
        border-radius: 6px;
    }

    .nav {
        width: 70%;
        line-height: 50px;
    }

    .user {
        flex: 1;
      
    }
    .user b {
        font-family: '楷体';
        margin-top: 15px;
        float: right;
        line-height: 30px;
        font-size: 16px;
        height: 30px;
      
    }

   .icon-arrow{
       float: right;
       margin-top: 8px;
       margin-left: 3px;
     
   }

    .personal-dropdown {
        
        margin-right: 20px;
        float: right;
    }

    .el-dropdown-link:focus {
      outline: none;
    }

    .header-row {
        margin-left: 0 !important;
        margin-right: 0 !important;
      
    }

    /* 每个导航项的样式 */

    .grid-content {
        border-radius: 6px; 
        transition: all 0.3s ease;
        cursor: pointer;
        height: 48px;
        line-height: 50px;
    }

    .a{
        width: 100%;
        height: 100%;
    }

    .grid-content:hover {
        background-color: #F2F6FC; 
        box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2); 
        transform: translateY(-4px); 
    }



    .grid-content:active {
        background-color: #cce4ff; 
        transform: translateY(2px);
    }
</style>
