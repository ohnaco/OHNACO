<template>
  <v-row>
    <v-col cols="2">
      <LeftNavBar></LeftNavBar>
    </v-col>
    <v-col
      cols="10"
      style="overflow: auto; height: 100vh"
      class="p-2 scroll type1"
    >
      <QuestionDetailCard></QuestionDetailCard>
    </v-col>
  </v-row>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import QuestionDetailCard from "@/components/devtalk/QuestionDetailCard.vue";

export default {
  name: "App",
  components: {
    LeftNavBar,
    QuestionDetailCard,
  },
  data() {
    return {
    };
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
</style>
