<template>
  <v-layout>
    <v-row>
      <v-col cols="12" md="2" v-show="$vuetify.breakpoint.mdAndUp"
        ><left-nav-bar></left-nav-bar
      ></v-col>
      <v-col cols="12" v-show="$vuetify.breakpoint.smAndDown" style="padding: 0 !important"
        ><top-nav-bar></top-nav-bar
      ></v-col>
      <v-col cols="12" sm="12" md="7" class="d-flex flex-column todo-main">
        <b-container class="bv-example-row">
        <div
          class="d-flex align-center text-h4"
          style="display: flex; justify-content: space-between"
        >
          <p
            class="text-h4 font-weight-bold blue-grey--text"
            :class="{ 'text-h5': $vuetify.breakpoint.xs }"
          >
            To Do
          </p>
          <img
            src="@/assets/images/calendar_black.svg"
            class="mobile_calendar_btn"
            @click="mobileCalendarOn"
          />
          <img
            src="@/assets/images/calendar_black.svg"
            class="mobile_calendar_large_btn"
            @click="modalOn"
          />
        </div>

        <div class="d-flex flex-row align-center ml-5 mt-3">
          <!-- 날짜 -->
          <span
            class="text-h6 font-weight-bold"
            :class="{ 'text-body-1': $vuetify.breakpoint.xs }"
            v-text="date"
          ></span>
          <!-- 데일리 커밋 -->
          <daily-commit v-if="isDateToday && this.user.githubid !== ''" />
        </div>
        </b-container>
        <CalendarSmall
          v-if="isMobileCanlendar"
          @modalOn_child="modalOn"
          @todoDate="moveDate"
          style="width: 100%"
          v-model="date"
          class="mobileCalendar"
        ></CalendarSmall>
        <b-container>
        <div v-show="!isMobileCanlendar">
          <todo-card
            v-for="todo in todoLists"
            :key="todo.todoid"
            :item="todo"
            @trueChange="tChange()"
            @falseChange="fChange()"
          />
        </div>
        <!-- Todo 추가 컴포넌트 -->
        <todo-add @finish-create="toggleCreate" v-if="isCreateTodo" :date="date" />
        </b-container>
        <!-- Todo 추가 버튼 -->
        <div class="mx-auto" v-if="!isPast">
          <img
            src="@/assets/images/todo-add-btn.svg"
            class="todo_add mt-3"
            style="cursor: pointer"
            @click="toggleCreate"
            v-if="!isCreateTodo"
          />
        </div>
      </v-col>
      <v-col cols="12" sm="0" md="3" lg="3">
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
const userHelper = createNamespacedHelpers("userStore");

export default {
  data() {
    return {
      isCreateTodo: false,
      isModal: false,
      date: this.$moment().format("YYYY-MM-DD"),
      isDateToday: null,
      isPast: null,
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
    ...userHelper.mapState(["user"]),
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
    checkPast(date) {
      const today = this.$moment().format("YYYY-MM-DD");
      if (date < today) {
        this.isPast = true;
      } else  {
        this.isPast = false;
      }
    },
  },
  watch: {
    date: function () {
      this.setTodoList(this.date);
      this.isDateToday = this.isToday(this.date);
      this.checkPast(this.date);
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
  cursor: pointer;
}
.mobile_calendar_large_btn {
  width: 30px;
  height: 30px;
  margin-right: 20px;
  cursor: pointer;
}
@media (max-width: 767px) {
  .todo-main{
    padding-left:24px !important;
  }
}
@media (max-width: 767px) {
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
    width: 35px;
    height: 35px;
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
  .mobile_calendar_large_btn {
    display: none;
  }
}

@media (min-width: 768px) {
  .col-md-7 {
    flex: 0 0 auto;
    margin-left: 8.333333%;
  }
}

@media (min-width: 950px) {
  .col-md-7 {
    flex: 0 0 auto;
    margin-left: 0%;
  }
}
</style>
