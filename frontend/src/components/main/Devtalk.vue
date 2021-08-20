<template>
  <v-layout class="justify-content-center">
    <v-carousel
      v-show="$vuetify.breakpoint.smAndUp"
      cycle
      width="400"
      height="auto"
      interval="3000"
      hide-delimiter-background
      show-arrows-on-hover
      hide-delimiters
      >
      <v-carousel-item
        v-for="(item,i) in webs"
        :key="i"
        :src="item.src"
      ></v-carousel-item>
    </v-carousel>
    <v-carousel 
      v-show="$vuetify.breakpoint.xs"
      cycle
      height="auto"
      width="230"
      interval="3000"
      hide-delimiter-background
      show-arrows-on-hover
      hide-delimiters
      >
      <v-carousel-item
        v-for="(item,i) in mobiles"
        :key="i"
        :src="item.src"
        width="230"
        height="auto"
        class="ml-auto mr-auto"
      ></v-carousel-item>
    </v-carousel>
  </v-layout>
</template>

<script>
  export default {
    data () {
      return {
        webs: [
          {
            src: require('@/assets/main/devtalk-list.png'),
          },
          {
            src: require('@/assets/main/devtalk-detail.png'),
          },
        ],
        mobiles: [
          {
            src: require('@/assets/main/m-devtalk-list.jpg'),
          },
          {
            src: require('@/assets/main/m-devtalk-detail.jpg'),
          },
        ],
      }
    },
  }
</script>