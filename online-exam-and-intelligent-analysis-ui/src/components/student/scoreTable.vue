//显示学生成绩
<template>
  <div class="table">
    <!-- <p class="title">我的成绩</p> -->
    <section class="content-el">
      <el-table ref="filterTable" :data="score" v-loading="loading" @row-click="handleRowClick">
        <el-table-column
          prop="answerDate"
          label="考试日期"
          sortable
          width="330"
          :span="6"
          column-key="answerDate"
          :filters="filter"
          :filter-method="filterHandler">
        </el-table-column>
        <el-table-column class="subject"
          prop="subject"
          label="课程名称"
          width="330"
          :span="6"
          filter-placement="bottom-end">
          <template v-slot="scope">
            <el-tag>{{scope.row.subject}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="score" label="考试分数" width="180"></el-table-column>
        <el-table-column label="是否及格" width="100">
          <template v-slot="scope">
            <el-tag :type="scope.row.score>= 60 ? 'success' : 'danger'">{{scope.row.score >= 60 ? "及格" : "不及格"}}</el-tag>
          </template>
        </el-table-column>
      </el-table>
      <el-row type="flex" justify="center" align="middle" class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[4,6,8,10]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </el-row>
    </section>
  </div>
</template>

<script>
import request from '@/utils/request';
export default {
  components: {
    request
  },
  data() {
    return {
      pagination: { //分页后的留言列表
        current: 1, //当前页
        total: null, //记录条数
        size: 10 //每页条数
      },
      loading: false, //加载标识符
      score: [], //学生成绩
      filter: null //过滤参数
    }
  },
  created() {
    this.getScore()
    this.loading = true //数据加载则遮罩表格
  },
  methods: {
    getScore() {
      let studentId = localStorage.getItem('studentId')
      request.get(`/api/exam/score/${this.pagination.current}/${this.pagination.size}/${studentId}`).then(res => {
        console.log(res)
        if(res.code == 200) {
          this.loading = false //数据加载完成去掉遮罩
          console.log(res.data)
          this.score = res.data.records
          this.pagination = {...res.data}
          let mapVal = this.score.map((element,index) => { //通过map得到 filter:[{text,value}]形式的数组对象
            let newVal = {}
            newVal.text = element.answerDate
            newVal.value = element.answerDate
            return newVal
          })
          let hash = []
          const newArr = mapVal.reduce((item, next) => { //对新对象进行去重操作
            hash[next.text] ? '' : hash[next.text] = true && item.push(next);
            return item
          }, []);
          this.filter = newArr
        }
      })
    },
    handleRowClick(row) {
      this.$router.push({path: '/answers', query: {scoreId: row.scoreId,examId:row.paperId}})
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getScore()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getScore()
    },
    formatter(row, column) {
      return row.address;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    }
  }
};
</script>

<style lang="less" scoped>


.pagination {
  padding-top: 20px;
}
.table {
  width: 980px;
  margin: 0 auto;
  .title {
    margin: 20px;
  }
  .content-el {
    background-color: #fff;
    padding: 20px;
    margin-top: 20px;
  }
}
</style>
