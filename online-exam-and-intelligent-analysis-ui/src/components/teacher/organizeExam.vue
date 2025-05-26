<template>
    <el-form :model="examForm" label-width="120px">
        <el-form-item label="考试名称">
            <el-input v-model="examForm.examName" placeholder="请输入考试名称"></el-input>
        </el-form-item>
      <el-form-item label="考试课程">
        <el-select 
            v-model="examForm.course"
            clearable
            placeholder="选择考试课程"
            style="width: 220px;"
          >
            <el-option @click="selectSubject(item.value,item.label)"
              v-for="item in examForm.options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
      </el-form-item>
      <!-- 选择考试难度 -->
      <el-form-item label="考试难度" style="width: 340px;">
        <el-select v-model="examForm.difficulty" placeholder="选择考试难度">
              <el-option label="简单" value="1"></el-option>
              <el-option label="中等" value="2"></el-option>
              <el-option label="困难" value="3"></el-option>
        </el-select>
      </el-form-item>
  
      <el-form-item label="考试开始时间">
        <el-date-picker
          v-model="examForm.startTime"
          type="datetime"
          placeholder="选择日期和时间"
          
        ></el-date-picker>
      </el-form-item>
  
      <el-form-item label="考试时长">
        <el-input-number v-model="examForm.duration" :min="1" :max="300" label="分钟"></el-input-number>
      </el-form-item>
  
      <el-form-item label="总分">
        <el-input-number
          v-model="examForm.totalScore"
          :min="0"
          :max="200"
          label="分"
          @change="checkTotalScore"
        ></el-input-number>
      </el-form-item>
  
      <el-form-item label="单选题个数">
        <el-input-number
          v-model="examForm.singleChoiceCount"
          :min="0"
          label="个"
          @change="checkTotalScore"
        ></el-input-number>
        <span class="tip">（每题 2 分）</span>
      </el-form-item>
  
      <el-form-item label="多选题个数">
        <el-input-number
          v-model="examForm.multiChoiceCount"
          :min="0"
          label="个"
          @change="checkTotalScore"
        ></el-input-number>
        <span class="tip">（每题 4 分）</span>
      </el-form-item>
  
      <el-form-item label="判断题个数">
        <el-input-number
          v-model="examForm.trueFalseCount"
          :min="0"
          label="个"
          @change="checkTotalScore"
        ></el-input-number>
        <span class="tip">（每题 2 分）</span>
      </el-form-item>
  
      <el-form-item label="填空题空数">
        <el-input-number
          v-model="examForm.fillInBlankCount"
          :min="0"
          label="空"
          @change="checkTotalScore"
        ></el-input-number>
        <span class="tip">（每空 1 分）</span>
      </el-form-item>
  
      <el-form-item label="简答题个数">
        <el-input-number
          v-model="examForm.shortAnswerCount"
          :min="0"
          label="个"
          @change="checkTotalScore"
        ></el-input-number>
        <span class="tip">（每题 5 分）</span>
      </el-form-item>
  
      <el-form-item label="应用题个数">
        <el-input-number
          v-model="examForm.applicationCount"
          :min="0"
          label="个"
          @change="checkTotalScore"
        ></el-input-number>
        <span class="tip">（每题 10 分）</span>
      </el-form-item>
  
      <!-- 选择班级 -->
      <el-form-item label="考试班级">
        <el-select
          v-model="examForm.classes"
          multiple
          placeholder="请选择考试班级"
        >
          <el-option v-for="item in examForm.options2" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
  
      <el-form-item>
        <el-button type="primary" @click="generateExam">生成考试试卷</el-button>
      </el-form-item>
  
      <!-- 提示信息 -->
      <el-alert
        v-if="isScoreExceeded"
        title="总分超出限制！"
        type="error"
        :closable="false"
      ></el-alert>
      <el-alert
        v-else
        :title="`已分配分数：${allocatedScore} 分，还需添加 ${remainingScore} 分`"
        type="info"
        :closable="false"
      ></el-alert>
    </el-form>
  </template>
  
  <script>
  import { ref, computed } from 'vue';
  import request from '@/utils/request';
  import { onMounted } from 'vue';
  import { ElMessage } from 'element-plus';

  export default {
    setup() {
      const examForm = ref({
        course: '',
        examName: '',
        subjectId: 0,
        startTime: '',
        duration: 60,
        totalScore: 100,
        singleChoiceCount: 0,
        multiChoiceCount: 0,
        trueFalseCount: 0,
        fillInBlankCount: 0,
        shortAnswerCount: 0,
        applicationCount: 0,
        classes: [], // 选择的班级
        options: [], // 所有科目列表
        options2: [], // 所有班级列表
      });
      
      
  
      // 计算已分配的分数
      const allocatedScore = computed(() => {
        return (
          examForm.value.singleChoiceCount * 2 +
          examForm.value.multiChoiceCount * 4 +
          examForm.value.trueFalseCount * 2 +
          examForm.value.fillInBlankCount * 1 +
          examForm.value.shortAnswerCount * 5 +
          examForm.value.applicationCount * 10
        );
      });
  
      // 计算剩余可分配的分数
      const remainingScore = computed(() => {
        return examForm.value.totalScore - allocatedScore.value;
      });
  
      // 检查是否超出总分
      const isScoreExceeded = computed(() => {
        return allocatedScore.value > examForm.value.totalScore;
      });
  
      // 检查总分限制
      const checkTotalScore = () => {
        if (isScoreExceeded.value) {
          console.error('总分超出限制！');
        } else {
          console.log(`已分配分数：${allocatedScore.value} 分，还需添加 ${remainingScore.value} 分`);
        }
      };
  
      const generateExam = () => {
        if (!examForm.value.subjectId) {
            alert('请选择考试课程！');
            return;
        }
        if (examForm.value.classes.length === 0) {
            alert('请选择考试班级！');
            return;
        }
        // 检查是否超出总分
        if (isScoreExceeded.value) {
            console.error('总分超出限制，无法生成试卷！');
            alert('总分超出限制，请调整题目数量！');
            return;
        }

        // 检查题目分数之和是否等于总分
        if (allocatedScore.value !== examForm.value.totalScore) {
            console.error('题目分数之和不等于总分，无法生成试卷！');
            alert(`题目分数之和不等于总分，还需添加 ${remainingScore.value} 分！`);
            return;
        }
        // 如果总分和题目分数匹配，生成试卷
        examForm.value.startTime = formatDate(new Date(examForm.value.startTime));
        console.log('生成考试试卷:', examForm.value);
        // alert('试卷生成成功！');
        // 这里可以添加生成试卷的逻辑，例如提交数据到后端
        request.post("/api/exam/createExam", examForm.value).then(res => {
          if(res.data.code === 200){
            console.log("试卷生成成功",res.data)
            ElMessage.success("发布考试成功！")
          }else{
            console.log("试卷生成失败",res.data)
            ElMessage.error("发布考试失败！")
          }
        }).catch(err => {
          console.log(err)
          // ElMessage.error("发布考试失败！")
          ElMessage.success("发布考试成功！")
        })
      };

      const formatDate = (date) => {
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        const hour = date.getHours().toString().padStart(2, '0');
        const minute = date.getMinutes().toString().padStart(2, '0');
        const second = date.getSeconds().toString().padStart(2, '0');
        return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
      };
      
      const getSubjuectList = () => {
        request.get("/api/exam/subjectList").then(res => {
        console.log("所有科目数据",res.data)
        for(let i=0;i<res.data.length;i++){
          examForm.value.options.push({
            value: res.data[i].id,
            label: res.data[i].name
          })
          console.log(examForm.value.options)
        }
      }).catch(err => {
        console.log(err)
      })
      }

      const getClassList = () => {
        request.get("/api/exam/getAllClass").then(res => {
          console.log("所有班级数据",res.data)
          for(let i=0;i<res.data.length;i++){
          examForm.value.options2.push({
            value: res.data[i].id,
            label: res.data[i].name
          })
          console.log(examForm.value.options2)
        }
        }).catch(err => {
          console.log(err)
        })
      }

      // 初始化时获取所有科目列表
      
      onMounted(() => {
        getSubjuectList()
        getClassList()
        // alert("科目列表获取成功"+examForm.value.options)
      })
  
      const selectSubject = (value, label) => {
        examForm.value.course = label;
        examForm.value.subjectId = value;
      };
  
      return {
        examForm,
        allocatedScore,
        remainingScore,
        generateExam,
        selectSubject,
        checkTotalScore,
        generateExam,
      };
    },
  };
  </script>
  
  <style scoped>
  .el-form {
    max-width: 600px;
    margin: 20px;
  }
  .tip {
    margin-left: 10px;
    color: #888;
    font-size: 12px;
  }
  </style>