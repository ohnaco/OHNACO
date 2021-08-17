<template>
  <v-avatar :tile="false" :size="150" color="blue-grey">
    <img :src="`${user.image}`" alt="avatar" />
  </v-avatar>
</template>

<script>
export default {
  name: "ProfileImage",
  props: {
    user: {
      type: Object,
    },
  },
};
</script>
