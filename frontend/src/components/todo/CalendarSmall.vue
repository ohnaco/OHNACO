<template>
    <div id="app">
  <v-app id="inspire">
    <v-row justify="center">
      <v-date-picker v-model="picker" color="#607D8B"></v-date-picker>
    </v-row>
  </v-app>
</div>
</template>

<script>
  export default {
    data () {
      return {
        picker: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      }
    },
  }
</script>

<style>

</style>
