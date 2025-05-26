<template>
    <div>
        <el-row class="card1-part">
            <el-icon style="margin-top: 3px;margin-right: 10px;"><DataAnalysis /></el-icon>
            <span style="font-weight: bold;">{{subjectDetail.subjectName}}</span>
            </el-row>
            <el-divider border-style="double" />
            <el-row >
                <span style="padding-left: 20px;font-size: 16px;color: #666;">
                    本门科目您的平均分为{{subjectDetail.avgScore}}分，评价等级为{{selfGrade.level}},在班级中您处于{{selfGrade.standard}}水平，超过{{selfGrade.percent}}%的同学，{{selfGrade.message}}
                </span>
                <el-divider border-style="double" />
            </el-row>
        <div style="margin: 20px;width: 100%;height: 500px;"><Schart2/></div>
        <el-divider border-style="double" />
        
    </div>
</template>
<script>
    import request from '@/utils/request'
    import { DataAnalysis,Male,ChatLineRound } from '@element-plus/icons-vue'
    import { mapState } from 'vuex'
    import Schart2 from '@/components/charts/Schart2.vue'

    export default {
        name: 'examRecord',
        components: {
            DataAnalysis,
            Male,
            ChatLineRound,
            Schart2
        },
        data() {
            return {
                selfGrade: {
                    percent: 0,
                    avgScore: 0,
                    level:'',
                    standard:'',
                    message:''
                },
                subjectDetail: {
                    subjectName: '未选择科目',
                    // examCount: '*',
                    // maxScore: '*',
                    // minScore: '*',
                    // avgScore: '*',
                    // level: '*'
                },
            }
        },
        computed: {
            ...mapState(['analysisSubject'])
        },
        watch: {
            analysisSubject: function (newVal, oldVal) {
                if(this.analysisSubject != null){
                    this.analysis()
                    // alert('已选择科目')
                }
            }
        },
        created() {   
            
            if (this.analysisSubject != null) {
                // this.value = this.analysisSubject
                this.analysis() 
                this.getSelfGrade()
            }
        },
        methods: {
            analysis() {
                if (this.analysisSubject == null) {
                    alert('请选择科目')
                    return;
                }
                let studentId = localStorage.getItem('studentId')
                request.get(`/api/analysis/getSubjectDetail/${studentId}/${this.analysisSubject}`).then((res) => {
                console.log(res.data)
                this.subjectDetail = res.data
                })
            },

            getSelfGrade() {
                if (this.analysisSubject == null) {
                    alert('请选择科目')
                    return;
                }
                let studentId = localStorage.getItem('studentId')
                request.get(`/api/analysis/selfGrade/${studentId}/${this.analysisSubject}`).then((res) => {
                    console.log("selfGrade-res.data",res.data)
                    // percent 精确到小数点后2位再乘100
                    res.data.percent = res.data.percent.toFixed(2)*100
                    this.selfGrade = res.data
                })
                console.log("selfGrade", this.selfGrade)
            }
        }
    }
</script>
<style scoped>
    .card1-part {
        margin: 12px 32px;
    }
    .el-col {
        margin: 10px 42px;
    }
</style>