<template>
    <div id="main3"></div>
</template>

<script>
 import * as echarts from 'echarts';
 import { mapState } from 'vuex'
 import request from '@/utils/request';



    export default {
        name: 'Schart1',
        data() {
            return {
                myChart: null,
                questionCount: {
                    easy: {},
                    medium: {},
                    hard: {}
                },
                option: {
                    title: {
                        text: '章节知识点正确率统计'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                        type: 'shadow'
                        }
                    },
                    legend: {},
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        max: 1,
                        min: 0,
                        boundaryGap: [0, 0.01]
                    },
                    yAxis: {
                        type: 'category',
                        data: [
                            // '线性表', '栈和队列', '串', '树与二叉树', '图论', '查找和排序'
                        ],
                        
                    },
                    series: [
                        {
                        name: '简单',
                        type: 'bar',
                        color: '#91cc75',// 绿色
                        label: {
                            show: true,
                            position: 'right',  
                            color: '#91cc75',
                            formatter: function(params) {
                                return `count:${params.value[2]}->${Math.round(params.value[0] * 100)}%`;
                            }                  
                        },
                        data: [
                        // [0.4,0,12],[0.4,1,11],[0.1,2,14],[0.1,3,13],[0.9,4,15],[0.6,5,16]
                            // 0.9, 0.67, 0.94, 0.70, 0.44, 0.60
                        ],
                        yAxisIndex: 0
                        },
                        {
                        name: '中等',
                        type: 'bar',
                        color: '#73c0de',// 蓝色
                        label: {
                            show: true,
                            position: 'right',  
                            color: '#73c0de',
                            formatter: function(params) {
                                return `count:${params.value[2]}->${Math.round(params.value[0] * 100)}%`;
                            }                           
                        },
                        data: [
                        // [0.6,2],[0.3,3],[0.3,4],[0.4,5],[0.5,6],[0.6,7]
                            // 0.95, 0.28, 0.30, 0.94, 0.41, 0.07
                        ]
                        },
                        {
                        name: '困难',
                        type: 'bar',
                        color: '#fc8452',// 橙色
                        label: {
                            show: true,
                            position: 'right',  
                            color: '#fc8452',
                            formatter: function(params) {
                                return `count:${params.value[2]}->${Math.round(params.value[0] * 100)}%`;
                            }
                                                 
                        },
                        
                        data: [
                            // [0.1,2],[0.2,3],[0.3,4],[0.4,5],[0.5,6],[0.6,7]
                            // 0.35, 0.28, 0.31, 0.94, 0.14, 0.67
                        ]
                        }

                    ]
                }
            }
        },
        computed: {
            ...mapState(['analysisSubject'])
        },
        watch: {
            analysisSubject: function() {
                this.getChartData()
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
                this.myChart = echarts.init(document.getElementById('main3'))
                this.myChart.setOption(this.option)
                console.log("this.option",this.option)
            },
            getChartData() {
                let studentId = localStorage.getItem('studentId')
                if(!studentId || !this.analysisSubject) {
                    alert('请求参数异常'+studentId+this.analysisSubject)
                    return;
                }
                request.get(`/api/analysis/correctRateByDifficulty/${studentId}/${this.analysisSubject}`).then(res => {
                    if(res.code === 200) {
                        console.log("章节",res.data)
                        let data = res.data
                        // 处理数据
                        let easy = []
                        let medium = []
                        let hard = []
                        for(let i=0;i<data["chapters"].length;i++) {
                            let chapter = data["chapters"][i]
                            // easy.push(data["简单"][chapter])
                            // medium.push(data["中等"][chapter])
                            // hard.push(data["困难"][chapter])  
                            easy.push([data["简单"][chapter],i,data["简单"][chapter+"-简单"]])
                            medium.push([data["中等"][chapter],i,data["中等"][chapter+"-中等"]])
                            hard.push([data["困难"][chapter],i,data["困难"][chapter+"-困难"]])  
                        }
                        console.log("章节知识点个数统计",this.questionCount)
                        console.log("章节知识点正确率统计",easy,medium,hard,data["chapters"])
                        // 重新设置option
                        this.option.yAxis.data = data["chapters"]
                        this.option.series[0].data = easy
                        this.option.series[1].data = medium
                        this.option.series[2].data = hard
                        console.log("option",this.option)
                        this.myChart.setOption(this.option)
                    }
                }).catch(err => {
                    console.log(err)
                })
            }
        },
        mounted() {
            this.init()
        }

    }
</script>

<style scoped>
#main3 {
    width: 100%;
    height: 100%;
    
}
</style>

