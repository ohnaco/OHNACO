<template>
  <div>
    <!-- create mode -->
    <v-container v-if="!editMode">
      <v-row dense>
        <v-col cols="12" class="pt-0">
          <v-card color="blue-grey lighten-5 rounded-xl pa-2">
            <div>
              <v-form ref="form">
                <div>
                  <v-col cols="6" md="5" lg="4" class="pb-0">
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
                  <v-col cols="12" class="pt-0 pb-0">
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
                <v-row dense>
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
                    <v-btn
                      icon
                      class="mr-2"
                      @click="finishCreate"
                      :class="{ 'v-size--x-large': !$vuetify.breakpoint.xs }"
                      ><img src="@/assets/images/todo-create-no.svg" class="mobile-btn-size"
                    /></v-btn>
                    <v-btn
                      icon
                      class="mr-2"
                      @click="onCreateOK"
                      :class="{ 'v-size--x-large': !$vuetify.breakpoint.xs }"
                      ><img src="@/assets/images/todo-create-ok.svg" class="mobile-btn-size"
                    /></v-btn>
                  </v-col>
                </v-row>
              </v-form>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <!-- edit mode -->
    <v-row v-else>
      <v-col cols="12" class="pt-0">
        <v-card color="blue-grey lighten-5 rounded-xl pa-2">
          <div>
            <v-form ref="form">
              <div>
                <v-col cols="6" md="5" lg="4" class="pb-0">
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
                <v-col cols="12" class="pt-0 pb-0">
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
              <v-row dense>
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
                  <v-btn
                    icon
                    class="mr-2"
                    @click="finishCreate"
                    :class="{ 'v-size--x-large': !$vuetify.breakpoint.xs }"
                    ><img src="@/assets/images/todo-create-no.svg" class="mobile-btn-size"
                  /></v-btn>

                  <v-btn
                    icon
                    class="mr-2"
                    @click="onCreateOK"
                    :class="{ 'v-size--x-large': !$vuetify.breakpoint.xs }"
                    ><img src="@/assets/images/todo-create-ok.svg" class="mobile-btn-size"
                  /></v-btn>
                </v-col>
              </v-row>
            </v-form>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import VueTimepicker from "vue2-timepicker/src/vue-timepicker.vue";
import { createNamespacedHelpers } from "vuex";
const userHelper = createNamespacedHelpers("userStore");
const todoAddHelper = createNamespacedHelpers("todoStore");

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
        completetime: "",
      },
    };
  },
  props: {
    editTodo: {
      type: Object,
    },
    editMode: {
      type: Boolean,
    },
    date: {
      type: String,
    },
  },
  created() {
    this.newTodo.userid = this.user.userid;
    this.newTodo.date = this.date;
    this.newTodo.completetime = "00:00:00";

    if (this.editMode) {
      this.newTodo.categoryid = this.editTodo.category.categoryid;
      this.newTodo.title = this.editTodo.title;
      this.newTodo.goaltime = this.editTodo.goaltime;
      this.newTodo.date = this.editTodo.date;
    }
  },
  computed: {
    ...userHelper.mapState(["user"]),
  },
  methods: {
    ...todoAddHelper.mapActions(["createTodo", "updateTodo"]),
    finishCreate() {
      this.$refs.form.reset();
      if (this.editMode) this.$emit("close-edit");
      else this.$emit("finish-create");
    },
    onCreateOK() {
      if (this.editMode) {
        this.newTodo.todoid = this.editTodo.todoid;
        this.updateTodo(JSON.stringify(this.newTodo));
      } else this.createTodo(JSON.stringify(this.newTodo));
      this.finishCreate();
    },
  },
};
</script>

<style lang="scss" scoped>
@media (max-width: 576px) {
  .container {
    padding-right: 6px !important;
    padding-left: 6px !important;
  }
  .row {
    margin-right: 0px !important;
    margin-left: 0px !important;
  }
}
@media (max-width: 768px) {
  .v-text-field--outlined.v-input--dense .v-label {
    font-size: 14px !important;
    height: 20px !important;
    line-height: 20px !important;
  }
  .v-input.theme--light.v-text-field.v-text-field--full-width.v-text-field--single-line.v-text-field--is-booted {
    padding-top: 0px;
  }
  .v-select.v-input--dense .v-select__selection--comma {
    font-size: 14px !important;
  }
  .mobile-btn-size {
    width: 35px;
    height: 35px;
  }
  .v-text-field input {
    flex: 1 1 auto;
    line-height: 32px !important;
    padding: 0px !important;
    max-width: 100%;
    min-width: 0px;
    width: 100%;
    font-size: 1.2rem;
  }
  .v-input .v-label {
    height: 32px !important;
    line-height: 32px !important;
    letter-spacing: normal;
  }
  .theme--light.v-label {
    font-size: 1.2rem !important;
  }

  .v-counter {
    font-size: 0.6rem !important;
  }
  .v-text-field--outlined.v-input--dense .v-label--active {
    display: none;
  }
  .v-select.v-text-field:not(.v-text-field--single-line) input {
    margin-top: 0;
    display: none;
  }
}
</style>
