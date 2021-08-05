<template>
  <!-- 카테고리별 공부시간 -->
  <div>
    <div class="ms-5 mt-5 mb-15"><b>카테고리별 공부시간</b></div>
    <apexchart
      class="ml-10 mr-10"
      type="pie"
      width="380"
      :options="chartOptions"
      :series="series"
    ></apexchart>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "CategoryStudyTime",
  components: {
    apexchart: VueApexCharts,
  },
  props: {
    categoryTime: {
      type: Array
    },
  },
  data: function () {
    return {
      series: [
        this.categoryTime[0].completetime,
        this.categoryTime[2].completetime,
        this.categoryTime[3].completetime,
        this.categoryTime[4].completetime,
        this.categoryTime[1].completetime,
      ],
      chartOptions: {
        chart: {
          width: 380,
          type: "pie",
        },
        dataLabels: {
          enabled: true,
        },
        legend: {
          horizontalAlign: "center",
          position: "bottom",
          fontSize: '10px',
        },
        labels: ["CS", "알고리즘", "자격증", "프레임워크", "기타"],
        colors: ["#607D8B", "#80DEEA", "#000000", "#C3C3C3", "#848484"],
        // responsive: [{
        //   breakpoint: 1200,
        //   options: {
        //     chart: {
        //       width: 50
        //     },
        //     legend: {
        //       show: false
        //     }
        //   }
        // }]
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
    };
  },
};
</script>

<style scoped>

</style>
