<template>
<div class="container">
  <button @click="goLogin">
    <img src="@/assets/images/login-btn.svg" alt="">
  </button>
</div>
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
