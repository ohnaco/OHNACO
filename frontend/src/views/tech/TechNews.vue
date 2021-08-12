<template>
  <v-row class="justify-center">
    <v-col cols="12" md="2" v-show="$vuetify.breakpoint.mdAndUp"
      ><left-nav-bar></left-nav-bar
    ></v-col>
    <v-col cols="12" v-show="$vuetify.breakpoint.smAndDown"><top-nav-bar></top-nav-bar></v-col>
    <v-col cols="12" sm="12" md="10" class="justify-space-between">
      <v-row dense class="ma-2 justify-space-between align-center">
        <v-col cols="12" sm="12" md="4" order="last">
          <v-btn-toggle v-model="type" borderless group>
            <v-btn>
              <v-icon left color="#ff8a65"> mdi-checkbox-blank-circle </v-icon>
              <span>Subscribe</span>
            </v-btn>

            <v-btn>
              <v-icon left color="#7b61ff"> mdi-checkbox-blank-circle </v-icon>
              <span>All</span>
            </v-btn>
          </v-btn-toggle>
        </v-col>

        <v-col cols="12" sm="12" md="4" order="first">
          <v-text-field
            v-model="keyword"
            label="검색어를 입력하세요."
            append-icon="mdi-magnify"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row dense>
        <v-col v-for="(tech, index) in techList" :key="index" cols="12" sm="4">
          <tech-card :item="tech"
        /></v-col>
      </v-row>
    </v-col>
  </v-row>
  </v-container>
</template>

<script>
import TechCard from "@/components/tech/TechCard.vue";
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TopNavBar from "@/components/common/TopNavBar.vue";

export default {
  components: {
    TechCard,
    LeftNavBar,
    TopNavBar,
  },
  data() {
    return {
      param: {
        pageno: 1,
        keyword: "",
      },
      type: "",
    };
  },
  created() {
    this.setAllTechList(this.param);
  },
  computed: {
    ...techHelper.mapState(["techList"]),
  },
  methods: {
    ...techHelper.mapActions(["setAllTechList"]),
  },
};
</script>

<style>
.subscribe-yes {
  color: #ff8a65;
}
.subscribe-no {
  color: #7b61ff;
}
</style>
