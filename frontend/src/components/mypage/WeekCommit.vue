<template>
  <div>
    <v-list-item
      color="#607D8B"
      class="pa-0 mt-5"
      :href="`https://github.com/${ info.githubid }`"
      target="_blank"
    >
      <v-avatar>
        <v-img
          src="@/assets/images/github-btn.svg"
          alt="github-logo"
          max-width="25"
          max-height="25"
        ></v-img>
      </v-avatar>
    <v-list-item-title>{{ info.githubid ? info.githubid : '' }}</v-list-item-title>    </v-list-item>
    <div class="progress d-flex" style="height: 36px; background: transparent">
      <div class="progress-bar" :class=" commit.day7 | countCommit" role="progressbar" style="width: 17%"></div>
      <div class="progress-bar" :class=" commit.day6 | countCommit" role="progressbar" style="width: 17%"></div>
      <div class="progress-bar" :class=" commit.day5 | countCommit" role="progressbar" style="width: 17%"></div>
      <div class="progress-bar" :class=" commit.day4 | countCommit" role="progressbar" style="width: 17%"></div>
      <div class="progress-bar" :class=" commit.day3 | countCommit" role="progressbar" style="width: 17%"></div>
      <div class="progress-bar" :class=" commit.day2 | countCommit" role="progressbar" style="width: 17%"></div>
      <div class="progress-bar" :class=" commit.day1 | countCommit" role="progressbar" style="width: 17%"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "WeekCommit",
  props: {
    commit: {
      type: Object,
    },
    info: {
      type: Object,
    },
  },
  filters: {
    countCommit: function (value) {
      if (value > 3) 
        return "color-" + 3;
      else return "color-" + value;
    },
  },
};
</script>

<style scoped>
.color-0 {
  background-color: #c6c8c9;
  border: solid 1px #607d8b;
}
.color-1 {
  background-color: #8ae99f;
  border: solid 1px #607d8b;
}
.color-2 {
  background-color: #3ecf5e;
  border: solid 1px #607d8b;
}
.color-3 {
  background-color: #4c964b;
  border: solid 1px #607d8b;
}
</style>
