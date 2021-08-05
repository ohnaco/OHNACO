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
        <!-- 우측달력 -->
        <p></p>
        <p></p>
        <CalendarSmall @modalOn_child="modalOn" @todoDate="moveDate" style="width:100%" v-model="date"></CalendarSmall>
        <!--우측달력 끝-->
      </v-col>
    </v-row>
    <!--큰달력 모달-->
    <ModalView v-if="this.isModal" @close-modal="isModal = false">
      <CalendarLarge @todoDate="moveDateAndCloseModal"/>
    </ModalView>
    <!--큰달력 모달 끝-->
  </v-layout>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TodoAdd from "@/components/todo/TodoAdd.vue";
import TodoCard from "@/components/todo/TodoCard.vue";
import CalendarSmall from "@/components/todo/CalendarSmall.vue";
import CalendarLarge from "@/components/todo/CalendarLarge.vue";
import ModalView from '@/components/common/ModalView.vue';
import { createNamespacedHelpers } from "vuex";
import DailyCommit from "@/components/todo/DailyCommit.vue";
const todoListHelper = createNamespacedHelpers("todoStore");

export default {
  data() {
    return {
      isCreateTodo: false,
      isModal : false,
      date : this.$moment().format("YYYY-MM-DD"),
    };
  },
  components: {
    LeftNavBar,
    TodoCard,
    TodoAdd,
    DailyCommit,
    CalendarSmall,
    CalendarLarge,
    ModalView,
  },
  computed: {
    ...todoListHelper.mapState(["todoLists"]),
  },
  created() {
    this.setTodoList(this.date);
  },
  methods: {
    ...todoListHelper.mapActions(["setTodoList"]),
    toggleCreate() {
      this.isCreateTodo = !this.isCreateTodo;
    },
    modalOn() {
      this.isModal=true;
    },
    moveDate(date) {
      this.date=date;
    },
    moveDateAndCloseModal(date) {
      this.isModal=false;
      this.date=date;
    },

  },

  watch: {
    date: function () {
      console.log("바꼇당ㅎㅎ");
      this.setTodoList(this.date);
    }
  },
};
</script>
