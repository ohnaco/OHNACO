<template>
  <v-card>
    <v-card-title style="background-color: #607d8b; color: white">
      정말 오나코를 탈퇴하시겠습니까?
    </v-card-title>
    <v-card-text class="mt-5">
      회원 탈퇴를 하시면 활동기록이 모두 삭제되며 복구할 수 없습니다.
    </v-card-text>
    <v-card-actions class="justify-end mr-2 pb-4">
      <v-btn color="red darken-1" text @click="$emit('submit')"> 탈퇴 </v-btn>
      <v-btn color="#607D8B" text @click="$emit('hide')"> 취소 </v-btn>
    </v-card-actions>
  </v-card>
</template>
