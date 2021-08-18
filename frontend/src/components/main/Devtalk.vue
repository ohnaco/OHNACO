<template>
  <v-carousel 
    cycle
    height="400"
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
            src: require('@/assets/images/devtalk-page.png'),
          },
          {
            src: require('@/assets/images/devdetail-page.png'),
          },
          {
            src: require('@/assets/images/devwrite-page.png'),
          },
        ],
      }
    },
  }
</script>