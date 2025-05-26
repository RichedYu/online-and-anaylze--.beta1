<template>
    <div id="main5"></div>
</template>

<script>
 import request from '@/utils/request';
import * as echarts from 'echarts';
 import { mapState } from 'vuex';

    export default {
        name: 'Schart5',
        data() {
            return {
                myChart: null,
                option: {
                    title: {
                        text: '本次考试中试题所属章节占比',
                        left: '35%',
                        top: 20,
                        textStyle: {
                            color: '#333',
                            fontSize: 18,
                            // 字体
                            fontFamily: '楷体'
                        }
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [
                        {
                        name: '班级评分等级分布',
                        type: 'pie',
                        radius: '50%',
                        left: '-5%',
                        label: {
                            show: true,
                            position: 'outside',
                            formatter: '{b}: {d}%',// 显示百分比
                        },
                        data: [
                            // { value: 1, name: '优秀(>=90)' },
                            // { value: 7, name: '良好(>=80)' },
                            // { value: 5, name: '中等(>=70)' },
                            // { value: 5, name: '及格(>=60)' },
                            // { value: 4, name: '不及格(<60)' }
                        ],
                        emphasis: {
                            itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                        }
                    ]
                }
            }
        },
        created() {
            if (this.analysisSubject != null && this.analysisScoreId!= null) {
                this.getChartData() 
            }
        },
        methods: {
            init() {
                this.myChart = echarts.init(document.getElementById('main5'))
                this.myChart.setOption(this.option)
            },
            getChartData() {
                if(this.analysisScoreId == null) {
                    return
                }
                request.get(`/api/analysis/getExamQuestionChapter/${this.analysisScoreId}`).then
                    (res => {
                        if(res.code === 200) {
                            console.log("数据",res.data)
                            this.option.series[0].data = res.data
                            this.myChart.setOption(this.option)
                        } else {
                            alert('知识点所属章节数据请求失败'+res.message)
                        }
                    })
                
            }
            
        },
        computed: {
            ...mapState(['analysisSubject','analysisScoreId'])
        },
        watch: {
            analysisScoreId: function (newVal, oldVal) {
                this.getChartData()
            }
        },
        mounted() {
            this.init()
        }

    }
</script>

<style scoped>
#main5 {
    width: 100%;
    height: 100%;
}
</style>

