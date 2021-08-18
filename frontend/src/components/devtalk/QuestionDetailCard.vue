<template>
  <div>
    <div class="qd_rectangle">
      <div class="profile">
        <!--제목 -->
        <div style="height: 22px; float: left" class="qCard_title mt-2 mb-2">
          <span style="font-size: 20px">Q.</span>
          {{ question.question.questiontitle }}
        </div>
        <!--제목끝 -->

        <!--따봉수 -->
        <div style="float: right" class="qd_14_medium pt-1" v-if="!question.question.userLike">
          <img src="@/assets/images/question-like-empty.svg" alt="" @click="like()" class="like-img"/>
          {{ question.question.like }}
        </div>
        <div style="float: right" class="qd_14_medium pt-1" v-if="question.question.userLike">
          <img src="@/assets/images/question-like.svg" style="width:25px ; height:25px" alt="" class="like-img" @click="unlike()"/>
          {{ question.question.like }}
        </div>
        <!--따봉 끝 -->

        <div style="clear: both; height: 0; overflow: hidden"></div>
        <!--간략 프로필 -->
        <div class="detail_profile">
          <div style="float: left">
            <v-avatar size="35px">
              <img :src="`${question.question.user.image}`" />
            </v-avatar>
          </div>
          <div style="float: left; width: 70px; height: 30px">
            <div class="profile_name">&nbsp;{{ question.question.user.nickname }}</div>
            <div class="profile_jobs">&nbsp;{{ question.question.user.positions.positionname }}</div>
          </div>
        </div>
        <!--간략 프로필 끝-->

        <!--날짜, 수정, 삭제버튼-->
        <div>
          <div style="float: left" class="qd_date">
            {{ date }}  조회수{{ question.question.visit }}
          </div>

          <div style="float: right" v-if="this.question.question.user.userid==this.user.userid">
            <a
              class="modNdel"
              style="color : #607d8b;"
              @click="this.updateQuestion"
              >수정</a
            >
            <a
              class="modNdel"
              style="color : #607d8b;"
              v-b-modal.modal-1
              >삭제</a
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

      <!--해시태그 -->
      <div style="margin: 20px">
        <a class="tag pt-auto pb-auto pl-2 pr-2 mr-1 ;font-size:8px"
              v-for="tag in question.question.tag"
              :key="tag.tagname"
              @click="moveTag(tag.tagname)"
                >{{tag.tagname}}</a>
      </div>
      <!--해시태그 끝-->
    </div>

    <!-- 답변 시작-->
    <div class="ans_count">
      <span style="font-family: GmarketSansBold; font-size: 22px; color: red"
        >A.</span
      >
      <span style="font-family: GmarketSansMedium; font-size: 22px; color: red"
      class="ans_cnt"
        >{{ answerList.length }}개의 답변이 있습니다.</span
      >
    </div>
    <div>
      <QuestionDetailAnswer v-for="comment in answerList"
      :key="comment.answerid"
      :item="comment"
      class="comment_margin"></QuestionDetailAnswer>
      <QuestionDetailAddAnswer></QuestionDetailAddAnswer>
    </div>
    <img src="@/assets/images/goback.svg" @click="goBack" />
    <!--답변 끝-->
    <!--삭제 모달-->
    <b-modal
      id="modal-1"
      hide-footer
      hide-header
      style="height: 20vh; width: 30%"
    >
      <div class="d-block text-center modal_content" style="height: 50%">
        <br />
        <br />
        <br />
        <h3>정말 삭제하시겠습니까?</h3>
        <br />
        <br />
        <br />
      </div>
      <div style="width: 100%" class="mb-3">
        <div style="width: 50%; float: left; text-align: center">
          <b-button
            size="lg"
            class="ml-5 mb-3"
            style="color: black; font-family: GmarketSansBold"
            @click="$bvModal.hide('modal-1')"
            >취소</b-button
          >
        </div>

        <div style="width: 50%; float: left; text-align: center">
          <b-button
            size="lg"
            class="mr-5"
            variant="danger"
            style="color: black; font-family: GmarketSansBold"
            @click="deleteQuestion2()"
            >확인</b-button
          >
        </div>
      </div>
    </b-modal>
    <!--삭제 모달 끝-->
  </div>
</template>

<script>
import QuestionDetailAnswer from "@/components/devtalk/QuestionDetailAnswer";
import QuestionDetailAddAnswer from "@/components/devtalk/QuestionDetailAddAnswer";
import Dev from "@/api/DevTalk";
import { createNamespacedHelpers } from "vuex";
const questionHelper = createNamespacedHelpers("devTalkStore");
const userHelper = createNamespacedHelpers("userStore");
import marked from "marked";

