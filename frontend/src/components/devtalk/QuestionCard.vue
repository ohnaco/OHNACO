<template>
  <div class="qCard">
    <v-row style="width: 100%; height: 100%; margin: 0px !important">
      <v-col
        cols="1"
        class="pt-auto pb-auto pl-2 pr-2 qInfo"
        style="height: 100%"
      >
        <div style="height: 33%">
          <div style="text-align: center">
            <img
              src="@/assets/images/question-comment.svg"
              style="height: 20px; width: 20px"
              class="mt-auto mb-auto img_info"
            />
          </div>
          <div
            style="
              width: 100%;
              height: 18px;
              text-align: center;
              padding: 3px 0;
              font-size: 12px;
            "
          >
            {{ item.answercount }}
          </div>
        </div>

        <div style="height: 33%">
          <div style="text-align: center">
            <img
              src="@/assets/images/question-like.svg"
              style="height: 20px; width: 20px"
              class="mt-auto mb-auto img_info"
            />
          </div>
          <div
            style="
              width: 100%;
              height: 18px;
              text-align: center;
              padding: 3px 0;
              font-size: 12px;
            "
          >
            {{ item.like }}
          </div>
        </div>

        <div style="height: 33%">
          <div style="text-align: center">
            <img
              src="@/assets/images/question-views.svg"
              style="height: 20px; width: 20px"
              class="mt-auto mb-auto img_info"
            />
          </div>
          <div
            style="
              width: 100%;
              height: 18px;
              text-align: center;
              padding: 3px 0;
              font-size: 12px;
            "
          >
            {{ item.visit }}
          </div>
        </div>
      </v-col>
      <v-col cols="11" class="content_card">
        <div class="qCard_title mt-2 mb-2 pagelink" v-on:click="gotoDetail">
          {{ item.questiontitle }}
        </div>

        <div
          style="height:56px"
          class="qCard_content pagelink"
          v-on:click="gotoDetail"
        >
          {{ item.questioncontent }}
        </div>

        <div style="height: 40%">
          <div class="tagNdate">
            <div
              style="
                height: 40%;
                width: 100%;
                white-space: nowrap;
                overflow: hidden;
              "
            >
              <a
                class="tag pt-auto pb-auto pl-2 pr-2 mr-1"
                v-for="tag in item.tag"
                :key="tag.tagname"
                @click="parentTagSearch(tag.tagname)"
                >{{ tag.tagname }}</a
              >
            </div>
            <div style="height: 40%; width: 100%" class="qCard_date pt-3">
              {{ date }}
            </div>
          </div>
          <div style="float: right; width: 110px; height: 100%" class="pt-3">
            <div style="float: left">
              <img
                :src="`${item.user.image}`"
                style="width: 35px; height: 35px"
              />
            </div>
            <div style="float: left; width: 70px; height: 30px">
              <div class="profile_name">&nbsp;{{ item.user.nickname }}</div>
              <div class="profile_jobs">
                &nbsp;{{ item.user.positions.positionname }}
              </div>
            </div>
          </div>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      date: "",
      profile_img: require("@/assets/images/profile-img.svg"),
    };
  },
  name: "QuestionCard",
  props: {
    item: {
      type: Object,
    },
  },

  methods: {
    gotoDetail() {
      this.$router.push({
        name: "QuestionDetail",
        params: { id: this.item.questionid },
      });
    },
    dateModify() {
      this.date +=
        this.item.questiondate.substr(0, 10) +
        " " +
        this.item.questiondate.substr(11, 8);
    },
    parentTagSearch(tagname) {
      this.$emit("tagChange", tagname);
    },
  },
  mounted() {
    this.dateModify();
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

.qCard {
  width: 100%;
  height: 160px;
  border-radius: 10px;
  border: solid 1.5px #607d8b;
  background-color: #ffffff;
  filter: drop-shadow(3px 3px 3px rgba(0, 0, 0, 0.25));
  margin-top: 13px;
}

.qInfo {
  font-family: "GmarketSansLight";
  font-size: 0.97vw;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #000000;
}

.qCard_title {
  width:95%;
  font-family: "GmarketSansMedium";
  font-size: 18px;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  overflow: hidden;
  white-space:nowrap;
  text-overflow: ellipsis;
  display: -webkit-box;
  color: #607d8b;
}

.qCard_content {
  width:95%;
  font-family: "GmarketSansLight";
  font-size: 14px;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #607d8b;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.qCard_date {
  font-family: "GmarketSansLight";
  font-size: 8px;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #607d8b;
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
  text-decoration: none;
  border-radius: 16px;
}
.tagNdate {
  float: left;
  min-width: 190px;
  height: 100%;
}
.profile_name {
  font-family: "GmarketSansMedium";
  font-size: 14px;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #607d8b;
}

.profile_jobs {
  font-family: "GmarketSansLight";
  font-size: 12px;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #607d8b;
}
.pagelink {
  cursor: pointer;
}
.pagelink:hover {
  text-decoration: underline;
}
.content_card{
    padding: 0px;
  }
@media (max-width: 768px) {
  .qCard_title {
    font-size: 14px;
  }

  .qCard_content {
    height: 40px !important;
    font-size: 10px;
  }
  .tagNdate {
    float: left;
    min-width: 150px;
    height: 100%;
  }.content_card{
    padding-left: 10px;
  }
  .img_info{
    width:15px !important;
    height:15px !important;
  }
  .qCard {
    height: 140px;
}
.pagination {
    font-size:10px !important;
}
}
@media (max-width: 425px) {
  .tag {
    display: none;
  }
}
</style>
