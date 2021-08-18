<template>
  <div style="display: -webkit-flex" class="techcard">
    <v-card
      min-width="280"
      max-width="15vw"
      :href="item.link"
      target="_blank"
      elevation="5"
      :class="{ subscribe: item.subscribe, all: !item.subscribe }"
    >
      <v-img
        contain
        :src="require(`@/assets/images/${item.image}.png`)"
        height="150px"
        class="ma-2"
      >
        <v-btn
          v-if="item.scrap"
          icon
          x-large
          right
          absolute
          @click.prevent
          @click="scrapTech(item.articleid)"
          class="justify-end"
        >
          <v-icon>mdi-bookmark</v-icon>
        </v-btn>
        <v-btn
          v-else
          icon
          x-large
          right
          absolute
          @click.prevent
          @click="scrapTech(item.articleid)"
          class="justify-end"
        >
          <v-icon>mdi-bookmark-outline</v-icon>
        </v-btn>
      </v-img>
      <v-card-text class="pb-0">
        <p class="text-lg-h6 font-weight-bold">{{ item.title }}</p>
        <p>{{ this.formatTime(item.publisheddate) }}</p>
      </v-card-text>

      <v-card-actions class="justify-end">
        <v-btn text color="teal accent-4" @click.prevent @click="reveal = true">
          더 보기
        </v-btn>
      </v-card-actions>

      <div>
        <v-expand-transition>
          <v-card
            v-if="reveal"
            class="transition-fast-in-fast-out v-card--reveal"
            style="height: 100%"
          >
            <v-card-text class="pb-0">
              <p class="text-lg-h6 font-weight-bold">{{ item.title }}</p>
              <p v-html="item.content"></p>
            </v-card-text>
            <v-card-actions class="pt-0 justify-end">
              <v-btn
                text
                color="teal accent-4"
                @click.prevent
                @click="reveal = false"
              >
                Close
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-expand-transition>
      </div>
    </v-card>
  </div>
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
      reveal: false,
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
    formatTime(time) {
      const moment = require("moment");
      const publish = moment(time);
      return publish.format("LL");
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

.v-card--reveal {
  bottom: 0;
  opacity: 1 !important;
  position: absolute;
  width: 100%;
}

.techcard:hover {
  -webkit-transform: scale(1.05);
  -moz-transform: scale(1.05);
  -ms-transform: scale(1.05);
  -o-transform: scale(1.05);
  transform: scale(1.05);
}

.techcard {
  -webkit-transition: all 0.1s linear;
  transition: all 0.1s linear;
}
</style>
