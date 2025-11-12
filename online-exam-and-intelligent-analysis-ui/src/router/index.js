import { createRouter, createWebHashHistory } from "vue-router";
import login from "@/views/LoginView.vue";
const routes = [
  {
    // 登录界面
    path: "/",
    name: "login",
    component: login,
  },

  {
    // 学生界面
    path: "/student",
    name: "student",
    component: () => import("@/views/StudentView.vue"),
    children: [
      { path: "", component: () => import("@/components/student/myExam.vue") }, // 我的考试
      {
        path: "examMsg",
        component: () => import("@/components/student/examMsg.vue"),
      }, // 试卷详情
      {
        path: "scoreTable",
        component: () => import("@/components/student/scoreTable.vue"),
      }, // 成绩表
      {
        path: "analysis",
        component: () => import("@/components/student/analysis.vue"),
        children: [
          // {path: '',component: () => import('@/components/student/analysis/scoreAnalysis.vue') },// 成绩分析
          {
            path: "",
            component: () =>
              import(
                "@/components/student/analysis/scoreAnalysis/examRecord.vue"
              ),
          }, // 成绩趋势
          {
            path: "classRating",
            component: () =>
              import(
                "@/components/student/analysis/scoreAnalysis/classRating.vue"
              ),
          }, // 班级评分等级分布
          {
            path: "ranking",
            component: () =>
              import("@/components/student/analysis/scoreAnalysis/ranking.vue"),
          }, // 各题型得分率排行
          // {path: 'examAnalysis',component: () => import('@/components/student/analysis/examAnalysis.vue') },// 考试分析
          {
            path: "questionType",
            component: () =>
              import(
                "@/components/student/analysis/examAnalysis/questionType.vue"
              ),
          }, // 题型分析
          {
            path: "difficulty",
            component: () =>
              import(
                "@/components/student/analysis/examAnalysis/difficulty.vue"
              ),
          }, // 难度分析
          {
            path: "chapterWeaknesses",
            component: () =>
              import(
                "@/components/student/analysis/examAnalysis/chapterWeaknesses.vue"
              ),
          }, // 章节薄弱点分析
          // {path: 'knowledgePointsAnalysis',component: () => import('@/components/student/analysis/knowledgePointsAnalysis.vue') },// 知识点分析
          {
            path: "degree",
            component: () =>
              import(
                "@/components/student/analysis/knowledgePointsAnalysis/degree.vue"
              ),
          }, // 知识点掌握程度分析
          {
            path: "weak",
            component: () =>
              import(
                "@/components/student/analysis/knowledgePointsAnalysis/weak.vue"
              ),
          }, // 薄弱知识点分析
          {
            path: "recommendation",
            component: () =>
              import("@/views/recommendation/ResourceRecommendation.vue"),
          }, // 智能推荐
        ],
      }, // 智能分析
      {
        path: "mistakes",
        component: () => import("@/components/student/mistakes.vue"),
      }, // 错题集
      {
        path: "myInfo",
        component: () => import("@/components/student/studentInfo.vue"),
      }, // 个人信息
    ],
  },
  // 答题界面
  { path: "/examView", component: () => import("@/views/ExamView.vue") },
  { path: "/exam", component: () => import("@/views/ExamView2.vue") },
  // 批改界面
  { path: "/markingView", component: () => import("@/views/MarkingView.vue") },
  // 试题解析界面
  { path: "/answers", component: () => import("@/views/Answers.vue") },
  // 考试结束界面
  { path: "/endView", component: () => import("@/views/EndView.vue") },
  // 教师界面
  {
    path: "/teacher",
    component: () => import("@/views/TeacherView.vue"),
    children: [
      { path: "", component: () => import("@/components/teacher/index.vue") }, // 首页信息
      {
        path: "classInfo",
        component: () => import("@/components/teacher/classInfo.vue"),
      }, // 班级信息
      {
        path: "studentInfo",
        component: () => import("@/components/teacher/studentInfo.vue"),
      }, // 学生信息
      {
        path: "marking",
        component: () => import("@/components/teacher/marking.vue"),
      }, // 批改试卷
      {
        path: "marked",
        component: () => import("@/components/teacher/marked.vue"),
      }, // 批改完成列表
      {
        path: "questions",
        component: () => import("@/components/teacher/questions.vue"),
      }, // 题库信息
      {
        path: "organizeExam",
        component: () => import("@/components/teacher/organizeExam.vue"),
      }, // 组织考试
      {
        path: "examList",
        component: () => import("@/components/teacher/examList.vue"),
      }, // 考试列表
      {
        path: "single",
        component: () =>
          import("@/components/teacher/createQuestion/single.vue"),
      }, // 单选题
      {
        path: "multiple",
        component: () =>
          import("@/components/teacher/createQuestion/multiple.vue"),
      }, // 多选题
      {
        path: "judge",
        component: () =>
          import("@/components/teacher/createQuestion/judge.vue"),
      }, // 判断题
      {
        path: "short",
        component: () =>
          import("@/components/teacher/createQuestion/short.vue"),
      }, // 简答题
      {
        path: "fill",
        component: () => import("@/components/teacher/createQuestion/fill.vue"),
      }, // 填空题
      {
        path: "application",
        component: () =>
          import("@/components/teacher/createQuestion/application.vue"),
      }, // 应用题
      {
        path: "personAnalysis",
        component: () => import("@/components/teacher/personAnalysis.vue"),
      }, // 个人薄弱知识点分析
      {
        path: "classAnalysis",
        component: () => import("@/components/teacher/classAnalysis.vue"),
      }, // 班级薄弱知识点分析
    ],
  },
  // 管理员界面
  {
    path: "/admin",
    component: () => import("@/views/AdminView.vue"),
    children: [
      { path: "", component: () => import("@/components/admin/index.vue") }, // 首页信息
      {
        path: "institutionInfo",
        component: () => import("@/components/admin/institutionInfo.vue"),
      }, // 学院信息
      {
        path: "majorInfo",
        component: () => import("@/components/admin/majorInfo.vue"),
      }, // 专业信息
      {
        path: "subjectInfo",
        component: () => import("@/components/admin/subjectInfo.vue"),
      }, // 学科信息
      {
        path: "chapterInfo",
        component: () => import("@/components/admin/chapterInfo.vue"),
      }, // 章节信息
      {
        path: "classInfo",
        component: () => import("@/components/admin/classInfo.vue"),
      }, // 班级信息
      {
        path: "teacherInfo",
        component: () => import("@/components/admin/teacherInfo.vue"),
      }, // 教师信息
      {
        path: "studentInfo",
        component: () => import("@/components/admin/studentInfo.vue"),
      }, // 学生信息
      {
        path: "questionInfo",
        component: () => import("@/components/admin/questionInfo.vue"),
      }, // 试题信息
      {
        path: "marking",
        component: () => import("@/components/teacher/marking.vue"),
      }, // 批改试卷
      {
        path: "marked",
        component: () => import("@/components/teacher/marked.vue"),
      }, // 批改完成列表
      {
        path: "organizeExam",
        component: () => import("@/components/teacher/organizeExam.vue"),
      }, // 组织考试
      {
        path: "examList",
        component: () => import("@/components/teacher/examList.vue"),
      }, // 考试列表
      {
        path: "single",
        component: () =>
          import("@/components/teacher/createQuestion/single.vue"),
      }, // 单选题
      {
        path: "multiple",
        component: () =>
          import("@/components/teacher/createQuestion/multiple.vue"),
      }, // 多选题
      {
        path: "judge",
        component: () =>
          import("@/components/teacher/createQuestion/judge.vue"),
      }, // 判断题
      {
        path: "short",
        component: () =>
          import("@/components/teacher/createQuestion/short.vue"),
      }, // 简答题
      {
        path: "fill",
        component: () => import("@/components/teacher/createQuestion/fill.vue"),
      }, // 填空题
      {
        path: "application",
        component: () =>
          import("@/components/teacher/createQuestion/application.vue"),
      }, // 应用题
      {
        path: "knowledgePoints",
        component: () => import("@/components/admin/knowledgePoints.vue"),
      }, // 知识点信息
      {
        path: "aiModel",
        component: () => import("@/components/admin/aiModelPanel.vue"),
      }, // AI模型管理
    ],
  },
];

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL), // 使用createWebHashHistory来创建hash模式的历史记录管理器
  routes,
});

export default router;
