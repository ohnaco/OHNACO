<template>
  <div class="Rectangle_tc" style="width: 100%; height: 100%">
    <img
      class="image_tc"
      src="http://itimg.chosun.com/sitedata/image/202105/06/2021050601877_0.png"
    />
    <div style="width: 100%; height: 10%; padding: 7px 13px 7px 13px">
      <p class="title_tc">{{ title }}</p>
    </div>
    <div style="width: 100%; height: 35%; padding: 7px 13px 7px 13px">
      <p class="content_tc">{{ content }}</p>
    </div>
    <div style="width: 100%; height: 10%; padding: 7px 13px 7px 13px">
      <p class="footer_tc">{{ footer }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      title: "제목",
      content: "뉴스 혹은 블로그 내용 몇줄",
      footer: "언론사",
    };
  },
  mounted() {},
  methods: {},
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
.Rectangle_tc {
  width: 23vw;
  height: 28vw;
  padding: 0 0 2.5px;
  border-radius: 2.5px;
  border: solid 0.5px #607d8b;
  background-color: #ffffff;
  box-sizing: border-box;
  mix-blend-mode: normal;
  filter: drop-shadow(7px 7px 2.5px rgba(255, 198, 166, 0.68));
}
.image_tc {
  width: 100%;
  height: 40%;
  margin: 0 0 10px;
  border-radius: 2.5px;
}
.title_tc {
  font-family: "GmarketSansMedium";
  font-size: 12px;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #000000;
}
.content_tc {
  font-family: "GmarketSansLight";
  font-size: 10px;
  font-weight: 300;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #000000;
}
.footer_tc {
  font-family: "GmarketSansLight";
  font-size: 8px;
  font-weight: 300;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: right;
  color: #000000;
}
</style>
