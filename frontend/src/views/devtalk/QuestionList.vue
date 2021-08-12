<template>
  <v-row>
    <v-col cols="2">
      <left-nav-bar></left-nav-bar>
    </v-col>
    <v-col cols="7" class="p-2">
      <b-container class="bv-example-row" style="height: 120px">
        <div class="mb-3">
          <h1
            style="
              font-family: 'GmarketSansMedium';
              font-size: 30px;
              color: #607d8b;
            "
          >
            Dev Talk
          </h1>
        </div>
        <div style="width: 100%; height: 30px">
          <div style="float: left; height: 100%" class="p-0">
            <b-form-input
              id="input-1"
              placeholder="검색"
              required
              style="width: 70%; float: left; font-size: 12px"
            ></b-form-input>
            <img
              src="@/assets/search.svg"
              style="float: left; width: 28px; height: 28px"
            />
          </div>

          <div
            style="height: 100%; width: 300px; float: right; height: 100%"
            class="pl-3 pt-0 pb-0"
          >
            <div style="float: right; height: 100%">
              <span class="filter_devTalk">●최신순&nbsp;&nbsp;</span>
              <span class="filter_devTalk">●답변순&nbsp;&nbsp;</span>
              <span class="filter_devTalk">●좋아요순&nbsp;&nbsp;</span>
              <v-btn
                elevation="2"
                style="
                  background-color: #607d8b;
                  color: white;
                  font-family: GmarketSansMedium;
                  font-size: 14px;
                  padding: 0px 12px;
                "
                @click="addQuestion()"
                >질문하기</v-btn
              >
            </div>
          </div>
        </div>
      </b-container>
      <b-container>
        <question-card
          v-for="q in question"
          :key="q.questionid"
          :item="q"
        />
      </b-container>
    </v-col>

    <v-col cols="3">
      <!-- 핫이슈, 인기게시물-->
      <div style="height: 120px"></div>

      <div>
        <span class="tag_section">인기 태그&nbsp;</span>
        <span><img src="@/assets/images/hashtag.svg" /></span>
        <hr class="m-1" style="border: solid 0.5px #607d8b" />
        <div style="height: 30vh"></div>
      </div>

      <div>
        <span class="tag_section">핫이슈&nbsp;</span>
        <span><img src="@/assets/images/hotissue.svg" /></span>
        <hr class="m-1" style="border: solid 0.5px #607d8b" />
        <div style="height: 30vh"></div>
      </div>
    </v-col>
  </v-row>
</template>
<script>
import QuestionCard from "@/components/devtalk/QuestionCard.vue";
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import http from "@/util/http-common.js";

export default {
  name: "App",
  components: {
    QuestionCard,
    LeftNavBar,
  },
  computed: {
  },
  data() {
    return {
      question:[],
    };
  },
  created() {
    this.setQuestion();
    
  },
  methods: {
    addQuestion() {
      this.$router.push("addquestion");
    },
    setQuestion() {
        http
          .get("/devtalk/listall")
          .then(response => {
            this.question = response.data.question
          })
          .catch(err => {
            alert(err)
          })
      },
  },
};
</script>
<style>
@font-face {
  font-family: "GmarketSansMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "GmarketSansBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "GmarketSansLight";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

.filter_devTalk {
  font-family: GmarketSansMedium;
  font-size: 14px;
  font-weight: lighter;
  height: 100%;
  color: #607d8b;
}

.tag_section {
  font-family: GmarketSansMedium;
  font-size: 16px;
  font-weight: lighter;
  color: #607d8b;
}
</style>
