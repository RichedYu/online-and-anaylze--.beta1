<template>
    <div class="common-layout">
      <el-container>
        <el-header><Header/></el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </div>
  </template>
  
  <script>
  import Header from '../components/student/Header.vue'

  export default {
    name: 'student',
    components: {
      Header
     
    },
    data() {
      return {
        
      };
    }
  }
  </script>
  
  <style scoped>

    .common-layout {
        height: 100vh;
        display: flex;
    }


    .el-header {
        width: 100%;
        padding: 0 5px;
        /* top: 0;
        padding: 0;
        position: fixed;  */
        
    }
    .el-main {
        /* flex: 1; */
        width: 100%;
        height: 100%;
        padding: 0;
        background-color: #F9F9F9;
       
    }
    
    
  </style>
  