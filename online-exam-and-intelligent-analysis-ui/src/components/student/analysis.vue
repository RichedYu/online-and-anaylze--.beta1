<template>
  <el-container style="margin: 0 10px">
    <el-header
      height="10px"
      style="margin: 10px 0px; text-align: left; padding: 5px 5px"
    >
      <el-row>
        <span style="padding: 10px 5px; width: 80px">考试科目:</span>
        <div v-for="(subject, index) in options" :key="index">
          <el-col
            :class="{ selected: selectedIndex === index }"
            style="padding: 7px 7px; margin: 3px 8px; font-size: 13px"
            @Click="selectSubject(subject.value, index)"
          >
            {{ subject.label }}
          </el-col>
        </div>
      </el-row>
    </el-header>
    <el-divider border-style="double" />
    <el-container>
      <el-aside width="230px" style="height: 100vh; background-color: white">
        <!-- <h5 class="mb-2">Default colors</h5> -->
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
        >
          <el-sub-menu index="1">
            <template #title>
              <el-icon><Promotion /></el-icon>
              <span>成绩分析</span>
            </template>
            <router-link to="/student/analysis">
              <el-menu-item index="1-1">考试记录</el-menu-item>
            </router-link>
            <router-link to="/student/analysis/classRating">
              <el-menu-item index="1-2">班级评分等级分布</el-menu-item>
            </router-link>
            <router-link to="/student/analysis/ranking">
              <el-menu-item index="1-3">各题型得分率排行</el-menu-item>
            </router-link>
            <!-- <el-sub-menu index="1-4">
            <template #title>item four</template>
            <el-menu-item index="1-4-1">item one</el-menu-item>
          </el-sub-menu> -->
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <el-icon><EditPen /></el-icon>
              <span>考试分析</span>
            </template>
            <router-link to="/student/analysis/questionType">
              <el-menu-item index="2-1">考试题型分析</el-menu-item>
            </router-link>
            <router-link to="/student/analysis/difficulty">
              <el-menu-item index="2-2">考试难度分析</el-menu-item>
            </router-link>
            <router-link to="/student/analysis/chapterWeaknesses">
              <el-menu-item index="2-3">章节薄弱点分析</el-menu-item>
            </router-link>
            <!-- <el-sub-menu index="1-4">
            <template #title>item four</template>
            <el-menu-item index="1-4-1">item one</el-menu-item>
          </el-sub-menu> -->
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <el-icon><Document /></el-icon>
              <span>知识点分析</span>
            </template>
            <router-link to="/student/analysis/degree">
              <el-menu-item index="3-1">知识点掌握程度分析</el-menu-item>
            </router-link>
            <router-link to="/student/analysis/weak">
              <el-menu-item index="3-2">ai智能分析报告</el-menu-item>
            </router-link>

            <!-- <el-sub-menu index="1-4">
            <template #title>item four</template>
            <el-menu-item index="1-4-1">item one</el-menu-item>
          </el-sub-menu> -->
          </el-sub-menu>
          <el-sub-menu index="4">
            <template #title>
              <el-icon><Trophy /></el-icon>
              <span>智能推荐</span>
            </template>
            <router-link to="/student/analysis/recommendation">
              <el-menu-item index="4-1">学习资源推荐</el-menu-item>
            </router-link>
          </el-sub-menu>
          <!-- <el-sub-menu index="4">
          <template #title>
            <el-icon><Trophy /></el-icon>
            <span>智能制定学习计划</span>
          </template>
            <el-menu-item index="4-1">考试记录</el-menu-item>
            <el-menu-item index="4-2">班级评分等级分布</el-menu-item>
            <el-menu-item index="4-3">各题型得分率排行</el-menu-item>
            <el-menu-item index="4-4">成绩趋势分析</el-menu-item>
         
        </el-sub-menu> -->
        </el-menu>
      </el-aside>
      <el-main
        style="
          margin-left: 5px;
          max-height: 100vh;
          -ms-overflow-style: none; /* IE and Edge */
          scrollbar-width: none; /* Firefox */
        "
      >
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { Document, Promotion, EditPen, Trophy } from "@element-plus/icons-vue";
import request from "@/utils/request";
import { DataAnalysis } from "@element-plus/icons-vue";
import { mapState } from "vuex";
import router from "@/router";
export default {
  name: "analysis",
  components: {
    DataAnalysis,
    Document,
    Promotion,
    EditPen,
    Trophy,
    router,
  },
  data() {
    return {
      options: [],
      selectedIndex: 0, // 初始选中第一个按钮
    };
  },
  created() {
    this.getSubjects();
  },
  computed: {
    ...mapState(["analysisSubject"]),
  },
  watch: {
    analysisSubject: function (newVal, oldVal) {},
  },
  methods: {
    getSubjects() {
      let studentId = localStorage.getItem("studentId");
      request.get(`/api/analysis/getSubjects/${studentId}`).then((res) => {
        console.log(res.data);
        this.options = res.data;
        if (this.options.length > 0) {
          this.$store.commit("setAnalysisSubject", this.options[0].value);
          console.log("success~", this.options[0].value);
        }
      });
    },
    selectSubject(subjectId, index) {
      this.selectedIndex = index;
      this.$store.commit("setAnalysisSubject", subjectId);
    },
  },
};
</script>

<style scoped>
.selected {
  background-color: #3199fa;
  color: white;
}
.el-menu-item {
  font-size: 13px;
  color: #696969;
}
.el-main {
  padding: 0;
  /* background: url(@/assets/images/analy1.jpg); */
}
#analysis {
  font-size: 16px;
  width: 100%;
  /* height: 50px; */
  font-weight: bold;
  background: #fff;
}
.mb-20 {
  display: flex;
  height: 850px;
}
.el-card {
  padding: 0;
  background-color: rgba(255, 255, 255, 0.7); /* 半透明背景*/
}
.el-col {
  border-radius: 4px;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s ease, border-color 0.3s ease; /* 添加过渡效果，使变化更平滑 */
}
.el-col:hover {
  /* 鼠标悬停时的样式 */
  /* background-color: #f0f0f0; */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 添加阴影效果 */
  /* 你可以在这里添加其他悬停时的样式，如阴影、字体颜色等 */
}
.card1 {
  width: 1175px;
  height: 50%;
  /* background-color: #fff; */
}
.card2 {
  width: 1175px;
  height: 80%;
  /* background-color: #fff; */
}
.card1-part {
  height: 20%;
  margin: 12px 12px;
  padding-top: 20px;
}
.card1-part-title {
  height: 100px;
  width: 120px;
  line-height: 40px;
  font-size: 14px;
  font-weight: bold;
  color: #333;
  text-align: center;
  margin: 10px 10px;
  box-shadow: 0 2px 3px 0 rgba(0, 0, 0, 0.3);
}
</style>
