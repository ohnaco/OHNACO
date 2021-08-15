<template>
  <v-layout>
    <!-- Desktop -->
    <v-app-bar dark color="blue-grey base" v-show="$vuetify.breakpoint.mdAndUp">
      <v-toolbar-title>
        <router-link to="/todo" class="d-flex align-center"
          ><img src="@/assets/images/logo-img.svg" class="mr-1" />
          <img src="@/assets/images/logo-txt.svg"
        /></router-link>
      </v-toolbar-title>
      <v-spacer />
      <v-toolbar-items>
        <v-btn v-for="item in nav" :key="item.icon" :to="item.path" :title="item.title" text>{{
          item.text
        }}</v-btn>
      </v-toolbar-items>
    </v-app-bar>

    <!-- Mobile -->
    <v-app-bar dark color="blue-grey base" v-show="$vuetify.breakpoint.smAndDown">
      <v-toolbar-title
        ><router-link to="/todo" class="d-flex align-center"
          ><img src="@/assets/images/logo-img.svg" class="mr-1" />
          <img src="@/assets/images/logo-txt.svg" /></router-link
      ></v-toolbar-title>
      <v-spacer />
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn dark icon v-bind="attrs" v-on="on"
            ><v-app-bar-nav-icon></v-app-bar-nav-icon
          ></v-btn>
        </template>
        <v-list class="cyan lighten-3">
          <v-list-item
            v-for="(item, index) in nav"
            :key="index"
            :title="item.title"
            :to="item.path"
          >
            <v-list-item-title
              ><b>{{ item.title }}</b></v-list-item-title
            >
          </v-list-item>
        </v-list>
      </v-menu>
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn dark icon v-bind="attrs" v-on="on"><v-icon>mdi-account</v-icon></v-btn>
        </template>
        <v-list class="indigo lighten-3">
          <v-list-item>
            <v-list-item-title light
              ><b>{{ user.nickname }} 님</b></v-list-item-title
            >
          </v-list-item>
          <v-list-item to="/mypage">
            <v-list-item-title><b>Mypage</b></v-list-item-title>
          </v-list-item>
          <v-list-item @click="logout">
            <v-list-item-title><b>Logout</b></v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </v-layout>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const userHelper = createNamespacedHelpers("userStore");

export default {
  data() {
    return {
      dialog: false,
      nav: [
        {
          icon: "home",
          text: "To Do",
          title: "To Do",
          path: "/todo",
          active: true,
        },
        {
          icon: "info",
          text: "Statistics",
          title: "Statistics",
          path: "/statistics",
          active: false,
        },
        {
          icon: "assignment_turned_in",
          text: "Tech",
          title: "Tech",
          path: "/tech",
          active: false,
        },
        {
          icon: "email",
          text: "Dev Talk",
          title: "Dev Talk",
          path: "/devtalk",
          active: false,
        },
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

<style scoped>
* {
  color: white;
}

a {
  text-decoration: none;
}
</style>
