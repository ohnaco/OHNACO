<template>
  <!-- 카테고리별 공부시간 -->
  <div>
    <div class="ms-5 mt-5 mb-15"><b>카테고리별 공부시간</b></div>
    <div id="chart">
      <apexchart
        type="pie"
        width="350"
        :options="chartOptions"
        :series="series"
      ></apexchart>
    </div>
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
    categoryTime: Array,
  },
  data: function () {
    return {
      series: [],
      chartOptions: {
        chart: {
          id: 'chart',
          width: 350,
          type: "pie",
        },
        dataLabels: {
          enabled: true,
        },
        legend: {
          horizontalAlign: "center",
          position: "bottom",
          fontSize: '9px',
        },
        labels: ["CS", "알고리즘", "자격증", "프레임워크", "기타"],
        colors: ["#607D8B", "#80DEEA", "#000000", "#C3C3C3", "#848484"],
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
  watch: {
    categoryTime: function () {
      const newSeries = [
        this.categoryTime[0].completetime,
        this.categoryTime[2].completetime,
        this.categoryTime[3].completetime,
        this.categoryTime[4].completetime,
        this.categoryTime[1].completetime,
      ]
      this.series = newSeries
    }
  },
};
</script>

<style scoped>
#chart {
  display: flex;
  justify-content: center;
}
</style>