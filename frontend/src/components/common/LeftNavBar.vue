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
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
  name: "LeftNavBar",
};
</script>

<style lang="css" scoped>
* {
  color: white;
}
</style>
