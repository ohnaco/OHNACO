<template>
  <v-card class="mx-auto">
    <v-navigation-drawer
      height="100vh"
      width="100%"
      class="blue-grey base pt-3 mx-auto"
      permanent
    >
      <v-container>
        <v-row justify="center">
          <router-link to="/"
            ><img src="@/assets/images/logo-img.svg" class="mr-1"
          /></router-link>
          <img src="@/assets/images/logo-txt.svg" />
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col align="center"><profile-image></profile-image></v-col>
        </v-row>
        <v-row>
          <v-col align="center"
            ><div class="text-h6">
              {{ username}}<v-icon medium color="white" class="ml-2">mdi-bell</v-icon>
            </div></v-col
          >
        </v-row>
      </v-container>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
import ProfileImage from "./ProfileImage.vue";

export default {
  name: "LeftNavBar",
  components: {
    ProfileImage,
  },
  data() {
    return {
      username: "코린이",
    };
  },
};
</script>

<style lang="css" scoped>
* {
  color: white;
}
</style>
