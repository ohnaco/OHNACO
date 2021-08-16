<template>
  <v-layout>
    <v-row dense>
      <v-col cols="12" md="2" v-show="$vuetify.breakpoint.mdAndUp"
        ><left-nav-bar></left-nav-bar
      ></v-col>
      <v-col cols="12" v-show="$vuetify.breakpoint.smAndDown"><top-nav-bar></top-nav-bar></v-col>
      <v-col cols="12" sm="12" md="7" class="d-flex flex-column">
        <div class="d-flex align-center text-h4 ma-5">
          <img src="@/assets/images/todo-icon.svg" class="mr-2 pencil-img" />
          <div class="text-sm-h6 text-md-h4">To Do List</div>
          <img
            src="@/assets/images/calendar_black.svg"
            class="mobile_calendar_btn"
            @click="mobileCalendarOn"
          />
        </div>

        <div class="d-flex flex-row align-center ma-5">
          <h4 v-text="date"></h4>
          <daily-commit v-if="isDateToday" />
        </div>
        <CalendarSmall
          v-if="isMobileCanlendar"
          @modalOn_child="modalOn"
          @todoDate="moveDate"
          style="width: 100%"
          v-model="date"
          class="mobileCalendar"
        ></CalendarSmall>
        <div v-if="!isMobileCanlendar">
          <todo-card
            v-for="todo in todoLists"
            :key="todo.todoid"
            :item="todo"
            @trueChange="tChange()"
            @falseChange="fChange()"
          />
        </div>
        <todo-add @finish-create="toggleCreate" v-if="isCreateTodo" :date="date" />
        <button @click="toggleCreate" v-if="!isCreateTodo">
          <img src="@/assets/images/todo-add-btn.svg" class="todo_add" />
        </button>
      </v-col>
      <v-col cols="12" sm="12" md="3">
        <!-- 우측달력 -->
        <CalendarSmall
          @modalOn_child="modalOn"
          @todoDate="moveDate"
          style="width: 100%"
          v-model="date"
          class="smallCalendar"
        ></CalendarSmall>
        <!--우측달력 끝-->
      </v-col>
    </v-row>
    <!--큰달력 모달-->
    <ModalView v-if="this.isModal" @close-modal="isModal = false">
      <CalendarLarge @todoDate="moveDateAndCloseModal" />
    </ModalView>
    <!--큰달력 모달 끝-->
  </v-layout>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TopNavBar from "@/components/common/TopNavBar.vue";
import TodoAdd from "@/components/todo/TodoAdd.vue";
import TodoCard from "@/components/todo/TodoCard.vue";
import CalendarSmall from "@/components/todo/CalendarSmall.vue";
import CalendarLarge from "@/components/todo/CalendarLarge.vue";
import ModalView from "@/components/common/ModalView.vue";
import DailyCommit from "@/components/todo/DailyCommit.vue";

import { createNamespacedHelpers } from "vuex";
const todoListHelper = createNamespacedHelpers("todoStore");

export default {
  data() {
    return {
      isCreateTodo: false,
      isModal: false,
      date: this.$moment().format("YYYY-MM-DD"),
      isDateToday: null,
      isAddOnGoing: false,
      isAnyOneGoing: false,
      isMobileCanlendar: false,
    };
  },
  components: {
    LeftNavBar,
    TopNavBar,
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
    this.isDateToday = this.isToday(this.date);
  },
  methods: {
    ...todoListHelper.mapActions(["setTodoList"]),
    toggleCreate() {
      this.isCreateTodo = !this.isCreateTodo;
    },
    modalOn() {
      this.isModal = true;
    },
    moveDate(date) {
      this.date = date;
    },
    moveDateAndCloseModal(date) {
      this.isModal = false;
      this.date = date;
    },
    isToday(date) {
      const today = this.$moment().format("YYYY-MM-DD");
      return date === today;
    },
    tChange() {
      this.isAnyOneGoing = true;
    },
    fChange() {
      this.isAnyOneGoing = false;
    },
    mobileCalendarOn() {
      this.isMobileCanlendar = !this.isMobileCanlendar;
    },
  },
  watch: {
    date: function () {
      this.setTodoList(this.date);
      this.isDateToday = this.isToday(this.date);
    },
  },
};
</script>

<style lang="scss" scoped>
.commit {
  width: 164px;
  height: 35px;
}
.todo_add {
  width: 63px;
  height: 63px;
}
.mobile_calendar_btn {
  position: absolute;
  right: 20px;
  width: 25px;
  height: 25px;
  display: none;
}
@media (max-width: 768px) {
  .h2,
  h2 {
    font-size: 14px !important;
    margin-bottom: 0px !important;
  }
  .pencil-img {
    width: 25px;
    height: 25px;
  }
  .todo_add {
    margin-top: 6px;
    width: 32px;
    height: 32px;
  }
  .mobile_calendar_btn {
    position: absolute;
    right: 20px;
    width: 25px;
    height: 25px;
    display: block;
  }
  .v-application .text-h4 {
    font-size: 1.5rem !important;
    font-weight: 400;
    line-height: 2.5rem;
    letter-spacing: 0.0073529412em !important;
    font-family: "GmarketSans" !important;
  }
  .v-application .ma-5 {
    margin-bottom: 5px !important;
    margin-top: 5px !important;
  }
  .container {
    padding: 6px;
  }
  .col-md-3 {
    flex: 0 0 auto;
    width: 40% !important;
  }
  .smallCalendar {
    display: none;
  }
}
</style>
