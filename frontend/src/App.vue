<template>
  <v-app>
    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const todoHelper = createNamespacedHelpers("todoStore");
const mypageHelper = createNamespacedHelpers("mypageStore");

export default {
  name: "App",

  data: () => ({
    //
  }),
  methods: {
    ...todoHelper.mapActions(["updateTime"]),
    ...mypageHelper.mapActions(["getMyPage"]),
    unLoadEvent: function (event) {
      if (this.ongoingIdFQ != "") {
        this.updateTime([this.ongoingIdFQ, this.formattedElapsedTime()]);
      }
      if (this.canLeaveSite) {
        return;
      }
      event.preventDefault();
      event.returnValue = "";
    },
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds((this.goingTimeFQ + (this.$moment() - this.exitTimeFQ)) / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    },
  },
  computed: {
    ...todoHelper.mapState(["exitTimeFQ", "goingTimeFQ", "ongoingIdFQ"]),
  },
  mounted() {
    window.addEventListener("beforeunload", this.unLoadEvent);
  },
  beforeUnmount() {
    window.removeEventListener("beforeunload", this.unLoadEvent);
  },
};
</script>
<style>
.m-unite {
    padding:24px !important;
}
@media (min-width: 768px) {
.m-unite {
    padding:36px !important;
}
}
</style>
