<template>
  <div>
    <div class="logo">
      <BigFullLogo/>
    </div>
    <!-- 회원가입 폼 -->
    <div class="join_form">
      <div class="join_name">회원가입</div>
      <div class="join_box">
        <!-- 이메일 -->
        <input 
          type="text" 
          class="email" 
          placeholder="이메일 주소"
          @input="emailCheck"
          v-model="email"
          v-bind:class="{
            error: error.email&&error.emailCheck, complete:!error.email&&email.length!=0&&!error.emailCheck}"
        />
        <div class="error_message" v-if="error.email">{{ error.email }}</div>
        <div class="error_message" v-if="error.emailCheck">{{ error.emailCheck }}</div>
        <!-- 비번 -->
        <input
          :type="passwordType"
          class="pwd"
          placeholder="비밀번호"
          v-model="password"
          v-bind:class="{error:error.password, complete:!error.password&&password.length!=0}"
        />
        <div class="error_message" v-if="error.password">{{ error.password }}</div>
        <!-- 비번확인 -->
        <div class="input_label">
          <input
            :type="passwordConfirmType"
            class="pwd-check"
            placeholder="비밀번호 확인"
            v-model="passwordConfirm"
            v-bind:class="{error : error.passwordConfirm, complete:!error.passwordConfirm&&passwordConfirm.length!==0}"
          />
          <div class="error_message" v-if="error.passwordConfirm">{{ error.passwordConfirm }}</div>
        </div>
        <!-- 페이지 전환 버튼 -->
        <div 
          class="back_next_btn"
          @click="goMain"
          >
          <!-- 이전페이지 버튼 -->
          <button class="page_btn">
            <svg width="59" height="22" viewBox="0 0 59 22" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M32.69 13.456H32.088V1.5H32.69V13.456ZM25.97 1.892C26.6607 1.892 27.2673 2.09267 27.79 2.494C28.3127 2.89533 28.7233 3.50667 29.022 4.328C29.3207 5.14 29.47 6.14333 29.47 7.338C29.47 8.53267 29.3207 9.54067 29.022 10.362C28.7233 11.174 28.3127 11.7807 27.79 12.182C27.2673 12.5833 26.6607 12.784 25.97 12.784C25.2793 12.784 24.6727 12.5833 24.15 12.182C23.6273 11.7807 23.2167 11.174 22.918 10.362C22.6193 9.54067 22.47 8.53267 22.47 7.338C22.47 6.14333 22.6193 5.14 22.918 4.328C23.2167 3.50667 23.6273 2.89533 24.15 2.494C24.6727 2.09267 25.2793 1.892 25.97 1.892ZM25.97 2.438C25.41 2.438 24.9107 2.62 24.472 2.984C24.0333 3.348 23.688 3.89867 23.436 4.636C23.1933 5.364 23.072 6.26467 23.072 7.338C23.072 8.41133 23.1933 9.31667 23.436 10.054C23.688 10.782 24.0333 11.328 24.472 11.692C24.9107 12.056 25.41 12.238 25.97 12.238C26.53 12.238 27.0247 12.056 27.454 11.692C27.8927 11.328 28.238 10.782 28.49 10.054C28.742 9.31667 28.868 8.41133 28.868 7.338C28.868 6.26467 28.742 5.364 28.49 4.636C28.238 3.89867 27.8927 3.348 27.454 2.984C27.0247 2.62 26.53 2.438 25.97 2.438ZM45.6668 4.566V1.5H46.2548V10.418H45.6668V5.098H42.7688V4.566H45.6668ZM39.4228 5.406C39.0681 6.28333 38.5595 6.988 37.8968 7.52C37.2435 8.052 36.4641 8.39733 35.5588 8.556V7.996C36.7255 7.78133 37.6448 7.20267 38.3168 6.26C38.9888 5.308 39.3435 4.05267 39.3808 2.494H35.6988V1.948H43.3428V2.494H39.9688C39.9408 3.38067 39.8241 4.16467 39.6188 4.846L43.5668 7.996L43.2308 8.458L39.4228 5.406ZM46.5908 12.63V13.176H36.5108V9.69H37.0988V12.63H46.5908Z" fill="#607D8B"/>
            <path fill-rule="evenodd" clip-rule="evenodd" d="M21 7.5C21 7.33795 20.9436 7.18253 20.8431 7.06794C20.7426 6.95335 20.6064 6.88897 20.4643 6.88897L7.82977 6.88897L11.2013 3.0444C11.2511 2.98759 11.2907 2.92015 11.3176 2.84592C11.3446 2.7717 11.3584 2.69214 11.3584 2.6118C11.3584 2.53145 11.3446 2.4519 11.3176 2.37767C11.2907 2.30345 11.2511 2.236 11.2013 2.17919C11.1515 2.12238 11.0924 2.07732 11.0273 2.04657C10.9623 2.01582 10.8925 2 10.8221 2C10.7516 2 10.6819 2.01582 10.6168 2.04657C10.5517 2.07732 10.4926 2.12238 10.4428 2.17919L6.15738 7.06739C6.10749 7.12415 6.06791 7.19158 6.04091 7.26581C6.0139 7.34005 6 7.41963 6 7.5C6 7.58037 6.0139 7.65995 6.04091 7.73419C6.06791 7.80842 6.10749 7.87585 6.15738 7.93261L10.4428 12.8208C10.4926 12.8776 10.5517 12.9227 10.6168 12.9534C10.6819 12.9842 10.7516 13 10.8221 13C10.8925 13 10.9623 12.9842 11.0273 12.9534C11.0924 12.9227 11.1515 12.8776 11.2013 12.8208C11.2511 12.764 11.2907 12.6966 11.3176 12.6223C11.3446 12.5481 11.3584 12.4685 11.3584 12.3882C11.3584 12.3079 11.3446 12.2283 11.3176 12.1541C11.2907 12.0799 11.2511 12.0124 11.2013 11.9556L7.82977 8.11102L20.4643 8.11102C20.6064 8.11102 20.7426 8.04665 20.8431 7.93206C20.9436 7.81747 21 7.66205 21 7.5Z" fill="#607D8B"/>
            </svg>
          </button>
          <!-- 다음페이지 버튼 : 회원가입 이메일 인증 -->
          <button 
            class="page_btn" 
            @click.prevent="goJoinEmail" 
            :disabled="!isSubmit"
            :class="{disabled : !isSubmit}"
          >
            <svg width="56" height="22" viewBox="0 0 56 22" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M13.23 7.282H11.34V13.456H10.752V1.5H11.34V6.736H13.23V7.282ZM6.23 12.07C6.79 12.07 7.35 12.014 7.91 11.902C8.47933 11.79 8.95067 11.6453 9.324 11.468V12.042C8.96 12.2193 8.498 12.3593 7.938 12.462C7.378 12.5647 6.80867 12.616 6.23 12.616H1.526V2.06H8.386V2.606H2.114V12.07H6.23ZM20.2008 1.528C21.6661 1.528 22.8841 1.71467 23.8548 2.088C24.8348 2.452 25.3248 2.998 25.3248 3.726C25.3248 4.454 24.8348 5.00467 23.8548 5.378C22.8841 5.742 21.6661 5.924 20.2008 5.924C18.7355 5.924 17.5128 5.742 16.5328 5.378C15.5621 5.00467 15.0768 4.454 15.0768 3.726C15.0768 2.998 15.5621 2.452 16.5328 2.088C17.5128 1.71467 18.7355 1.528 20.2008 1.528ZM20.2008 2.074C18.9315 2.074 17.8581 2.214 16.9808 2.494C16.1128 2.774 15.6788 3.18467 15.6788 3.726C15.6788 4.26733 16.1128 4.678 16.9808 4.958C17.8581 5.238 18.9315 5.378 20.2008 5.378C21.4701 5.378 22.5388 5.238 23.4068 4.958C24.2841 4.678 24.7228 4.26733 24.7228 3.726C24.7228 3.18467 24.2841 2.774 23.4068 2.494C22.5388 2.214 21.4701 2.074 20.2008 2.074ZM26.1088 7.814H14.2928V7.268H26.1088V7.814ZM25.1708 13.19H15.2448V9.438H25.1708V13.19ZM24.5828 12.644V9.984H15.8328V12.644H24.5828Z" fill="#607D8B"/>
            <path fill-rule="evenodd" clip-rule="evenodd" d="M27 7.62543C27 7.45968 27.0564 7.30072 27.1569 7.18352C27.2574 7.06631 27.3936 7.00047 27.5357 7.00047H40.1702L36.7987 3.06822C36.6981 2.95087 36.6416 2.79171 36.6416 2.62575C36.6416 2.45979 36.6981 2.30063 36.7987 2.18328C36.8992 2.06593 37.0357 2 37.1779 2C37.3202 2 37.4566 2.06593 37.5572 2.18328L41.8426 7.18296C41.8925 7.24101 41.9321 7.30998 41.9591 7.3859C41.9861 7.46183 42 7.54323 42 7.62543C42 7.70763 41.9861 7.78903 41.9591 7.86496C41.9321 7.94088 41.8925 8.00985 41.8426 8.0679L37.5572 13.0676C37.4566 13.1849 37.3202 13.2509 37.1779 13.2509C37.0357 13.2509 36.8992 13.1849 36.7987 13.0676C36.6981 12.9502 36.6416 12.7911 36.6416 12.6251C36.6416 12.4592 36.6981 12.3 36.7987 12.1826L40.1702 8.25039H27.5357C27.3936 8.25039 27.2574 8.18455 27.1569 8.06734C27.0564 7.95014 27 7.79118 27 7.62543Z" fill="#607D8B"/>
            </svg>
          </button>
        </div>
        <!-- footer -->
        <div>
          <div class="join_footer_text">
            이미 계정이 있으신가요?
            <button class="go_login" @click="gologin">로그인</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BigFullLogo from '@/components/common/BigFullLogo.vue'
