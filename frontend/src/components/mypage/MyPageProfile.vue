<template>
  <v-card color="#607D8B">
    <TopNavBar/> 
    <v-container class="pa-3">
      <v-list-item nowrap class="ml-8 mr-8 flex-wrap justify-content-center">
        <!-- 프로필 사진 -->
        <v-col cols="12" lg="3">
          <v-list-item-avatar size="200" class="m-0">
            <v-img :src="`${info.image}`" alt="profile-img"></v-img>
          </v-list-item-avatar>
        </v-col>
        <!-- 이름 설정 버튼 -->
        <v-col cols="12" lg="9">
          <v-list-item-content class="pa-3 white--text">
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
        </v-col>
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
