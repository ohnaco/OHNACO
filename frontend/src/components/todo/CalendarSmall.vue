<template>
  <div id="app">
    <v-app id="inspire">
      <v-row justify="center">
        <v-date-picker
          full-width
          v-model="picker"
          color="#607D8B"
          v-on="moveTodoDate()"
        >
        </v-date-picker>
      </v-row>
    </v-app>
  </div>
</template>

<script>
export default {
  props: {
    value: String,
  },
  data() {
    return {
      picker: this.value,
    };
  },
  methods: {
    moveTodoDate() {
      this.$emit("todoDate", this.picker);
    },
  },

  watch: {
    value: function () {
      this.picker = this.value;
    },
  },
};
</script>

<style>
.v-date-picker-title__date {
  font-family: GmarketSansLight;
  text-align: left;
  font-weight: 500;
  position: relative;
  overflow: hidden;
  padding-bottom: 8px;
  margin-bottom: -8px;
}
.v-picker__title {
  color: #fff;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  padding: 16px;
  display: none;
}
@media (max-width: 768px) {
  .moveLargeCalendar {
    display: none;
  }
}
</style>
