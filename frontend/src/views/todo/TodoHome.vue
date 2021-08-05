<template>
  <v-layout>
    <v-row>
      <v-col cols="2"><left-nav-bar></left-nav-bar></v-col>
      <v-col cols="7" class="d-flex flex-column">
        <div class="d-flex align-center text-h4 ma-5">
          <img src="@/assets/images/todo-icon.svg" class="mr-2" />
          <b>To Do List</b>
        </div>
        <div class="d-flex flex-row align-center ma-5">
          <h2>{{ date }}</h2>
          <daily-commit />
        </div>
        <todo-card v-for="todo in todoLists" :key="todo.todoid" :item="todo" />
        <todo-add @finish-create="toggleCreate" v-if="isCreateTodo" />
        <button @click="toggleCreate">
          <img src="@/assets/images/todo-add-btn.svg" />
        </button>
      </v-col>
      <v-col cols="3">
        <!-- 달력 -->
      </v-col>
    </v-row>
  </v-layout>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TodoAdd from "@/components/todo/TodoAdd.vue";
import TodoCard from "@/components/todo/TodoCard.vue";
import { createNamespacedHelpers } from "vuex";
import DailyCommit from "@/components/todo/DailyCommit.vue";
const todoListHelper = createNamespacedHelpers("todoStore");

export default {
  data() {
    return {
      isCreateTodo: false,
      date: "8월 5일 (목)"
    };
  },
  components: {
    LeftNavBar,
    TodoCard,
    TodoAdd,
    DailyCommit,
  },
  computed: {
    ...todoListHelper.mapState(["todoLists"]),
  },
  created() {
    this.setTodoList();
  },
  methods: {
    ...todoListHelper.mapActions(["setTodoList"]),
    toggleCreate() {
      this.isCreateTodo = !this.isCreateTodo;
    },
  },
};
</script>
