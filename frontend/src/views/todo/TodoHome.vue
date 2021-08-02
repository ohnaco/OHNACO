<template>
  <v-layout>
    <v-row>
      <v-col cols="2"><left-nav-bar></left-nav-bar></v-col>
      <v-col cols="7" class="d-flex flex-column">
        <div class="d-flex align-center text-h4 ma-5"
          ><img src="@/assets/images/todo-icon.svg" class="mr-2"/><b>To Do List</b></div
        >
        <todo-card :items="todoLists"></todo-card>
        <button><img src="@/assets/images/todo-add-btn.svg"></button>
      </v-col>
      <v-col cols="3">
        <!-- 달력 -->
      </v-col>
    </v-row>
  </v-layout>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TodoCard from "@/components/todo/TodoCard.vue";
import { createNamespacedHelpers } from 'vuex';
const todoListHelper = createNamespacedHelpers('todoStore');

export default {
  components: {
    LeftNavBar,
    TodoCard,
  },
  computed: {
    ...todoListHelper.mapState({
      todoLists: state => state.todoLists
    })
  },
  created() {
    this.setTodoList();
  },
  methods: {
    ...todoListHelper.mapActions([
      'setTodoList'
    ])
  }
};
</script>
