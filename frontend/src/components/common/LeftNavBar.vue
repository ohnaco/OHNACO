<template>
  <v-card class="mx-auto">
    <v-navigation-drawer
      class="blue-grey base mx-auto"
      style="max-width: calc(100vw - 84vw)"
      fixed
      permanent
    >
      <v-row dense class="mt-5">
        <v-col cols="12" align="center"
          ><router-link to="/todo"
            ><img src="@/assets/images/left-nav-logo.svg" /></router-link
        ></v-col>
      </v-row>
      <v-row dense class="mt-5">
        <v-col align="center">
          <router-link to="/mypage">
            <profile-image :user="user"></profile-image>
          </router-link>
        </v-col>
      </v-row>
      <v-row dense class="mt-2">
        <v-col align="center"
          ><router-link to="/mypage"
            ><div class="text-h6 text-decoration-underline">
              {{ user.nickname }}
            </div></router-link
          ></v-col
        >
      </v-row>
      <v-row dense class="mt-15 pl-2 align-start">
        <v-list class="text-h5 font-weight-bold menu-item">
          <v-list-item v-for="item in items" :key="item.title" class="mb-5"
            ><router-link :to="item.path" class="white--text">
              {{ item.title }}
            </router-link></v-list-item
          >
        </v-list>
        <v-row dense>
          <v-col
            align="right"
            style="position: absolute; top: calc(100vh - 35px)"
            ><button class="white--text" @click="logout">Logout</button></v-col
          >
        </v-row>
      </v-row>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
import ProfileImage from "./ProfileImage.vue";
import { createNamespacedHelpers } from "vuex";
const userHelper = createNamespacedHelpers("userStore");

export default {
  name: "LeftNavBar",
  components: {
    ProfileImage,
  },
  data() {
    return {
      items: [
        { title: "To Do", path: "/todo" },
        { title: "Statistics", path: "/statistics" },
        { title: "Tech", path: "/tech" },
        { title: "Dev Talk", path: "/devtalk" },
      ],
    };
  },
  created() {
    this.getUserInfo();
  },
  computed: {
    ...userHelper.mapState(["user"]),
  },
  methods: {
    ...userHelper.mapActions(["getUserInfo", "logout"]),
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
a:hover {
  -webkit-transform: scale(1.05);
  -moz-transform: scale(1.05);
  -ms-transform: scale(1.05);
  -o-transform: scale(1.05);
  transform: scale(1.05);
}
a {
  -webkit-transition: all 0.1s linear;
  transition: all 0.1s linear;
}
</style>
