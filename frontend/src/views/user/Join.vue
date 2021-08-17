<template>
  <div class="container">
    <!-- 회원가입 폼 -->
    <div class="join-form">
      <img @click="goMain" src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <div class="join-name">회원가입</div>
      <div class="join-box">
        <!-- 이메일 -->
        <input
          type="text"
          class="email"
          placeholder="이메일 주소"
          @input="emailCheck"
          v-model="email"
          v-bind:class="{
            error: error.email && error.emailCheck,
            complete: !error.email && email.length != 0 && !error.emailCheck,
          }"
        />
        <div class="error-message" v-if="error.email">{{ error.email }}</div>
        <div class="error-message" v-if="error.emailCheck">
          {{ error.emailCheck }}
        </div>
        <!-- 비번 -->
        <input
          :type="passwordType"
          class="pwd"
          placeholder="비밀번호"
          v-model="password"
          v-bind:class="{
            error: error.password,
            complete: !error.password && password.length != 0,
          }"
        />
        <div class="error-message" v-if="error.password">
          {{ error.password }}
        </div>
        <!-- 비번확인 -->
        <div class="input_label">
          <input
            :type="passwordConfirmType"
            class="pwd-check"
            placeholder="비밀번호 확인"
            v-model="passwordConfirm"
            v-bind:class="{
              error: error.passwordConfirm,
              complete: !error.passwordConfirm && passwordConfirm.length !== 0,
            }"
          />
          <div class="error-message" v-if="error.passwordConfirm">
            {{ error.passwordConfirm }}
          </div>
        </div>
        <!-- 페이지 전환 버튼 -->
        <div class="page-btn">
          <!-- 이전페이지 버튼 -->
          <button 
            class="mr-15"
            @click="goLogin"
          >
            <img src="@/assets/images/cancel-btn.svg" alt="back" />
          </button>
          <!-- 다음페이지 버튼 : 회원가입 이메일 인증 -->
          <button
            class="ms-15"
            @click.prevent="goJoinEmail"
            :disabled="!isSubmit"
            :class="{ disabled: !isSubmit }"
          >
            <img src="@/assets/images/next-btn.svg" alt="next" />
          </button>
        </div>
        <!-- footer -->
        <div>
          <div class="join-footer-text">
            이미 계정이 있으신가요?
            <button class="go-login ml-1" @click="goLogin">로그인</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as EmailValidator from "email-validator";
import PV from "password-validator";
import User from "@/api/User";

export default {
  name: "Join",
  data: function () {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        password: false,
        passwordConfirm: false,
        emailCheck: false,
      },
      isSubmit: false,
      isCheck: false,
      passwordType: "password",
      passwordConfirmType: "password",
    };
  },
  created() {
    this.component = this;
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(12)
      .has()
      .digits()
      .has()
      .letters()
      .has()
      .not()
      .spaces();
  },
  watch: {
    email: function () {
      this.checkForm();
    },
    password: function () {
      this.checkForm();
    },
    passwordConfirm: function () {
      this.checkForm();
    },
  },
  methods: {
    goMain: function () {
      this.$router.push({ name: "Main" });
    },
    goLogin: function () {
      this.$router.push({ name: "Login" });
    },
    checkForm: function () {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8~12자리를 입력해주세요.";
      else this.error.password = false;

      if (this.password != this.passwordConfirm)
        this.error.passwordConfirm = "비밀번호가 다릅니다";
      else this.error.passwordConfirm = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    emailCheck: function () {
      let { email } = this;
      let data = {
        email,
      };
      // 존재하면 false
      if (this.email) {
        User.requestSignupIdCheck(
          data,
          (res) => {
            this.isCheck = res.data.status;
            this.error.emailCheck = res.data.message;
            if (this.isCheck) {
              this.error.emailCheck = null;
            }
          },
          (err) => {
            console.log(err);
          }
        );
      }
    },
    goJoinEmail: function () {
      if (this.isSubmit && this.isCheck) {
        let { email, password } = this;
        let data = {
          email,
          password,
        };
        this.isSubmit = false;
        User.requestSignup(
          data,
          (res) => {
            console.log(res);
            this.isSubmit = true;
            alert("이메일로 인증번호가 발송되었습니다. 메일함을 확인해주세요.");
            this.$router.push({
              name: "JoinEmail",
              params: { email: this.email },
            });
          },
          (err) => {
            this.isSubmit = true;
            console.log(err);
          }
        );
      }
    },
  },
};
</script>

<style scoped>
.join-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.join-name {
  margin: 0 1px 10px 1px;
  font-family: GmarketSansTTF;
  font-size: 18px;
  font-weight: bold;
  color: #607d8b;
}
.join-box {
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
.pwd-check {
  width: 230px;
  height: 30px;
  margin: 10px 0 10px 0px;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
input::placeholder {
  font-family: GmarketSansTTF;
  font-size: 15px;
  font-weight: 300;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #c6c8c9;
}
.v-application .error {
  background-color: #ffffff !important;
  border: solid 1px crimson;
}
.error-message {
  font-family: GmarketSansTTF;
  font-size: 10px;
  color: crimson;
  display: flex;
  justify-content: center;
}
.page-btn {
  background-color: transparent;
  outline: 0;
  border: 0;
  margin-top: 10px;
  cursor: pointer;
}
.join-footer-text {
  display: flex;
  height: 16px;
  flex-grow: 0;
  margin-top: 50px;
  font-family: GmarketSansTTF;
  font-size: 12px;
  font-weight: 300;
  color: #c6c8c9;
}
.go-login {
  color: #607d8b;
  cursor: pointer;
}
</style>
