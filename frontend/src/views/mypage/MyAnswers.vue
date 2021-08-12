<template>
  <div>
    <div>
      <MyPageProfile
        :info="mypageInfo.info"
        :commit="mypageInfo.commit"
      />
    </div>
    <v-container class="mt-5" style="width: 70%">
      내 답변 ({{ mypageInfo.answerCount }})
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
    InfiniteLoading
  },
  data: function () {
    return {
      pagenum: 1,
      myAnswers: []
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
      MyPage.requestMyAnswers(
        this.pagenum,
        (res) => {
          setTimeout(() => {
            if (res.data.length) {
              this.pagenum += 1;
              this.myAnswers = this.myAnswers.concat(res.data)
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