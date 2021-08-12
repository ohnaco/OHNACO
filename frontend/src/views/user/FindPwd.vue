<template>
  <div>
    <div class="findpwd-form">
      <img src="@/assets/images/full-logo.svg" alt="ohnaco-logo" />
      <div class="findpwd-name">비밀번호 찾기</div>
      <div class="findpwd-box">
        <div class="findpwd-text">
          사용하던 계정의 이메일 주소를 입력해주세요.
        </div>
        <!-- 이메일 -->
        <input
          type="text"
          class="email"
          placeholder="이메일"
          v-model="email"
        />
        <!-- 페이지 전환 버튼 -->
        <div class="page-btn">
          <!-- 이전 : 로그인 페이지 -->
          <button 
            class="mr-15" 
            @click="goLogin"
          >
            <img src="@/assets/images/back-btn.svg" alt="back" />
          </button>
          <!-- 다음 : 메일 전송 확인 페이지 -->
          <button 
            class="ms-15" 
            @click="goFindPwd"
          >
            <img src="@/assets/images/next-btn.svg" alt="next" />
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "FindPwd",
  data: function () {
    return {
      email: '',
    };
  },
  methods: {
    goLogin: function () {
      this.$router.push({ name: "Login" });
    },
    goFindPwd: function () {
      this.$router.push({ name : "FindPwdEmail", params: { email: this.email }})
    }
  },
};
</script>

<style scoped>
.findpwd-form {
  display: grid;
  place-items: center;
}
.findpwd-name {
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
.findpwd-box {
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
.findpwd-text {
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
.email {
  width: 230px;
  height: 30px;
  margin: 20px 0px 18px 0px;
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
</style>
