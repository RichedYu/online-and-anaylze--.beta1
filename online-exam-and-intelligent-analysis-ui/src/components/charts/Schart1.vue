<template>
    <div id="main1"></div>
</template>

<script>
import request from '@/utils/request'
 import * as echarts from 'echarts';
 import { mapState } from 'vuex';

    export default {
        name: 'Schart1',
        data() {
            return {
                myChart: null,
                option: {
                    title: {
                        // text: '近几次考试班级平均分和个人分数',
                        text: '成绩趋势',
                        left: 'center',
                        top: 20,
                        textStyle: {
                            color: '#333',
                            fontSize: 14,
                            // 字体
                            fontFamily: '楷体'
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: []
                    },
                    yAxis: {
                        type: 'value',
                        max: 100,
                        min: 0,
                    },
                    //  legend: {
                    //     data: [ '个人分数', '班级平均分'],
                    //     left: 'left',
                    //     bottom: 15,
                    //     left: 30,
                    // },
                    series: [
                        // {
                        // name: '班级平均分',
                        // label: {
                        //     show: true,
                        //     position: 'bottom',
                        //     color: 'blue',
                        //     formatter: '{c}'
                        // },
                        // data: [
                        //     // 58, 60, 54, 68, 55, 67, 60
                        // ],
                        // type: 'line'
                        // },
                        {
                        name: '个人分数',
                        color: '#73c0de',
                        label: {
                            show: true,
                            position: 'top',
                            color: '#73c0de',
                            formatter: '{c}'
                        },
                        data: [
                            // 70, 60, 84, 68, 75, 77, 80
                        ],
                        type: 'line'
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
                if(this.analysisSubject!= null) {
                    this.getChartData()
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
            initChart() {
                this.myChart = echarts.init(document.getElementById('main1'));
                this.myChart.setOption(this.option);
            },
            getChartData() {
                let studentId = localStorage.getItem('studentId')
                if(!studentId || !this.analysisSubject) {
                    alert('请求参数异常'+studentId+this.analysisSubject)
                    return;
                }
                console.log(studentId,this.analysisSubject)
                request.get(`/api/analysis/trend/${studentId}/${this.analysisSubject}`).then
                    (res => {
                        console.log(res)
                        // 班级平均分遍历
                        let classAvg = res.data.classAvgList
                        console.log(" classAvg",classAvg)
                        let classAvgData = []
                        for(let i=classAvg.length-1;i>=0;i--) {
                            classAvgData.push(classAvg[i])
                        }
                        // 个人分数遍历
                        let personalScore = res.data.personalList
                        console.log(" personalScore",personalScore)
                        let personalScoreData = []
                        for(let i=personalScore.length-1;i>=0;i--) {
                            personalScoreData.push(personalScore[i])
                        }
                        // 日期遍历
                        let date = res.data.dateList
                        console.log(" date",date)
                        let dateData = []
                        for(let i=date.length-1;i>=0;i--) {
                            dateData.push(date[i])
                        }
                        
                        // 更新option
                        this.option.xAxis.data = dateData
                        // this.option.series[0].data = classAvgData
                        // this.option.series[1].data = personalScoreData
                        this.option.series[0].data = personalScoreData
                        this.myChart.setOption(this.option)
                    })
                
            }
        },
        mounted() {
            this.initChart();
        }

    }
</script>

<style scoped>
#main1 {
    width: 100%;
    height: 100%;
}
</style>

