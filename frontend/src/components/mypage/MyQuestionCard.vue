<template>
  <v-container>
    <v-card-actions>
      <div class="mr-4">내 질문 ({{ questionCount }})</div>
      <v-btn @click="goMyQuestions" icon style="margin-left: 20px">
        <v-img src="@/assets/images/mypage-all-btn.svg" alt="show-all"></v-img>
      </v-btn>
    </v-card-actions>
    <!-- 질문 5개 모아보기 -->
    <v-simple-table class="elevation-2">
      <template v-slot:default>
        <tbody>
          <tr
            v-for="(question, i) in questions"
            :key="i"
            @click="gotoDetail(question)"
            style="cursor: pointer"
          >
            <td>
              <div class="mt-3 mb-2">
                <img
                  src="@/assets/images/question-mark.svg"
                  style="width: 11px"
                  alt="question"
                />
                <b class="ml-1">{{ question.questiontitle }}</b>
              </div>
            </td>
            <td align="right">{{ question.questiondate | datetime }}</td>
            <v-divider></v-divider>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import moment from "moment";

export default {
  name: "MyQuestionCard",
  props: {
    questions: {
      type: Array,
    },
    questionCount: {
      type: Number,
    },
  },
  filters: {
    datetime: function (value) {
      if (value) return moment(String(value)).format("YYYY.MM.DD");
    },
  },
  methods: {
    goMyQuestions: function () {
      this.$router.push({ name: "MyQuestions" });
    },
    gotoDetail: function (question) {
      this.$router.push({
        name: "QuestionDetail",
        query: { id: question.questionid },
      });
    },
  },
};
</script>
