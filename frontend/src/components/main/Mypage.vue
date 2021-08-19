<template>
<div>
  <v-carousel
    v-show="$vuetify.breakpoint.mdAndUp"
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
  <v-carousel
    v-show="$vuetify.breakpoint.smAndDown"
    cycle
    height="auto"
    width="130"
    interval="3000"
    hide-delimiter-background
    show-arrows-on-hover
    hide-delimiters
    >
    <v-carousel-item
    >
    <v-img
      :src='require("@/assets/main/m-mypage.gif")'
    ></v-img>
    </v-carousel-item>
  </v-carousel>
</div>
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