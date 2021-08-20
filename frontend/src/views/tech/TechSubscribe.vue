<template>
  <v-row class="justify-center">
    <v-col cols="12" md="2" v-show="$vuetify.breakpoint.mdAndUp"
      ><left-nav-bar></left-nav-bar
    ></v-col>
    <v-col
      cols="12"
      v-show="$vuetify.breakpoint.smAndDown"
      style="padding: 0 !important"
      ><top-nav-bar></top-nav-bar
    ></v-col>
    <v-col cols="12" sm="12" md="10">
      <v-col
        class="text-h4 font-weight-bold blue-grey--text"
        :class="{ 'text-h5': $vuetify.breakpoint.xs }"
        cols="12"
        >구독 설정</v-col
      >
      <v-col cols="12" class="d-flex pt-0 pb-0">
        <button class="mr-auto" @click="goTech()">
          <img src="@/assets/images/back-btn.svg" alt="back" />
        </button>
        <v-btn-toggle v-model="type" dense borderless mandatory group>
          <v-btn
            value="my"
            text
            dark
            color="cyan lighten-1"
            @click="setSubscribeBlogList()"
            class="ml-auto"
          >
            <v-icon left color="cyan"> mdi-checkbox-blank-circle </v-icon
            ><b>내 구독 목록</b></v-btn
          >
          <v-btn
            value="all"
            text
            dark
            color="blue-grey"
            @click="setAllBlogList()"
          >
            <v-icon left color="blue-grey"> mdi-checkbox-blank-circle </v-icon
            ><b>전체 목록</b></v-btn
          >
        </v-btn-toggle>
      </v-col>
      <!-- 블로그 목록 -->
      <v-col v-for="item in blogList" :key="item.blogid" cols="12" class="mr-2">
        <v-card
          class="mb-2 justify-center"
          elevation="4"
          :class="{ subscribe: item.issubscribe, all: !item.issubscribe }"
          :href="item.link"
          target="_blank"
        >
          <div class="d-flex flex-no-wrap justify-space-between">
            <!-- 이미지 -->
            <v-avatar class="ma-3" size="100" tile>
              <v-img
                contain
                :src="require(`@/assets/images/${item.blogname}.png`)"
              ></v-img>
            </v-avatar>
            <!-- 이름 -->
            <v-card-title
              class="text-center"
              :class="{ 'text-subtitle-1': $vuetify.breakpoint.xs }"
              v-text="item.description"
            ></v-card-title>
            <!-- 구독 버튼 -->
            <v-card-actions class="d-flex flex-column justify-center mr-1">
              <span :class="{ 'text-subtitle-2': $vuetify.breakpoint.xs }"
                >구독</span
              >
              <v-btn
                v-if="item.issubscribe"
                fab
                icon
                height="40px"
                right
                width="40px"
                @click.prevent
                @click="unSubscribeBlog(item.blogid)"
              >
                <v-icon>mdi-star</v-icon>
              </v-btn>

              <v-btn
                v-else
                fab
                icon
                height="40px"
                right
                width="40px"
                @click.prevent
                @click="subscribeBlog(item.blogid)"
                ><v-icon>mdi-star-outline</v-icon></v-btn
              >
            </v-card-actions>
          </div>
        </v-card>
      </v-col>
    </v-col>
  </v-row>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TopNavBar from "@/components/common/TopNavBar.vue";

import { createNamespacedHelpers } from "vuex";
const techHelper = createNamespacedHelpers("techStore");

export default {
  data() {
    return {
      type: "all",
    };
  },
  components: {
    LeftNavBar,
    TopNavBar,
  },
  created() {
    this.setAllBlogList();
  },
  computed: {
    ...techHelper.mapState(["blogList"]),
  },
  methods: {
    ...techHelper.mapActions([
      "setAllBlogList",
      "setSubscribeBlogList",
      "subscribeBlog",
      "unSubscribeBlog",
    ]),
    goTech() {
      this.$router.push({ name: "Tech" });
    },
  },
};
</script>

<style lang="scss" scoped>
.subscribe {
  border: 1px solid rgba(0, 0, 0, 0.2);
  box-shadow: 6px 6px 3px rgb(#00bcd4, 0.4) !important;
}

.all {
  border: 1px solid rgba(0, 0, 0, 0.2);
  box-shadow: 6px 6px 3px rgb(#607d8b, 0.4) !important;
}
</style>
