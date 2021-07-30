<template>
  <v-layout>
    <v-row>
      <v-col cols="2"><left-nav-bar></left-nav-bar></v-col>
      <v-col cols="7" class="d-flex flex-column">
        <v-flex class="d-flex align-end text-h4 mt-10 mb-5"
          ><img src="@/assets/images/todo-icon.svg" /><b>To Do List</b></v-flex
        >
        <todo-card :items="todoList"></todo-card>
      </v-col>
      <v-col cols="3">
        <!-- 달력 -->
      </v-col>
    </v-row>
  </v-layout>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TodoCard from "@/components/todo/TodoCard.vue";

export default {
  data() {
    return {
      todoList: [
        {
          id: "algo-0730-1",
          title: "SWEA 미로 찾기 풀기",
          category: { kor: "알고리즘", eng: "algorithm" },
          target: 3,
          start: false,
        },
        {
          id: "os-0730-2",
          title: "세마포어 vs 뮤텍스",
          category: { kor: "운영체제", eng: "operating-system" },
          target: 4,
          start: false,
        },
      ],
    };
  },
  components: {
    LeftNavBar,
    TodoCard,
  },
};
</script>
