<!-- Todo Card 왼쪽 상단에 표시되는 Category 부분 -->
<template>
  <v-col class="d-inline-flex ml-3">
    <h class="category-underline pr-2 pl-2" :class="todoCategory.categoryid">
      {{ todoCategory.categoryname }}
    </h>
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
  font-size:18px;
  border-bottom: 6px solid black;
}

.algorithm {
  border-bottom-color: rgb(255, 198, 166);
}

.cs {
  border-bottom-color: rgb(255, 146, 231);
}

.framework {
  border-bottom-color: rgb(95, 201, 79);
}

.certificate {
  border-bottom-color: rgb(149, 151, 235);
}
@media ( max-width: 768px ) {
  .v-card__subtitle, .v-card__text, .v-card__title {
    padding: 6px;
}
.category-underline {
  font-size:12px;
}
}
</style>
