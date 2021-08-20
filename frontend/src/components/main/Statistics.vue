<template>
  <v-layout class="justify-content-center">
    <v-card
      v-show="$vuetify.breakpoint.smAndUp"
      >
      <v-img
        :src='require("@/assets/main/statistics.gif")'
      ></v-img>
    </v-card>
    <v-card
      v-show="$vuetify.breakpoint.xs"
      height="auto"
      width="250"
      >
      <v-img
        :src='require("@/assets/main/m-statistics.gif")'
      ></v-img>
    </v-card>
  </v-layout>
</template>

<script>
export default {

}
</script>

<style>

</style>