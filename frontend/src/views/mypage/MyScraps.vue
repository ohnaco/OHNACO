<template>
  <div>
    <div>
      <MyPageProfile
        :info="mypageInfo.info"
        :commit="mypageInfo.commit"
      />
    </div>
    <v-container class="mt-5" style="width: 70%">
      내 스크랩 ({{ mypageInfo.scrapCount }})
      <v-divider></v-divider>
        <infinite-loading @infinite="infiniteHandler" spinner="circles"></infinite-loading>
      <v-divider></v-divider>
    </v-container>
  </div>
</template>

<script>
import MyPageProfile from "@/components/mypage/MyPageProfile.vue";

import InfiniteLoading from 'vue-infinite-loading';
import MyPage from "@/api/MyPage";

import { createNamespacedHelpers } from 'vuex';
const mypageHelper = createNamespacedHelpers('mypageStore');

export default {
  name: 'MyPage',
  components: {
    MyPageProfile,
    InfiniteLoading,
  },
  data: function () {
    return {
      pagenum: 1,
      myScraps: []
    }
  },
  created() {
    this.getMyPage()
  },
  computed: {
    ...mypageHelper.mapState(['mypageInfo']),
  },
  methods: {
    ...mypageHelper.mapActions(['getMyPage']),
    infiniteHandler($state) {
      MyPage.requestMyScraps(
        this.pagenum,
        (res) => {
          setTimeout(() => {
            if (res.data.length) {
              this.pagenum += 1;
              this.myScraps = this.myScraps.concat(res.data)
              $state.loaded();
            } else {
              $state.complete();
            }
          },1000)
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
};
</script>