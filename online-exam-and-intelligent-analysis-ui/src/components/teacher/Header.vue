<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex" id="header">
    <div style="width: 200px;padding-left: 10px;font-weight: bold;color: rgb(12, 56, 99);font-family: '楷体';font-size: 16px;">
      在线考试及智能分析系统</div>
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">教师端</div>
    <div style="flex: 1"></div>
    <div style="width: 50px;text-align: center;margin-top: 15px;">
      <el-dropdown>
    <span class="el-dropdown-link">
      {{ user.name }}
      <el-icon class="el-icon--right">
        <arrow-down />
      </el-icon>
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/')">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      user: {
        name: '张三'
      }
    }
  },
  created() {
      // 从路由获取用户名
      this.user.name = this.$route.query.username;
      console.log(this.user.name);
        
      }
}
</script>

<style scoped>
#header{
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2); /* 增强阴影效果 */
  border-radius: 3px; /* 圆角 */
  background-color: #FAFAFA;
}
</style>