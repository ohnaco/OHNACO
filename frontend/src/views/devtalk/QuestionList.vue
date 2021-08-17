<template>
  <v-layout>
    <v-row dense>
      <v-col cols="12" md="2" v-show="$vuetify.breakpoint.mdAndUp"
        ><left-nav-bar></left-nav-bar
      ></v-col>
      <v-col cols="12" v-show="$vuetify.breakpoint.smAndDown" style="padding: 0 !important"
        ><top-nav-bar></top-nav-bar
      ></v-col>
      <v-col cols="12" sm="12" md="10" class="d-flex p2">
        <v-col cols="12" sm="12" md="9" class="d-flex flex-column p2">
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
                  v-model="searchText"
                  placeholder="검색"
                  required
                  style="width: 70%; float: left; font-size: 12px"
                ></b-form-input>
                <img
                  src="@/assets/search.svg"
                  style="float: left; width: 28px; height: 28px"
                  @click="search()"
                />
              </div>

              <div
                style="height: 100%; width: 300px; float: right; height: 100%"
                class="pl-3 pt-0 pb-0"
              >
                <div style="float: right; height: 100%">
                  <span
                    class="filter_devTalk pagelink"
                    v-bind:class="{
                      filter_selected:
                        isSortDate && !isSortLike && !isSortComment,
                    }"
                    @click="sortDate()"
                    >●최신순&nbsp;&nbsp;</span
                  >
                  <span
                    class="filter_devTalk pagelink"
                    v-bind:class="{
                      filter_selected:
                        !isSortDate && !isSortLike && isSortComment,
                    }"
                    @click="sortComment()"
                    >●답변순&nbsp;&nbsp;</span
                  >
                  <span
                    class="filter_devTalk pagelink"
                    v-bind:class="{
                      filter_selected:
                        !isSortDate && isSortLike && !isSortComment,
                    }"
                    @click="sortLike()"
                    >●좋아요순&nbsp;&nbsp;</span
                  >
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
          <b-container class="pt-0">
            <question-card
              v-for="q in pageOfItems"
              :key="q.questionid"
              :item="q"
              @tagChange="searchTag"
            />
            <div
              class="card-footer pb-0 pt-3"
              style="text-align: center; background-color: white"
            >
              <jw-pagination
                :items="question"
                @changePage="onChangePage"
                style="text-align: center"
              ></jw-pagination>
            </div>
          </b-container>
        </v-col>
        <v-col cols="12"  md="3" sm="0" class="hotissue_section">
          <!-- 핫이슈, 인기게시물-->
          <div style="height: 120px"></div>

          <div>
            <span class="tag_section">인기 태그&nbsp;</span>
            <span><img src="@/assets/images/hashtag.svg" /></span>
            <hr class="m-1" style="border: solid 0.5px #607d8b" />
            <div style="height: 30vh; overflow: scroll" class="box">
              <a
                class="tag pt-auto pb-auto pl-2 pr-2 mr-1 ;font-size:8px"
                v-for="tag in tags"
                :key="tag"
                @click="searchTag(tag)"
                >{{ tag }} <br
              /></a>
            </div>
          </div>

          <div>
            <span class="tag_section">핫이슈&nbsp;</span>
            <span><img src="@/assets/images/hotissue.svg" /></span>
            <hr class="m-1" style="border: solid 0.5px #607d8b" />
            <div style="height: 30vh">
              <p
                v-for="issue in hotIssue"
                :key="issue.questionid"
                class="mb-2 pagelink"
                style="
                  width: 80%;
                  overflow: hidden;
                  white-space: nowrap;
                  text-overflow: ellipsis;
                  font-family: GmarketSansLight;
                  color: #607d8b;
                "
                @click="gotoDetail(issue.questionid)"
              >
                {{ issue.questiontitle }}<br />
              </p>
            </div>
          </div>
        </v-col>
      </v-col>
    </v-row>
  </v-layout>
</template>
<script>
import QuestionCard from "@/components/devtalk/QuestionCard.vue";
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TopNavBar from "@/components/common/TopNavBar.vue";
import http from "@/util/http-common.js";

