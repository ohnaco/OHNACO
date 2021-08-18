<template>
  <div>
    <TopNavBar />
    <header style="background: #607d8b; position: relative">
      <v-container class="pa-3">
        <v-list-item nowrap class="ml-8 mr-8 flex-wrap justify-content-center">
          <!-- 프로필 사진 -->
          <v-col cols="12" lg="3">
            <v-list-item-avatar size="200" class="m-0">
              <v-img :src="info.image" alt="profile-img"></v-img>
            </v-list-item-avatar>
          </v-col>
          <!-- 이름 설정 버튼 -->
          <v-col cols="12" lg="9">
            <v-list-item-content class="pa-3 white--text">
              <v-list-item-title
                class="font-weight-bold"
                style="font-size: 1.7rem"
              >
                <v-card-actions>
                  <div class="mr-4">
                    {{ mypageInfo.info.nickname }}
                  </div>
                  <v-btn icon @click="goChangeInfo">
                    <v-img
                      src="@/assets/images/settings-btn.svg"
                      alt="settings-btn"
                    ></v-img>
                  </v-btn>
                </v-card-actions>
              </v-list-item-title>
              <!-- 희망 직무 -->
              <v-list-item-subtitle
                class="ml-2 white--text"
                style="font-size: 1.4rem"
              >
                {{ info.positions ? info.positions.positionname : "" }}
              </v-list-item-subtitle>
              <!-- github -->
              <div v-if="info.githubid">
                <WeekCommit :commit="commit" :info="info" />
              </div>
            </v-list-item-content>
          </v-col>
        </v-list-item>
      </v-container>
    </header>
  </div>
</template>

<script>
import TopNavBar from "@/components/common/TopNavBar.vue";
import WeekCommit from "@/components/mypage/WeekCommit.vue";
import { createNamespacedHelpers } from "vuex";
const mypageHelper = createNamespacedHelpers("mypageStore");

export default {
  name: "MyPageProfile",
  components: {
    TopNavBar,
    WeekCommit,
  },
  props: {
    info: {
      type: Object,
    },
    commit: {
      type: Object,
    },
  },
  created() {
    this.getMyPage();
  },
  computed: {
    ...mypageHelper.mapState(["mypageInfo"]),
  },
  methods: {
    ...mypageHelper.mapActions(["getMyPage"]),
    goChangeInfo: function () {
      this.$router.push({ name: "ChangeInfo" });
    },
  },
};
</script>
