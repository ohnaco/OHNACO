<template>
  <div class="container">
    <div v-if="option=='week'" class="d-flex justify-space-between">
      <div><b>이번주 총 공부시간</b></div>
      <h2 style="color:crimson"><b>{{ totaltime }}</b></h2>
    </div>
    <div v-if="option=='month'" class="d-flex justify-space-between">
      <div><b>이번달 총 공부시간</b></div>
      <h2 style="color:crimson"><b>{{ totaltime }}</b></h2>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    totalTime: { 
      type: Number,
    },
    option: {
      type: String,
    }
  },
  computed: {
    totaltime: function () {
      const hour = Math.floor(this.totalTime / 3600)
      const minute = Math.floor((this.totalTime % 3600) / 60)
      return hour + "시간 " + minute + '분 '
    }
  }
};
</script>

<style>
</style>
