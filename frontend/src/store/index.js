import Vue from "vue";
import Vuex from "vuex";
import todoStore from "./modules/todoStore";
import userStore from "./modules/userStore";
import devTalkStore from "./modules/devTalkStore";
import techStore from "./modules/techStore";
import statisticsStore from "./modules/statisticsStore";
import mypageStore from "./modules/mypageStore";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    todoStore: todoStore,
    userStore: userStore,
    devTalkStore: devTalkStore,
    techStore: techStore,
    statisticsStore: statisticsStore,
    mypageStore: mypageStore,
  },
});

export default store;
