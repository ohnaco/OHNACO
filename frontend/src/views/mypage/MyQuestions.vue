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
        내 질문 ({{ mypageInfo.questionCount }})
        <button @click="goback"><img src="@/assets/images/mypage-back-btn.svg" alt="back-btn"></button>
      </div>
      <v-divider></v-divider>
      <QuestionCard
        v-for="question in myQuestions"
        :key="question.questionid"
        :item="question"
        @click="goDetail(question)"
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
    InfiniteLoading,
  },
  data: function () {
    return {
      pageno: 1,
      myQuestions: []
    }
  },
  created() {
    this.getMyPage();
  },
  computed: {
    ...mypageHelper.mapState(['mypageInfo']),
  },
  methods: {
    ...mypageHelper.mapActions(['getMyPage']),
    goback: function () {
      this.$router.push({ name: 'MyPage' })
    },
    goDetail: function (question) {
      this.$router.push({
        name: "QuestionDetail",
        params:{ id: question.questionid },
      });
    },
    infiniteHandler($state) {
      MyPage.requestMyQuestions(
        this.pageno,
        ({data}) => {
          setTimeout(() => {
            if (data.question.length) {
              this.pageno += 1;
              this.myQuestions.push(...data.question)
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
