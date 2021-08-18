<template>
  <div>
    <div>
      <MyPageProfile :info="mypageInfo.info" :commit="mypageInfo.commit" />
    </div>
    <v-container style="width: 70%">
      <div class="mt-10 mb-3 d-flex justify-space-between">
        내 스크랩 ({{ mypageInfo.scrapCount }})
        <button @click="goback">
          <img src="@/assets/images/mypage-back-btn.svg" alt="back-btn" />
        </button>
      </div>
      <v-divider></v-divider>
      <v-row>
        <v-col
          v-for="scrap in myScraps"
          :key="scrap.articleid"
          cols="12"
          sm="12"
          md="6"
          xl="4"
          class="d-flex justify-center mb-2"
        >
          <TechCard :item="scrap" />
        </v-col>
      </v-row>
      <infinite-loading @infinite="infiniteHandler" spinner="circles"></infinite-loading>
      <v-divider></v-divider>
    </v-container>
  </div>
</template>

<script>
import MyPageProfile from "@/components/mypage/MyPageProfile.vue";
import TechCard from "@/components/tech/TechCard.vue";

import InfiniteLoading from "vue-infinite-loading";
import MyPage from "@/api/MyPage";

import { createNamespacedHelpers } from "vuex";
const mypageHelper = createNamespacedHelpers("mypageStore");

export default {
  name: "MyPage",
  components: {
    MyPageProfile,
    TechCard,
    InfiniteLoading,
  },
  data: function () {
    return {
      pageno: 1,
      myScraps: [],
    };
  },
  created() {
    this.getMyPage();
  },
  computed: {
    ...mypageHelper.mapState(["mypageInfo"]),
  },
  methods: {
    ...mypageHelper.mapActions(["getMyPage"]),
    goback: function () {
      this.$router.push({ name: "MyPage" });
    },
    infiniteHandler($state) {
      MyPage.requestMyScraps(
        this.pageno,
        ({ data }) => {
          setTimeout(() => {
            console.log(data.scrap);
            if (data.scrap.length) {
              this.pageno += 1;
              this.myScraps.push(...data.scrap);
              $state.loaded();
            } else {
              $state.complete();
            }
          }, 100);
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
};
</script>
