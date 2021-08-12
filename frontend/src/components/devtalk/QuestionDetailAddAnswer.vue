<template>
  <div class="qd_rectangle mb-3">
    <div class="p-3 pt-0 pb-0">
      <!--답변 입력부분-->
      <v-textarea
        v-model="content"
        background-color="grey lighten-2"
        color="cyan"
        label="답변을 입력해주세요."
        shaped
        style="font-family: GmarketSansMedium"
      ></v-textarea>
      <div style="clear: both; height: 0; overflow: hidden"></div>
      <!--답변 입력부분 끝-->
    </div>
    <hr
      class="m-0 mt-0 mb-2"
      style="border: solid 0.1px #607d8b; width: 100%"
    />
    <!-- 미리보기-->
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
        v-html="compiledMarkdown"
        style="width: 100%; word-break: break-all"
      ></div>
      <v-btn
        elevation="2"
        class="m-3"
        style="
          position: absolute;
          right: 0px;
          bottom: 0px;
          font-family: GmarketSansMedium;
        "
        @click="newComment"
        >등록</v-btn
      >
    </div>
    <!-- 미리보기 끝-->
  </div>
</template>

<script>
import marked from "marked";
import { createNamespacedHelpers } from "vuex";
const devtalkHelper = createNamespacedHelpers("devTalkStore");

export default {
  data() {
    return {
      content: "",
    };
  },
  methods: {
    ...devtalkHelper.mapActions(["addComment"]),
    newComment(){
      const com = {
        questionid: this.$parent.$route.params.id,
        answercontent: this.content,
        answertitle: "",
      }
      this.addComment(com);
      setTimeout(() => {  this.$router.push({
        name: "QuestionDetail",
        params:{id:this.$parent.$route.params.id},
      }); }, 1000);
    },
  },

  computed: {
    compiledMarkdown: function () {
      return marked(this.content, { sanitize: true });
    },
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
