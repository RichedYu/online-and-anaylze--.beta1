<template>
    <div id="main4"></div>
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
                option: {
                    title: {
                        text: '各题型得分率排行',
                        left: 'center',
                        top: 20,
                        textStyle: {
                            color: '#333',
                            fontSize: 14,
                            // 字体
                            fontFamily: '楷体'
                        }
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
                            // '简答题', '单选题', '多选题', '判断题', '填空题', '应用题'
                        ]
                    },
                    series: [
                        {
                        type: 'bar',
                        label: {
                            show: true,
                            position: 'inside',
                            color: 'gray',
                            formatter: function(params) {
                                return `count:${params.value[2]}->${Math.round(params.value[0] * 100)}%`;
                            }                  
                            },
                        data: [
                            // 0.6, 0.67, 0.74, 0.80, 0.84, 0.90
                            // [0.12,0,'4'],[0.17,1,'3'],[0.26,2,'2'],[0.35,3,'1'],[0.4,4,'10'],[0.43,5,'11']
                        ],
                        itemStyle: {
                            color: function(params) {
                                // 颜色列表
                                var colorList = ['#C1232B','#B5C334','#FCCE10','#E87C25','#27727B','#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD','#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'];
                                return colorList[params.dataIndex];
                            }
                        }
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
                this.myChart = echarts.init(document.getElementById('main4'))
                this.myChart.setOption(this.option)
            },
            getChartData() {
                let studentId = localStorage.getItem('studentId')
                if(!studentId || !this.analysisSubject) {
                    alert('请求参数异常'+studentId+this.analysisSubject)
                    return;
                }
                console.log(studentId,this.analysisSubject)
                request.get(`/api/analysis/correctRate/${studentId}/${this.analysisSubject}`).then(res => {
                    console.log(res)
                    if(res.code === 200) {
                        let data = res.data
                        console.log("各题型得分率排行",data)
                        // 处理数据 将该JSON数据转换为echarts所需的格式按题型正确率排序
                        let arr = []
                        for(let i in data) {
                            arr.push({
                                name: i,
                                value: data[i]
                            })
                        }
                        // 按题型正确率排序
                        arr.sort(function(a, b) {
                            return a.value - b.value
                        })
                        let questionType = []
                        for(let i=0;i<arr.length;i++) {
                            let name = arr[i].name
                            questionType.push(name.substring(0,name.indexOf('-')))
                        }
                        this.option.yAxis.data = questionType
                        let newData = []
                        for(let i=0;i<arr.length;i++) {
                            let name = arr[i].name
                            let value = [arr[i].value,i,name.substring(name.indexOf('-')+1)]
                            newData.push(value)
                        }
                        console.log("newData",newData)
                        this.option.series[0].data = newData
                        this.myChart.setOption(this.option)
                    }else{
                        alert('各题型得分率排行数据请求失败'+res.message)
                    }
                })
            }

        },
        mounted() {
            this.init()
        }

    }
</script>

<style scoped>
#main4 {
    width: 100%;
    height: 95%;
    
}
</style>

