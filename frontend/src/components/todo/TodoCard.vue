<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <!-- default mode - today -->
        <v-card
          color="blue-grey lighten-5 rounded-xl"
          v-if="!isEdit && !isOngoing && tempus == 'today'"
        >
          <div>
            <div>
              <v-layout row>
                <!-- 카테고리 명 -->
                <todo-card-category :todoCategory="item.category" />
                <v-col
                  class="text-caption mr-3"
                  v-text="item.todoid"
                  align="right"
                />
              </v-layout>

              <div class="d-flex justify-end">
                <v-btn icon @click="activateEditMode">
                  <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon color="pink" @click="deleteTodo(item.todoid)">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </div>

              <v-card-title class="text-h5 ml-4" v-text="item.title" />

              <v-card-actions class="justify-space-between align-end pt-0">
                <div class="d-flex text-caption align-center ml-2">
                  <img
                    src="@/assets/images/todo-card-clock.svg"
                    class="mr-1"
                  />{{ item.goaltime }} / {{ completetime }}
                </div>
                <v-btn
                  v-if="this.item.goaltime <= this.item.completetime"
                  class="ml-2 mt-3"
                  fab
                  icon
                  right
                >
                  <img src="@/assets/images/todo-create-ok.svg" />
                </v-btn>
                <v-btn fab icon right v-if="!this.$parent.isAnyOneGoing">
                  <img
                    class="start_btn"
                    src="@/assets/images/start-btn.svg"
                    @click="[(isOngoing = true), start()]"
                  />
                </v-btn>
              </v-card-actions>
            </div>
          </div>
        </v-card>

        <!-- default mode -past-->
        <v-card
          color="blue-grey lighten-5 rounded-xl"
          v-if="!isEdit && !isOngoing && tempus == 'past'"
        >
          <div>
            <div>
              <v-layout row>
                <!-- 카테고리 명 -->
                <todo-card-category :todoCategory="item.category" />
                <v-col
                  class="text-caption mr-3"
                  v-text="item.todoid"
                  align="right"
                />
              </v-layout>

              <div class="d-flex justify-end">
                <v-btn icon color="blue" @click="moveToday">
                  <v-icon>mdi-plus</v-icon>
                </v-btn>
              </div>

              <v-card-title class="text-h5 ml-4" v-text="item.title" />

              <v-card-actions class="justify-space-between align-end pt-0">
                <div class="d-flex text-caption align-center ml-2">
                  <img
                    src="@/assets/images/todo-card-clock.svg"
                    class="mr-1"
                  />{{ item.goaltime }} / {{ completetime }}
                </div>
                <v-btn
                  v-if="this.item.goaltime > this.item.completetime"
                  class="ml-2 mt-3"
                  fab
                  icon
                  right
                >
                  <img src="@/assets/images/todo-create-no.svg" />
                </v-btn>
                <v-btn v-else class="ml-2 mt-3" fab icon right>
                  <img src="@/assets/images/todo-create-ok.svg" />
                </v-btn>
              </v-card-actions>
            </div>
          </div>
        </v-card>

        <!-- default mode future-->
        <v-card
          color="blue-grey lighten-5 rounded-xl"
          v-if="!isEdit && !isOngoing && tempus == 'future'"
        >
          <div>
            <div>
              <v-layout row>
                <!-- 카테고리 명 -->
                <todo-card-category :todoCategory="item.category" />
                <v-col
                  class="text-caption mr-3"
                  v-text="item.todoid"
                  align="right"
                />
              </v-layout>

              <div class="d-flex justify-end">
                <v-btn icon @click="activateEditMode">
                  <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon color="pink" @click="deleteTodo(item.todoid)">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </div>

              <v-card-title class="text-h5 ml-4" v-text="item.title" />

              <v-card-actions class="justify-space-between align-end pt-0">
                <div
                  class="d-flex text-caption align-center ml-2"
                  style="height: 76px"
                >
                  <img
                    src="@/assets/images/todo-card-clock.svg"
                    class="mr-1"
                  />{{ item.goaltime }} / {{ completetime }}
                </div>
              </v-card-actions>
            </div>
          </div>
        </v-card>

        <!--ongoing mode -->
        <v-card color="cyan lighten-3 rounded-xl" v-if="!isEdit && isOngoing">
          <div>
            <div>
              <v-layout row>
                <!-- 카테고리 명 -->
                <todo-card-category :todoCategory="item.category" />
                <div style="height: 36px">
                  <div
                    style="font-family: GmarketSansLight; float: left"
                    class="ml-3 ongoing_title"
                  >
                    {{ item.title }}
                  </div>
                  <div
                    class="text-caption mr-3"
                    v-text="item.todoid"
                    align="right"
                  />
                </div>
              </v-layout>

              <div class="d-flex justify-end"></div>
              <div class="time">
                {{ formattedElapsedTime }}
              </div>
              <v-card-actions class="justify-space-between align-end pt-0">
                <div class="d-flex text-caption align-center ml-2">
                  <img
                    src="@/assets/images/todo-card-clock.svg"
                    class="mr-1"
                  />{{ item.goaltime }}
                </div>
                <v-btn v-if="false" class="ml-2 mt-3" fab icon right> </v-btn>
                <v-btn fab icon right>
                  <img class="start_btn" src="@/assets/images/pause.svg" @click="stop" />
                </v-btn>
              </v-card-actions>
            </div>
          </div>
        </v-card>

        <!-- edit mode -->
        <div v-if="isEdit && !isOngoing">
          <todo-add
            :editTodo="item"
            :editMode="isEdit"
            :todoid="item.todoid"
            @close-edit="deActivateEditMode"
          />
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TodoAdd from "./TodoAdd.vue";
import TodoCardCategory from "./TodoCardCategory.vue";
import { createNamespacedHelpers } from "vuex";
const todoHelper = createNamespacedHelpers("todoStore");

