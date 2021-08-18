<template>
  <v-layout>
    <v-app-bar dark color="blue-grey base" v-show="$vuetify.breakpoint.mdAndUp">
      <v-toolbar-title>
        <img src="@/assets/images/simple-logo.svg" />
      </v-toolbar-title>
      <v-spacer />
      <v-toolbar-items>
        <v-btn text to="/login" style="background-color: #607d8b">로그인</v-btn>
        <v-btn text to="/join" style="background-color: #607d8b"
          >회원가입</v-btn
        >
      </v-toolbar-items>
    </v-app-bar>

    <v-app-bar
      dark
      color="blue-grey base"
      v-show="$vuetify.breakpoint.smAndDown"
    >
      <v-toolbar-title>
        <img src="@/assets/images/simple-logo.svg" />
      </v-toolbar-title>
      <v-spacer />
      <v-menu offset-y>
        <template v-slot:activator="{ on, attrs }">
          <v-btn dark icon v-bind="attrs" v-on="on">
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
          </v-btn>
        </template>
        <v-list class="cyan lighten-3">
          <v-list-item>
            <v-list-item
              v-for="(item, index) in items"
              :key="index"
              :to="item.to"
            >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </v-list-item>
        </v-list>
      </v-menu>
    </v-app-bar>
  </v-layout>
</template>

<script>
export default {
  data: function () {
    return {
      items: [
        { title: "로그인", to: "/login" },
        { title: "회원가입", to: "/join" },
      ],
    };
  },
};
</script>

<style></style>