export default {
  name: "App",
  components: {
    QuestionCard,
    LeftNavBar,
    TopNavBar,
  },
  computed: {
    },
  data() {
    return {
      isSortDate: true,
      isSortLike: false,
      isSortComment: false,
      question: [],
      hotIssue: [],
      searchText: "",
      tags: [
        "Java",
        "Spring",
        "Javascript",
        "MySQL",
        "C++",
        "C",
        "Android",
        "Ajax",
      ],
      pageOfItems: [],
    };
  },
  created() {
    if (this.$route.params.tag == null) this.setQuestion();
    else this.searchTag(this.$route.params.tag);
    this.setHotIssue();
  },

  methods: {
    addQuestion() {
      this.$router.push("addquestion");
    },
    setQuestion() {
      http
        .get("devtalk/listall")
        .then((response) => {
          this.question = response.data.question;
        })
        .catch((err) => {
          alert(err);
        });
    },

    setHotIssue() {
      http
        .get("devtalk/sublist")
        .then((response) => {
          this.hotIssue = response.data.issue;
        })
        .catch((err) => {
          alert(err);
        });
    },

    onChangePage(pageOfItems) {
      // update page of items
      this.pageOfItems = pageOfItems;
    },
    sortDate() {
      if (this.isSortDate && !this.isSortLike && !this.isSortComment) {
        //아무것도안함
      } else {
        //클래스 변경, 정렬
        this.isSortDate = true;
        this.isSortLike = false;
        this.isSortComment = false;
        this.question.sort(function (a, b) {
          return b.questionid - a.questionid;
        });
      }
    },

    sortLike() {
      if (!this.isSortDate && this.isSortLike && !this.isSortComment) {
        //아무것도안함
      } else {
        //클래스 변경, 정렬
        this.isSortDate = false;
        this.isSortLike = true;
        this.isSortComment = false;
        this.question.sort(function (a, b) {
          return b.like - a.like;
        });
      }
    },

    sortComment() {
      if (!this.isSortDate && !this.isSortLike && this.isSortComment) {
        //아무것도안함
      } else {
        //클래스 변경, 정렬
        this.isSortDate = false;
        this.isSortLike = false;
        this.isSortComment = true;
        this.question.sort(function (a, b) {
          return b.answercount - a.answercount;
        });
      }
    },

    async search() {
      await http
        .get("devtalk/listall")
        .then((response) => {
          this.question = response.data.question;
        })
        .catch((err) => {
          alert(err);
        });
      this.question = this.question.filter(
        (question) =>
          question.questiontitle.includes(this.searchText) ||
          question.questioncontent.includes(this.searchText)
      );
    },

    async searchTag(tag) {
      await http
        .get("devtalk/listall")
        .then((response) => {
          this.question = response.data.question;
        })
        .catch((err) => {
          alert(err);
        });
      const arr = [];
      for (var i = 0; i < this.question.length; i++) {
        for (var j = 0; j < this.question[i].tag.length; j++) {
          if (this.question[i].tag[j].tagname == tag) {
            arr.push(this.question[i]);
            break;
          }
        }
      }
      this.question = arr;
    },

    gotoDetail(qid) {
      this.$router.push({
        name: "QuestionDetail",
        query: { id: qid },
      });
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
  color: #a2aeb4;
}
.filter_selected {
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
.box::-webkit-scrollbar {
  display: none; /* Chrome, Safari, Opera*/
}

.page-link {
  position: relative;
  display: block;
  color: #607d8b !important;
  text-decoration: none;
  background-color: #fff;
  border: 1px solid #fff !important;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
    border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.page-item.disabled .page-link {
  color: #6c757d;
  pointer-events: none;
  background-color: #fff;
  border-color: #fff;
}
.pagelink {
  cursor: pointer;
}
.pagelink:hover {
  text-decoration: underline;
}
.page-item.active .page-link {
    background-color: #C1D5DF !important;
    border-color: #C1D5DF !important;
}
.side_bar{
  display: block;
}
.top_bar{
  display: none;
}
@media (max-width: 767px) {
  .hotissue_section {
    display: none;
  }
}
@media (min-width: 768px) {
  .col-md-9 {
    flex: 0 0 auto;
    width: 58.33333333%;
    margin-left : 8.333333%;
}
}

@media (min-width: 950px) {
  .col-md-9 {
    flex: 0 0 auto;
    width: 58.33333333%;
    margin-left : 0%;
}
}
</style>
