<template>
  <v-layout class="justify-content-center">
    <v-carousel
      v-show="$vuetify.breakpoint.smAndUp"
      cycle
      height="auto"
      width="400"
      interval="3000"
      hide-delimiter-background
      show-arrows-on-hover
      hide-delimiters
      >
      <v-carousel-item
        v-for="(item,i) in items"
        :key="i"
        :src="item.src"
      ></v-carousel-item>
    </v-carousel>
    <v-card
      v-show="$vuetify.breakpoint.xs"
      height="auto"
      width="230"
      >
      <v-img
        :src='require("@/assets/main/m-mypage.gif")'
      ></v-img>
    </v-card>
  </v-layout>
</template>

<script>
  export default {
    data () {
      return {
        items: [
          {
            src: require('@/assets/main/mypage.png'),
          },
          {
            src: require('@/assets/main/mypage2.png'),
          },
        ],
      }
    },
  }
</script>