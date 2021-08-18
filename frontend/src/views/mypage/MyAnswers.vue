<template>
  <div>
    <div>
      <MyPageProfile
        :info="mypageInfo.info"
        :commit="mypageInfo.commit"
      />
    </div>
    <v-container style="width: 60%">
      <div class="mt-10 mb-3 d-flex justify-space-between">
        내 답변 ({{ mypageInfo.answerCount }})
        <button @click="goback"><img src="@/assets/images/mypage-back-btn.svg" alt="back-btn"></button>
      </div>
      <v-divider></v-divider>
      <AnswerCard
        v-for="(answer, i) in myAnswers"
        :key="i"
        :item="answer"
      />
        <infinite-loading @infinite="infiniteHandler" spinner="circles"></infinite-loading>
      <v-divider></v-divider>
    </v-container>
  </div>
</template>

<script>
import MyPageProfile from "@/components/mypage/MyPageProfile.vue";
import AnswerCard from "@/components/mypage/AnswerCard.vue";

import InfiniteLoading from "vue-infinite-loading";
import MyPage from "@/api/MyPage";

import { createNamespacedHelpers } from "vuex";
const mypageHelper = createNamespacedHelpers("mypageStore");

export default {
  name: 'MyPage',
  components: {
    MyPageProfile,
    AnswerCard,
    InfiniteLoading,
  },
  data: function () {
    return {
      pageno: 1,
      myAnswers: [],
    }
  },
  created() {
    this.getMyPage()
  },
  computed: {
    ...mypageHelper.mapState(["mypageInfo"]),
  },
  methods: {
    ...mypageHelper.mapActions(["getMyPage"]),
    goback: function () {
      this.$router.push({ name: "MyPage" })
    },
    infiniteHandler($state) {
      MyPage.requestMyAnswers(
        this.pageno,
        ({data}) => {
          setTimeout(() => {
            if (data.answer.length) {
              this.pageno += 1;
              this.myAnswers.push(...data.answer)
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