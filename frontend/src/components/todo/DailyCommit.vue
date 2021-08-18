<template>
  <div class="d-flex align-center ml-3">
    <img src="@/assets/images/daily-commit-no.svg" class="commitImg" v-if="!todayCommit" />
    <img src="@/assets/images/daily-commit-ok.svg" class="commitImg" v-else />
    <v-btn icon
      ><img src="@/assets/images/daily-commit-refresh.svg" @click="refreshCommit"
    /></v-btn>
  </div>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const todoHelper = createNamespacedHelpers("todoStore");

export default {
  beforeMount() {
    this.refreshCommit();
  },
  computed: {
    ...todoHelper.mapState(["todayCommit"]),
  },
  methods: {
    ...todoHelper.mapActions(["refreshCommit"]),
  },
};
</script>

<style>
.commitImg {
  width: 150px;
  height: 35px;
}

@media (max-width: 768px) {
  .commitImg {
    width: 120px !important;
    height: 30px !important;
  }
}
</style>
