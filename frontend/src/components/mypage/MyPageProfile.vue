<template>
  <v-card color="#607D8B">
    <TopNavBar/> 
    <v-container class="pa-0">
      <v-list-item class="ml-10 mr-10 flex-wrap">
        <!-- 프로필 사진 -->
        <v-list-item-avatar size="230" class="m-5">
          <v-img src="@/assets/images/profile-img.svg" alt="ohnaco-logo"></v-img>
        </v-list-item-avatar>
        <!-- 이름 설정 버튼 -->
        <v-list-item-content class="pa-5 white--text ml-5">
          <v-list-item-title class="text-h4">
            <v-card-actions>
              <div class="mr-4">
                {{ info.nickname }}
              </div>
              <v-btn 
              icon
              @click="goChangeInfo"
              >
                <v-img src="@/assets/images/settings-btn.svg" alt="settings-btn"></v-img>
              </v-btn>
            </v-card-actions>
          </v-list-item-title>
          <!-- 희망 직무 -->
          <v-list-item-subtitle class="text-h5 ml-3 white--text">{{ info.positions.positionname }}</v-list-item-subtitle>
          <!-- github -->
          <WeekCommit
            :commit="commit"
            :githubid="info.githubid"
          />
        </v-list-item-content>
      </v-list-item>
    </v-container>
  </v-card>
</template>

<script>
import TopNavBar from "@/components/common/TopNavBar.vue";
import WeekCommit from "@/components/mypage/WeekCommit.vue";

export default {
  name: 'MyPageProfile',
  components: {
    TopNavBar,
    WeekCommit
  },
  props: {
    info: {
      type: Object
    },
    commit: {
      type: Object
    }
  },
  methods: {
    goChangeInfo: function () {
      this.$router.push({ name: "ChangeInfo" });
    }
  }
}
</script>
