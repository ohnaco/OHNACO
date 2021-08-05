import Vue from "vue";
import Vuex from "vuex";
import statisticsStore from "./modules/statisticsStore";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    statisticsStore: statisticsStore,
  }
});

export default store;
