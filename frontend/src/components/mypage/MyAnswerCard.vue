<template>
  <v-container class="mt-5">
    <v-card-actions>
      <div class="mr-4">내 답변 ({{ answerCount }})</div>
      <v-btn @click="goMyAnswers" icon style="margin-left: 20px">
        <v-img src="@/assets/images/mypage-all-btn.svg" alt="show-all"></v-img>
      </v-btn>
    </v-card-actions>
    <!-- 답변 5개 모아보기 -->
    <v-simple-table class="elevation-2">
      <template v-slot:default>
        <tbody>
          <tr
            v-for="(answer, i) in answers"
            :key="i"
            @click="gotoDetail(answer)"
            style="cursor: pointer"
          >
            <td>
              <v-list-item three-line class="pa-0">
                <v-list-item-content>
                  <v-list-item-title class="mt-3 mb-5">
                    <img
                      src="@/assets/images/question-mark.svg"
                      style="width: 11px"
                      alt="question"
                    />
                    <b class="ml-1">{{ answer.questiontitle }}</b>
                  </v-list-item-title>
                  <v-list-item-subtitle class="mb-2">
                    <img
                      src="@/assets/images/answer-mark.svg"
                      style="width: 11px"
                      alt="answer"
                    />
                    {{ answer.answercontent }}
                  </v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </td>
            <td align="right">
              {{ answer.answerdate | datetime }}
            </td>
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
  name: "MyAnswerCard",
  props: {
    answers: {
      type: Array,
    },
    answerCount: {
      type: Number,
    },
  },
  filters: {
    datetime: function (value) {
      if (value) return moment(String(value)).format("YYYY.MM.DD");
    },
  },
  methods: {
    goMyAnswers: function () {
      this.$router.push({ name: "MyAnswers" });
    },
    gotoDetail: function (answer) {
      this.$router.push({
        name: "QuestionDetail",
        query: { id: answer.questionid },
      });
    },
  },
};
</script>