export default {
  data() {
    return {
      isEdit: false,
      isOngoing: false,
      idDone: false,
      timer: undefined,
      time: 0,
      tempus: "today",
      completetime: "",
    };
  },
  components: { TodoCardCategory, TodoAdd },
  name: "TodoCard",
  props: {
    item: {
      type: Object,
    },
  },
  methods: {
    activateEditMode() {
      this.isEdit = true;
    },
    deActivateEditMode() {
      this.isEdit = false;
    },
    ...todoHelper.mapActions(["deleteTodo"]),
    ...todoHelper.mapActions(["moveTodayAdd"]),
    ...todoHelper.mapActions(["moveTodayDelete"]),
    ...todoHelper.mapActions(["deleteTodo"]),
    ...todoHelper.mapActions(["stateChange"]),
    ...todoHelper.mapActions(["forceQuit"]),
    ...todoHelper.mapActions(["updateTime"]),
    ...todoHelper.mapActions(["setTime"]),
    ...todoHelper.mapActions(["setGoingTime"]),
    ...todoHelper.mapActions(["setId"]),
    ...todoHelper.mapActions(["setTimeFQ"]),
    ...todoHelper.mapActions(["setGoingTimeFQ"]),
    ...todoHelper.mapActions(["setIdFQ"]),
    start() {
      this.parentTrue();
      this.time =
        (this.completetime.substr(0, 2) * 3600 +
          this.completetime.substr(3, 2) * 60 +
          this.completetime.substr(6, 2) * 1) *
        1000;
      this.stateChange(this.item.todoid);
      this.timer = setInterval(() => {
        this.time += 1000;
      }, 1000);
      this.setTimeFQ(this.$moment());
      this.setGoingTimeFQ(this.time);
      this.setIdFQ(this.item.todoid);
    },
    stop() {
      this.setId("");
      this.setIdFQ("");
      this.parentFalse();
      this.completetime = this.formattedElapsedTime;
      this.updateTime([this.item.todoid, this.formattedElapsedTime]);
      this.isOngoing = false;
      clearInterval(this.timer);
    },
    tempusCheck() {
      const today = this.$moment().format("YYYY-MM-DD");
      if (this.item.date == today) {
        this.tempus = "today";
      } else if (this.item.date < today) {
        this.tempus = "past";
      } else {
        this.tempus = "future";
      }
    },
    autoStartCheck() {
      if (this.item.todoid == this.ongoingId) {
        this.time += this.goingTime;
        this.time += this.$moment() - this.exitTime;
        this.isOngoing = true;
        this.parentTrue();
        this.timer = setInterval(() => {
          this.time += 1000;
        }, 1000);
      }
    },
    moveToday() {
      const newTodo = {
        categoryid: this.item.category.categoryid,
        title: this.item.title,
        goaltime: this.item.goaltime,
        completetime: "00:00:00",
        date: this.$moment().format("YYYY-MM-DD"),
      };
      this.moveTodayAdd(JSON.stringify(newTodo));
      //this.moveTodayDelete(this.item.todoid);
      //JSON.stringify(this.newTodo)
    },
    parentTrue() {
      this.$emit("trueChange");
    },
    parentFalse() {
      this.$emit("falseChange");
    },
  },
  computed: {
    ...todoHelper.mapState(["exitTime"]),
    ...todoHelper.mapState(["goingTime"]),
    ...todoHelper.mapState(["ongoingId"]),
    ...todoHelper.mapState(["exitTimeFQ"]),
    ...todoHelper.mapState(["goingTimeFQ"]),
    ...todoHelper.mapState(["ongoingIdFQ"]),
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.time / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    },
  },
  created() {
    this.completetime = this.item.completetime;
    this.tempusCheck();
  },
  mounted() {
    window.addEventListener("beforeunload", this.unLoadEvent);
    this.autoStartCheck();
  },
  beforeUnmount() {
    window.removeEventListener("beforeunload", this.unLoadEvent);
  },
  beforeDestroy() {
    if (this.isOngoing) {
      this.setId(this.item.todoid);
      this.setGoingTime(this.time);
      this.setTime(this.$moment());
      this.updateTime([this.item.todoid, this.formattedElapsedTime]);
    }
  },
};
</script>

