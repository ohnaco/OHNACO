<template>
  <!-- 일주일 유저 평균 공부시간 -->
  <div>
    <div id="chart" class="ms-3">
      <div class="ms-5 mt-5" v-if="option=='week'"><b>최근 일주일 평균 공부시간</b></div>
      <div class="ms-5 mt-5" v-if="option=='month'"><b>일별 평균 공부시간</b></div>
      <apexchart 
        type="line" 
        height="350" 
        :options="chartOptions" 
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
    statsInfo: [Array, Object],
    option: String,
  },
  data: function () {
    return {
      series: [],
      chartOptions: {
        chart: {
          height: 350,
          type: 'line',
          id: 'chart',
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
          categories: [],
          labels: {
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
    }
  },
  watch: {
    statsInfo: function () {
      const newSeries = [{
        name: "나의 평균 공부시간",
        data: [
          this.statsInfo.myTime[0].time,
          this.statsInfo.myTime[1].time,
          this.statsInfo.myTime[2].time,
          this.statsInfo.myTime[3].time,
          this.statsInfo.myTime[4].time,
          this.statsInfo.myTime[5].time,
          this.statsInfo.myTime[6].time,
        ]
        },
        {
          name: "전체 유저 평균 공부시간",
          data: [
            this.statsInfo.positionMemberTime[0].time,
            this.statsInfo.positionMemberTime[1].time,
            this.statsInfo.positionMemberTime[2].time,
            this.statsInfo.positionMemberTime[3].time,
            this.statsInfo.positionMemberTime[4].time,
            this.statsInfo.positionMemberTime[5].time,
            this.statsInfo.positionMemberTime[6].time,
          ]
        },
        {
          name: '직군별 유저 평균 공부시간',
          data: [
            this.statsInfo.entireMemberTime[0].time,
            this.statsInfo.entireMemberTime[1].time,
            this.statsInfo.entireMemberTime[2].time,
            this.statsInfo.entireMemberTime[3].time,
            this.statsInfo.entireMemberTime[4].time,
            this.statsInfo.entireMemberTime[5].time,
            this.statsInfo.entireMemberTime[6].time,
          ]
        }
      ]
      const weekxaxis = [
        this.statsInfo.myTime[0].date,
        this.statsInfo.myTime[1].date,
        this.statsInfo.myTime[2].date,
        this.statsInfo.myTime[3].date,
        this.statsInfo.myTime[4].date,
        this.statsInfo.myTime[5].date,
        this.statsInfo.myTime[6].date,
        ]
      const monthxaxis = ['월', '화', '수', '목', '금', '토', '일']
      this.series = newSeries
      if (this.option == 'week') {
        console.log(this.chartOptions)
        this.chartOptions = {...this.chartOptions, ...{
          xaxis: {
            categories: weekxaxis
          }
        }}
      } 
      if (this.option == 'month') {
        console.log(this.chartOptions)
        this.chartOptions = {...this.chartOptions, ...{
          xaxis: {
            categories: monthxaxis
          }
        }}
      }
    },
  },
};
</script>