export default {
  data() {
    return {
      isAuthor: false, //글쓴이인지
      profile_img: require("@/assets/images/profile-img.svg"),
      date:"",
    };
  },

  methods: {
    ...questionHelper.mapActions(["detailQuestion"]),
    ...questionHelper.mapActions(["deleteQuestion"]),
    ...questionHelper.mapActions(["setAnswer"]),
    goBack() {
      this.$router.push("devtalk");
    },
    dateModify(){
      this.date+=this.question.question.questiondate.substr(0,10)+" "+this.question.question.questiondate.substr(11,8);
    },
    updateQuestion(){
      this.$router.push({
        name: "QuestionUpdate",
        params:{origin:this.question.question},
      });
    },
    deleteQuestion2(){
      this.deleteQuestion(this.question.question.questionid);
      setTimeout(() => {  this.goBack(); }, 500);
    },
    like(){
      if(!this.question.question.userlike){
        Dev.questionLike(this.question.question.questionid);
        this.question.question.userLike=true;
        this.question.question.like++;
      }
    },

    unlike(){
      if(this.question.question.userLike){
        Dev.questionLike(this.question.question.questionid);
        this.question.question.userLike=false;
        this.question.question.like--;
      }
    },
    moveTag(tag){
      console.log(tag);
      this.$router.push({
        name: "DevTalk",
        params:{tag:tag},
      });
    }
  },

  components: {
    QuestionDetailAnswer,
    QuestionDetailAddAnswer,
  },

  computed: {
    ...questionHelper.mapState(["question"]),
    ...questionHelper.mapState(["answerList"]),
    ...userHelper.mapState(["user"]),
    compiledMarkdown: function () {
      return marked(this.question.question.questioncontent, { sanitize: true });
    },
  },
  created(){
    this.detailQuestion(this.$parent.parentid);
    this.dateModify();
  },
};
</script>

<style>
.modNdel{
font-family: GmarketSansMedium;
                font-size: 14px;
                color : #607d8b;
                margin: 0 7px 0 0;
                text-decoration:none;
                cursor:pointer;
}
.comment_margin{
  margin-bottom: 32px;
}
.qd_rectangle {
  width: 100%;
  border-radius: 10px;
  box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.25);
  border: solid 1px #607d8b;
  background-color: #ffffff;
}

.qd_date {
  font-family: "GmarketSansLight";
  font-size: 12px;
  color: #607d8b;
}

.qd_14_medium {
  font-family: "GmarketSansMedium";
  font-size: 14px;
  color: #607d8b;
}

#editor {
  margin: 0;
  height: 100%;
  font-family: "Helvetica Neue", Arial, sans-serif;
  color: #333;
}
.detail_profile{
  width: 110px;
  height: 35px;
  margin-bottom: 12px;
  margin-top: 12px;
}

textarea,
#editor div {
  display: inline-block;
  height: 100%;
  vertical-align: top;
  box-sizing: border-box;
  padding: 0 20px;
}

textarea {
  border: none;
  border-right: 1px solid #ccc;
  resize: none;
  outline: none;
  background-color: #f6f6f6;
  font-size: 14px;
  font-family: "Monaco", courier, monospace;
  padding: 20px;
}

code {
  color: #f66;
}

.tag {
  background-color: #80deea;
  font-family: "GmarketSansLight";
  font-size: 8px;
  height: 80%;
  border: none;
  color: #607d8b;
  text-align: center;
  padding: 3px 7px;
  margin: 0 7px 0 0;
  text-decoration: none;
  border-radius: 16px;
}
.modal_content {
  font-family: "GmarketSansMedium";
  font-size: 18px;
}
.profile{
    padding: 12px;
  }
  .ans_count{
    margin-top: 24px;
    margin-bottom: 24px;
  }
@media (max-width: 425px) {
  .comment_margin{
  margin-bottom: 8px;
}
  .ans_count{
    margin-top: 8px !important;
    margin-bottom: 8px !important;
  }
}
@media (max-width: 768px) {
  .modNdel{
      font-size: 12px;
}
  .comment_margin{
  margin-bottom: 12px;
}
  .ans_count{
    margin-top: 12px;
    margin-bottom: 12px;
  }
  .qCard_title{
    height: 20px;
    font-size:12px;
  }
  .profile{
    padding: 8px !important;
  }
  .ans_cnt{
    font-size:12px !important;
  }
  .detail_profile{
  width: 110px;
  height: 35px;
  margin-bottom: 2px;
  margin-top: 2px;
}
}
</style>
