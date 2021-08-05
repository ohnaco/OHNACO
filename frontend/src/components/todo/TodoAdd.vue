<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-card color="blue-grey lighten-5 rounded-xl">
          <div>
            <v-form ref="form">
              <div>
                <v-col cols="3" class="pb-0">
                  <!-- 카테고리 select box -->
                  <v-select
                    v-model="newTodo.categoryid"
                    :items="categoryItems"
                    label="분류"
                    color="cyan"
                    dense
                    outlined
                    hide-details
                  ></v-select>
                </v-col>
              </div>
              <div>
                <v-col cols="12" class="pt-0">
                  <v-text-field
                    v-model="newTodo.title"
                    color="cyan"
                    label="할 일을 입력해주세요."
                    counter
                    maxlength="50"
                    full-width
                    single-line
                  >
                  </v-text-field>
                </v-col>
              </div>
              <v-row>
                <v-col md="9">
                  <vue-timepicker
                    format="HH:mm:ss"
                    v-model="newTodo.goaltime"
                    :minute-interval="5"
                    :second-interval="10"
                    manual-input
                    close-on-complete
                    class="ml-3"
                  >
                    <template v-slot:icon>
                      <img src="@/assets/images/todo-card-clock.svg" />
                    </template>
                  </vue-timepicker>
                </v-col>
                <v-col class="d-flex justify-end">
                  <v-btn icon x-large class="mr-2"
                    ><img src="@/assets/images/todo-create-no.svg"
                  /></v-btn>
                  <v-btn icon x-large class="mr-2"
                    ><img src="@/assets/images/todo-create-ok.svg"
                  /></v-btn>
                </v-col>
              </v-row>
            </v-form>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import VueTimepicker from "vue2-timepicker/src/vue-timepicker.vue";

export default {
  name: "TodoAdd",
  components: { VueTimepicker },
  data() {
    return {
      //   카테고리도 api로 불러오는 방식이면 좋을 것 같음.
      categoryItems: [
        { value: "algorithm", text: "알고리즘" },
        { value: "cs", text: "CS" },
        { value: "framework", text: "프레임워크" },
        { value: "certificate", text: "자격증" },
        { value: "etc", text: "기타" },
      ],
      newTodo: {
        userid: "",
        categoryid: "",
        title: "",
        goaltime: "",
      },
    };
  },
};
</script>
