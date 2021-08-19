<template>
<div>
  <v-carousel 
    v-show="$vuetify.breakpoint.mdAndUp"
    cycle
    height="auto"
    width="400"
    class="pa-0"
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
  <!-- <v-carousel 
    v-show="$vuetify.breakpoint.smAndDown"
    cycle
    height="auto"
    width="400"
    interval="3000"
    hide-delimiter-background
    show-arrows-on-hover
    hide-delimiters
    >
    <v-carousel-item
      v-for="(mobile,i) in mobiles"
      :key="i"
      :src="mobile.src"
    ></v-carousel-item>
  </v-carousel> -->
</div>
</template>

<script>
  export default {
    data () {
      return {
        items: [
          {
            src: require('@/assets/main/tech-sub.gif'),
          },
          {
            src: require('@/assets/main/tech-scr.gif'),
          },
        ],
        // mobiles: [
        //   {
        //     src: require('@/assets/main/mypage.png'),
        //   },
        //   {
        //     src: require('@/assets/main/todo.png'),
        //   },
        // ],
      }
    },
  }
</script>