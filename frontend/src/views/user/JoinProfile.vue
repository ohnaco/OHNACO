<template>
  <div class="container">
    <!-- 회원가입 프로필 등록 폼 -->
    <div class="join-form">
      <img src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <div class="join-name">회원가입</div>
      <div class="join-box">
        <!-- 프로필 사진 추가 및 미리보기-->
        <div>
          <img src="@/assets/images/profile-img.svg" alt="ohnaco-logo" />
        </div>
        <!-- 닉네임 -->
        <input
          type="text"
          id="nickname"
          class="nickname"
          placeholder="닉네임*"
          @input="nicknameCheck"
          v-model="nickname"
          v-bind:class="{
            error: error.nickname && error.nicknameCheck,
            complete:
              !error.nickname && nickname.length !== 0 && error.nicknameCheck,
          }"
        />
        <div class="error-message" v-if="error.nickname">
          {{ error.nickname }}
        </div>
        <div class="error-message" v-if="error.nicknameCheck">
          {{ error.nicknameCheck }}
        </div>
        <!-- 깃헙 아이디 -->
        <input
          type="text"
          id="github"
          class="github"
          placeholder="Github ID"
          v-model="githubid"
        />
        <!-- 희망 직무 -->
        <select v-model="position" class="form-select">
          <option value="" disabled>희망직무</option>
          <option value="Backend">Backend</option>
          <option value="Frontend">Frontend</option>
          <option value="Android">Android</option>
          <option value="IOS">IOS</option>
          <option value="SI">SI</option>
          <option value="Embedded">Embedded</option>
          <option value="Game">Game</option>
          <option value="etc.">etc.</option>
        </select>
        <!-- 버튼 -->
        <div class="page-btn">
          <!-- 이전 페이지: 회원가입 페이지 -->
          <button 
            class="mr-15" 
            @click="goJoin"
          >
            <img src="@/assets/images/cancel-btn.svg" alt="back" />
          </button>
          <!-- 다음 페이지 : 회원가입 완료 페이지 -->
          <button
            class="ml-15"
            @click="joinFinish"
          >
            <img src="@/assets/images/complete-btn.svg" alt="next" />
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import User from "@/api/User";

export default {
  name: "Join",
  data: function () {
    return {
      email: this.$route.params.email,
      nickname: "",
      githubid: "",
      position: "",
      error: {
        nickname: false,
        nicknameCheck: false,
      },
      isSubmit: false,
      isCheck: false,
    };
  },
  created() {
    this.component = this;
  },
  watch: {
    nickname: function () {
      this.checkForm();
    },
  },
  methods: {
    goJoin: function () {
      this.$router.push({ name: "Join" });
    },
    checkForm: function () {
      if (this.nickname.length <= 1)
        this.error.nickname = "두글자 이상 닉네임을 입력해주세요";
      else this.error.nickname = false;

      let isSubmit = true;
      Object.values(this.error).map((v) => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    nicknameCheck: function () {
      let { nickname } = this;
      let data = {
        nickname,
      };
      if (this.nickname) {
        User.requestSignupNicknameCheck(data, (res) => {
          console.log(res);
          if (res.data.status) {
            this.error.nicknameCheck = null;
            this.isCheck = res.data.status;
          } else this.error.nicknameCheck = res.data.message;
        }),
          (err) => {
            console.log(err);
          };
      }
    },
    joinFinish: function () {
      if (this.isSubmit && this.isCheck) {
        let { email, nickname, githubid, position } = this;
        let data = {
          email,
          nickname,
          githubid,
          position,
        };
        this.isSubmit = false;
        User.requestSignupProfile(
          data,
          (res) => {
            console.log(res);
            this.isSubmit = true;
            alert("회원가입이 완료 되었습니다. 로그인 해주세요.");
            this.$router.push({ name: "JoinSuccess" });
          },
          (err) => {
            console.log(err);
            this.isSubmit = true;
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
.nickname {
  width: 230px;
  height: 30px;
  margin: 30px 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
.error-message {
  font-family: GmarketSansTTF;
  font-size: 10px;
  color: crimson;
}
.github {
  width: 230px;
  height: 30px;
  margin: 10px 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #ffffff;
}
.form-select {
  width: 230px;
  height: 30px;
  margin: 10px 0 18px 0px;
  padding: 0px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  padding: 0px 16.6px 0px 17.2px;
  font-family: GmarketSansTTF;
  font-size: 15px;
  font-weight: 300;
  cursor: pointer;
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
</style>
