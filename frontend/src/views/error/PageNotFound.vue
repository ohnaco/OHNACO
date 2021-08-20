<template>
  <div class="container mt-10">
    <div class="row">
      <div class="col-sm-12 text-center">
        <div class="text-head">
          <div class="d-flex justify-content-center" style="font-size: 80px">
            4
            <div
              class="animate__animated animate__bounce"
              style="color: #80deea"
            >
              O
            </div>
            4
          </div>
          <img src="@/assets/images/full-logo.svg" alt="" />
        </div>

        <div class="text-box">
          <div style="font-size: 20px">페이지를 찾을 수 없습니다.</div>
          <div style="font-size: 13px">
            <div class="mt-5 mb-0">
              존재하지 않거나, 사용할 수 없는 페이지입니다.
            </div>
            <div class="mb-5">
              입력하신 주소가 정확한지 다시 한 번 확인해주세요.
            </div>
          </div>
          <button class="btn">
            <!-- Home -->
            <router-link to="/" style="color: #fff">Home</router-link>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "animate.css";

export default {
  name: "PageNotFound",
};
</script>

<style scoped>
@font-face {
  font-family: "GmarketSansMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "GmarketSansBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.animate__animated.animate__bounce {
  --animate-duration: 2s;
}
a {
  text-decoration: none;
}
button {
  background-color: #607d8b;
  border-color: #607d8b;
}
.text-head {
  font-family: "GmarketSansBold";
  color: #607d8b;
}
.text-box {
  font-family: "GmarketSansMedium";
  color: #607d8b;
}
</style>
