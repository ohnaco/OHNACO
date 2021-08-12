<template>
  <div>
    <div class="findpwd-form">
      <!-- 로고 -->
      <img src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <!-- 체크표시 -->
      <img src="@/assets/images/find-pwd.svg" alt="success" />
      <!-- 문구 -->
      <p class="findpwd-text1">
        {{ email }}로 비밀번호 변경 링크가 전송되었습니다.
      </p>
      <p class="findpwd-text2 mb-0">
        메일함을 확인해주세요.
      </p>
      <p class="findpwd-text2">
        최대 5분 소요됩니다.
      </p>
      <div class="findpwd-footer-text">
        이메일을 받지 못하셨나요?
        <button class="send-email-again" @click="sendAgain">재전송</button>
      </div>
      <!-- 로그인 버튼 -->
      <button class="mt-1" @click="goLogin"><img src="@/assets/images/login-btn.svg" alt="success" /></button>
    </div>
  </div>
</template>

<script>
export default {
  name: "FindPwdEmail",
  data: function () {
    return {
      email: this.$route.params.email
    }
  },
  methods: {
    goLogin: function () {
      this.$router.push({ name: "Login" });
    },
  },
};
</script>

<style scoped>
.findpwd-form {
  display: grid;
  place-items: center;
}
.findpwd-text1 {
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-weight: bold;
  font-size: 18px;
  color: #607d8b;
  margin-top: 40px;
}
.findpwd-text2 {
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-size: 13px;
  color: #607d8b;
}
.findpwd-footer-text {
  display: flex;
  height: 16px;
  flex-grow: 0;
  margin: 3px;
  font-family: GmarketSansTTF;
  font-size: 12px;
  font-weight: 300;
  color: #c6c8c9;
}
.send-email-again {
  color: #607d8b;
  cursor: pointer;
}
</style>
