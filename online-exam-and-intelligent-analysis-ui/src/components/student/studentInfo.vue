<template>
  <div class="profile-container">
    <!-- 选项卡 -->
    <el-tabs v-model="activeTab" type="card" @tab-click="handleTabClick">
      <el-tab-pane label="基本信息" name="basicInfo"></el-tab-pane>
      <el-tab-pane label="修改密码" name="changePassword"></el-tab-pane>
    </el-tabs>

    <!-- 基本信息 -->
    <el-card v-if="activeTab === 'basicInfo'" class="profile-card">
      <template #header>
        <div class="card-header">
          <span class="card-title">基本信息</span>
          <el-button type="primary" @click="toggleEditMode">
            {{ isEditMode ? '取消' : '编辑' }}
          </el-button>
        </div>
      </template>

      <!-- 基本信息表单 -->
      <el-form :model="studentInfo" label-width="120px">
        <!-- 不可编辑的字段 -->
        <el-form-item label="用户ID">
          <el-input v-model="studentInfo.userId" disabled></el-input>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="studentInfo.studentId" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="studentInfo.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="studentInfo.sex" disabled>
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="学院">
          <el-input v-model="studentInfo.instituteName" disabled></el-input>
        </el-form-item>
        <el-form-item label="专业">
          <el-input v-model="studentInfo.majorName" disabled></el-input>
        </el-form-item>
        <el-form-item label="班级">
          <el-input v-model="studentInfo.className" disabled></el-input>
        </el-form-item>

        <!-- 可编辑的字段 -->
        <el-form-item label="电话">
          <el-input v-model="studentInfo.tel" :disabled="!isEditMode"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="studentInfo.email" :disabled="!isEditMode"></el-input>
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="studentInfo.cardId" :disabled="!isEditMode"></el-input>
        </el-form-item>
      </el-form>

      <!-- 保存按钮 -->
      <div v-if="isEditMode" class="save-button">
        <el-button type="primary" @click="saveProfile">保存</el-button>
      </div>
    </el-card>

    <!-- 修改密码 -->
    <el-card v-if="activeTab === 'changePassword'" class="profile-card password-card">
      <template #header>
        <div class="card-header">
          <span class="card-title">修改密码</span>
        </div>
      </template>

      <!-- 修改密码表单 -->
      <el-form :model="passwordForm" :rules="passwordRules" ref="passwordFormRef" label-width="120px">
        <el-form-item label="旧密码" prop="oldPassword">
          <el-input v-model="passwordForm.oldPassword" type="password" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="passwordForm.newPassword" type="password" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input v-model="passwordForm.confirmPassword" type="password" show-password></el-input>
        </el-form-item>
      </el-form>

      <!-- 修改密码按钮 -->
      <div class="save-button">
        <el-button type="primary" @click="changePassword">修改密码</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { onMounted } from 'vue';
import request from '@/utils/request';

export default {
  setup() {
    // 当前激活的选项卡
    const activeTab = ref('basicInfo');

    // 个人信息数据
    const studentInfo = ref({
      userId: 123456,
      studentId: '20230001',
      name: '张三',
      sex: 1, // 1: 男, 0: 女
      tel: '13800138000',
      email: 'zhangsan@example.com',
      cardId: '123456789012345678',
      instituteName: '计算机学院',
      majorName: '软件工程',
      className: '2023级1班',
    });

    // 编辑模式
    const isEditMode = ref(false);

    // 切换编辑模式
    const toggleEditMode = () => {
      isEditMode.value = !isEditMode.value;
    };

    // 得到个人信息
    const getProfile = () => {
      studentInfo.value.studentId = localStorage.getItem('studentId');
      console.log(studentInfo.value.studentId);
				request.get(`/api/exam/studentInfo/${studentInfo.value.studentId}`).then(res => {
					if(res.code === 200){
            console.log(res.data);
						studentInfo.value = res.data;
						if(studentInfo.value.sex === '0'){
							studentInfo.value.sex = 0;
						}else if(studentInfo.value.sex === '1'){
							studentInfo.value.sex = 1;
						}
          }
				}).catch(err => {
					console.log(err);
				})
    }
    onMounted(() => {
      getProfile();
    });

    // 保存个人信息
    const saveProfile = () => {
      // 这里可以调用 API 保存数据
      request.put('/api/exam/updateInfo', {"id":studentInfo.value.userId,"tel":studentInfo.value.tel,"email":studentInfo.value.email,"cardId":studentInfo.value.cardId}).then(res => {
		      if(res.code === 200){
		        // this.$message({
		        //   message: '更新成功',
		        //   type: 'success'
		        // })
            ElMessage.success('保存成功！');
            isEditMode.value = false; // 退出编辑模式
		      }else{
		        // this.$message({
		        //   message: '更新失败',
		        //   type: 'error'
		        // })
            ElMessage.error('保存失败！');
		      }
		    })
      
      
    };

    // 修改密码表单数据
    const passwordForm = ref({
      oldPassword: '',
      newPassword: '',
      confirmPassword: '',
    });

    // 修改密码表单验证规则
    const passwordRules = {
      oldPassword: [
        { required: true, message: '请输入旧密码', trigger: 'blur' },
      ],
      newPassword: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        { min: 6, message: '密码长度至少为 6 位', trigger: 'blur' },
      ],
      confirmPassword: [
        { required: true, message: '请确认新密码', trigger: 'blur' },
        {
          validator: (rule, value, callback) => {
            if (value !== passwordForm.value.newPassword) {
              callback(new Error('两次输入的密码不一致'));
            } else {
              callback();
            }
          },
          trigger: 'blur',
        },
      ],
    };

    // 修改密码
    const changePassword = () => {
      // 这里可以调用 API 修改密码
      ElMessage.success('密码修改成功！');
      passwordForm.value = {
        oldPassword: '',
        newPassword: '',
        confirmPassword: '',
      };
    };

    // 选项卡切换事件
    const handleTabClick = (tab) => {
      console.log('切换到选项卡:', tab.props.name);
    };

    return {
      activeTab,
      studentInfo,
      isEditMode,
      toggleEditMode,
      saveProfile,
      passwordForm,
      passwordRules,
      changePassword,
      handleTabClick,
    };
  },
};
</script>

<style scoped>
.profile-container {
  max-width: 1200px;
  margin: 0px auto;
  padding: 20px;
  background: #f5f7fa;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.profile-card {
  border: none;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  margin-top: 20px;
}

.password-card {
  margin-top: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 24px;
  background: linear-gradient(135deg, #91a4bd, #79bbff);
  border-radius: 12px 12px 0 0;
}

.card-title {
  font-size: 20px;
  font-weight: 600;
  color: #fff;
}

.el-form {
  padding: 24px;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-input,
.el-radio-group {
  width: 100%;
}

.save-button {
  text-align: right;
  padding: 16px 24px;
  border-top: 1px solid #ebeef5;
  background: #fafafa;
  border-radius: 0 0 12px 12px;
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

/* 响应式布局 */
@media (max-width: 768px) {
  .profile-container {
    padding: 10px;
  }

  .el-form {
    padding: 16px;
  }

  .card-header {
    padding: 12px 16px;
  }

  .card-title {
    font-size: 18px;
  }

  .el-form-item {
    margin-bottom: 16px;
  }

  .el-button--primary {
    padding: 8px 16px;
    font-size: 12px;
  }
}
</style>