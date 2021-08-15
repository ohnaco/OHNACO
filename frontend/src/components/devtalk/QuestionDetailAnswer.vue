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
          <div class="profile_jobs">
            &nbsp;{{ item.user.positions.positionname }}
          </div>
        </div>
      </div>
      <!--간략 프로필 끝-->
      <!--따봉수 -->
      <div v-if="!item.userLike" style="float: right" class="qd_14_medium pt-1">
        <img src="@/assets/images/question-like-empty.svg" alt=""
        @click="like()" />
        {{ item.like }}
      </div>

      <div v-if="item.userLike" style="float: right" class="qd_14_medium pt-1">
        <img src="@/assets/images/question-like.svg" alt="" style="width:25px ; height:25px"
        @click="unlike()"/>
        {{ item.like }}
      </div>
      <!--따봉 끝 -->
      <div style="clear: both; height: 15px; overflow: hidden"></div>
      <!--날짜, 수정, 삭제버튼-->
      <div>
        <div style="float: left" class="qd_date">
          {{ date }}
        </div>

        <div
          style="float: right"
          v-if="this.item.user.userid == this.$parent.user.userid"
        >
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
            @click="modifyMode()"
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
            @click="commentRemove"
            >삭제</v-btn
          >
        </div>
      </div>
      <!--날짜, 수정, 삭제버튼끝-->
    </div>
    <!-- 본문-->
    <div v-if="!isEdit">
      <div style="clear: both; height: 0; overflow: hidden"></div>
      <hr
        class="m-0 mt-2 mb-2"
        style="border: solid 0.1px #607d8b; width: 100%"
      />
      <div id="editor">
        <div v-html="compiledMarkdown"></div>
      </div>
    </div>
    <!-- 본문끝-->

    <!-- 수정모드-->
    <div v-if="isEdit" class="p-3 pt-0 pb-0">
      <v-textarea
        v-model="modifyContent"
        background-color="grey lighten-2"
        color="cyan"
        label="답변을 입력해주세요."
        shaped
        style="font-family: GmarketSansMedium; width: 100%"
      ></v-textarea>
      <div style="clear: both; height: 0; overflow: hidden"></div>

      <div id="editor" style="position: relative">
        <div
          style="font-family: GmaerketSansMedium; color: #c6c8c9; width: 100%"
          v-if="content === ''"
        >
          미리보기
          <p></p>
          <p></p>
        </div>
        <div
          v-html="compiledMarkdown_modify"
          style="width: 100%; word-break: break-all"
        ></div>
        <div style="width:100%" class="p-0">
          <v-btn
            elevation="2"
            class="mt-3 mb-3 "
            style="font-family: GmarketSansMedium; float: right"
            @click="modify"
            >수정</v-btn
          >
          <v-btn
            elevation="2"
            class="mt-3 mb-3 mr-3"
            style="font-family: GmarketSansMedium; float: right"
            @click="modifyCancle"
            >취소</v-btn
          >
        </div>
      </div>
    </div>
    <!-- 수정모드-->
  </div>
</template>

<script>
import marked from "marked";
import { createNamespacedHelpers } from "vuex";
import Dev from "@/api/DevTalk";
const devtalkHelper = createNamespacedHelpers("devTalkStore");

export default {
  data() {
    return {
      date: "",
      profile_img: require("@/assets/images/profile-img.svg"),
      isEdit: false,
      modifyContent: "",
    };
  },
  props: {
    item: {
      type: Object,
    },
  },
  methods: {
    ...devtalkHelper.mapActions(["deleteComment"]),
    ...devtalkHelper.mapActions(["updateComment"]),
    dateModify() {
      this.date +=
        this.item.answerdate.substr(0, 10) +
        " " +
        this.item.answerdate.substr(11, 8);
    },
    commentRemove() {
      this.deleteComment(this.item.answerid);
    },
    modifyMode() {
      this.modifyContent = this.item.answercontent;
      this.isEdit = true;
    },
    modifyCancle() {
      this.isEdit = false;
    },
    modify() {
      const newQuestion = {
        answerid:this.item.answerid,
        answertitle: this.item.answertitle,
        answercontent: this.modifyContent,
      }
      this.updateComment(JSON.stringify(newQuestion));
      this.isEdit = false;
    },

    like(){
      if(!this.item.userLike){
        Dev.answerLike(this.item.answerid);
        this.item.userLike=true;
        this.item.like++;
      }
    },

    unlike(){
      if(this.item.userLike){
        Dev.answerLike(this.item.answerid);
        this.item.userLike=false;
        this.item.like--;
      }
    },

  },

  computed: {
    compiledMarkdown: function () {
      return marked(this.item.answercontent, { sanitize: true });
    },
    compiledMarkdown_modify: function () {
      return marked(this.modifyContent, { sanitize: true });
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
