<template>
  <v-layout style="background: #EFEFEF">
    <v-row style="background: #EFEFEF" class="justify-content-center">
      <v-col cols="12" md="2" v-show="$vuetify.breakpoint.mdAndUp"
        ><LeftNavBar/>
      </v-col>
      <v-col cols="12" v-show="$vuetify.breakpoint.smAndDown">
        <TopNavBar/>
      </v-col>
      <v-col cols="10">
        <!-- 상단 제목 및 버튼 -->
        <v-flex class="d-flex justify-space-between text-h4 mt-5 mb-5 ml-5 mr-5">
          <div class="align-items-center"><b>Statistics</b></div>
          <!-- 주간 월간 버튼 -->
          <div class="align-items-center">
            <v-btn-toggle 
              borderless
              tile
              background-color="#EFEFEF"
            >
              <v-btn 
                text
                @click="week"
                color="#607D8B"
                class="pa-1">
              <v-avatar>
                <img src="@/assets/images/week-btn.svg" alt="week-btn" />
              </v-avatar>
              </v-btn>
              <v-btn 
                text
                @click="month"
                color="#607D8B"
                class="pa-1">
              <v-avatar>
                <img src="@/assets/images/month-btn.svg" alt="week-btn" />
              </v-avatar>
              </v-btn>
            </v-btn-toggle>
          </div>
        </v-flex>
        <v-flex>
          <v-row wrap class="pb-5">
            <v-col lg="6" md="6" sm="12" cols="12">
              <!-- 총 공부시간 -->
              <v-card
                height="80"
                class="pa-3"
              >
              <TotalStudyTime
                :option="this.option"
                :totalTime="statsInfo.totalTime"
              />
              </v-card>
            </v-col>
            <v-row>
              <!-- 목표시간 공부시간 -->
              <v-col lg="4" md="6" sm="12" cols="12">
                <v-card
                  height="600"
                  class="pa-3"
                >
                <GoalVsReal 
                  :categoryTime="statsInfo.categoryTime"
                />
                </v-card>
              </v-col>
              <!-- 지난 2일 공부시간 -->
              <v-col lg="4" md="6" sm="12" cols="12">
                <v-card
                  height="600"
                  class="pa-3"
                >
                <CompareMyself 
                  :todayTime="statsInfo.todayTime"
                  :yesterdayTime="statsInfo.yesterdayTime"
                />
                </v-card>
              </v-col>
              <!-- 전체 유저별 카테고리 공부시간 -->
              <v-col lg="4" md="6" sm="12" cols="12">
                <v-card
                  height="600"
                  class="pa-3"
                >
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
                <v-card
                  height="420"
                  class="pa-3"
                >
                <CategoryStudyTime
                  :categoryTime="statsInfo.categoryTime"
                />
                </v-card>
              </v-col>
              <!-- 주간, 요일별 공부시간 -->
              <v-col lg="8" md="6" sm="12" cols="12">
                <v-card
                  height="420"
                  class="pa-3"
                >
                <CompareOther
                  :option="this.option"
                  :statsInfo="statsInfo"
                />
                </v-card>
              </v-col>
            </v-row>
          </v-row>
        </v-flex>
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
import CompareOtherByCategory from "@/components/statistics/CompareOtherByCategory.vue";
import GoalVsReal from "@/components/statistics/GoalVsReal.vue";
import CompareOther from "@/components/statistics/CompareOther.vue";
import { createNamespacedHelpers } from 'vuex';
const statisticsHelper = createNamespacedHelpers('statisticsStore');


export default {
  components: {
    TopNavBar,
    LeftNavBar,
    TotalStudyTime,
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
  mounted() {
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