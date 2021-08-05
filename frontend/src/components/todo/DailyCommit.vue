<template>
  <div class="d-flex align-center ml-3">
    <img src="@/assets/images/daily-commit-no.svg" v-if="!todayCommit" />
    <img src="@/assets/images/daily-commit-ok.svg" v-if="todayCommit" />
    <v-btn icon><img
      src="@/assets/images/daily-commit-refresh.svg"
      @click="commitRefresh"
    /></v-btn>
  </div>
</template>

<script>
export default {
  data() {
    return {
      todayCommit: false,
    };
  },
  methods: {
    commitRefresh() {
      this.todayCommit = !this.todayCommit;
    },
  },
};
</script>
