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
      date.setSeconds(
        (this.goingTimeFQ + (this.$moment() - this.exitTimeFQ)) / 1000
      );
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
.form-control:focus {
   box-shadow: 0 0 0 0.25rem rgb(134 183 254 / 25%) !important;
   border-color: rgb(134 183 254) !important;
}
.form-select:focus {
   box-shadow: 0 0 0 0.25rem rgb(134 183 254 / 25%) !important;
   border-color: rgb(134 183 254) !important;
}
.v-application .error {
  box-shadow: 0 0 0 0.25rem rgb(255 82 82 / 25%) !important;
    background-color: #ff5252 !important;
    border-color: #ff5252 !important;
}
.m-unite {
  padding: 24px !important;
}
@media (min-width: 768px) {
  .m-unite {
    padding: 36px !important;
  }
}
</style>
