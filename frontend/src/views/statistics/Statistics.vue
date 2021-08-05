<template>
  <v-layout style="background: #EFEFEF">
    <v-row style="background: #EFEFEF">
      <v-col cols="2"><left-nav-bar></left-nav-bar></v-col>
      <v-col cols="10" class="pa-10">
        <v-flex class="d-flex justify-space-between text-h4 mt-10 mb-5">
          <b>Statistics</b>
          <div>
            <!-- 주간 버튼 -->
            <v-btn 
            text
            @click="week"
            color="#607D8B">
              <v-avatar>
                <img src="@/assets/images/week-btn.svg" alt="week-btn" />
              </v-avatar>
            </v-btn>
            <!-- 월간 버튼 -->
            <v-btn 
            text
            @click="month"
            color="#607D8B">
              <v-avatar>
                <img src="@/assets/images/month-btn.svg" alt="week-btn" />
              </v-avatar>
            </v-btn>
          </div>
        </v-flex>
        <v-flex class="d-flex">
          <v-row wrap>
            <!-- 총 공부시간 -->
            <v-col lg="6" md="6" sm="10" xs="10">
              <v-card
                height="80"
                class="pa-3"
              >
              <total-study-time 
              :option="this.option"
              :totalTime="statsInfo.totalTime.totalcompletetime"
              ></total-study-time>
              </v-card>
            </v-col>
            <!-- 랭킹 -->
            <v-col lg="6" md="6" sm="10" xs="10">
              <v-card
                height="80"
                class="pa-3"
              >
              <total-ranking></total-ranking>
              </v-card>
            </v-col>
            <!-- 목표시간 공부시간 -->
            <v-col lg="4" md="6" sm="10" xs="10">
              <v-card
                height="600"
                class="pa-3"
              >
              <goal-vs-real 
              :categoryTime="statsInfo.categoryTime"
              ></goal-vs-real>
              </v-card>
            </v-col>
            <!-- 지난 2일 공부시간 -->
            <v-col lg="4" md="6" sm="10" xs="10">
              <v-card
                height="600"
                class="pa-3"
              >
              <compare-myself 
              :todayTime="statsInfo.todayTime"
              :yesterdayTime="statsInfo.yesterdayTime"
              ></compare-myself>
              </v-card>
            </v-col>
            <!-- 유저별 공부시간 -->
            <v-col lg="4" md="6" sm="10" xs="10">
              <v-card
                height="600"
                class="pa-3"
              >
              <compare-other-by-category 
              :categoryTime="statsInfo.categoryTime"
              :positionTime="statsInfo.positionTime"
              :entireCategoryTime="statsInfo.entireCategoryTime"
              ></compare-other-by-category>
              </v-card>
            </v-col>
            <!-- 카테고리별 공부시간 -->
            <v-col lg="5" md="6" sm="10" xs="10">
              <v-card
                height="420"
                class="pa-3"
              >
              <category-study-time 
              :categoryTime="statsInfo.categoryTime"
              ></category-study-time>
              </v-card>
            </v-col>
            <!-- 주간 공부시간 -->
            <v-col lg="7" md="12" sm="10" xs="10">
              <v-card
                height="420"
                class="pa-3"
              >
              <compare-other 
              :option="this.option"
              :myTime="statsInfo.myTime"
              :entireMemberTime="statsInfo.entireMemberTime"
              :positionMemberTime="statsInfo.positionMemberTime"
              ></compare-other>
              </v-card>
            </v-col>
          </v-row>
        </v-flex>
      </v-col>
    </v-row>
  </v-layout>
</template>

<script>
import LeftNavBar from "@/components/common/LeftNavBar.vue";
import TotalStudyTime from "@/components/statistics/TotalStudyTime.vue";
import TotalRanking from "@/components/statistics/TotalRanking.vue";
import CategoryStudyTime from "@/components/statistics/CategoryStudyTime.vue";
import CompareMyself from "@/components/statistics/CompareMyself.vue";
import CompareOtherByCategory from "@/components/statistics/CompareOtherByCategory.vue";
import GoalVsReal from "@/components/statistics/GoalVsReal.vue";
import CompareOther from "@/components/statistics/CompareOther.vue";
import { createNamespacedHelpers } from 'vuex';
const statisticsHelper = createNamespacedHelpers('statisticsStore');


export default {
  components: {
    LeftNavBar,
    TotalStudyTime,
    TotalRanking,
    CategoryStudyTime,
    CompareMyself,
    CompareOtherByCategory,
    GoalVsReal,
    CompareOther,
  },
  data: function () {
    return {
      option: 'week'
    }
  },
  computed: {
    ...statisticsHelper.mapState(['statsInfo']),
  },
  created() {
    this.getStats(this.option)
  },
  methods: {
    ...statisticsHelper.mapActions(['getStats']),
    week: function () {
      this.option = 'week'
    },
    month: function () {
      this.option = 'month'
    }
  },
  watch: {
    option: function () {
      this.getStats(this.option)
    }
  }
};
</script>

<style scoped>
.row {
  justify-content: center;
}
</style>
