<template>
  <div class="qd_rectangle">
    <div class="answer_profile pt-0">
      <!--간략 프로필 -->
      <div style=" width:110px ; height:35px float:left" class="mb-3 mt-3">
        <div style="float: left">
          <v-avatar size="35px">
            <img :src="`${item.user.image}`" />
          </v-avatar>
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
        <img
          src="@/assets/images/question-like-empty.svg"
          alt=""
          style="cursor: pointer"
          @click="like()"
        />
        {{ item.like }}
      </div>

      <div v-if="item.userLike" style="float: right" class="qd_14_medium pt-1">
        <img
          src="@/assets/images/question-like.svg"
          alt=""
          style="width: 25px; height: 25px; cursor: pointer"
          @click="unlike()"
        />
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
          <a class="modNdel" style="color: #607d8b" @click="modifyMode()"
            >수정</a
          >
          <a class="modNdel" style="color: #607d8b" @click="showDialog">삭제</a>
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
        <div style="width: 100%" class="p-0">
          <v-btn
            elevation="2"
            class="mt-3 mb-3 mod-btn"
            style="font-family: GmarketSansMedium; float: right"
            @click="modify"
            >수정</v-btn
          >
          <v-btn
            elevation="2"
            class="mt-3 mb-3 mr-3 mod-btn"
            style="font-family: GmarketSansMedium; float: right"
            @click="modifyCancle"
            >취소</v-btn
          >
        </div>
      </div>
    </div>
    <!-- 수정모드-->
    <!--삭제 모달-->
    <v-dialog max-width="500" v-model="isModal">
      <delete-modal
        @hide="hideDialog"
        @submit="commentRemove()"
        :msg="'Answer'"
      />
    </v-dialog>
    <!--삭제 모달 끝-->
  </div>
</template>

<script>
import marked from "marked";
import DeleteModal from "@/components/common/DeleteModal.vue";
import { createNamespacedHelpers } from "vuex";
import Dev from "@/api/DevTalk";
import hljs from "highlight.js";
import "highlight.js/styles/atom-one-dark.css";
const devtalkHelper = createNamespacedHelpers("devTalkStore");

marked.setOptions({
  renderer: new marked.Renderer(),
  highlight: function (code) {
    return hljs.highlightAuto(code).value;
  },
  pedantic: false,
  gfm: true,
  tables: true,
  breaks: false,
  sanitize: false,
  smartLists: true,
  smartypants: false,
  xhtml: false,
});

export default {
  data() {
    return {
      date: "",
      profile_img: require("@/assets/images/profile-img.svg"),
      isEdit: false,
      isModal: false,
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
    showDialog() {
      this.isModal = true;
    },
    hideDialog() {
      this.isModal = false;
    },
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
        answerid: this.item.answerid,
        answertitle: this.item.answertitle,
        answercontent: this.modifyContent,
      };
      this.updateComment(JSON.stringify(newQuestion));
      this.isEdit = false;
    },

    like() {
      if (!this.item.userLike) {
        Dev.answerLike(this.item.answerid);
        this.item.userLike = true;
        this.item.like++;
      }
    },

    unlike() {
      if (this.item.userLike) {
        Dev.answerLike(this.item.answerid);
        this.item.userLike = false;
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

  components: {
    DeleteModal,
  },
};
</script>

<style>
.modNdel {
  font-family: GmarketSansMedium;
  font-size: 14px;
  color: #607d8b;
  margin: 0 7px 0 0;
  text-decoration: none;
  cursor: pointer;
}
.answer_profile {
  padding: 12px;
}
.ad_rectangle {
  border-radius: 10px;
  box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.25);
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
@media (max-width: 768px) {
  .modNdel {
    font-size: 12px;
  }
  .answer_profile {
    padding: 8px !important;
  }
  .mod-btn {
    font-size: 12px !important;
    height: 20px !important;
  }
  .v-btn:not(.v-btn--round).v-size--default {
    height: 36px;
  }
}
</style>
