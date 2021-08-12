<template>
  <div class="qd_rectangle mb-3">
    <div class="p-3 pt-0">
      <!--간략 프로필 -->
      <div style=" width:110px ; height:35px float:left" class="mb-3 mt-3">
        <div style="float: left">
          <img :src="profile_img" style="width: 35px; height: 35px" />
        </div>
        <div style="float: left; width: 70px; height: 30px">
          <div class="profile_name">&nbsp;{{ item.user.nickname }}</div>
          <div class="profile_jobs">&nbsp;{{ item.user.positions.positionname }}</div>
        </div>
      </div>
      <!--간략 프로필 끝-->
      <!--따봉수 -->
      <div style="float: right" class="qd_14_medium pt-1">
        <img src="@/assets/images/question-like-empty.svg" alt="" />
        {{ item.like }}
      </div>
      <!--따봉 끝 -->
      <div style="clear: both; height: 15px; overflow: hidden"></div>
      <!--날짜, 수정, 삭제버튼-->
      <div>
        <div style="float: left" class="qd_date">
          {{ date }}
        </div>

        <div style="float: right" v-if="isAuthor">
          <v-btn
            elevation="2"
            style="
              height: 25px;
              background-color: #ffc21f;
              color: white;
              font-family: GmarketSansMedium;
              font-size: 14px;
              margin: 0 7px 0 0;
            "
            >수정</v-btn
          >
          <v-btn
            elevation="2"
            style="
              height: 25px;
              background-color: #e23131;
              color: white;
              font-family: GmarketSansMedium;
              font-size: 14px;
            "
            >삭제</v-btn
          >
        </div>
      </div>
      <!--날짜, 수정, 삭제버튼끝-->
    </div>
    <!-- 본문-->
    <div style="clear: both; height: 0; overflow: hidden"></div>
    <hr
      class="m-0 mt-2 mb-2"
      style="border: solid 0.1px #607d8b; width: 100%"
    />
    <div id="editor">
      <div v-html="compiledMarkdown"></div>
    </div>
    <!-- 본문끝-->
  </div>
</template>

<script>
import marked from "marked";

export default {
  data() {
    return {
      date: "",
      profile_img: require("@/assets/images/profile-img.svg"),
    };
  },
  props: {
    item: {
      type: Object,
    },
  },
  methods: {
    dateModify(){
      this.date+=this.item.answerdate.substr(0,10)+" "+this.item.answerdate.substr(11,8);
    },
  },

  computed: {
    compiledMarkdown: function () {
      return marked(this.item.answercontent, { sanitize: true });
    },
  },
  mounted() {
    this.dateModify();
  },
};
</script>

<style>
.ad_rectangle {
  width: 95%;
  border-radius: 10px;
  box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.25);
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
</style>
