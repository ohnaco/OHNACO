<template>
  <div class="container">
    <!-- 로그인 폼 -->
    <div class="login-form">
      <img @click="goMain" src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <div class="login-name">로그인</div>
      <div class="login-box">
        <!-- 이메일 -->
        <b-form-input
          type="text" 
          :state="null"
          id="email" 
          class="email" 
          placeholder="이메일 주소" 
          v-model="email" 
        ></b-form-input>
        <!-- 비번 -->
        <b-form-input
          type="password"
          :state="null"
          id="pwd"
          class="pwd"
          placeholder="비밀번호"
          v-model="password"
          @keyup.enter="doLogin"
        ></b-form-input>
        <!-- 로그인 버튼 -->
        <button class="mt-1" @click="doLogin">
          <img src="@/assets/images/login-btn.svg" alt="login" />
        </button>
        <div class="mt-3">
          <button class="signup-btn" @click="goSignup">회원가입</button>
          <button class="findpwd-btn" @click="goFindpwd">비밀번호 찾기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { createNamespacedHelpers } from "vuex";
const userHelper = createNamespacedHelpers("userStore");

export default {
  name: "Login",
  data: function () {
    return {
      email: null,
      password: null,
    };
  },
  methods: {
    ...userHelper.mapActions(["login"]),
    goMain: function () {
      this.$router.push({ name: "Main" });
    },
    goSignup: function () {
      this.$router.push({ name: "Join" });
    },
    goFindpwd: function () {
      this.$router.push({ name: "FindPwd" });
    },
    doLogin: function () {
      let { email, password } = this;
      let data = {
        email,
        password,
      };
      this.login(data);
    },
  },
};
</script>

<style scoped>
.login-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.login-name {
  margin: 0 1px 10px 1px;
  font-family: GmarketSansTTF;
  font-size: 18px;
  font-weight: bold;
  color: #607d8b;
}
.login-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 300px;
  padding: 32px 0px 19px;
  border-radius: 10px;
  border: solid 1px #607d8b;
  background-color: rgba(255, 255, 255, 0);
}
.email {
  width: 230px;
  height: 30px;
  margin: 0 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
.pwd {
  width: 230px;
  height: 30px;
  margin: 10px 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
input::placeholder {
  font-family: GmarketSansTTF;
  font-size: 13px;
  font-weight: 300;
  color: #c6c8c9;
}
.signup-btn {
  outline: 0;
  border: 0;
  margin: 5px 50px 5px 5px;
  font-size: 12px;
  color: #607d8b;
  cursor: pointer;
}
.findpwd-btn {
  outline: 0;
  border: 0;
  margin: 5px 5px 5px 50px;
  font-size: 12px;
  color: #607d8b;
  cursor: pointer;
}
</style>
