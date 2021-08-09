<template>
  <!-- 목표 공부시간 vs 실제 공부시간-->
  <div>
    <div class="ms-5 mt-5 mb-5"><b>목표시간 VS 실제시간</b></div>
    <div>
    <apexchart
      type="bar"
      height="495"
      width="90%"
      :options="chartOptions"
      :series="series"
    ></apexchart>
    </div>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "GoalVsReal",
  components: {
    apexchart: VueApexCharts,
  },
  props: {
    categoryTime: { 
      type: Array,
    },
  },
  data: function () {
    return {
      series: [{
        name: '실제시간',
        data: [
          {
          x: '전체',
          y: this.categoryTime[5].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[5].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[0].categoryname,
          y: this.categoryTime[0].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[0].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[2].categoryname,
          y: this.categoryTime[2].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[2].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[3].categoryname,
          y: this.categoryTime[3].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[3].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[4].categoryname,
          y: this.categoryTime[4].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[4].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[1].categoryname,
          y: this.categoryTime[1].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[1].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
        ]
      }],
      chartOptions: {
        chart: {
          type: 'bar',
          offsetY: 20,
          toolbar: {
            show: false
          },
        },
        plotOptions: {
          bar: {
            columnWidth: '60%',
          }
        },
        yaxis: {
          labels: {
            show: false,
          }
        },
        colors: ['#FF8A65'],
        dataLabels: {
          enabled: false
        },
        legend: {
          horizontalAlign: "right",
          position: "top",
          showForSingleSeries: true,
          customLegendItems: ['실제 공부시간', '목표 공부시간'],
          markers: {
            fillColors: ['#FF8A65', '#C6C8C9']
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
    categoryTime: function () {
      const newSeries = [{
        name: '실제시간',
        data: [
          {
          x: '전체',
          y: this.categoryTime[5].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[5].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[0].categoryname,
          y: this.categoryTime[0].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[0].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[2].categoryname,
          y: this.categoryTime[2].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[2].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[3].categoryname,
          y: this.categoryTime[3].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[3].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[4].categoryname,
          y: this.categoryTime[4].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[4].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
          {
          x: this.categoryTime[1].categoryname,
          y: this.categoryTime[1].completetime,
          goals: [
            {
              name: '목표시간',
              value: this.categoryTime[1].goaltime,
              strokeWidth: 5,
              strokeColor: '#C6C8C9'
            }
          ]},
        ]
      }]
      this.series = newSeries
    }
  },
};
</script>