<template>
  <v-card
    max-width="300"
    :href="item.link"
    target="_blank"
    elevation="5"
    :class="{ subscribe: item.subscribe, all: !item.subscribe }"
  >
    <v-img contain :src="require(`@/assets/images/${item.image}.png`)" height="150px">
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
// import { createNamespacedHelpers } from "vuex";
// const techHelper = createNamespacedHelpers("techStore");
import Tech from "@/api/Tech";

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
    // ...techHelper.mapActions(["scrapTech"]),
    scrapTech(id) {
      Tech.scrapTech(
        id,
        (res) => {
          if (res.data.status) {
            this.item.scrap = !this.item.scrap;
          } else alert("scrap fail");
        },
        (err) => {
          alert(err);
        }
      );
    },
    diffTime(time) {
      const moment = require("moment");
      const today = moment();
      const publish = moment(time);
      return moment.duration(today.diff(publish)).hours();
    },
  },
};
</script>

<style lang="scss" scoped>
.subscribe {
  border: 1px solid rgba(0, 0, 0, 0.2);
  box-shadow: 8px 8px 4px rgb(#ff8a65, 0.4) !important;
}

.all {
  border: 1px solid rgba(0, 0, 0, 0.2);
  box-shadow: 8px 8px 4px rgb(#7b61ff, 0.4) !important;
}
</style>
