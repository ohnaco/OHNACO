<template>
  <v-card class="mx-auto">
    <v-navigation-drawer height="100vh" width="100%" class="blue-grey base pt-3 mx-auto" permanent>
      <v-container>
        <v-row justify="center">
          <router-link to="/"><img src="@/assets/images/logo-img.svg" class="mr-1" /></router-link>
          <img src="@/assets/images/logo-txt.svg" />
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col align="center"><profile-image :user="user"></profile-image></v-col>
        </v-row>
        <v-row>
          <v-col align="center"
            ><router-link to="/mypage"
              ><div class="text-h6" v-text="user.nickname"></div
            ></router-link></v-col
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
          <v-col align="right"><button class="white--text" @click="logout">Logout</button></v-col>
        </v-row>
      </v-container>
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
  computed: {
    ...userHelper.mapState(["user"]),
  },
  methods: {
    ...userHelper.mapActions(["logout"]),
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
