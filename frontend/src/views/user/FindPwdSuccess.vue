<template>
  <div class="container">
    <div class="success-form">
      <!-- 로고 -->
      <img src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <!-- 체크표시 -->
      <img src="@/assets/images/check-icon.svg" alt="success" />
      <!-- 문구 -->
      <p class="change-pwd-success-text1">비밀번호 변경이 완료되었습니다.</p>
      <p class="change-pwd-success-text2">
        새로운 비밀번호로 다시 로그인 해주세요.
      </p>
      <!-- 로그인 버튼 -->
      <button class="mt-1" @click="goLogin">
        <img src="@/assets/images/login-btn.svg" alt="success" />
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "FindPwdSuccess",
  methods: {
    goLogin: function () {
      this.$router.push({ name: "Login" });
    },
  },
};
</script>

<style scoped>
.success-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.change-pwd-success-text1 {
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-weight: bold;
  font-size: 18px;
  color: #607d8b;
  margin-top: 40px;
}
.change-pwd-success-text2 {
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-size: 13px;
  color: #607d8b;
}
</style>
