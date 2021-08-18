<template>
  <v-layout style="background: #EFEFEF; height: 100%;">
    <v-row class="justify-content-center" style="background: #EFEFEF">
      <v-col cols="12" lg="2" v-show="$vuetify.breakpoint.mdAndUp">
        <LeftNavBar />
      </v-col>
      <v-col
        cols="12"
        v-show="$vuetify.breakpoint.smAndDown"
        style="padding: 0 !important"
      >
        <TopNavBar />
      </v-col>
      <v-col cols="12" lg="10" class="">
        <!-- 상단 제목 및 버튼 -->
        <b-container class="bv-example-row">
          <div class="mb-3">
            <p
              class="text-h4 font-weight-bold mb-0 blue-grey--text"
              :class="{ 'text-h5': $vuetify.breakpoint.xs }"
            >
              Statistics
            </p>
          </div>
        </b-container>
        <b-container class="pt-0">
          <!-- 주간 월간 버튼 -->
          <div class="d-flex justify-content-end">
            <v-btn-toggle
              v-model="option"
              mandatory
              borderless
              group
              background-color="#EFEFEF"
            >
              <v-btn value="week" @click="week" color="#607D8B" class="pa-1">
                <img src="@/assets/images/week-btn.svg" alt="week-btn" />
              </v-btn>
              <v-btn value="month" @click="month" color="#607D8B" class="pa-1">
                <img src="@/assets/images/month-btn.svg" alt="week-btn" />
              </v-btn>
            </v-btn-toggle>
          </div>
        </b-container>
        <b-container class="pt-0">
          <v-row wrap class="pb-5 justify-content-center">
            <v-row class="pa-0">
              <v-col lg="6" md="6" sm="12" cols="12">
                <!-- 총 공부시간 -->
                <v-card height="80" class="pa-3">
                  <TotalStudyTime
                    :option="this.option"
                    :totalTime="statsInfo.totalTime"
                  />
                </v-card>
              </v-col>
            </v-row>
            <v-row class="pa-0">
              <!-- 목표시간 공부시간 -->
              <v-col lg="4" md="6" sm="12" cols="12">
                <v-card height="600" class="pa-3">
                  <GoalVsReal :categoryTime="statsInfo.categoryTime" />
                </v-card>
              </v-col>
              <!-- 지난 2일 공부시간 -->
              <v-col lg="4" md="6" sm="12" cols="12">
                <v-card height="600" class="pa-3">
                  <CompareMyself
                    :todayTime="statsInfo.todayTime"
                    :yesterdayTime="statsInfo.yesterdayTime"
                  />
                  <YesterdayTotal
                    :todayTime="statsInfo.todayTime"
                    :yesterdayTime="statsInfo.yesterdayTime"
                  />
                </v-card>
              </v-col>
              <!-- 전체 유저별 카테고리 공부시간 -->
              <v-col lg="4" md="6" sm="12" cols="12">
                <v-card height="600" class="pa-3">
                  <CompareOtherByCategory
                    :categoryTime="statsInfo.categoryTime"
                    :positionTime="statsInfo.positionTime"
                    :entireCategoryTime="statsInfo.entireCategoryTime"
                  />
                </v-card>
              </v-col>
            </v-row>
            <v-row>
              <!-- 카테고리별 공부시간 -->
              <v-col lg="4" md="6" sm="12" cols="12">
                <v-card height="420" class="pa-3">
                  <CategoryStudyTime :categoryTime="statsInfo.categoryTime" />
                </v-card>
              </v-col>
              <!-- 주간, 요일별 공부시간 -->
              <v-col lg="8" md="6" sm="12" cols="12">
                <v-card height="420" class="pa-3">
                  <CompareOther :option="this.option" :statsInfo="statsInfo" />
                </v-card>
              </v-col>
            </v-row>
          </v-row>
        </b-container>
      </v-col>
    </v-row>
  </v-layout>
</template>

<script>
import TopNavBar from "@/components/common/TopNavBar.vue";
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TotalStudyTime from "@/components/statistics/TotalStudyTime.vue";
import CategoryStudyTime from "@/components/statistics/CategoryStudyTime.vue";
import CompareMyself from "@/components/statistics/CompareMyself.vue";
import YesterdayTotal from "@/components/statistics/YesterdayTotal.vue";
import CompareOtherByCategory from "@/components/statistics/CompareOtherByCategory.vue";
import GoalVsReal from "@/components/statistics/GoalVsReal.vue";
import CompareOther from "@/components/statistics/CompareOther.vue";
import { createNamespacedHelpers } from "vuex";
const statisticsHelper = createNamespacedHelpers("statisticsStore");

export default {
  components: {
    TopNavBar,
    LeftNavBar,
    TotalStudyTime,
    CategoryStudyTime,
    CompareMyself,
    YesterdayTotal,
    CompareOtherByCategory,
    GoalVsReal,
    CompareOther,
  },
  data: function () {
    return {
      option: "week",
    };
  },
  computed: {
    ...statisticsHelper.mapState(["statsInfo"]),
  },
  mounted() {
    this.getStats(this.option);
  },
  methods: {
    ...statisticsHelper.mapActions(["getStats"]),
    week: function () {
      this.option = "week";
    },
    month: function () {
      this.option = "month";
    },
  },
  watch: {
    option: function () {
      this.getStats(this.option);
    },
  },
};
</script>
