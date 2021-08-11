<template>
  <button @click="goLogin">로그인</button>
</template>

<script>

export default {
  name: "Home",
  methods: {
    goLogin: function () {
      this.$router.push({ name: "Login" });
    },
  },
};
</script>
