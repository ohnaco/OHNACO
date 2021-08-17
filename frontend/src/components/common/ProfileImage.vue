<template>
  <v-avatar :tile="false" :size="150" color="grey lighten-4">
    <img :src="`${user.info ? user.info.image : ''}`" alt="avatar" />
  </v-avatar>
</template>

<script>
export default {
  name: "ProfileImage",
  props: {
    user: {
      type: [Array, Object],
    },
  },
};
</script>
