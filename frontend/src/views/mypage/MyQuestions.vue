<template>
  <div>
    <div>
      <MyPageProfile
        :info="mypageInfo.info"
        :commit="mypageInfo.commit"
      />
    </div>
    <v-container class="mt-5" style="width: 70%">
      내 질문 ({{ mypageInfo.questionCount }})
      <v-divider></v-divider>
      <QuestionCard
      v-for="(question, i) in myQuestions"
      :key="i"
      />
      <infinite-loading @infinite="infiniteHandler" spinner="circles"></infinite-loading>
      <v-divider></v-divider>
    </v-container>
  </div>
</template>

<script>
import MyPageProfile from "@/components/mypage/MyPageProfile.vue";
import QuestionCard from "@/components/devtalk/QuestionCard.vue";

import InfiniteLoading from 'vue-infinite-loading';
import MyPage from "@/api/MyPage";

import { createNamespacedHelpers } from 'vuex';
const mypageHelper = createNamespacedHelpers('mypageStore');

export default {
  name: 'MyPage',
  components: {
    MyPageProfile,
    QuestionCard,
    InfiniteLoading
  },
  data: function () {
    return {
      pagenum: 1,
      myQuestions: []
    }
  },
  created() {
    this.getMyPage();
    // this.getQuestions(this.pagenum);
  },
  computed: {
    ...mypageHelper.mapState(['mypageInfo']),
  },
  methods: {
    ...mypageHelper.mapActions(['getMyPage']),
    infiniteHandler($state) {
      MyPage.requestMyQuestions(
        this.pagenum,
        (res) => {
          setTimeout(() => {
            if (res.data.length) {
              this.pagenum += 1;
              this.myQuestions = this.myQuestions.concat(res.data)
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
