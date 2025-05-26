<template>
    <div>
<!--    头部-->
    <Header/>
<!--    主体-->
    <div style="display: flex;margin-left: 240px;">
<!--      侧边栏-->
      <Aside/>
<!--      内容区-->
      <router-view style="flex:1"/>
    </div>

  </div>
</template>
<script>
  import Header from "@/components/admin/Header";
  import Aside from "@/components/admin/Aside";

  export default {
    name: "AdminView",
    components: {
      Header,
      Aside
    }
  }

</script>

<style scoped>

</style>