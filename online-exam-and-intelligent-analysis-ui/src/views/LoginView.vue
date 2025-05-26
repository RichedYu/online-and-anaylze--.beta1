<template>
    <div class="login-container">
      <!-- 登录标题 -->
      <h2 class="login-title">在线考试及智能分析系统</h2>
      
      <!-- 登录卡片 -->
      <el-card class="login-card" :body-style="{ padding: '20px' }">
        <!-- 登录表单 -->
        <el-form :model="form" ref="form" label-width="100px" @submit.prevent="handleLogin">
          
          <!-- 登录方式选择 -->
          <el-radio-group v-model="loginMethod" @change="handleLoginMethodChange">
            <el-radio label="account">账号密码登录</el-radio>
            <el-radio label="phone">验证码登录</el-radio>
          </el-radio-group>
  
          <!-- 账号密码登录 -->
          <div v-if="loginMethod === 'account'">
            <el-form-item label="账号" :rules="[{ required: true, message: '请输入账号', trigger: 'blur' }]">
              <el-input v-on:keyup.enter="handleLogin" v-model="form.username" placeholder="请输入账号" class="input"/>
            </el-form-item>
  
            <el-form-item label="密码" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
              <el-input v-on:keyup.enter="handleLogin" v-model="form.password" type="password" placeholder="请输入密码" class="input"/>
            </el-form-item>
          </div>
  
          <!-- 手机号验证码登录 -->
          <div v-if="loginMethod === 'phone'">
            <el-form-item label="手机号" :rules="[{ required: true, message: '请输入手机号', trigger: 'blur' }]">
              <el-input v-on:keyup.enter="handleLogin" v-model="form.phone" placeholder="请输入手机号" maxlength="11" show-word-limit class="input" />
            </el-form-item>
  
            <el-form-item label="验证码" :rules="[{ required: true, message: '请输入验证码', trigger: 'blur' }]">
              <el-input v-on:keyup.enter="handleLogin" v-model="form.verificationCode" placeholder="请输入验证码" class="input-yzm"/>
              <el-button @click="sendVerificationCode" size="small" class="verification-code-button">
                获取验证码
              </el-button>
            </el-form-item>
          </div>
  
          <!-- 身份选择 -->
          <el-form-item label="身份" :rules="[{ required: true, message: '请选择身份', trigger: 'change' }]">
            <el-radio-group v-model="form.role">
              <el-radio v-on:keyup.enter="handleLogin" label="0">管理员</el-radio>
              <el-radio v-on:keyup.enter="handleLogin" label="1">教师</el-radio>
              <el-radio v-on:keyup.enter="handleLogin" label="2">学生</el-radio>
            </el-radio-group>
          </el-form-item>
  
          <!-- 登录按钮 -->
          <el-form-item>
            <div class="button-container">
            <el-button type="primary" block :loading="isLoading" :disabled="isLoading" @click="handleLogin">
              登 录
            </el-button>
          </div>
          </el-form-item>
        </el-form>
  
        <!-- 忘记密码链接 -->
        <div class="forgot-password">
          <el-link type="primary" @click="handleForgetPassword">忘记密码？</el-link>
        </div>
        <div class="error">{{ loginError }}</div>
      
      </el-card>
    </div>
  </template>
  
  <script>
