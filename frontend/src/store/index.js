import Vue from "vue";
import Vuex from "vuex";
import todoStore from "./modules/todoStore";
import userStore from "./modules/userStore";
<<<<<<< HEAD
import devTalkStore from "./modules/devTalkStore";
=======
import techStore from "./modules/techStore";
import statisticsStore from "./modules/statisticsStore";
>>>>>>> 59216e3935d0218dbe22bb773b8396c07c3143e8

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    todoStore: todoStore,
    userStore: userStore,
<<<<<<< HEAD
    devTalkStore: devTalkStore,
=======
    techStore: techStore,
    statisticsStore: statisticsStore,
>>>>>>> 59216e3935d0218dbe22bb773b8396c07c3143e8
  },
});

export default store;
