<template>
    <v-container>
        <v-row>
            <v-col cols="12">
                <!-- default mode -->
                <v-card color="blue-grey lighten-5 rounded-xl" v-if="!isEdit && !isOngoing">
                    <div>
                        <div>
                            <v-layout row>
                                <!-- 카테고리 명 -->
                                <todo-card-category :todoCategory="item.category" />
                                <v-col class="text-caption mr-3" v-text="item.todoid" align="right" />
                            </v-layout>

                            <div class="d-flex justify-end">
                                <v-btn icon color="blue">
                                    <v-icon>mdi-plus</v-icon>
                                </v-btn>
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
                                    <img src="@/assets/images/todo-card-clock.svg" class="mr-1" />{{ item.goaltime }}
                                </div>
                                <v-btn v-if="item.issuccess === false" class="ml-2 mt-3" fab icon right>
                                    <img src="@/assets/images/start-btn.svg" @click="[isOngoing=true, start()]" />
                                </v-btn>
                                <v-btn v-else class="ml-2 mt-3" fab icon right>
                                    <img src="@/assets/images/todo-create-ok.svg"/>
                                </v-btn>
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
                                <div>
                                  <div style="font-family:GmarketSansLight ; font-size:22px" class="pt-3">{{item.title}}</div>
                                </div>
                                <v-col class="text-caption mr-3" v-text="item.todoid" align="right" />
                            </v-layout>

                            <div class="d-flex justify-end" style="height:36px"></div>
                              <div style="height:64px" class="time">
                                {{formattedElapsedTime}}
                              </div>
                            <v-card-actions class="justify-space-between align-end pt-0">
                                <div class="d-flex text-caption align-center ml-2">
                                    <img src="@/assets/images/todo-card-clock.svg" class="mr-1" />{{ item.goaltime }}
                                </div>
                                <div v-if="!ispaused">
                                    <v-btn v-if="item.issuccess === false" class="ml-2 mt-3" fab icon right>
                                        <img src="@/assets/images/pause.png" alt="" style="width: 58px; height: 58px" v-on:click="stop" />
                                    </v-btn>
                                    <v-btn v-if="item.issuccess === false" class="ml-2 mt-3" fab icon right>
                                        <img src="@/assets/images/stop.png" alt="" style="width: 58px; height: 58px" v-on:click="reset" />
                                    </v-btn>
                                </div>
                                <div v-else>
                                    <v-btn v-if="item.issuccess === false" class="ml-2 mt-3" fab icon right>
                                        <img src="@/assets/images/start.png" alt="" style="width: 58px; height: 58px" v-on:click="start"/>
                                    </v-btn>
                                </div>
                            </v-card-actions>
                        </div>
                    </div>
                </v-card>

                <!-- edit mode -->
                <div v-if="isEdit && !isOngoing">
                    <todo-add :editTodo="item" :editMode="isEdit" :todoid="item.todoid" @close-edit="deActivateEditMode" />
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
      ispaused : true,
      timer: undefined,
      time: 0,
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
    start() {
      this.ispaused=!this.ispaused;
      this.timer = setInterval(() => {
        this.time += 1000;
      }, 1000);
    },
    stop() {
      this.ispaused=!this.ispaused;
      clearInterval(this.timer);
    },
    reset() {
      this.isOngoing=false;
      this.item.issuccess=true;
      //리셋하면 카테고리, 완료시간, 제목 디비로 보내기(완료했다고 업데이트)
      //그리고 컴포넌트 변경(원래카드로, 시작대신 완료체크된거뜨게)
    },
  },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.time / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    }
  },
};
</script>

<style>

.time {
  text-shadow: 0 2px 2px rgba(0, 0, 0, 0.25);
  font-family: GmarketSansBold;
  font-size: 3.0rem;
  font-weight: bold;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
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

</style>
