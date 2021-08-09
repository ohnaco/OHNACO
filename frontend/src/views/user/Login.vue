<template>
  <div>
    <!-- 로그인 폼 -->
    <div class="join-form">
      <img src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <div class="join-name">로그인</div>
      <div class="join-box">
        <!-- 이메일 -->
        <input
          type="text"
          id="email"
          class="email"
          placeholder="이메일 주소"
          v-model="email"
        />
        <!-- 비번 -->
        <input
          type="password"
          id="pwd"
          class="pwd"
          placeholder="비밀번호"
          v-model="password"
        />
        <!-- 로그인 버튼 -->
        <button class="mt-1" @click="doLogin"><img src="@/assets/images/login-btn.svg" alt="login" /></button>
        <div>
          <button class="signup-btn" @click="goSignup">회원가입</button>
          <button class="findpwd-btn" @click="goFindpwd">비밀번호 찾기</button>
        </div>
        <!-- 소셜 로그인 -->
        <div class="social-login">
          <div>
            <img src="@/assets/images/login-line.svg" alt="line" />
          </div>
          <p class="social-text">소셜 계정 로그인</p>
          <div>
            <img src="@/assets/images/login-line.svg" alt="line" />
          </div>
        </div>
        <!-- github 버튼 -->
        <button class="github-btn" @click="githubLogin">
          <img src="@/assets/images/github-btn.svg" alt="gihub_logo" />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import User from "../../api/User";
import { createNamespacedHelpers } from "vuex";
const userHelper = createNamespacedHelpers("userStore");

export default {
  name: "Join",
  data: function () {
    return {
      email: null,
      password: null,
    };
  },
  methods: {
    ...userHelper.mapActions(["setUser"]),
    goSignup: function () {
      this.$router.push({ name: "Join" });
    },
    goFindpwd: function () {
      this.$router.push({ name: "" });
    },
    doLogin: function () {
      let { email, password } = this;
      let data = {
        email,
        password,
      };
      User.requestLogin(
        data,
        (res) => {
          localStorage.setItem("jwt-access-token", res.headers.authorization.substring(7));
          this.setUser(res);
          alert("로그인 되었습니다. 오나코에서 오늘 하루도 힘내 코딩하세요 :)");
          this.$router.push({ name: "Todo" });
        },
        (err) => {
          console.log(err);
          alert("이메일과 비밀번호를 다시 확인해주세요.");
        }
      );
    },
    githubLogin: function (res) {
      console.log(res);
    },
  },
};
</script>

<style scoped>
.join-form {
  display: grid;
  place-items: center;
}
.join-name {
  width: 109px;
  height: 27px;
  flex-grow: 0;
  margin: 0 178px 10px 177px;
  font-family: GmarketSansTTF;
  font-size: 18px;
  font-weight: bold;
  text-align: center;
  color: #607d8b;
}
.join-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 300px;
  margin: 10px 0 0;
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
.social-login {
  margin-top: 10px;
  display: flex;
  justify-content: space-evenly;
  font-size: 12px;
  color: #607d8b;
}
.social-text {
  margin-top: 5px;
  font-size: 10px;
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
.github-btn {
  cursor: pointer;
}
</style>
