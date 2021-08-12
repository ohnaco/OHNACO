<template>
  <v-card max-width="300" elevation="10" :href="item.link" target="_blank">
    <v-img
      src="http://itimg.chosun.com/sitedata/image/202105/06/2021050601877_0.png"
      height="150px"
    >
      <v-btn
        v-if="item.scrap"
        icon
        x-large
        right
        absolute
        @click.prevent
        @click="scrapTech(item.articleid)"
      >
        <v-icon>mdi-bookmark</v-icon>
      </v-btn>
      <v-btn v-else icon x-large right absolute @click.prevent @click="scrapTech(item.articleid)">
        <v-icon>mdi-bookmark-outline</v-icon>
      </v-btn>
    </v-img>

    <v-card-title>{{ item.title }}</v-card-title>

    <v-card-subtitle>{{ this.diffTime(item.publisheddate) }}시간 전</v-card-subtitle>

    <v-card-actions>
      <v-spacer></v-spacer>

      <v-btn icon @click="show = !show" @click.prevent>
        <v-icon>{{ show ? "mdi-chevron-up" : "mdi-chevron-down" }}</v-icon>
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-text v-html="item.content"></v-card-text>
      </div>
    </v-expand-transition>
  </v-card>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const techHelper = createNamespacedHelpers("techStore");

export default {
  props: {
    item: {
      Type: Object,
    },
  },
  data() {
    return {
      show: false,
    };
  },
  methods: {
    ...techHelper.mapActions(["scrapTech"]),
    diffTime(time) {
      const moment = require("moment");
      const today = moment();
      const publish = moment(time);
      return moment.duration(today.diff(publish)).hours();
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
