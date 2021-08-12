<template>
  <button @click="goLogin">로그인</button>
</template>

<script>

export default {
  name: "Main",
  methods: {
    goLogin: function () {
      this.$router.push({ name: "Login" });
    },
  },
};
</script>
