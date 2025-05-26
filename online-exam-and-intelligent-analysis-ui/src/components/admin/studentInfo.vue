<template>
    <div>
   
   <div style="margin: 20px 5px">
    <!--    新增按钮-->
   <div style="margin: 3px 10px;margin-bottom: 10px;float: left">
     <el-button type="primary" @click="handleAdd">新增</el-button>
   </div>
   <!--    新增弹窗-->
    <el-dialog v-model="dialogVisible" width="30%"  :title="title" :before-close="handleClose">
            <el-form :model="form" :rules="rules" ref="form" status-icon label-width="auto" style="max-width: 600px">
            <el-form-item label="姓名" prop="name">
            <el-input v-model="form.name" style="width:80%" placeholder="请输入姓名" v-on:keyup.enter="create"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" style="width:80%" placeholder="请输入密码" v-on:keyup.enter="create"/>
            </el-form-item>
            <el-form-item label="性别" prop="sex">
            <el-input v-model="form.sex" style="width:80%" placeholder="请输入性别" v-on:keyup.enter="create"/>
            </el-form-item>
            <el-form-item label="联系电话" prop="tel">
            <el-input v-model="form.tel" style="width:80%" placeholder="请输入联系电话" v-on:keyup.enter="create"/>
            </el-form-item>
            <el-form-item label="邮箱" prop="email">
            <el-input v-model="form.email" style="width:80%" placeholder="请输入邮箱" v-on:keyup.enter="create"/>
            </el-form-item>
            <el-form-item label="身份证号" prop="cardId">
            <el-input v-model="form.cardId" style="width:80%" placeholder="请输入身份证号" v-on:keyup.enter="create"/>
            </el-form-item>
           
            <el-form-item label="所属学院" prop="institutionId">
              <el-select 
            v-model="institutionName"
            clearable
            placeholder="选择所属学院"
            style="width: 215px;"
          >
            <el-option @click="selectInstitution(item.value,item.label)"
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
            </el-form-item>
            </el-form>
            <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="create">{{button}}</el-button>
            </div>
            </template>
           
        </el-dialog>  
   <!--    搜索框-->
    <div style="float: right;">
        <el-input v-model="keyword" style="width: 240px" placeholder="请输入关键字" v-on:keyup.enter="list"/>
        <el-button style="margin-left: 10px" type="primary" @click="list">检索</el-button>
    </div>
     
   </div>
   <div class="info">
    <!--    显示表格-->
        <el-table :data="tableData" height="440" stripe style="width: 100%" >
            <el-table-column prop="studentId" label="学号" sortable/>
            <el-table-column prop="name" label="姓名"/>
            <el-table-column prop="className" label="班级"/>
            <el-table-column prop="majorName" label="所属专业"/>
            <el-table-column prop="sex" label="性别"/>
            <el-table-column prop="tel" label="联系电话"/>
            <el-table-column prop="createTime" label="创建时间"/>
            <el-table-column prop="updateTime" label="更新时间"/>
            <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
                <el-button type="text" @click="handleEdit(scope.row)">详情</el-button>
                <el-popconfirm title="确认删除吗?" @confirm="del(scope.row.majorId)">
                <template #reference>
                  <el-button type="text" >删除</el-button>
                    
                </template>
                </el-popconfirm>
            </template>
            </el-table-column>
        </el-table>
    </div>

    <!--    分页查询-->
   <div style="margin: 10px 0">
     <el-pagination
         v-model:current-page="currentPage"
         v-model:page-size="pageSize"
         :page-sizes="[2,8,16]"
         layout="total,sizes,prev, pager, next, jumper"
         :total="total"
         @size-change="handleSizeChange"
         @current-change="handleCurrentChange"
     />
   </div>
    </div>
