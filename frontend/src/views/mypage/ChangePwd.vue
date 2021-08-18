<template>
  <div>
    <TopNavBar/> 
    <!-- 비밀번호 변경 폼 -->
    <div class="update-pwd-form mt-15">
      <div class="update-pwd-name">비밀번호 변경</div>
      <div class="update-pwd-box">
        <div class="mb-10">
          <img src="@/assets/images/change-pwd-message.svg" alt="change-pwd-message">
        </div>
        <!-- 현재비번 -->
        <b-form-input
          :type="passwordType"
          :state="null"
          class="pwd"
          placeholder="비밀번호"
          v-model="password"
          v-bind:class="{
            error: error.password,
            complete: !error.password && password.length != 0,
          }"
        ></b-form-input>
        <div class="error-message" v-if="error.password">
          {{ error.password }}
        </div>
        <!-- 비번 -->
        <b-form-input
          :type="newpasswordType"
          :state="null"
          class="newpwd"
          placeholder="새로운 비밀번호"
          v-model="newpassword"
          v-bind:class="{
            error: error.newpassword,
            complete: !error.newpassword && newpassword.length != 0,
          }"
        ></b-form-input>
        <div class="error-message" v-if="error.newpassword">
          {{ error.newpassword }}
        </div>
        <!-- 비번확인 -->
        <div class="input_label">
          <b-form-input
            :type="newpasswordConfirmType"
            :state="null"
            class="newpwd-check"
            placeholder="새로운 비밀번호 확인"
            v-model="newpasswordConfirm"
            v-bind:class="{
              error: error.newpasswordConfirm,
              complete: !error.newpasswordConfirm && newpasswordConfirm.length !== 0,
            }"
          ></b-form-input>
          <div class="error-message" v-if="error.newpasswordConfirm">
            {{ error.newpasswordConfirm }}
          </div>
        </div>
        <!-- 버튼 -->
        <div class="page-btn">
          <!-- 이전 페이지: 마이페이지 -->
          <button 
            class="mr-15" 
            @click="goupdateInfo"
          >
            <img src="@/assets/images/cancel-btn.svg" alt="back" />
          </button>
          <!-- 다음 페이지 : 마이페이지 -->
          <button
            class="ml-15"
            @click="updatePwd"
          >
            <img src="@/assets/images/complete-btn.svg" alt="next" />
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MyPage from "@/api/MyPage";
import TopNavBar from "@/components/common/TopNavBar.vue";
import PV from "password-validator";

export default {
  name: "ChagePwd",
  components: {
    TopNavBar
  },
  data: function () {
    return {
      password: "",
      newpassword: "",
      newpasswordConfirm: "",
      passwordSchema: new PV(),
      error: {
        password: false,
        newpassword: false,
        newpasswordConfirm: false,
      },
      isSubmit: false,
      passwordType: "password",
      newpasswordType: "password",
      newpasswordConfirmType: "password",
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
    newpassword: function () {
      this.checkForm();
    },
    newpasswordConfirm: function () {
      this.checkForm();
    },
  },
  methods: {
    goupdateInfo: function () {
      this.$router.push({ name: "ChangeInfo" });
    },
    checkForm: function () {
      if (this.newpassword == this.password)
        this.error.password = "현재 비밀번호와 새로운 비밀번호가 일치합니다.";
      else this.error.password = false;

      if (this.newpassword.length >= 0 &&
        !this.passwordSchema.validate(this.newpassword))
        this.error.newpassword = "영문,숫자 포함 8~12자리를 입력해주세요.";
      else this.error.newpassword = false;

      if (this.newpassword != this.newpasswordConfirm)
        this.error.newpasswordConfirm = "비밀번호가 다릅니다";
      else this.error.newpasswordConfirm = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    updatePwd: function () {
      if (this.isSubmit) {
        let data = {
          password: this.password,
          newpassword: this.newpassword,
        };
        this.isSubmit = false;
        MyPage.updateMyPwd(
          data,
          (res) => {
            console.log(res);
            if (res.data.status == false) {
              alert('비밀번호를 다시 확인해주세요.')
            } else {
              this.isSubmit = true;
              alert("비밀번호가 변경되었습니다.");
              this.$router.push({ name: "MyPage" });
            }
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
.update-pwd-form {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.update-pwd-name {
  width: 120px;
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
.update-pwd-box {
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
  margin: 0 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
.newpwd {
  width: 230px;
  height: 30px;
  margin: 10px 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
.newpwd-check {
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
</style>
