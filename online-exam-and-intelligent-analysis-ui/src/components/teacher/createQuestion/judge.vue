<template>
    <div class="judgment-create">
      <el-card class="form-card">
        <template #header>
          <div class="card-header">
            <span class="card-title">判断题创编</span>
          </div>
        </template>
  
        <!-- 表单 -->
        <el-form :model="form" :rules="rules" ref="formRef" label-width="120px">
          <!-- 科目 -->
          <el-form-item label="科目" prop="subjectId">
            <el-select
              v-model="form.subjectId"
              placeholder="请选择科目"
              @change="handleSubjectChange"
            >
              <el-option
                v-for="subject in subjects"
                :key="subject.value"
                :label="subject.label"
                :value="subject.value"
              ></el-option>
            </el-select>
          </el-form-item>
  
          <!-- 章节 -->
          <el-form-item label="章节" prop="chapterId">
            <el-select
              v-model="form.chapterId"
              placeholder="请选择章节"
              @change="handleChapterChange"
            >
              <el-option
                v-for="chapter in chapters"
                :key="chapter.value"
                :label="chapter.label"
                :value="chapter.value"
              ></el-option>
            </el-select>
          </el-form-item>
  
          <!-- 知识点集 -->
          <el-form-item label="知识点集" prop="knowledgePointIds">
            <el-select
              v-model="form.knowledgePointIds"
              multiple
              placeholder="请选择知识点"
            >
              <el-option
                v-for="point in knowledgePoints"
                :key="point.value"
                :label="point.label"
                :value="point.value"
              ></el-option>
            </el-select>
          </el-form-item>
  
          <!-- 题目内容 -->
          <el-form-item label="题目内容" prop="question">
            <el-input
              v-model="form.question"
              type="textarea"
              :rows="4"
              placeholder="请输入题目内容"
            ></el-input>
          </el-form-item>
  
          <!-- 题目解析 -->
          <el-form-item label="题目解析" prop="analysis">
            <el-input
              v-model="form.analysis"
              type="textarea"
              :rows="4"
              placeholder="请输入题目解析"
            ></el-input>
          </el-form-item>
  
          <!-- 正确答案 -->
          <el-form-item label="正确答案" prop="correctAnswers">
            <el-select v-model="form.correctAnswers" placeholder="请选择正确答案">
              <el-option label="正确" value="1"></el-option>
              <el-option label="错误" value="0"></el-option>
            </el-select>
          </el-form-item>
  
          <!-- 难度 -->
          <el-form-item label="难度" prop="level">
            <el-select v-model="form.level" placeholder="请选择难度">
              <el-option label="简单" value="1"></el-option>
              <el-option label="普通" value="2"></el-option>
              <el-option label="中等" value="3"></el-option>
              <el-option label="较难" value="4"></el-option>
              <el-option label="困难" value="5"></el-option>
            </el-select>
          </el-form-item>
  
          <!-- 创建按钮 -->
          <el-form-item>
            <el-button type="primary" @click="submitForm">创建</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script>
  import { ref, onMounted } from 'vue';
  import { ElMessage } from 'element-plus';
  import request from '@/utils/request';
  
  export default {
    setup() {
      // 表单数据
      const form = ref({
        subjectId: '', // 科目 ID
        chapterId: '', // 章节 ID
        knowledgePointIds: [], // 知识点 ID 列表
        knowledgePointIdsStr: '', // 知识点 ID 列表字符串
        question: '',
        analysis: '',
        correctAnswers: '', // 正确答案
        level: '', // 难度
      });
  
      // 科目列表
      const subjects = ref([]);
  
      // 章节列表
      const chapters = ref([]);
  
      // 知识点列表
      const knowledgePoints = ref([]);
  
      // 表单验证规则
      const rules = {
        subjectId: [{ required: true, message: '请选择科目', trigger: 'change' }],
        chapterId: [{ required: true, message: '请选择章节', trigger: 'change' }],
        knowledgePointIds: [
          { required: true, message: '请选择知识点', trigger: 'change' },
        ],
        question: [{ required: true, message: '请输入题目内容', trigger: 'blur' }],
        analysis: [{ required: true, message: '请输入题目解析', trigger: 'blur' }],
        correctAnswer: [
          { required: true, message: '请选择正确答案', trigger: 'change' },
        ],
        level: [{ required: true, message: '请选择难度', trigger: 'change' }],
      };
  
      // 表单引用
      const formRef = ref(null);
  
      // 获取科目列表
      const getSubjectList = async () => {
        try {
          const res = await request.get('/api/exam/subjectList');
          subjects.value = res.data.map(item => ({
            value: item.id,
            label: item.name,
          }));
          console.log('所有科目数据:', subjects.value);
        } catch (err) {
          console.error('获取科目列表失败:', err);
        }
      };
  
      // 获取章节列表
      const getChapterList = async (subjectId) => {
        if (!subjectId) {
          chapters.value = []; // 清空章节列表
          return;
        }
        try {
          const res = await request.get(`/api/exam/chapterList/${subjectId}`);
          chapters.value = res.data.map(item => ({
            value: item.id,
            label: item.name,
          }));
          console.log('所有章节数据:', chapters.value);
        } catch (err) {
          console.error('获取章节列表失败:', err);
        }
      };
  
      // 获取知识点列表
      const getKnowledgePointsList = async (chapterId) => {
        if (!chapterId) {
          knowledgePoints.value = []; // 清空知识点列表
          return;
        }
        try {
          const res = await request.get(`/api/exam/knowledgePointsList/${chapterId}`);
          knowledgePoints.value = res.data.map(item => ({
            value: item.id,
            label: item.name,
          }));
          console.log('所有知识点数据:', knowledgePoints.value);
        } catch (err) {
          console.error('获取知识点列表失败:', err);
        }
      };
  
      // 科目选择变化事件
      const handleSubjectChange = (subjectId) => {
        form.value.chapterId = ''; // 清空已选章节
        form.value.knowledgePointIds = []; // 清空已选知识点
        getChapterList(subjectId); // 获取章节列表
      };
  
      // 章节选择变化事件
      const handleChapterChange = (chapterId) => {
        form.value.knowledgePointIds = []; // 清空已选知识点
        getKnowledgePointsList(chapterId); // 获取知识点列表
      };
  
      // 提交表单
    const submitForm = () => {
      formRef.value.validate((valid) => {
        if (valid) {
          // 表单验证通过，提交数据到后端
          console.log('提交的数据:', form.value);
          // 这里可以调用 API 提交数据
          // 将知识点列表转为[id, id, id]格式字符串
          form.value.knowledgePointIdsStr ='['+form.value.knowledgePointIds.join(',')+']';
          request.post('/api/exam/createJudge', form.value).then(
            (res) => {
              if(res.code === 200) {
                ElMessage.success('创建成功！');
                // 重置表单
                form.value = {
                  subjectId: '', // 科目 ID
                  chapterId: '', // 章节 ID
                  knowledgePointIds: [], // 知识点 ID 列表
                  knowledgePointIdsStr: '', // 知识点 ID 列表字符串
                  question: '',
                  analysis: '',
                  correctAnswers: '', // 正确答案
                  level: '', // 难度
                };
                // 重置表单验证规则
                formRef.value.resetFields();
              }else{
                ElMessage.error('创建失败！');
              }
            },
            (err) => {
              ElMessage.error('创建失败！');
              console.error('创建失败:', err);
            }
          );
        } else {
          // 表单验证失败，提示用户
          ElMessage.warning('请填写完整信息！');
        }
      });
    };
  
      // 页面加载时获取科目列表
      onMounted(() => {
        getSubjectList();
      });
  
      return {
        form,
        subjects,
        chapters,
        knowledgePoints,
        rules,
        formRef,
        handleSubjectChange,
        handleChapterChange,
        submitForm,
      };
    },
  };
  </script>
  
  <style scoped>
  .judgment-create {
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(135deg, #f5f7fa, #e6f7ff);
    padding: 20px;
  }
  
  .form-card {
    width: 100%;
    max-width: 800px;
    border: none;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    background: #fff;
  }
  
  .card-header {
    display: flex;
    height: 20px;
    justify-content: space-between;
    align-items: center;
    padding: 16px 24px;
    background: linear-gradient(135deg, #91a4bd, #79bbff);
    border-radius: 12px 12px 0 0;
  }
  
  .card-title {
    font-size: 18px;
    font-weight: 580;
    color: #fff;
  }
  
  .el-form {
    padding: 24px;
  }
  
  .el-form-item {
    margin-bottom: 20px;
  }
  
  .el-input,
  .el-select {
    width: 100%;
  }
  
  .el-button--primary {
    background: linear-gradient(135deg, #409eff, #79bbff);
    border: none;
    border-radius: 8px;
    padding: 10px 24px;
    font-size: 14px;
    font-weight: 500;
    color: #fff;
    transition: all 0.3s ease;
  }
  
  .el-button--primary:hover {
    opacity: 0.9;
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(64, 158, 255, 0.3);
  }
  
  .el-button--primary:active {
    transform: translateY(0);
    box-shadow: none;
  }
  </style>