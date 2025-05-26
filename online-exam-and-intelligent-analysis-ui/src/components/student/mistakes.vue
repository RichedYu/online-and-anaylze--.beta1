<template>
    <div class="error-book-container">
      <!-- 头部筛选 -->
      <el-header height="10px" style="margin: 10px 0px; text-align: left; padding: 5px 5px">
        <el-row>
          <span style="padding: 10px 5px; width: 80px">考试科目:</span>
          <div v-for="(subject, index) in options" :key="index">
            <el-col
              :class="{ selected: selectedIndex === index }"
              style="padding: 7px 7px; margin: 3px 8px; font-size: 13px"
              @click="selectSubject(subject.value, index)"
            >
              {{ subject.label }}
            </el-col>
          </div>
        </el-row>
      </el-header>
  
      <el-main>
        <!-- 错题列表 -->
        <el-card class="error-list-card">
          <el-table :data="paginatedErrorList" stripe v-loading="loading" style="height: 580px;">
            <!-- 题型 -->
            <el-table-column prop="type" label="题型" width="120">
              <template #default="{ row }">
                <el-tag :type="getTypeTagType(row.type)">
                  {{ row.type }}
                </el-tag>
              </template>
            </el-table-column>
  
            <!-- 得分 -->
            <el-table-column prop="score" label="分值" width="100"></el-table-column>
  
            <!-- 答题次数 -->
            <el-table-column prop="answerCount" label="答错次数" width="100"></el-table-column>
  
            <!-- 题目内容 -->
            <el-table-column prop="question" label="题目内容">
              <template #default="{ row }">
                <div v-if="isImageContent(row.question)">
                  <img
                    v-for="(img, index) in parseImageUrls(row.question)"
                    :key="index"
                    :src="img"
                    style="max-width: 100%; margin: 5px 0"
                  />
                </div>
                <div v-else v-html="row.question"></div>
              </template>
            </el-table-column>
  
            <!-- 答案 -->
            <el-table-column prop="answer" label="答案" width="150">
              <template #default="{ row }">
                <el-button type="text" @click="showAnswerDialog(row)">
                  查看答案
                </el-button>
              </template>
            </el-table-column>
  
            <!-- 解析 -->
            <el-table-column prop="analysis" label="解析">
              <template #default="{ row }">
                <el-button type="text" @click="showAnalysisDialog(row)">
                  查看解析
                </el-button>
              </template>
            </el-table-column>
          </el-table>
  
          <!-- 分页 -->
          <el-pagination
            class="pagination"
            :current-page="pagination.current"
            :page-size="pagination.size"
            :total="total"
            @current-change="handleCurrentChange"
            @size-change="handleSizeChange"
            layout="total, sizes, prev, pager, next, jumper"
          ></el-pagination>
        </el-card>
      </el-main>
  
      <!-- 答案对话框 -->
      <el-dialog v-model="answerDialogVisible" title="答案" width="50%">
        <div v-html="currentAnswer" style="padding: 20px;font-size: 16px;"></div>
      </el-dialog>
  
      <!-- 解析对话框 -->
      <el-dialog v-model="analysisDialogVisible" title="解析" width="50%">
        <div v-html="currentAnalysis" style="padding: 20px 20px;font-size: 16px;line-height: 1.5;text-align: left;"></div>
      </el-dialog>
    </div>
  </template>
  
  <script>
