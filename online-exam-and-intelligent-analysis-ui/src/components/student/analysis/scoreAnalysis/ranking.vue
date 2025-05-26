<template>
    <div>
        <el-row class="card1-part">
            <el-icon style="margin-top: 3px;margin-right: 10px;"><DataAnalysis /></el-icon>
            <span style="font-weight: bold;">{{subjectDetail.subjectName}}</span>
            </el-row>
            <el-divider border-style="double" />
            <el-row >
                <span style="padding-left: 20px;font-size: 16px;color: #666;line-height: 24px;">
                    本门科目中，您累计考试的各个题型的得分率如下图所示：<br/>
                    您可以根据下图加强相关题型的训练，提升您的成绩~
                </span>
                <el-divider border-style="double" />
            </el-row>
        <div style="margin: 20px;width: 100%;height: 500px;"><Schart4/></div>
        <el-divider border-style="double" />
        
    </div>
</template>
<script>
    import request from '@/utils/request'
    import { DataAnalysis,Male,ChatLineRound } from '@element-plus/icons-vue'
    import { mapState } from 'vuex'
    import Schart4 from '@/components/charts/Schart4.vue'

    export default {
        name: 'examRecord',
        components: {
            DataAnalysis,
            Male,
            ChatLineRound,
            Schart4
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