<template>
  <div>
    <div>
      <MyPageProfile
        :info="mypageInfo.info"
        :commit="mypageInfo.commit"
      />
    </div>
    <div class="container d-flex flex-wrap">
      <v-col md="6" cols="12">
        <MyQuestionCard
          :questions="mypageInfo.question"
          :questionCount="mypageInfo.questionCount"
        />
        <MyAnswerCard
          :answers="mypageInfo.answer"
          :answerCount="mypageInfo.answerCount"
        />
      </v-col>
      <v-col md="6" cols="12">
        <MyTechCard
          :scraps="mypageInfo.scrap"
          :scrapCount="mypageInfo.scrapCount"
        />
      </v-col>
    </div>
  </div>
</template>

<script>
import MyPageProfile from "@/components/mypage/MyPageProfile.vue";
import MyAnswerCard from "@/components/mypage/MyAnswerCard.vue";
import MyQuestionCard from "@/components/mypage/MyQuestionCard.vue";
import MyTechCard from "@/components/mypage/MyTechCard.vue";
import { createNamespacedHelpers } from 'vuex';
const mypageHelper = createNamespacedHelpers('mypageStore');

export default {
  name: 'MyPage',
  components: {
    MyPageProfile,
    MyAnswerCard,
    MyQuestionCard,
    MyTechCard,
  },
  created() {
    this.getMyPage()
  },
  computed: {
    ...mypageHelper.mapState(['mypageInfo']),
  },
  methods: {
    ...mypageHelper.mapActions(['getMyPage']),
  },
};
</script>