</template>
<script>
import request from "@/utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "institutionInfo",
  
  data() {
    return {
        options: [],
        title: "新增学生",
        institutionName: '',
        form: {
            eno: '',
            name: null,
            tel: '',
            sex: '',
            email: '',
            position: '',
            cardId: '',
            password: '',
            institutionId: null,
            classNum: ''
        },
        rules: {
            name: [
                { required: true, message: "请输入姓名", trigger: "blur" }
            ],
           
        },
        keyword: null,
        currentPage:1,// 当前页
        pageSize:8,// 每页显示记录数
        total:0,// 共多少条记录
        tableData: [],
        dialogVisible: false,
        button: '新增学生'
    }
  },
  created() {
    this.list()
  },
  methods: {
    list() {
        let current = this.currentPage
        let size = this.pageSize
        let keyword = this.keyword
        if(keyword === ''){
            keyword = null
        }
      request.get(`/api/exam/studentList/${current}/${size}/${keyword}`).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
        console.log("tableData",this.tableData)
      }).catch(err => {
        console.log(err)
      })
    },
    getInstitution() {
      request.get("/api/exam/institutionList").then(res => {
        console.log("所有学院数据",res.data)
        for(let i=0;i<res.data.length;i++){
          this.options.push({
            value: res.data[i].id,
            label: res.data[i].name
          })
        }
      }).catch(err => {
        console.log(err)
      })
    },
    selectInstitution(value,label) {
      this.form.institutionId = value
      this.institutionName = label
    },
    handleSizeChange(size) {
      this.pageSize = size
      this.list()
    },
    handleCurrentChange(page) {
      this.currentPage = page
      this.list()
    },

    handleEdit(row) {
      this.title = "教师详情"
      this.button = "更新"
      this.getInstitution()
      this.dialogVisible = true
      request.get("/api/exam/teacherInfo/" + row.eno).then(res => {
        console.log("teacherInfo",res.data)
        this.form = res.data
        if(res.data.position === '1'){
          this.form.position = '助教'
        }else if(res.data.position === '2'){
          this.form.position = '讲师'
        }else if(res.data.position === '3'){
          this.form.position = '副教授'
        }else if(res.data.position === '4'){
          this.form.position = '教授'
        }
      }).catch(err => {
        console.log(err)
      })
    },
    handleAdd() {
      this.title = "新增学生"
      this.form = {}
      this.getInstitution()
      this.dialogVisible = true
    },
    create(){
      // 表单验证是否通过
      this.$refs['form'].validate((valid) => {
        // valid就是表单校验后返回的结果
        if(!valid){
          this.$message({
            type:"error",
            message:"表单校验失败"
          })
          return false
        }else {
          this.save()
        }
      })
    },
    save(){
      if(this.form.position === '教授'){
        this.form.position = '4'
      }else if(this.form.position === '副教授'){
        this.form.position = '3'
      }else if(this.form.position === '讲师'){
        this.form.position = '2'
      }else if(this.form.position === '助教'){
        this.form.position = '1'
      }
      request.post('/api/exam/createTeacher', this.form).then(response => {
          if (response.code === 200) {
              this.$message.success('创建成功');
              this.dialogVisible = false;
              this.list()
          }else if(response.code === 208){
              this.$message.error('该教师已存在');
          }else {
              this.$message.error('创建失败');
          }
            
      }).catch(error => {
          console.log(error);
      });
    },
    // getTeacherInfo(row) {
    //   console.log("teacherInfo",row)
    //   request.get("/api/exam/teacherInfo/" + row.eno).then(res => {
    //     console.log("teacherInfo",res.data)
    //     this.teacherInfoData = res.data
    //     this.openTeacherInfo = true
    //   }).catch(err => {
    //     console.log(err)
    //   })
    // },
    del(id) {
        // 如果id不是数字，则提示错误
        if (isNaN(id)) {
          ElMessage({
            message: "请选择要删除的教师ID",
            type: "error"
          })
          return
        }
      request.delete("/api/exam/deleteTeacher/" + id).then(res => {
        if (res.code === 200) {
          ElMessage({
            message: "删除成功",
            type: "success"
          })
          this.list()
        } else {
          ElMessage({
            message: res.msg,
            type: "error"
          })
        }
      })
    }
  }
}
</script>
<style scoped>
.myClass{
  font-size: 16px;
  font-weight: bold;
  font-family: '楷体';
  margin-left: 10px;
  float: left;
}
.info{
  
  margin-top: 60px;
  margin-right: 30px;
  
}
</style>