<template>
  <div class="d-flex align-center ml-3">
    <img src="@/assets/images/daily-commit-no.svg" v-if="!todayCommit" />
    <img src="@/assets/images/daily-commit-ok.svg" v-else />
    <v-btn icon><img
      src="@/assets/images/daily-commit-refresh.svg"
      @click="onRefresh"
    /></v-btn>
  </div>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const todoHelper = createNamespacedHelpers("todoStore");

export default {
  data() {
    return {
      todayCommit: null,
    };
  },
  created() {
    this.onRefresh();
  },
  methods: {
    ...todoHelper.mapActions(["refreshCommit"]),
    ...todoHelper.mapGetters(["getDay1Commit"]),
    onRefresh() {
        this.refreshCommit();
        if (this.getDay1Commit() !== "0") this.todayCommit = true;
        else this.todayCommit = false;
    }
  },
};
</script>
