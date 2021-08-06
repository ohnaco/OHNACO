<template>
  <!-- 일주일 유저 평균 공부시간 -->
  <div>
    <div class="ms-3" v-if="option=='week'">
      <div class="ms-5 mt-5"><b>최근 일주일 평균 공부시간</b></div>
      <apexchart 
        type="line" 
        height="350" 
        :options="weekOptions" 
        :series="series"></apexchart>
    </div>
    <div class="ms-3" v-if="option=='month'">
      <div class="ms-5 mt-5"><b>일별 평균 공부시간</b></div>
      <apexchart 
        type="line" 
        height="350" 
        :options="monthOptions" 
        :series="series"></apexchart>
    </div>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "CompareOther",
  components: {
    apexchart: VueApexCharts,
  },
  props: {
    myTime: {
      type: Array
    },
    positionMemberTime: {
      type: Array
    },
    entireMemberTime: {
      type: Array
    },
    option: {
      type: String,
    },
  },
  data: function () {
    return {
      series: [{
        name: "나의 평균 공부시간",
        data: [
          this.myTime[0].time,
          this.myTime[1].time,
          this.myTime[2].time,
          this.myTime[3].time,
          this.myTime[4].time,
          this.myTime[5].time,
          this.myTime[6].time,
        ]
        },
        {
          name: "전체 유저 평균 공부시간",
          data: [
            this.positionMemberTime[0].time,
            this.positionMemberTime[1].time,
            this.positionMemberTime[2].time,
            this.positionMemberTime[3].time,
            this.positionMemberTime[4].time,
            this.positionMemberTime[5].time,
            this.positionMemberTime[6].time,
          ]
        },
        {
          name: '직군별 유저 평균 공부시간',
          data: [
            this.entireMemberTime[0].time,
            this.entireMemberTime[1].time,
            this.entireMemberTime[2].time,
            this.entireMemberTime[3].time,
            this.entireMemberTime[4].time,
            this.entireMemberTime[5].time,
            this.entireMemberTime[6].time,
          ]
        }
      ],
      weekOptions: {
        chart: {
          height: 350,
          type: 'line',
          zoom: {
            enabled: false
          },
          toolbar: {
            show: false
          },
        },
        legend: {
          horizontalAlign: "right",
          position: "top",
        },
        colors: ["#FF8A65", "#C4C4C4", "#80DEEA"],
        xaxis: {
          categories: [
            this.myTime[0].date,
            this.myTime[1].date,
            this.myTime[2].date,
            this.myTime[3].date,
            this.myTime[4].date,
            this.myTime[5].date,
            this.myTime[6].date,
            ],
            labels: {
              rotate: 45,
              style: {
                fontSize: '10px'
              },
              
            }
        },
        yaxis: {
          show: true,
          labels: {
            formatter: function (val) {
              const hour = Math.floor(val / 3600)
              return hour + "시간 "
            }
          }
        },
        tooltip: {
          y: {
            formatter: function (val) {
              const hour = Math.floor(val / 3600)
              const minute = Math.floor((val % 3600) / 60)
              return hour + "시간 " + minute + '분 '
            }
          }
        }
      },
      monthOptions: {
        chart: {
          height: 350,
          type: 'line',
          zoom: {
            enabled: false
          },
          toolbar: {
            show: false
          },
        },
        legend: {
          horizontalAlign: "right",
          position: "top",
        },
        colors: ["#FF8A65", "#C4C4C4", "#80DEEA"],
        xaxis: {
          categories: ['월', '화', '수', '목', '금', '토', '일'],
        },
        yaxis: {
          show: true,
          labels: {
            formatter: function (val) {
              const hour = Math.floor(val / 3600)
              return hour + "시간 "
            }
          }
        },
        tooltip: {
          y: {
            formatter: function (val) {
              const hour = Math.floor(val / 3600)
              const minute = Math.floor((val % 3600) / 60)
              return hour + "시간 " + minute + '분 '
            }
          }
        }
      },
    }
  },
  watch: {
    myTime: function () {
      const newSeries = [{
        name: "나의 평균 공부시간",
        data: [
          this.myTime[0].time,
          this.myTime[1].time,
          this.myTime[2].time,
          this.myTime[3].time,
          this.myTime[4].time,
          this.myTime[5].time,
          this.myTime[6].time,
        ]
        },
        {
          name: "전체 유저 평균 공부시간",
          data: [
            this.positionMemberTime[0].time,
            this.positionMemberTime[1].time,
            this.positionMemberTime[2].time,
            this.positionMemberTime[3].time,
            this.positionMemberTime[4].time,
            this.positionMemberTime[5].time,
            this.positionMemberTime[6].time,
          ]
        },
        {
          name: '직군별 유저 평균 공부시간',
          data: [
            this.entireMemberTime[0].time,
            this.entireMemberTime[1].time,
            this.entireMemberTime[2].time,
            this.entireMemberTime[3].time,
            this.entireMemberTime[4].time,
            this.entireMemberTime[5].time,
            this.entireMemberTime[6].time,
          ]
        }
      ]
      this.series = newSeries
    }
  },
};
</script>
