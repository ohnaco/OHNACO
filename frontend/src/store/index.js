import Vue from "vue";
import Vuex from "vuex";
import todoStore from "./modules/todoStore";
import userStore from "./modules/userStore";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    todoStore: todoStore,
    userStore: userStore,
  },
});

export default store;
