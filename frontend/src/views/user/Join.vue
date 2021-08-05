<template>
  <div>
<<<<<<< HEAD
    <div class="logo">
      <BigFullLogo />
    </div>
=======
>>>>>>> dbdbc4797a8c2f045453c6acad2d57e491b54a73
    <!-- 회원가입 폼 -->
    <div class="join-form">
      <img src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
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
<<<<<<< HEAD
        <div class="error_message" v-if="error.email">{{ error.email }}</div>
        <div class="error_message" v-if="error.emailCheck">
=======
        <div class="error-message" v-if="error.email">{{ error.email }}</div>
        <div class="error-message" v-if="error.emailCheck">
>>>>>>> dbdbc4797a8c2f045453c6acad2d57e491b54a73
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
<<<<<<< HEAD
        <div class="error_message" v-if="error.password">
=======
        <div class="error-message" v-if="error.password">
>>>>>>> dbdbc4797a8c2f045453c6acad2d57e491b54a73
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
<<<<<<< HEAD
          <div class="error_message" v-if="error.passwordConfirm">
=======
          <div class="error-message" v-if="error.passwordConfirm">
>>>>>>> dbdbc4797a8c2f045453c6acad2d57e491b54a73
            {{ error.passwordConfirm }}
          </div>
        </div>
        <!-- 페이지 전환 버튼 -->
<<<<<<< HEAD
        <div class="back_next_btn" @click="goMain">
          <!-- 이전페이지 버튼 -->
          <button class="page_btn">
            <svg
              width="59"
              height="22"
              viewBox="0 0 59 22"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M32.69 13.456H32.088V1.5H32.69V13.456ZM25.97 1.892C26.6607 1.892 27.2673 2.09267 27.79 2.494C28.3127 2.89533 28.7233 3.50667 29.022 4.328C29.3207 5.14 29.47 6.14333 29.47 7.338C29.47 8.53267 29.3207 9.54067 29.022 10.362C28.7233 11.174 28.3127 11.7807 27.79 12.182C27.2673 12.5833 26.6607 12.784 25.97 12.784C25.2793 12.784 24.6727 12.5833 24.15 12.182C23.6273 11.7807 23.2167 11.174 22.918 10.362C22.6193 9.54067 22.47 8.53267 22.47 7.338C22.47 6.14333 22.6193 5.14 22.918 4.328C23.2167 3.50667 23.6273 2.89533 24.15 2.494C24.6727 2.09267 25.2793 1.892 25.97 1.892ZM25.97 2.438C25.41 2.438 24.9107 2.62 24.472 2.984C24.0333 3.348 23.688 3.89867 23.436 4.636C23.1933 5.364 23.072 6.26467 23.072 7.338C23.072 8.41133 23.1933 9.31667 23.436 10.054C23.688 10.782 24.0333 11.328 24.472 11.692C24.9107 12.056 25.41 12.238 25.97 12.238C26.53 12.238 27.0247 12.056 27.454 11.692C27.8927 11.328 28.238 10.782 28.49 10.054C28.742 9.31667 28.868 8.41133 28.868 7.338C28.868 6.26467 28.742 5.364 28.49 4.636C28.238 3.89867 27.8927 3.348 27.454 2.984C27.0247 2.62 26.53 2.438 25.97 2.438ZM45.6668 4.566V1.5H46.2548V10.418H45.6668V5.098H42.7688V4.566H45.6668ZM39.4228 5.406C39.0681 6.28333 38.5595 6.988 37.8968 7.52C37.2435 8.052 36.4641 8.39733 35.5588 8.556V7.996C36.7255 7.78133 37.6448 7.20267 38.3168 6.26C38.9888 5.308 39.3435 4.05267 39.3808 2.494H35.6988V1.948H43.3428V2.494H39.9688C39.9408 3.38067 39.8241 4.16467 39.6188 4.846L43.5668 7.996L43.2308 8.458L39.4228 5.406ZM46.5908 12.63V13.176H36.5108V9.69H37.0988V12.63H46.5908Z"
                fill="#607D8B"
              />
              <path
                fill-rule="evenodd"
                clip-rule="evenodd"
                d="M21 7.5C21 7.33795 20.9436 7.18253 20.8431 7.06794C20.7426 6.95335 20.6064 6.88897 20.4643 6.88897L7.82977 6.88897L11.2013 3.0444C11.2511 2.98759 11.2907 2.92015 11.3176 2.84592C11.3446 2.7717 11.3584 2.69214 11.3584 2.6118C11.3584 2.53145 11.3446 2.4519 11.3176 2.37767C11.2907 2.30345 11.2511 2.236 11.2013 2.17919C11.1515 2.12238 11.0924 2.07732 11.0273 2.04657C10.9623 2.01582 10.8925 2 10.8221 2C10.7516 2 10.6819 2.01582 10.6168 2.04657C10.5517 2.07732 10.4926 2.12238 10.4428 2.17919L6.15738 7.06739C6.10749 7.12415 6.06791 7.19158 6.04091 7.26581C6.0139 7.34005 6 7.41963 6 7.5C6 7.58037 6.0139 7.65995 6.04091 7.73419C6.06791 7.80842 6.10749 7.87585 6.15738 7.93261L10.4428 12.8208C10.4926 12.8776 10.5517 12.9227 10.6168 12.9534C10.6819 12.9842 10.7516 13 10.8221 13C10.8925 13 10.9623 12.9842 11.0273 12.9534C11.0924 12.9227 11.1515 12.8776 11.2013 12.8208C11.2511 12.764 11.2907 12.6966 11.3176 12.6223C11.3446 12.5481 11.3584 12.4685 11.3584 12.3882C11.3584 12.3079 11.3446 12.2283 11.3176 12.1541C11.2907 12.0799 11.2511 12.0124 11.2013 11.9556L7.82977 8.11102L20.4643 8.11102C20.6064 8.11102 20.7426 8.04665 20.8431 7.93206C20.9436 7.81747 21 7.66205 21 7.5Z"
                fill="#607D8B"
              />
            </svg>
          </button>
          <!-- 다음페이지 버튼 : 회원가입 이메일 인증 -->
          <button
            class="page_btn"
=======
        <div class="page-btn" @click="goMain">
          <!-- 이전페이지 버튼 -->
          <button class="mr-15">
            <img src="@/assets/images/back-btn.svg" alt="back" />
          </button>
          <!-- 다음페이지 버튼 : 회원가입 이메일 인증 -->
          <button
            class="ms-15"
>>>>>>> dbdbc4797a8c2f045453c6acad2d57e491b54a73
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
            <button class="go-login" @click="goLogin">로그인</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as EmailValidator from "email-validator";
import PV from "password-validator";
import User from "../../api/User";

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
      this.$router.push({ name: "" });
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
            console.log(res);
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
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
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
