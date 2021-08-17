<template>
  <v-container>
    <v-row dense>
      <v-col cols="12">
        <!-- default mode - today -->
        <v-card
          color="blue-grey lighten-5 rounded-xl"
          v-if="!isEdit && !isOngoing && (tempus == 'today' || tempus == 'future')"
        >
          <div>
            <div>
              <v-row>
                <!-- 카테고리 명 -->
                <todo-card-category :todoCategory="item.category" />
                <v-col class="text-caption mr-3" v-text="item.todoid" align="right" />
              </v-row>

              <div class="d-flex justify-end">
                <v-btn icon @click="activateEditMode">
                  <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon color="red" @click="showDialog">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </div>

              <v-card-title class="text-h5 ml-4" v-text="item.title" />

              <v-card-actions class="justify-space-between align-end pt-0">
                <div
                  class="d-flex text-caption align-center font-weight-bold ml-2"
                  :class="{ 'future-target-time': tempus == 'future' }"
                >
                  <img src="@/assets/images/todo-card-clock.svg" class="mr-1" />{{ item.goaltime }}
                  /
                  <span
                    :class="{
                      'success-text': this.item.goaltime <= this.item.completetime,
                      'fail-text': this.item.goaltime > this.item.completetime,
                    }"
                    >&nbsp;{{ completetime }}</span
                  >
                </div>
                <img
                  v-if="this.item.goaltime <= this.item.completetime"
                  class="mobile-btn-size mb-0"
                  src="@/assets/images/todo-create-ok.svg"
                />
                <img
                  v-if="!this.$parent.isAnyOneGoing && tempus == 'today'"
                  class="mobile-btn-size mb-0"
                  src="@/assets/images/start-btn.svg"
                  @click="[(isOngoing = true), start()]"
                />
              </v-card-actions>
            </div>
          </div>
        </v-card>

        <!-- default mode - past-->
        <v-card
          color="blue-grey lighten-5 rounded-xl"
          v-if="!isEdit && !isOngoing && tempus == 'past'"
        >
          <div>
            <div>
              <v-row>
                <!-- 카테고리 명 -->
                <todo-card-category :todoCategory="item.category" />
                <v-col class="text-caption mr-3" v-text="item.todoid" align="right" />
              </v-row>

              <div class="d-flex justify-end">
                <v-btn icon color="blue" @click="moveToday">
                  <v-icon>mdi-plus</v-icon>
                </v-btn>
              </div>

              <v-card-title class="text-xs-h6 text-lg-h5 ml-4" v-text="item.title" />

              <v-card-actions class="justify-space-between align-end pt-0">
                <div class="d-flex text-caption align-center ml-2 font-weight-bold">
                  <img src="@/assets/images/todo-card-clock.svg" class="mr-1" />{{ item.goaltime }}
                  /
                  <span
                    :class="{
                      'success-text': this.item.goaltime <= this.item.completetime,
                      'fail-text': this.item.goaltime > this.item.completetime,
                    }"
                    >&nbsp;{{ completetime }}</span
                  >
                </div>
                <img
                  v-if="this.item.goaltime > this.item.completetime"
                  class="mobile-btn-size mb-0"
                  src="@/assets/images/todo-create-no.svg"
                />
                <img v-else class="mobile-btn-size mb-0" src="@/assets/images/todo-create-ok.svg" />
              </v-card-actions>
            </div>
          </div>
        </v-card>

        <!--ongoing mode -->
        <v-card color="cyan lighten-3 rounded-xl" v-if="!isEdit && isOngoing">
          <div>
            <div>
              <v-row>
                <!-- 카테고리 명 -->
                <todo-card-category :todoCategory="item.category" />
                <v-col class="text-caption mr-3" v-text="item.todoid" align="right" />
              </v-row>

              <v-col cols="12" class="text-h6 text-center pa-1" v-text="item.title"></v-col>

              <div class="time">
                {{ formattedElapsedTime }}
              </div>
              <v-card-actions class="justify-space-between align-end pt-0">
                <div class="d-flex text-caption align-center ml-2 font-weight-bold">
                  <img src="@/assets/images/todo-card-clock.svg" class="mr-1" />{{ item.goaltime }}
                </div>
                <v-btn fab icon right>
                  <img class="mobile-btn-size" src="@/assets/images/pause.svg" @click="stop" />
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
    <v-dialog max-width="500" v-model="isModal">
      <delete-todo-modal @hide="hideDialog" @submit="deleteTodo(item.todoid)" />
    </v-dialog>
  </v-container>
</template>

<script>
import TodoAdd from "./TodoAdd.vue";
import TodoCardCategory from "./TodoCardCategory.vue";
import DeleteTodoModal from "./DeleteTodoModal.vue";
import { createNamespacedHelpers } from "vuex";
const todoHelper = createNamespacedHelpers("todoStore");

export default {
  data() {
    return {
      isModal: false,
      isEdit: false,
      isOngoing: false,
      idDone: false,
      timer: undefined,
      time: 0,
      tempus: "today",
      completetime: "",
    };
  },
  components: { TodoCardCategory, TodoAdd, DeleteTodoModal },
  name: "TodoCard",
  props: {
    item: {
      type: Object,
    },
  },
  methods: {
    showDialog() {
      this.isModal = true;
    },
    hideDialog() {
      this.isModal = false;
    },
    activateEditMode() {
      this.isEdit = true;
    },
    deActivateEditMode() {
      this.isEdit = false;
    },
    ...todoHelper.mapActions([
      "deleteTodo",
      "moveTodayAdd",
      "moveTodayDelete",
      "deleteTodo",
      "stateChange",
      "forceQuit",
      "updateTime",
      "setTime",
      "setGoingTime",
      "setId",
      "setTimeFQ",
      "setGoingTimeFQ",
      "setIdFQ",
    ]),
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
    ...todoHelper.mapState([
      "exitTime",
      "goingTime",
      "ongoingId",
      "exitTimeFQ",
      "goingTimeFQ",
      "ongoingIdFQ",
    ]),
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

<style lang="scss" scoped>
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

.future-target-time {
  margin-top: 30px;
}

.success-text {
  color: #4caf50;
}

.fail-text {
  color: #e53935;
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
  .mobile-btn-size {
    width: 35px;
    height: 35px;
    margin-bottom: 5px;
  }
  .future-target-time {
    margin-top: 20px;
  }
}
</style>
