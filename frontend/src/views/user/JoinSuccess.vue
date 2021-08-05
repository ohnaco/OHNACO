<template>
  <div>
    <!-- 로고 -->
    <div class="logo">
      <BigFullLogo />
    </div>
    <div>
      <!-- 체크표시 -->
      <svg
        width="100"
        height="100"
        viewBox="0 0 133 133"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          fill-rule="evenodd"
          clip-rule="evenodd"
          d="M66.5 0C29.7739 0 0 29.7739 0 66.5C0 103.226 29.7739 133 66.5 133C103.226 133 133 103.226 133 66.5C133 29.7739 103.226 0 66.5 0ZM95.3247 55.2555C95.8555 54.6487 96.2596 53.942 96.5132 53.1768C96.7668 52.4117 96.8648 51.6035 96.8015 50.7999C96.7381 49.9962 96.5147 49.2134 96.1443 48.4974C95.774 47.7814 95.2642 47.1467 94.6449 46.6307C94.0256 46.1146 93.3094 45.7276 92.5384 45.4924C91.7673 45.2572 90.957 45.1787 90.1552 45.2613C89.3533 45.3439 88.576 45.586 87.8691 45.9735C87.1622 46.3609 86.54 46.8858 86.0389 47.5173L60.0434 78.7058L46.5923 65.2486C45.4521 64.1474 43.925 63.538 42.3399 63.5518C40.7548 63.5656 39.2386 64.2014 38.1177 65.3222C36.9968 66.4431 36.361 67.9594 36.3472 69.5445C36.3335 71.1296 36.9428 72.6567 38.044 73.7969L56.1804 91.9332C56.7744 92.5269 57.4856 92.9902 58.2686 93.2938C59.0516 93.5974 59.8893 93.7345 60.7282 93.6964C61.5671 93.6583 62.3889 93.4458 63.1412 93.0725C63.8935 92.6992 64.5597 92.1732 65.0975 91.5282L95.3247 55.2555Z"
          fill="#80DEEA"
        />
      </svg>
      <!-- 문구 -->
      <p class="joinsuccess_text1">오나코 회원가입이 완료되었습니다.</p>
      <p class="joinsuccess_text2">
        오나코의 모든 서비스를 이용하실 수 있습니다.
      </p>
      <!-- 로그인 버튼 -->
      <button class="login_btn" @click="login">로그인</button>
    </div>
  </div>
</template>

<script>
import BigFullLogo from "@/components/common/BigFullLogo.vue";

export default {
  name: "JoinSuccess",
  components: {
    BigFullLogo,
  },
  methods: {
    login: function () {
      this.$router.push({ name: "Login" });
    },
  },
};
</script>

<style scoped>
.login_btn {
  width: 290px;
  height: 40px;
  margin: 30px 1px 10px 0;
  padding: 10.6px 16.6px 9.9px 17.2px;
  border-radius: 5px;
  border: solid 1px #607d8b;
  background-color: #607d8b;
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-weight: bold;
  font-size: 15px;
  color: #ffffff;
  cursor: pointer;
}
.joinsuccess_text1 {
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-weight: bold;
  font-size: 21px;
  color: #607d8b;
  margin-top: 40px;
}
.joinsuccess_text2 {
  font-family: Gmarket Sans TTF;
  font-style: normal;
  font-size: 15px;
  color: #607d8b;
}
</style>
>
