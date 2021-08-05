<template>
  <div id="app" class="m-5">
    <v-app id="inspire">
      <v-row justify="center">
        <v-date-picker full-width v-model="picker" color="#607D8B">
          <img src="@/assets/images/calendar.svg" style="position:absolute ; top:10px ; right:25px" @click="onEmit()">
        </v-date-picker>
      </v-row>
    </v-app>
  </div>
</template>

<script>
export default {
  data() {
    return {
      picker: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substr(0, 10),
    };
  },
  methods: {
    onEmit() {
      this.$emit("modalOn_child");
    },
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
}</style>