import request from '@/utils/request';

    export default {
      data() {
        return {
          form: {
            username: '',
            password: '',
            phone: '',
            verificationCode: '',
            role: '2', // 默认选择学生  
          },
          loginMethod: 'account', // 当前选择的登录方式，默认为账号密码登录
          isLoading: false,
          loginError: '',
        };
      },
      methods: {
        // 处理登录
        handleLogin() {
          this.isLoading = true;
          this.loginError = ''; // 清除之前的错误信息
          try {
            if (this.loginMethod === 'account') {
                this.accountLogin();
            } else if (this.loginMethod === 'phone') {
                this.phoneLogin();
            }
          } catch (error) {
            console.error(error);
          }
          
        },

        // 账号登录 API 函数
        accountLogin() {
          request.post('/api/exam/login/byAccount', {
            account: this.form.username,
            pwd: this.form.password,
            role: this.form.role
          }).then((response) => {
            console.log(response);
            if (response.code === 200) {
              // 登录成功，保存用户信息到 localStorage
              localStorage.setItem('token', response.data.jwt);
              localStorage.setItem('userInfo', JSON.stringify(response.data.userInfo));
              switch (response.data.userInfo.role) {
                case '0':
                  // 跳转到管理员首页
                  this.$router.push({path:'/admin',query:{username: response.data.userInfo.name}});
                  break;
                case '1':
                  // 跳转到教师首页
                  localStorage.setItem('eno', response.data.eno);
                  this.$router.push({path:'/teacher',query:{username: response.data.userInfo.name}});
                  break;
                case '2':
                  // 跳转到学生首页
                  localStorage.setItem('studentId', response.data.studentId);
                  localStorage.setItem('studentName', response.data.userInfo.name)
                  this.$router.push({path:'/student',query:{username: response.data.userInfo.name}});
              }
              
            } else{
              this.loginError = response.message;
            }
            this.isLoading = false;
          }).catch((error) => {
            this.loginError = '登录失败，请稍后再试';
            this.isLoading = false;
          })
          ;
        },

        // 手机号登录 API 函数
        phoneLogin() {
          if (!this.form.phone) {
            this.loginError = '请输入手机号';
            return;
          }
          if (!this.form.verificationCode) {
            this.loginError = '请输入验证码';
            return;
          }
          // 发送登录请求
          request.post('/api/exam/login/byVerify', {
            "tel": this.form.phone,
            "verifyCode": this.form.verificationCode,
            "role": this.form.role
          }).then((response) => {
              if (response.code === 200) {
                // 登录成功，保存用户信息到 localStorage
                localStorage.setItem('token', response.data.jwt);
                localStorage.setItem('userInfo', JSON.stringify(response.data.userInfo));
                switch (response.data.userInfo.role) {
                  case '0':
                    // 跳转到管理员首页
                    this.$router.push('/admin');
                    break;
                  case '1':
                    // 跳转到教师首页
                    localStorage.setItem('eno', response.data.eno);
                    this.$router.push('/teacher');
                    break;
                  case '2':
                    // 跳转到学生首页
                    localStorage.setItem('studentId', response.data.studentId);
                    this.$router.push('/student');
                }

              } else {
                this.loginError = response.message;
              }
              this.isLoading = false;
            }
          ).catch((error) => {
            this.loginError = '登录失败，请稍后再试';
            this.isLoading = false;
          });
        },


        // 发送验证码
        sendVerificationCode() {
          if (!this.form.phone) {
            this.loginError = '请输入手机号';
            return;
          }
          if(this.form.phone.length !== 11){
            this.loginError = '手机号格式不正确';
            return;
          }
          // 发送验证码的 API 请求
          request.post('/api/exam/login/getCode', {"tel": this.form.phone,"role": this.form.role}).then((response) =>{
            if (response.code === 200) {
              this.$message.success('验证码发送成功');
              this.sendVerificationCodeSuccess();
            } else {
              this.$message.error("验证码发送失败，请稍后再试");
              return;
            }
          }
          ).catch((error) => {
            this.loginError = '验证码发送失败，请稍后再试';
          })

        },
        // 发送验证码成功
        sendVerificationCodeSuccess() {
          let count = 60;
          const button = document.querySelector('.verification-code-button');
          button.setAttribute('disabled', 'true');
          let verificationCodeTimer = setInterval(() => {
            if (count === 0) {
              button.removeAttribute('disabled');
              button.innerText = '获取验证码';
              clearInterval(verificationCodeTimer);
            } else {
              button.innerText = `${count--}秒`;
            }
          }, 1000);
        },

        // 忘记密码操作
        handleForgetPassword() {
          alert('请通过手机号进行重置密码');
        },

        // 切换登录方式
        handleLoginMethodChange() {
          this.loginError = ''; // 切换登录方式时清空错误信息
        },
      },
    };
</script>
  
  <style scoped>

    .error {
      color: red;
      text-align: center;

    }
    .login-container {
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: url('@/assets/images/login_bg.png') no-repeat center center fixed; /* 背景图片 */
    background-size: cover; /* 自适应页面，覆盖整个容器 */
    background-position: center center; /* 保证背景图片居中 */
    background-attachment: fixed; /* 背景固定，随着滚动不会移动 */
    }
  
    .login-card {
        width: 420px;
        height: 300px;
        border-radius: 8px;
        box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
        background-color: rgba(255, 255, 255, 0.7); /* 半透明背景 */
    }
  
    .login-title {
    font-family: 'KaiTi', '楷体', 'serif';  /* 设置为楷体 */
    font-size: 28px;
    text-align: center;
    margin-bottom: 20px;
    color: white;  /* 字体颜色为白色 */
    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.7); /* 加上阴影效果 */
    }
  
  .forgot-password {
    text-align: center;
    margin-top: 10px;
  }
  
  .verification-code-button {
    margin-left: 10px;
    height: 32px;
    width: 75px;
  }
  
  .input{
    width: 235px;
  }
  .input-yzm{
    width: 150px;
  }

    .button-container { 
      text-align: center;
      margin-left: 39px;
      width: 100px;
    }
  </style>
  