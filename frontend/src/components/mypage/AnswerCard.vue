<template>
  <div @click="goDetail" class="qd_rectangle mb-3">
    <div class="p-3">
      <div class="mb-5 mt-2">
        <img src="@/assets/images/question-mark.svg" style="width: 11px" alt="question">
        <b class="ml-1">{{ item.questiontitle }}</b>
      </div>
      <hr
        class="m-0 mt-2 mb-2"
        style="border: solid 0.1px #607d8b; width: 100%"
      />
      <div class="mt-5">
        <img src="@/assets/images/answer-mark.svg" style="width: 11px" alt="answer">
        {{ item.answercontent }}
      </div>
      <div class="d-flex justify-content-end qd_date">
          {{ item.answerdate | datetime }}
      </div>
    </div>
  </div>
</template>

<script>
import moment from 'moment'

export default {
  props: {
    item: {
      type: Object,
    }
  },
  filters: {
    datetime: function (value) {
      if (value)
        return moment(String(value)).format('YYYY.MM.DD')
    }
  },
  methods: {
    goDetail: function () {
      this.$router.push({
        name: "QuestionDetail",
        query:{ id: this.item.questionid },
      });
    }
  }
}
</script>

<style>

</style>