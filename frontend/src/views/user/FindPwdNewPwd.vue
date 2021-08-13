<template>
  <div>
    <!-- 비밀번호 변경 폼 -->
    <div class="change-pwd-form">
      <img src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <div class="change-pwd-name">비밀번호 변경</div>
      <div class="change-pwd-box">
        <div class="change-pwd-text">새로운 비밀번호를 입력해주세요.</div>
        <!-- 비번 -->
        <input
          :type="passwordType"
          class="pwd"
          placeholder="비밀번호"
          v-model="password"
          v-bind:class="{
            error: error.password,
            complete: !error.password,
          }"
        />
        <div class="error-message" v-if="error.password">
          {{ error.password }}
        </div>
        <!-- 비번 확인 -->
        <div class="input_label">
          <input
            :type="passwordConfirmType"
            class="pwd-check"
            placeholder="비밀번호 확인"
            v-model="passwordConfirm"
            v-bind:class="{
              error: error.passwordConfirm,
              complete: !error.passwordConfirm,
            }"
          />
          <div class="error-message" v-if="error.passwordConfirm">
            {{ error.passwordConfirm }}
          </div>
        </div>
        <!-- 비밀번호 변경 완료 버튼 -->
        <button class="mt-1" @click="doChangePwd">
          <img src="@/assets/images/find-pwd-btn.svg" alt="change-pwd" />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";
import User from "@/api/User";

export default {
  name: "FindPwdNewPwd",
  data: function () {
    return {
      email: this.$route.query.email,
      code: this.$route.query.code,
      password: '',
      passwordConfirm: '',
      passwordSchema: new PV(),
      error: {
        password: false,
        passwordConfirm: false,
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
    };
  },
  created() {
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
    password: function () {
      this.checkForm();
    },
    passwordConfirm: function () {
      this.checkForm();
    },
  },
  methods: {
    checkForm: function () {
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
    doChangePwd: function () {
      if (this.isSubmit) {
        let data = {
          email: this.$route.query.email,
          code: this.$route.query.code,
          password: this.password,
        };
        this.isSubmit = false
        User.requestFindPwd(
          data, 
          (res) => {
            if (res.data.status == 'success') {
              this.$router.push({ name: "FindPwdSuccess" });
              this.isSubmit = true
            } else {
              this.isSubmit = false
              alert('페이지가 만료되었습니다.')
            }
          },
          (err) => {
            this.isSubmit = true
            console.log(err)
          }
        )
      }
    },
  },
};
</script>

<style scoped>
.change-pwd-form {
  display: grid;
  place-items: center;
}
.change-pwd-name {
  width: 120px;
  height: 27px;
  flex-grow: 0;
  margin: 0 178px 10px 177px;
  font-family: GmarketSansTTF;
  font-size: 18px;
  font-weight: bold;
  text-align: center;
  color: #607d8b;
}
.change-pwd-text {
  position: relative;
  display: flex;
  justify-content: center;
  height: 26px;
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-weight: 300;
  font-size: 12px;
  color: #000000;
}
.change-pwd-box {
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
  font-size: 13px;
  font-weight: 300;
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
</style>
