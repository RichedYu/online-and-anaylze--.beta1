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
            <el-form-item label="专业名称" prop="name"><br>
            <el-input v-model="form.name" style="width:80%" placeholder="请输入专业名称" v-on:keyup.enter="create"/>
            </el-form-item>
            <el-select 
            v-model="institutionName"
            clearable
            placeholder="选择所属学院"
            style="width: 220px;margin: 10px;"
          >
            <el-option @click="selectInstitution(item.value,item.label)"
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
            </el-form>
            <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="create">创建</el-button>
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
        <el-table :data="tableData" height="440" stripe style="width: 100%">
            <el-table-column prop="majorId" label="专业编号" sortable/>
            <el-table-column prop="majorName" label="专业名称"/>
            <el-table-column prop="institutionName" label="所属学院"/>
            <el-table-column prop="classCount" label="班级数量"/>
            <el-table-column prop="studentCount" label="学生人数"/>
            <el-table-column prop="createTime" label="创建时间"/>
            <el-table-column prop="updateTime" label="更新时间"/>

            <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
                <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
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
        title: "新增专业",
        institutionName: '',
        form: {
            name: '',
            institutionId: ''
        },
        rules: {
            institutionName: [
                { required: true, message: "请输入专业名称", trigger: "blur" }
            ],
            institutionId: [
                { required: true, message: "请选择所属学院", trigger: "blur" }
            ]
        },
        keyword: null,
        currentPage:1,// 当前页
        pageSize:8,// 每页显示记录数
        total:0,// 共多少条记录
        tableData: [],
        dialogVisible: false
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
      request.get(`/api/exam/majorList/${current}/${size}/${keyword}`).then(res => {
        console.log("tableData:"+res.data.records[0].institutionId)
        this.tableData = res.data.records
        this.total = res.data.total
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
      this.$router.push({
        path: "/edit",
        query: {
          id: row.id
        }
      })
    },
    handleAdd() {
      this.title = "新增专业"
      this.form.name = ''
      this.institutionName = ''
      this.getInstitution()
      this.dialogVisible = true
    },
    create(){
      request.post('/api/exam/createMajor', this.form).then(response => {
          if (response.code === 200) {
              this.$message.success('创建成功');
              this.dialogVisible = false;
              this.list()
          }else if(response.code === 208){
              this.$message.error('该专业已存在');
          }else {
              this.$message.error('创建失败');
          }
            
      }).catch(error => {
          console.log(error);
      });
    },
    del(id) {
        // 如果id不是数字，则提示错误
        if (isNaN(id)) {
          ElMessage({
            message: "请选择要删除的专业",
            type: "error"
          })
          return
        }
      request.delete("/api/exam/deleteMajor/" + id).then(res => {
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