<style>
.time {
  height: 64px;
  text-shadow: 0 2px 2px rgba(0, 0, 0, 0.25);
  font-family: GmarketSansBold;
  font-size: 3rem;
  font-weight: bold;
  font-stretch: normal;
  font-style: normal;
  line-height: 64px;
  letter-spacing: normal;
  text-align: center;
  color: #ffffff;
}

.pause {
  width: 10%;
  flex-grow: 0;
  margin: 0 4px 2px 0;
  padding: 5.2px 0px 5.2px 3px;
  float: left;
  object-fit: cover;
}

.stop {
  width: 10%;
  margin: 0 0 2px 4px;
  padding: 5.2px 0px 5.2px 3px;
  float: left;
  object-fit: cover;
}
.v-card__subtitle,
.v-card__text,
.v-card__title {
  padding: 12px;
}

.v-btn--fab.v-size--default {
  height: 56px;
  width: 56px;
}

.start_btn {
  height: 56px;
  width: 56px;
}
.ongoing_title {
  font-size: 22px;
}

.pause_img {
  width: 56px;
  height: 56px;
}

@media (max-width: 768px) {
  .v-card__subtitle,
  .v-card__text,
  .v-card__title {
    padding-bottom: 6px;
    padding-top: 0px;
  }
  .v-btn--fab.v-size--default {
    height: 28px;
    width: 28px;
  }
  .start_btn {
    height: 30px;
    width: 30px;
  }
  .v-btn--icon.v-size--default {
    height: 18px;
    width: 18px;
    margin-right: 12px;
  }
  .container,
  .container-fluid,
  .container-lg,
  .container-md,
  .container-sm,
  .container-xl,
  .container-xxl {
    width: 100%;
    padding-right: 6px !important;
    padding-left: 6px !important;
    margin-right: auto;
    margin-left: auto;
  }
  .col-xl,
  .col-xl-auto,
  .col-xl-12,
  .col-xl-11,
  .col-xl-10,
  .col-xl-9,
  .col-xl-8,
  .col-xl-7,
  .col-xl-6,
  .col-xl-5,
  .col-xl-4,
  .col-xl-3,
  .col-xl-2,
  .col-xl-1,
  .col-lg,
  .col-lg-auto,
  .col-lg-12,
  .col-lg-11,
  .col-lg-10,
  .col-lg-9,
  .col-lg-8,
  .col-lg-7,
  .col-lg-6,
  .col-lg-5,
  .col-lg-4,
  .col-lg-3,
  .col-lg-2,
  .col-lg-1,
  .col-md,
  .col-md-auto,
  .col-md-12,
  .col-md-11,
  .col-md-10,
  .col-md-9,
  .col-md-8,
  .col-md-7,
  .col-md-6,
  .col-md-5,
  .col-md-4,
  .col-md-3,
  .col-md-2,
  .col-md-1,
  .col-sm,
  .col-sm-auto,
  .col-sm-12,
  .col-sm-11,
  .col-sm-10,
  .col-sm-9,
  .col-sm-8,
  .col-sm-7,
  .col-sm-6,
  .col-sm-5,
  .col-sm-4,
  .col-sm-3,
  .col-sm-2,
  .col-sm-1,
  .col,
  .col-auto,
  .col-12,
  .col-11,
  .col-10,
  .col-9,
  .col-8,
  .col-7,
  .col-6,
  .col-5,
  .col-4,
  .col-3,
  .col-2,
  .col-1 {
    width: 100%;
    padding: 6px !important;
  }
  .v-application .justify-end {
    justify-content: flex-end !important;
  }
  .row {
    margin-right: 0px !important;
    margin-left: 0px !important;
  }

  .ongoing_title {
    font-size: 14px;
  }
  .time {
    height: 36px;
    font-size: 2rem;
    line-height: 38px;
  }
  .pause_img {
    width: 25px;
    height: 25px;
  }
}
</style>
