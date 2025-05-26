<template>
    <div id="main6"></div>
</template>

<script>
 import request from '@/utils/request';
import * as echarts from 'echarts';
 import { mapState } from 'vuex';

    export default {
        name: 'Schart6',
        data() {
            return {
                myChart: null,
                option: {
                    color: ['#67F9D8', '#FFE434', '#56A3F1', '#FF917C'],
                    title: {
                        text: '本次考试中试题所属章节得分率',
                        left: '35%',
                        top: 10,
                        textStyle: {
                            color: '#333',
                            fontSize: 18,
                            // 字体
                            fontFamily: '楷体'
                        }
                    },
                    // legend: {
                    //     orient: 'vertical',
                    //     left: 'left'
                    // },
                    radar: [
                        
                        {
                        indicator: [
                            { text: '1', max: 1 },
                            { text: 'Indicator2', max: 1 },
                            { text: 'Indicator3', max: 1 },
                            { text: 'Indicator4', max: 1 },
                            { text: 'Indicator5', max: 1 },
                            { text: 'Indicator6', max: 1 },
                            { text: 'Indicator7', max: 1 },
                            { text: 'Indicator8', max: 1 },
                        ],
                        center: ['50%', '50%'],
                        radius: 150,
                        axisName: {
                            color: '#428BD4',
                            // backgroundColor: '#666',
                            // borderRadius: 3,
                            // padding: [3, 5]
                        }
                        
                        }
                    ],
                    series: [
                        {
                        type: 'radar',
                        label: {
                            show: true,
                            position: 'outside',
                            color: 'gray',
                            formatter: function(params) {
                                return `${params.value}%`;
                            }                  
                            },
                        emphasis: {
                            lineStyle: {
                            width: 4
                            }
                        },
                        data: [
                            {
                            value: [
                            // 0.3,0.58, 0.4, 0.8, 0.2,0.9,0.6
                            ],
                            name: 'Data A',
                            areaStyle: {
                                color: 'rgba(176, 88, 152, 0.6)'
                            }
                            },
                            // {
                            // value: [60, 5, 0.3, -100, 1500],
                            // name: 'Data B',
                            // areaStyle: {
                            //     color: 'rgba(255, 228, 52, 0.6)'
                            // }
                            // }
                        ]
                        },
                        
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
                this.myChart = echarts.init(document.getElementById('main6'))
                this.myChart.setOption(this.option)
            },
            getChartData() {
                if(this.analysisScoreId == null) {
                    return
                }
                request.get(`/api/analysis/getExamQuestionChapterScoreRate/${this.analysisScoreId}`).then
                    (res => {
                        if(res.code === 200) {
                            console.log("数据",res.data)
                            this.option.series[0].data[0].value = res.data.data
                            this.option.radar[0].indicator = res.data.indicator
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
#main6 {
    width: 100%;
    height: 100%;
}
</style>

