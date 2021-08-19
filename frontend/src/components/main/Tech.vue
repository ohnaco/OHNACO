<template>
  <v-layout class="justify-content-center">
    <v-carousel
      v-show="$vuetify.breakpoint.smAndUp"
      cycle
      width="380"
      height="auto"
      interval="23000"
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
      width="250"
      interval="20000"
      hide-delimiter-background
      show-arrows-on-hover
      hide-delimiters
      >
      <v-carousel-item
        v-for="(item,i) in mobiles"
        :key="i"
        :src="item.src"
        width="250"
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
            src: require('@/assets/main/tech-sub.gif'),
          },
          {
            src: require('@/assets/main/tech-scr.gif'),
          },
        ],
        mobiles: [
          {
            src: require('@/assets/main/m-tech-sub.gif'),
          },
          {
            src: require('@/assets/main/m-tech-scr.gif'),
          },
        ],
      }
    },
  }
</script>