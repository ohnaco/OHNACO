<template>
  <v-carousel 
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