import * as EmailValidator from "email-validator";
import PV from "password-validator";
import User from "../../api/User";

export default {
  name: "Join",
  components: {
    BigFullLogo,
  },
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
    }
  },
  created() {
    this.component = this;
    this.passwordSchema
      .is().min(8)
      .is().max(12)
      .has().digits()
      .has().letters()
      .has().not().spaces();
  },
  watch: {
    email: function() {
      this.checkForm()
    },
    password: function() {
      this.checkForm();
    },
    passwordConfirm: function() {
      this.checkForm();
    },
  },
  methods: {
    goMain: function () {
      this.$router.push({ name: "" })
    },
    gologin: function () {
      this.$router.push({ name: "Login" })
    },
    checkForm: function() {
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
        this.error.passwordConfirm = '비밀번호가 다릅니다'
      else this.error.passwordConfirm = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    emailCheck: function () {
        let {email} = this;
        let data = {
          email, 
        };
        // 존재하면 false
      if (this.email) {
        User.requestSignupIdCheck(
          data,
          res => {
            console.log(res)
            this.isCheck = res.data.status
            this.error.emailCheck = res.data.message
            if (this.isCheck) {
              this.error.emailCheck = null
            }
          },
          err => {
            console.log(err)
          }
        )
      }
    },
    goJoinEmail: function () {      
      if (this.isSubmit && this.isCheck) {
        let {email, password} = this;
        let data = {
          email, 
          password
        };
        this.isSubmit = false;
        User.requestSignup(
          data,
          res => {
            console.log(res);
            this.isSubmit = true;
            alert('이메일로 인증번호가 발송되었습니다. 메일함을 확인해주세요.')
            this.$router.push({name:'JoinEmail', params: {email : this.email}})
          },
          err => {
            this.isSubmit = true;
            console.log(err);
          }
        )
      }
    },
  }
};
</script>

