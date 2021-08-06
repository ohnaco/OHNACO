<template>
  <!-- 직군별 유저 카테고리 공부시간 -->
  <div>
    <div class="ms-5 mt-5 mb-10"><b>유저 vs 나의 공부시간</b></div>
    <div>
      <apexchart 
        type="bar" 
        height="474" 
        :options="chartOptions" 
        :series="series"></apexchart>
    </div>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "CompareOtherByCategory",
  components: {
    apexchart: VueApexCharts,
  },
  props: {
    categoryTime: {
      type: Array
    },
    positionTime: {
      type: Array
    },
    entireCategoryTime: {
      type: Array
    },
  },
  data: function () {
    return {
      series: [{
        name: 'CS',
        data: [
          this.entireCategoryTime[0].time,
          this.positionTime[0].time,
          this.categoryTime[0].completetime,
        ]
      }, 
      {
        name: '알고리즘',
        data: [
          this.entireCategoryTime[2].time,
          this.positionTime[2].time,
          this.categoryTime[2].completetime,
        ]
      }, 
      {
        name: '자격증',
        data: [
          this.entireCategoryTime[3].time,
          this.positionTime[3].time,
          this.categoryTime[3].completetime,
        ]
      }, 
      {
        name: '프레임워크',
        data: [
          this.entireCategoryTime[4].time,
          this.positionTime[4].time,
          this.categoryTime[4].completetime,
        ]
      }, 
      {
        name: '기타',
        data: [
          this.entireCategoryTime[1].time,
          this.positionTime[1].time,
          this.categoryTime[1].completetime,
        ]
      }],
      chartOptions: {
        chart: {
          type: 'bar',
          height: 350,
          stacked: true,
          toolbar: {
            show: false
          },
        },
        plotOptions: {
          bar: {
            horizontal: false,
          },
        },
        dataLabels: {
          enabled: true,
          style: {
            colors: ['#fff']
          },
          formatter: function (val) {
            const hour = Math.floor(val / 3600)
            return hour + '시간'
          }
        },
        xaxis: {
          type: 'text',
          categories: ['전체 유저', '직군별 유저', '나'],
        },
        yaxis: {
          show: false
        },
        legend: {
          position: 'right',
          offsetY: 30
        },
        colors: ["#607D8B", "#80DEEA", "#000000", "#C3C3C3", "#848484"],
        fill: {
          opacity: 1
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
        name: 'CS',
        data: [
          this.entireCategoryTime[0].time,
          this.positionTime[0].time,
          this.categoryTime[0].completetime,
        ]
      }, 
      {
        name: '알고리즘',
        data: [
          this.entireCategoryTime[2].time,
          this.positionTime[2].time,
          this.categoryTime[2].completetime,
        ]
      }, 
      {
        name: '자격증',
        data: [
          this.entireCategoryTime[3].time,
          this.positionTime[3].time,
          this.categoryTime[3].completetime,
        ]
      }, 
      {
        name: '프레임워크',
        data: [
          this.entireCategoryTime[4].time,
          this.positionTime[4].time,
          this.categoryTime[4].completetime,
        ]
      }, 
      {
        name: '기타',
        data: [
          this.entireCategoryTime[1].time,
          this.positionTime[1].time,
          this.categoryTime[1].completetime,
        ]
      }]
      this.series = newSeries
    }
  },
};
</script>