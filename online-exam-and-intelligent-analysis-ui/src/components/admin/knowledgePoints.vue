<template>
    <div>
      <div style="margin: 20px 5px">
        <!-- 新增按钮 -->
        <div style="margin: 3px 10px; margin-bottom: 10px; float: left">
          <el-button type="primary" @click="handleAdd">新增</el-button>
        </div>
  
        <!-- 新增弹窗 -->
        <el-dialog v-model="dialogVisible" width="30%" :title="title" :before-close="handleClose">
          <el-form :model="form" :rules="rules" ref="form" status-icon label-width="auto" style="max-width: 600px">
            <el-form-item label="知识点名称" prop="name">
              <el-input v-model="form.name" style="width: 80%" placeholder="请输入知识点名称" v-on:keyup.enter="create" />
            </el-form-item>
            <el-select
              v-model="chapterName"
              clearable
              placeholder="选择所属章节"
              style="width: 220px; margin: 10px"
            >
              <el-option
                @click="selectChapter(item.value, item.label)"
                v-for="item in chapterOptions"
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
  
        <!-- 搜索框 -->
        <div style="float: right">
          <el-input v-model="keyword" style="width: 240px" placeholder="请输入关键字" v-on:keyup.enter="list" />
          <el-button style="margin-left: 10px" type="primary" @click="list">检索</el-button>
        </div>
      </div>
  
      <div class="info">
        <!-- 显示表格 -->
        <el-table :data="tableData" height="440" stripe style="width: 100%">
          <el-table-column prop="knowledgeId" label="知识点编号" sortable />
          <el-table-column prop="knowledgeName" label="知识点名称" />
          <el-table-column prop="chapterName" label="所属章节" />
          <el-table-column prop="questionCount" label="试题数量" />
          <el-table-column prop="createTime" label="创建时间" />
          <el-table-column prop="updateTime" label="更新时间" />
  
          <el-table-column fixed="right" label="操作" width="120">
            <template #default="scope">
              <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
              <el-popconfirm title="确认删除吗?" @confirm="del(scope.row.knowledgePointId)">
                <template #reference>
                  <el-button type="text">删除</el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>
  
      <!-- 分页查询 -->
      <div style="margin: 10px 0">
        <el-pagination
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          :page-sizes="[2, 8, 16]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </div>
  </template>
  
  <script>
  import request from "@/utils/request";
  import { ElMessage } from "element-plus";
  
  export default {
    name: "KnowledgePointInfo",
    data() {
      return {
        chapterOptions: [], // 章节选项
        title: "新增知识点",
        chapterName: "", // 所属章节名称
        form: {
          name: "", // 知识点名称
          chapterId: "", // 所属章节 ID
        },
        rules: {
          name: [{ required: true, message: "请输入知识点名称", trigger: "blur" }],
          chapterId: [{ required: true, message: "请选择所属章节", trigger: "blur" }],
        },
        keyword: null, // 搜索关键字
        currentPage: 1, // 当前页
        pageSize: 8, // 每页显示记录数
        total: 0, // 总记录数
        tableData: [], // 表格数据
        dialogVisible: false, // 弹窗显示状态
      };
    },
    created() {
      this.list(); // 初始化加载数据
    },
    methods: {
      // 获取知识点列表
      list() {
        let current = this.currentPage;
        let size = this.pageSize;
        let keyword = this.keyword;
        if (keyword === "") {
          keyword = null;
        }
        request
          .get(`/api/exam/knowledgePointsList/${current}/${size}/${keyword}`)
          .then((res) => {
            console.log("知识点数据:", res.data.records);
            this.tableData = res.data.records;
            this.total = res.data.total;
          })
          .catch((err) => {
            console.log(err);
          });
      },
  
      // 获取章节列表
      getChapterList() {
        request
          .get("/api/exam/chapterList")
          .then((res) => {
            console.log("所有章节数据", res.data);
            this.chapterOptions = res.data.map((item) => ({
              value: item.chapterId,
              label: item.chapterName,
            }));
          })
          .catch((err) => {
            console.log(err);
          });
      },
  
      // 选择章节
      selectChapter(value, label) {
        this.form.chapterId = value;
        this.chapterName = label;
      },
  
      // 分页大小变化
      handleSizeChange(size) {
        this.pageSize = size;
        this.list();
      },
  
      // 当前页变化
      handleCurrentChange(page) {
        this.currentPage = page;
        this.list();
      },
  
      // 编辑知识点
      handleEdit(row) {
        this.$router.push({
          path: "/editKnowledgePoint",
          query: {
            id: row.knowledgePointId,
          },
        });
      },
  
      // 打开新增弹窗
      handleAdd() {
        this.title = "新增知识点";
        this.form.name = "";
        this.chapterName = "";
        this.getChapterList(); // 加载章节列表
        this.dialogVisible = true;
      },
  
      // 创建知识点
      create() {
        request
          .post("/api/exam/createKnowledgePoint", this.form)
          .then((response) => {
            if (response.code === 200) {
              ElMessage.success("创建成功");
              this.dialogVisible = false;
              this.list(); // 刷新列表
            } else if (response.code === 208) {
              ElMessage.error("该知识点已存在");
            } else {
              ElMessage.error("创建失败");
            }
          })
          .catch((error) => {
            console.log(error);
          });
      },
  
      // 删除知识点
      del(id) {
        if (isNaN(id)) {
          ElMessage({
            message: "请选择要删除的知识点",
            type: "error",
          });
          return;
        }
        request
          .delete("/api/exam/deleteKnowledgePoint/" + id)
          .then((res) => {
            if (res.code === 200) {
              ElMessage({
                message: "删除成功",
                type: "success",
              });
              this.list(); // 刷新列表
            } else {
              ElMessage({
                message: res.msg,
                type: "error",
              });
            }
          })
          .catch((err) => {
            console.log(err);
          });
      },
    },
  };
  </script>
  
  <style scoped>
  .info {
    margin-top: 60px;
    margin-right: 30px;
  }
  </style>