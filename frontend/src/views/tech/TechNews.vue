<template>
<v-container>
<v-row>
  <v-col cols="2"><left-nav-bar></left-nav-bar></v-col>
    <v-col cols="8" style="height:100vh" class="p-4">
        <b-container class="bv-example-row" style="height:20%">
            <b-row class="mb-3">
                <h1 style="font-family: 'GmarketSansMedium';font-size: 30px; color: #607d8b;">Tech</h1>
            </b-row>
            <b-row>
                <b-col style="float:left" class="p-0">
                    <b-form-input id="input-1" placeholder="검색" required style="width:70% ; float:left ; font-size:12px"></b-form-input>
                    <img src="@/assets/search.svg" style="float:left">
                </b-col>
                <b-col style=" height:100%" class="pl-3 pr-3 pt-0 pb-0">
                    <div style="float:right ; height:100%">
                        <span class="tech_category tech_news">●News&nbsp;&nbsp;</span>
                        <span class="tech_category tech_blog">●Blog&nbsp;&nbsp;</span>
                        <span class="tech_category tech_all">●All&nbsp;&nbsp;</span>
                    </div>
                </b-col>

            </b-row>
        </b-container>
        <b-container style=" height:80% ; overflow: auto;" class="scroll type1">
          <b-row style="height:60%">
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
          </b-row>

        <b-row style="height:60%">
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
          </b-row>

          <b-row style="height:60%">
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
          </b-row>

        <b-row style="height:60%">
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
            <b-col style="width:30% ; height:100%" class="p-3">
              <TechCard></TechCard>
            </b-col>
          </b-row>
         
        </b-container>
    </v-col>
</v-row>
</v-container>
</template>

<script>
import TechCard from "@/components/tech/TechCard.vue";
import LeftNavBar from "@/components/common/LeftNavBar.vue";

export default {
  name: "App",
  components : {
      TechCard,
      LeftNavBar,
  },
  data() {
    return {
        title:"제목",
        content:"뉴스 혹은 블로그 내용 몇줄뉴스 혹은 블로그 내용 몇줄뉴스 혹은 블로그 내용 몇줄뉴스 혹은 블로그 내용 몇줄",
        footer:"언론사",
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
input::placeholder {
  font-size: 12px;
  font-family: "GmarketSansLight";
}

.tech_category {
  font-family: GmarketSansLight;
  font-size: 14px;
  font-weight: lighter;
  height: 100%;
}
.tech_news {
  color: #FF8A65;
}
.tech_blog {
  color: #7B61FF;
}
.tech_all {
}

/* 스크롤바 설정*/
.type1::-webkit-scrollbar{
    width: 6px;
}

/* 스크롤바 막대 설정*/
.type1::-webkit-scrollbar-thumb{
    height: 17%;
    background-color: rgba(143, 143, 143, 0.678);
    /* 스크롤바 둥글게 설정    */
    border-radius: 10px;    
}

/* 스크롤바 뒷 배경 설정*/
.type1::-webkit-scrollbar-track{
    background-color: rgba(196, 196, 196, 0.692);
}

</style>
