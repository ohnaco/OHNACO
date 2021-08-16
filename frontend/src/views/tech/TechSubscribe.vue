<template>
  <v-row class="justify-center">
    <v-col cols="12" md="2" v-show="$vuetify.breakpoint.mdAndUp"
      ><left-nav-bar></left-nav-bar
    ></v-col>
    <v-col cols="12" v-show="$vuetify.breakpoint.smAndDown"><top-nav-bar></top-nav-bar></v-col>
    <v-col cols="12" sm="12" md="10" class="justify-space-between">
      <v-row dense>
        <v-col class="text-h4 font-weight-bold" cols="12">구독 설정</v-col>
        <v-col cols="12" class="d-flex justify-end">
          <v-btn text dark color="cyan lighten-1" @click="setSubscribeBlogList()">
            <v-icon left color="cyan"> mdi-checkbox-blank-circle </v-icon><b>내 구독 목록</b></v-btn
          >
          <v-btn text dark color="blue-grey" @click="setAllBlogList()">
            <v-icon left color="blue-grey"> mdi-checkbox-blank-circle </v-icon
            ><b>전체 목록</b></v-btn
          >
        </v-col>
        <v-col v-for="item in blogList" :key="item.blogid" cols="12">
          <v-card class="mb-2" elevation="4">
            <div class="d-flex flex-no-wrap justify-space-between">
              <v-avatar class="ma-3" size="100" tile>
                <v-img contain :src="require(`@/assets/images/${item.blogname}.png`)"></v-img>
              </v-avatar>
              <v-card-title class="text-h6" v-text="item.description"></v-card-title>
              <v-card-actions>
                <v-btn
                  v-if="item.issubscribe"
                  class="ml-2 mt-3"
                  fab
                  icon
                  height="40px"
                  right
                  width="40px"
                  @click="unSubscribeBlog(item.blogid)"
                >
                  <v-icon>mdi-star</v-icon>
                </v-btn>

                <v-btn
                  v-else
                  class="ml-2 mt-3"
                  fab
                  icon
                  height="40px"
                  right
                  width="40px"
                  @click="subscribeBlog(item.blogid)"
                  ><v-icon>mdi-star-outline</v-icon></v-btn
                >

                <v-btn
                  class="ml-2 mt-3"
                  fab
                  icon
                  height="40px"
                  right
                  width="40px"
                  :href="item.link"
                  target="_blank"
                  ><v-icon>mdi-link</v-icon></v-btn
                >
              </v-card-actions>
            </div>
          </v-card>
        </v-col>
      </v-row>
    </v-col>
    <v-col>
      <button class="mr-15" @click="goTech()">
        <img src="@/assets/images/back-btn.svg" alt="back" />
      </button>
    </v-col>
  </v-row>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TopNavBar from "@/components/common/TopNavBar.vue";

import { createNamespacedHelpers } from "vuex";
const techHelper = createNamespacedHelpers("techStore");

export default {
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
