<template>
  <div class="container">
    <div class="join-form">
      <img src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <div class="join-name">회원가입</div>
      <div class="join-box">
        <div class="check-email-text">
          <div>'{{ this.$route.params.email }}'</div>
          <div>인증번호를 입력해주세요.</div>
          <div>최대 5분 소요됩니다.</div>
        </div>
        <!-- 인증번호 -->
        <b-form-input
          type="text"
          :state="null"
          class="certify-num"
          placeholder="인증코드"
          v-model="token"
          @keyup.enter="goJoinProfile"
        ></b-form-input>
        <!-- 페이지 전환 버튼 -->
        <div class="page-btn">
          <!-- 이전 : 회원가입 메인 페이지 -->
          <button class="mr-15" @click="goJoin">
            <img src="@/assets/images/back-btn.svg" alt="back" />
          </button>
          <!-- 다음 : 회원가입 프로필 페이지 -->
          <button class="ms-15" @click="goJoinProfile">
            <img src="@/assets/images/next-btn.svg" alt="next" />
          </button>
        </div>
        <div class="join-footer-text">
          이메일을 받지 못하셨나요?
          <button class="send-email-again" @click="sendAgain">재전송</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import User from "@/api/User";

export default {
  name: "JoinEmail",
  data: function () {
    return {
      email: this.$route.params.email,
      token: "",
    };
  },
  methods: {
    goJoin: function () {
      this.$router.push({ name: "Join" });
    },
    goJoinProfile: function () {
      let { email, token } = this;
      let data = {
        email,
        token,
      };
      User.requestSignupEmail(
        data,
        (res) => {
          if (res.data.status) {
            this.$router.push({
              name: "JoinProfile",
              params: { email: this.$route.params.email },
            });
            alert("인증되었습니다.");
          } else {
            alert("인증코드가 일치하지 않습니다.");
          }
        },
        (err) => {
          console.log(err);
        }
      );
    },
    sendAgain: function () {
      let { email } = this;
      let data = {
        email,
      };
      User.requestSignupEmailAgain(
        data,
        (res) => {
          console.log(res);
          alert("메일이 재전송 되었습니다. 메일함을 확인해주세요");
        },
        (err) => {
          console.log(err);
        }
      );
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
.check-email-text {
  position: relative;
  display: flex;
  align-items: center;
  flex-direction: column;
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-weight: 300;
  font-size: 11px;
  color: #000000;
}
.certify-num {
  width: 200px;
  height: 30px;
  margin: 30px 0px 18px 0px;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
input::placeholder {
  font-family: GmarketSansTTF;
  font-size: 15px;
  font-weight: 300;
  color: #c6c8c9;
}
.page-btn {
  margin: 5px;
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
.send-email-again {
  color: #607d8b;
  cursor: pointer;
}
</style>
