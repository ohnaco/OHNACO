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
          type="password"
          id="pwd"
          class="pwd"
          placeholder="새로운 비밀번호"
          v-model="password"
        />
        <!-- 비번 확인 -->
        <input
          type="password"
          id="pwd"
          class="pwd"
          placeholder="새로운 비밀번호 확인"
          v-model="password"
          @keyup.enter="doChangePwd"
        />
        <!-- 로그인 버튼 -->
        <button class="mt-1" @click="doChangePwd">
          <img src="@/assets/images/find-pwd-btn.svg" alt="change-pwd" />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FindPwdNewPwd",
  data: function () {
    return {
      newpassword: '',
      newpasswordComfirm: '',
    };
  },
  methods: {
    doChangePwd: function () {
      this.$router.push({ name: "FindPwdSuccess" });
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
input::placeholder {
  font-family: GmarketSansTTF;
  font-size: 13px;
  font-weight: 300;
  color: #c6c8c9;
}
</style>
