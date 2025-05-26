<template>
    <div>
        <el-row class="card1-part">
            <el-icon style="margin-top: 3px;margin-right: 10px;"><DataAnalysis /></el-icon>
            <span style="font-weight: bold;">{{subjectDetail.subjectName}}</span>
            </el-row>
            <el-divider border-style="double" />
        <el-row class="card1-part">
            <el-col span="6" class="card1-part-title">
                <el-statistic title="考试次数" :value="subjectDetail.examCount">
                    <template #suffix>
                    
                    </template>
                </el-statistic>
                
            </el-col>
            <el-col span="6" class="card1-part-title">
                <el-statistic :value="subjectDetail.maxScore">
                <template #title>
                <div style="display: inline-flex; align-items: center">
                    历史最高分
                    <el-icon style="margin-left: 4px" :size="12">
                    <Male />
                    </el-icon>
                </div>
                </template>
                <template #suffix>/100</template>
                </el-statistic>
            </el-col>
            <el-col span="6" class="card1-part-title">
                <el-statistic :value="subjectDetail.minScore">
                <template #title>
                <div style="display: inline-flex; align-items: center">
                    历史最低分
                    <el-icon style="margin-left: 4px" :size="12">
                    <Male />
                    </el-icon>
                </div>
                </template>
                <template #suffix>/100</template>
                </el-statistic>
                
            </el-col>
            <el-col span="6" class="card1-part-title">
                <el-statistic :value="subjectDetail.avgScore">
                <template #title>
                <div style="display: inline-flex; align-items: center">
                    平均分
                    <el-icon style="margin-left: 4px" :size="12">
                    <Male />
                    </el-icon>
                </div>
                </template>
                <template #suffix>/100</template>
                </el-statistic>
                  
            </el-col>
            <el-col span="6" class="card1-part-title">
                <el-statistic title="评分等级" :value="subjectDetail.level">
                    <template #suffix>
                    <el-icon style="vertical-align: -0.125em">
                        <ChatLineRound />
                    </el-icon>
                    </template>
                </el-statistic>
               
            </el-col>
        </el-row>
        <el-divider border-style="double" />
        <div style="margin-top: 20px;width: 100%;height: 450px;"><Schart1/></div>
    </div>
</template>
<script>
    import request from '@/utils/request'
    import { DataAnalysis,Male,ChatLineRound } from '@element-plus/icons-vue'
    import { mapState } from 'vuex'
    import Schart1 from '@/components/charts/Schart1.vue'

    export default {
        name: 'examRecord',
        components: {
            DataAnalysis,
            Male,
            ChatLineRound,
            Schart1
        },
        data() {
            return {
                subjectDetail: {
                    subjectName: '未选择科目',
                    examCount: '*',
                    maxScore: '*',
                    minScore: '*',
                    avgScore: '*',
                    level: '*'
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