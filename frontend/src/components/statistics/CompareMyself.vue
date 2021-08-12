<template>
  <!-- 어제의 나 vs 오늘의 나 -->
  <div>
    <div class="ms-5 mt-5 mb-5"><b>지난 2일간 공부기록</b></div>
    <div id="chart">
      <apexchart
        type="radar"
        height="380"
        :options="chartOptions"
        :series="series1"
      ></apexchart>
    </div>
    <div id="chart">
      <apexchart
        type="bar"
        height="100"
        :options="barOptions"
        :series="series2"
      ></apexchart>
    </div>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "CompareMyself",
  components: {
    apexchart: VueApexCharts,
  },
  props: {
    todayTime: Array,
    yesterdayTime: Array,
  },
  data: function () {
    return {
      series1: [
        {
          name: "1 days ago",
          data: [
            this.todayTime[0].completetime,
            this.todayTime[2].completetime,
            this.todayTime[3].completetime,
            this.todayTime[4].completetime,
            this.todayTime[1].completetime,
          ],
        },
        {
          name: "2 days ago",
          data: [
            this.yesterdayTime[0].completetime,
            this.yesterdayTime[2].completetime,
            this.yesterdayTime[3].completetime,
            this.yesterdayTime[4].completetime,
            this.yesterdayTime[1].completetime,
          ],
        },
      ],
      series2: [
        {
          name: "전체 시간",
          data: [
            this.todayTime[5].completetime,
            this.yesterdayTime[5].completetime,
          ]
        },
      ],
      chartOptions: {
        chart: {
          offsetY: 20,
          type: "radar",
          dropShadow: {
            enabled: true,
            blur: 1,
          },
          toolbar: {
            show: false
          },
        },
        stroke: {
          width: 2,
        },
        xaxis: {
          categories: ["CS", "알고리즘", "자격증", "프레임워크", "기타"],
        },
        yaxis: {
          show: false
        },
        colors: ["#FF8A65", "#90A4AE"],
        legend: {
          show: false
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
      barOptions: {
        chart: {
          type: 'bar',
          toolbar: {
            show: false
          },
        },
        plotOptions: {
          bar: {
            barHeight: '100%',
            distributed: true,
            horizontal: true,
            dataLabels: {
              show: false
            },
          }
        },
        colors: ['#FF8A65', '#607D8B'],
        dataLabels: {
          enabled: true,
          style: {
            colors: ['#fff']
          },
          formatter: function (val) {
            const hour = Math.floor(val / 3600)
            const minute = Math.floor((val % 3600) / 60)
            return hour + "시간 " + minute + '분 '
          },
        },
        xaxis: {
          categories: ['1 days ago', '2 days ago'],
          labels: {
            show: false
          }
        },
        legend: {
          show: false
        },
        grid: {
          show: false,
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
      }
    };
  },
};
</script>