<template>
    <div id="main2"></div>
</template>

<script>
 import request from '@/utils/request';
import * as echarts from 'echarts';
 import { mapState } from 'vuex';

    export default {
        name: 'Schart1',
        data() {
            return {
                myChart: null,
                option: {
                    title: {
                        text: '班级评分等级分布情况',
                        left: '40%',
                        top: 20,
                        textStyle: {
                            color: '#333',
                            fontSize: 14,
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
            if (this.analysisSubject != null) {
                this.value = this.analysisSubject
                this.getChartData() 
            }
        },
        methods: {
            init() {
                this.myChart = echarts.init(document.getElementById('main2'))
                this.myChart.setOption(this.option)
            },
            getChartData() {
                let studentId = localStorage.getItem('studentId')
                if(!studentId || !this.analysisSubject) {
                    alert('请求参数异常'+studentId+this.analysisSubject)
                    return;
                }
                console.log(studentId,this.analysisSubject)
                request.get(`/api/analysis/grade/${studentId}/${this.analysisSubject}`).then
                    (res => {
                        if(res.code === 200) {
                            console.log("Schart2:",res.data)
                            this.option.series[0].data = res.data
                            this.myChart.setOption(this.option)
                        } else {
                            alert('班级评分等级分布数据请求失败'+res.message)
                        }
                    })
                
            }
            
        },
        computed: {
            ...mapState(['analysisSubject'])
        },
        watch: {
            analysisSubject: function (newVal, oldVal) {
                this.getChartData()
            }
        },
        mounted() {
            this.init()
        }

    }
</script>

<style scoped>
#main2 {
    width: 100%;
    height: 100%;
}
</style>

