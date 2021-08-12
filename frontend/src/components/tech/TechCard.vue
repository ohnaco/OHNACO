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
