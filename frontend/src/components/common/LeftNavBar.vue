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
      <v-container class="mt-15">
        <v-row v-for="item in items" :key="item.title" class="menu-item">
          <router-link :to="item.path" class="white--text">
            {{ item.title }}
          </router-link>
        </v-row>
        <v-row class="mt-15">
          <v-col align="right"
            ><p class="white--text" v-on:click="logout">Logout</p></v-col
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
      items: [
        { title: "To Do", path: "/todo" },
        { title: "Statistics", path: "/statistics" },
        { title: "Tech", path: "/tech" },
        { title: "Dev Talk", path: "/devtalk" },
      ],
      right: null,
    };
  },
  methods: {
    logout() {
      console.log("로그아웃");
    },
  },
};
</script>

<style lang="css" scoped>
* {
  color: white;
}

a {
  text-decoration: none;
}

.menu-item {
  font-size: 30px;
  padding: 20px 30px;
}
</style>
