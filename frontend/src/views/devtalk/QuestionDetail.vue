<template>
  <v-layout>
    <v-row>
      <v-col cols="12" md="0" lg="2" v-show="$vuetify.breakpoint.mdAndUp"
        ><left-nav-bar></left-nav-bar
      ></v-col>
      <v-col cols="12" v-show="$vuetify.breakpoint.smAndDown"
        ><top-nav-bar></top-nav-bar
      ></v-col>
      <v-col
        cols="12"
        md="12"
        lg="10"
        class="d-flex flex-column p2 scroll type1"
        style="overflow: auto; height: 100vh"
      >
        <QuestionDetailCard class="detail_qCard"></QuestionDetailCard>
      </v-col>
    </v-row>
  </v-layout>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import QuestionDetailCard from "@/components/devtalk/QuestionDetailCard.vue";
import TopNavBar from "@/components/common/TopNavBar.vue";

export default {
  name: "App",
  components: {
    LeftNavBar,
    QuestionDetailCard,
    TopNavBar,
  },
  data() {
    return {
      parentid: "",
    };
  },
  created() {
    this.parentid = this.$route.query.id;
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
.type1::-webkit-scrollbar {
  width: 6px;
}

/* 스크롤바 막대 설정*/
.type1::-webkit-scrollbar-thumb {
  height: 17%;
  background-color: rgba(143, 143, 143, 0.678);
  /* 스크롤바 둥글게 설정    */
  border-radius: 10px;
}

/* 스크롤바 뒷 배경 설정*/
.type1::-webkit-scrollbar-track {
  background-color: rgba(196, 196, 196, 0.692);
}
.detail_qCard {
  padding-left: 36px;
  padding-right: 36px;
  padding-top: 36px;
}

@media (max-width: 425px) {
  .detail_qCard {
    padding-left: 8px !important;
    padding-right: 8px !important;
    padding-top: 4px !important;
  }
}

@media (max-width: 768px) {
  .detail_qCard {
    padding-left: 12px;
    padding-right: 12px;
    padding-top: 18px;
  }
}
</style>
