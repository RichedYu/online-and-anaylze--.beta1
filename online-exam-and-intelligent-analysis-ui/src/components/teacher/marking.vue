<template>
    <div>
   
   <div style="margin: 20px 5px">
   <el-select 
            v-model="subjectName"
            clearable
            placeholder="选择试卷"
            style="width: 180px;margin-bottom: 5px;float: left;"
          >
            <el-option @click="selectSubject(item.value,item.label)"
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
          <el-select 
            v-model="className"
            clearable
            placeholder="选择班级"
            style="width: 180px;margin-bottom: 5px;margin-left: 10px;float: left;"
          >
            <el-option @click="selectChapter(item.value,item.label)"
              v-for="item in options2"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
  
   <!--    搜索框-->
    <div style="float: right;">
        <el-input v-model="keyword" style="width: 240px" placeholder="请输入关键字" v-on:keyup.enter="list"/>
        <el-button style="margin-left: 10px" type="primary" @click="list">检索</el-button>
    </div>
     
   </div>
   <div class="info">
    <!--    显示表格-->
        <el-table :data="tableData" height="440" stripe style="width: 100%">
            <el-table-column prop="scoreId" label="ID" sortable/>
            <el-table-column prop="studentName" label="学生姓名"/>
            <el-table-column prop="paperName" label="试卷"/>
            <el-table-column prop="score" label="得分"/>
            <el-table-column prop="answerDate" label="答题时间"/>
            <el-table-column prop="answerTime" label="用时"/>
            <el-table-column prop="reviewStatus" label="状态" sortable/>
            

            <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
                <el-button type="text" @click="handleEdit(scope.row)">批阅</el-button>
                <el-popconfirm title="确认删除吗?" @confirm="del(scope.row.subjectId)">
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
  name: "marking",
  
  data() {
    return {
        options: [],
        options2: [],
        title: "新增试题",
        paperId: 0,
        paperName: '',
        classId: 0,
        className: '',
        form: {
            name: ''
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
    this.getSubjuectList()
    this.getClassList()
  },
  methods: {
    list() {
        let current = this.currentPage
        let size = this.pageSize
        let keyword = this.keyword
        if(keyword === ''){
            keyword = null
        }
      request.get(`/api/exam/reviewList/${current}/${size}/${keyword}/${this.paperId}/${this.classId}`).then(res => {
        console.log("tableData:"+res.data.records)
        this.tableData = res.data.records
        for(let i=0;i<this.tableData.length;i++){
            if(this.tableData[i].reviewStatus === '0'){
                this.tableData[i].reviewStatus = "待批阅"
            }else if(this.tableData[i].reviewStatus === '1'){
                this.tableData[i].reviewStatus = "已批阅"
            }
        }
        this.total = res.data.total
      }).catch(err => {
        console.log(err)
      })
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
        path: "/markingView",
        query: {
          id: row.scoreId,
          examCode: row.paperId,
          subject: row.subjectName
        }
      })
    },
   
    getSubjuectList() {
      request.get("/api/exam/subjectList").then(res => {
        console.log("所有科目数据",res.data)
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
    getClassList() {
      request.get("/api/exam/getAllClass").then(res => {
          console.log("所有班级数据",res.data)
          for(let i=0;i<res.data.length;i++){
          this.options2.push({
            value: res.data[i].id,
            label: res.data[i].name
          })
          console.log(this.options2)
        }
        }).catch(err => {
          console.log(err)
        })
    },
    selectSubject(value,label) {
      this.subjectId = value
      this.subjectName = label
      this.list()
      this.chapterName = ''
      this.options2 = []
      this.getChapterList()
      console.log("选择的科目",value,label)
    },
    getChapterList() {
      request.get(`/api/exam/chapterList/${this.subjectId}`).then(res => {
        console.log("所有章节数据",res.data)
        for(let i=0;i<res.data.length;i++){
          this.options2.push({
            value: res.data[i].id,
            label: res.data[i].name
          })
        }
        }).catch(err => {
          console.log(err)
        })
    },
    selectChapter(value,label) {
      this.classId = value
      this.className = label
      this.list()
      console.log("选择的班级",value,label)
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
      request.post('/api/exam/createSubject', this.form).then(response => {
          if (response.code === 200) {
              this.$message.success('创建成功');
              this.dialogVisible = false;
              this.list()
          }else if(response.code === 208){
              this.$message.error('该课程已存在');
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
      request.delete("/api/exam/deleteSubject/" + id).then(res => {
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