import { ref, computed, onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import request from '@/utils/request';

export default {
  setup() {
    // 考试科目选项
    const options = ref([]);

    // 当前选中的科目索引
    const selectedIndex = ref(0);

    // 当前选中的科目
    const selectedSubject = ref('');

    // 分页配置
    const pagination = ref({
      current: 1, // 当前页码
      size: 10, // 每页显示条数
    });

    // 总条数
    const total = ref(0);

    // 错题列表
    const errorList = ref([]);

    // 加载状态
    const loading = ref(false);

    // 答案对话框的显示状态
    const answerDialogVisible = ref(false);

    // 解析对话框的显示状态
    const analysisDialogVisible = ref(false);

    // 当前答案内容
    const currentAnswer = ref('');

    // 当前解析内容
    const currentAnalysis = ref('');

    // 获取科目数据
    const getSubjects = async () => {
      try {
        const studentId = localStorage.getItem('studentId'); // 从本地存储获取学生 ID
        const response = await request.get(`/api/analysis/getSubjects/${studentId}`);
        options.value = response.data.map((subject) => ({
          label: subject.label, // 假设后端返回的科目数据中有 name 字段
          value: subject.value, // 假设后端返回的科目数据中有 id 字段
        }));
        if (options.value.length > 0) {
          selectedSubject.value = options.value[0].value; // 默认选中第一个科目
          fetchErrorList(); // 获取第一个科目的错题数据
        }
      } catch (error) {
        ElMessage.error('获取科目数据失败，请稍后重试');
        console.error(error);
      }
    };

    // 获取错题数据
    const fetchErrorList = async () => {
      try {
        loading.value = true;
        const studentId = localStorage.getItem('studentId'); // 从本地存储获取学生 ID
        const subjectId = selectedSubject.value; // 当前选中的科目 ID
        const { current, size } = pagination.value;

        const response = await request.get('/api/exam/getWrongQuestion', {
          params: {
            studentId,
            subjectId,
            page: current,
            size,
          },
        });

        errorList.value = response.data.records; // 错题列表
        total.value = response.data.total; // 总条数
      } catch (error) {
        ElMessage.error('获取错题数据失败，请稍后重试');
        console.error(error);
      } finally {
        loading.value = false;
      }
    };

    // 分页后的错题列表
    const paginatedErrorList = computed(() => {
      return errorList.value;
    });

    // 选择科目
    const selectSubject = (value, index) => {
      selectedSubject.value = value;
      selectedIndex.value = index;
      pagination.value.current = 1; // 切换科目后重置页码
      fetchErrorList(); // 重新获取数据
    };

    // 获取题型标签的类型
    const getTypeTagType = (type) => {
      switch (type) {
        case '单选题':
          return 'primary';
        case '多选题':
          return 'success';
        case '判断题':
          return 'warning';
        case '填空题':
          return 'info';
        case '简答题':
          return 'danger';
        case '应用题':
          return 'gray';
        default:
          return '';
      }
    };

    // 处理页码变化
    const handleCurrentChange = (current) => {
      pagination.value.current = current;
      fetchErrorList(); // 重新获取数据
    };

    // 处理每页条数变化
    const handleSizeChange = (size) => {
      pagination.value.size = size;
      pagination.value.current = 1; // 切换每页条数后重置页码
      fetchErrorList(); // 重新获取数据
    };

    // 判断内容是否为图片链接
    const isImageContent = (content) => {
      return content.startsWith('[') && content.endsWith(']');
    };

    // 解析图片链接
    const parseImageUrls = (content) => {
      if (isImageContent(content)) {
        return content.slice(1, -1).split(',');
      }
      return [];
    };

    // 显示答案对话框
    const showAnswerDialog = (row) => {
      currentAnswer.value = row.answer;
      answerDialogVisible.value = true;
    };

    // 显示解析对话框
    const showAnalysisDialog = (row) => {
      currentAnalysis.value = row.analysis;
      analysisDialogVisible.value = true;
    };

    // 初始化加载数据
    onMounted(() => {
      getSubjects(); // 获取科目数据
    });

    return {
      options,
      selectedIndex,
      selectedSubject,
      errorList,
      paginatedErrorList,
      pagination,
      total,
      loading,
      selectSubject,
      getTypeTagType,
      handleCurrentChange,
      handleSizeChange,
      isImageContent,
      parseImageUrls,
      showAnswerDialog,
      showAnalysisDialog,
      answerDialogVisible,
      analysisDialogVisible,
      currentAnswer,
      currentAnalysis,
    };
  },
};
</script>
  
  <style scoped>
  .error-book-container {
    padding: 20px;
    background: #f5f7fa;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }
  
  .error-list-card {
    margin-top: 20px;
    border: none;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  }
  
  .selected {
    background-color: #409eff;
    color: #fff;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .el-table {
    width: 100%;
  }
  
  .el-tag {
    font-size: 12px;
  }
  
  .pagination {
    margin-top: 20px;
    text-align: right;
  }
  </style>