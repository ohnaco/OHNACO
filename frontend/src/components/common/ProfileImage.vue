<template>
  <v-avatar :tile="false" :size="200" color="grey lighten-4">
    <img :src="`${user.image}`" alt="avatar" />
  </v-avatar>
</template>

<script>
export default {
  name: 'ProfileImage',
  props: {
    user: {
      type: Object
    }
  }
}
</script>
