<template>
  <div id="app">
    <v-app id="inspire">
      <v-row justify="center">
        <v-date-picker full-width v-model="picker" color="#607D8B" v-on="moveTodoDate()">
          <img src="@/assets/images/calendar.svg" style="position:absolute ; top:10px ; right:25px" @click="onEmit()"
          class="moveLargeCalendar">
        </v-date-picker>
      </v-row>
    </v-app>
  </div>
</template>

<script>
export default {
  props: {
    value : String
  },
  data() {
    return {
      picker: this.value,
    };
  },
  methods: {
    onEmit() {
      this.$emit("modalOn_child");
    },
    moveTodoDate() {
      this.$emit("todoDate", this.picker);
    }
  },

  watch: {
    value: function () {
      this.picker=this.value;
    }
  },
};
</script>

<style>
.v-date-picker-title__date {
    font-family : GmarketSansLight;
    text-align: left;
    font-weight: 500;
    position: relative;
    overflow: hidden;
    padding-bottom: 8px;
    margin-bottom: -8px;
}

@media (max-width: 768px) {
  .moveLargeCalendar{
    display: none;
  }
}
</style>
