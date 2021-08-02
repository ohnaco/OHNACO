<template>
  <v-container>
    <v-row>
      <v-col v-for="(item, i) in items" :key="i" cols="12">
        <v-card color="blue-grey lighten-5 rounded-xl">
          <div>
            <div>
              <v-layout row>
                <!-- 카테고리 명 -->
                <todo-card-category
                  :todoCategory="item.category" />

                <v-col class="text-caption mr-3" v-text="item.todoid" align="right" />
              </v-layout>

              <v-card-title
                class="text-h5 ml-4"
                v-text="item.title"
              />

              <v-card-actions class="justify-space-between align-end pt-0">
                <div class="d-flex text-caption align-center ml-2"><img src="@/assets/images/todo-card-clock.svg" class="mr-1">{{ item.goaltime }}</div>
                <v-btn
                  v-if="item.issuccess === false"
                  class="ml-2 mt-3"
                  fab
                  icon
                  right
                >
                  <img src="@/assets/images/start-btn.svg" />
                </v-btn>
              </v-card-actions>
            </div>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TodoCardCategory from "./TodoCardCategory.vue";
export default {
  components: { TodoCardCategory },
  name: "TodoCard",
  props: {
    items: { type: Array, default: () => [] },
  },
};
</script>
