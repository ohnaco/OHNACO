<template>
  <!-- 어제의 나 vs 오늘의 나 total -->
  <div>
    <div id="chart">
      <apexchart
        type="bar"
        height="100"
        :options="barOptions"
        :series="series"
      ></apexchart>
    </div>
  </div>
</template>

<script>
import VueApexCharts from "vue-apexcharts";

export default {
  name: "YesterdayTotal",
  components: {
    apexchart: VueApexCharts,
  },
  props: {
    todayTime: Array,
    yesterdayTime: Array,
  },
  data: function () {
    return {
      series: [
        {
          name: "전체 시간",
          data: [],
        },
      ],
      barOptions: {
        chart: {
          type: "bar",
          id: "chart",
          toolbar: {
            show: false,
          },
        },
        plotOptions: {
          bar: {
            barHeight: "100%",
            distributed: true,
            horizontal: true,
            dataLabels: {
              show: false,
            },
          },
        },
        colors: ["#FF8A65", "#607D8B"],
        dataLabels: {
          enabled: true,
          style: {
            colors: ["#fff"],
          },
          formatter: function (val) {
            const hour = Math.floor(val / 3600);
            const minute = Math.floor((val % 3600) / 60);
            return hour + "시간 " + minute + "분 ";
          },
        },
        xaxis: {
          categories: ["1 days ago", "2 days ago"],
          labels: {
            show: false,
          },
        },
        legend: {
          show: false,
        },
        grid: {
          show: false,
        },
        tooltip: {
          y: {
            formatter: function (val) {
              const hour = Math.floor(val / 3600);
              const minute = Math.floor((val % 3600) / 60);
              return hour + "시간 " + minute + "분 ";
            },
          },
        },
      },
    };
  },
  watch: {
    todayTime: function () {
      this.series[0].data[0] = this.todayTime[5].completetime;
    },
    yesterdayTime: function () {
      this.series[0].data[1] = this.yesterdayTime[5].completetime;
    },
  },
};
</script>
