<template>
  <v-col class="d-inline-flex">
    <h3 class="category-underline" :class="todoCategory.eng">
      {{ todoCategory.kor }}
    </h3>
  </v-col>
</template>

<script>
export default {
  props: {
    todoCategory: { type: Object, default: () => {} },
  },
};
</script>

<style lang="scss" scoped>
.category-underline {
  border-bottom: 6px solid black;
}

.algorithm {
  border-bottom-color: rgb(255, 198, 166);
}

.operating-system {
  border-bottom-color: rgb(255, 146, 231);
}
</style>
