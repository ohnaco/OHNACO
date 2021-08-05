<template>
  <div>
    <div class="success-form">
      <!-- 로고 -->
      <img src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <!-- 체크표시 -->
      <img src="@/assets/images/check-icon.svg" alt="success" />
      <!-- 문구 -->
      <p class="joinsuccess-text1">오나코 회원가입이 완료되었습니다.</p>
      <p class="joinsuccess-text2">
        오나코의 모든 서비스를 이용하실 수 있습니다.
      </p>
      <!-- 로그인 버튼 -->
      <button class="mt-1" @click="goLogin"><img src="@/assets/images/login-btn.svg" alt="success" /></button>
    </div>
  </div>
</template>

<script>
export default {
  name: "JoinSuccess",
  methods: {
    goLogin: function () {
      this.$router.push({ name: "Login" });
    },
  },
};
</script>

<style scoped>
.success-form {
  display: grid;
  place-items: center;
}
.joinsuccess-text1 {
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-weight: bold;
  font-size: 18px;
  color: #607d8b;
  margin-top: 40px;
}
.joinsuccess-text2 {
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-size: 13px;
  color: #607d8b;
}
</style>
