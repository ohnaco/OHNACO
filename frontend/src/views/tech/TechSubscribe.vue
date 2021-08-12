<template>
  <v-row class="justify-center">
    <v-col cols="12" md="2" v-show="$vuetify.breakpoint.mdAndUp"
      ><left-nav-bar></left-nav-bar
    ></v-col>
    <v-col cols="12" v-show="$vuetify.breakpoint.smAndDown"><top-nav-bar></top-nav-bar></v-col>
    <v-col cols="12" sm="12" md="10" class="justify-space-between">
      <v-row dense>
        <v-col v-for="item in blogList" :key="item.blogid" cols="12" sm="12" md="6">
          <v-card>
            <div class="d-flex flex-no-wrap justify-space-between">
              <v-avatar class="ma-3" size="125" tile>
                <v-img :src="src"></v-img>
              </v-avatar>
              <div>
                <v-card-title class="text-h5" v-text="item.blogname"></v-card-title>
                <v-card-subtitle v-text="item.description"></v-card-subtitle>
              </div>
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
                  class="ml-2 mt-5"
                  fab
                  icon
                  height="40px"
                  right
                  width="40px"
                  @click="subscribeBlog(item.blogid)"
                  ><v-icon>mdi-star-outline</v-icon></v-btn
                >

                <v-btn
                  class="ml-2 mt-5"
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
  </v-row>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TopNavBar from "@/components/common/TopNavBar.vue";

export default {
  components: {
    LeftNavBar,
    TopNavBar,
  },
  data() {
    return {
      selected: 1,
      src: "http://itimg.chosun.com/sitedata/image/202105/06/2021050601877_0.png",
    };
  },
};
</script>
