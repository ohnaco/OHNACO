<template>
  <v-card>
    <v-card-title class="blue-grey base white--text">
      {{ this.msg }} 삭제
    </v-card-title>
    <v-card-text class="text-body-1 mt-5">
      {{ this.msg }}을 정말 삭제하시겠습니까?
    </v-card-text>
    <v-card-actions class="justify-end mr-2 pb-4">
      <v-btn color="red darken-1" text @click="$emit('submit')"> 삭제 </v-btn>
      <v-btn color="#607D8B" text @click="$emit('hide')"> 취소 </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  props: {
    msg: {
      type: String,
    },
  },
};
</script>