<style scoped>
.join_form {
  display: grid;
  place-items: center;
}
.join_name {
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
.join_box {
  left: 488px;
  top: 360px;
  width: 280px;
  flex-grow: 0;
  margin: 10px 0 0;
  padding: 32px 72px 19px;
  border-radius: 10px;
  border: solid 1px #607d8b;
  background-color: rgba(255, 255, 255, 0);
}
.email {
  width: 250px;
  height: 20px;
  margin: 0 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
.pwd {
  width: 250px;
  height: 20px;
  margin: 10px 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
.pwd-check {
  width: 250px;
  height: 20px;
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
.error_message {
  font-family: GmarketSansTTF;
  font-size: 10px;
  color: crimson;
}
.page_btn {
  background-color: transparent;
  outline: 0;
  border: 0;
  margin-top: 10px;
  cursor: pointer;
}
.back_next_btn {
  display: flex;
  justify-content: space-between;
  width: 290px;
}
.join_footer_text {
  position: relative;
  height: 16px;
  flex-grow: 0;
  margin: 66px 0 0 56px;
  font-family: GmarketSansTTF;
  font-size: 14px;
  font-weight: 300;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #c6c8c9;
  margin-left: 30px;
  margin-right: 30px;
}
.go_login {
  position: relative;
  background-color: transparent;
  outline: 0;
  border: 0;
  color: #607d8b;
  cursor: pointer;
}
</style>
