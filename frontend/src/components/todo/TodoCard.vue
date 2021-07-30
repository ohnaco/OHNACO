<template>
  <v-container>
    <v-row>
      <v-col v-for="(item, i) in items" :key="i" cols="12">
        <v-card color="blue-grey lighten-5 rounded-xl">
          <div class="d-flex flex-no-wrap justify-space-between">
            <div>
              <!-- 카테고리 명 -->
              <todo-card-category
                :todoCategory="item.category"
              ></todo-card-category>

              <v-card-title
                class="text-h5 ml-4"
                v-text="item.title"
              ></v-card-title>

              <v-card-actions class="align-right">
                <v-btn
                  v-if="item.start === false"
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
