<template>
  <v-row class="justify-center">
    <v-col cols="12" md="2" v-show="$vuetify.breakpoint.mdAndUp"
      ><left-nav-bar></left-nav-bar
    ></v-col>
    <v-col cols="12" v-show="$vuetify.breakpoint.smAndDown" style="padding: 0 !important"
      ><top-nav-bar></top-nav-bar
    ></v-col>
    <v-col cols="12" sm="12" md="10">
      <b-container class="bv-example-row"><p
          class="text-h4 font-weight-bold mb-0 blue-grey--text"
          :class="{ 'text-h5': $vuetify.breakpoint.xs }"
        >
          Tech
        </p></b-container
      >
      <b-container class="pt-0">
      <v-row dense class="ma-2 justify-end align-center">
        <v-col cols="12" sm="12" md="5" align="end">
          <v-btn-toggle v-model="type" dense borderless mandatory group>
            <v-btn value="all">
              <v-icon left color="#7b61ff"> mdi-checkbox-blank-circle </v-icon>
              <span style="color: #7b61ff" class="font-weight-bold">All</span>
            </v-btn>

            <v-btn value="subscribe">
              <v-icon left color="#ff8a65"> mdi-checkbox-blank-circle </v-icon>
              <span style="color: #ff8a65" class="font-weight-bold">Subscribe</span>
            </v-btn>

            <v-btn text color="cyan base" to="/tech/subscribe"
              ><v-icon left color="cyan"> mdi-cog </v-icon
              ><span class="font-weight-bold">구독 설정</span></v-btn
            >
          </v-btn-toggle>
        </v-col>
      </v-row>
      <!-- Tech Card -->
      <v-row dense>
        <v-col
          v-for="(tech, index) in techList"
          :key="index"
          cols="12"
          xs="12"
          sm="6"
          lg="4"
          xl="3"
          class="d-flex justify-center mb-2"
        >
          <tech-card :item="tech"
        /></v-col>
        <infinite-loading
          :identifier="infiniteId"
          @infinite="infiniteHandler"
          spinner="circles"
        ></infinite-loading>
      </v-row>
      </b-container>
    </v-col>
  </v-row>
</template>

<script>
import TechCard from "@/components/tech/TechCard.vue";
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TopNavBar from "@/components/common/TopNavBar.vue";
import InfiniteLoading from "vue-infinite-loading";

import Tech from "@/api/Tech";

export default {
  components: {
    TechCard,
    LeftNavBar,
    TopNavBar,
    InfiniteLoading,
  },
  data() {
    return {
      param: {
        pageno: 1,
        keyword: "",
      },
      techList: [],
      type: "all",
      infiniteId: +new Date(),
    };
  },
  watch: {
    type: function () {
      this.param.pageno = 1;
      this.techList = [];
      this.infiniteId += 1;
    },
  },
  methods: {
    infiniteHandler($state) {
      if (this.type === "all") {
        Tech.loadAllTechList(
          this.param,
          ({ data }) => {
            setTimeout(() => {
              if (data.list.content.length) {
                this.param.pageno += 1;
                this.techList.push(...data.list.content);
                $state.loaded();
              } else {
                $state.complete();
              }
            }, 100);
          },
          (err) => {
            console.log(err);
          }
        );
      } else {
        Tech.loadSubscribeTechList(
          this.param,
          ({ data }) => {
            setTimeout(() => {
              if (data.list.content.length) {
                this.param.pageno += 1;
                this.techList.push(...data.list.content);
                $state.loaded();
              } else {
                $state.complete();
              }
            }, 100);
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
  },
};
</script>
