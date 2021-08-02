import Vue from "vue";
import Vuex from "vuex";
import todoStore from "./modules/todoStore";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    todoStore: todoStore,
  }
});

export default store;