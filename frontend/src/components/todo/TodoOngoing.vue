<template>
  <div class="Rectangle">
    <div style="width: 100%; height: 40%">
      <div class="Category">
        {{ category }}
        <div class="Category_Line"></div>
      </div>
      <div class="title">{{ title }}</div>
      <div class="issue">{{ issue }}</div>
    </div>
    <div style="width: 100%; height: 60%">
      <div class="time">
        {{formattedElapsedTime}}</div>
      <div class="pause">
        <img
          src="@/assets/pause.png"
          alt=""
          style="width: 100%; height: 100%"
          v-on:click="stop"
          v-if="ispaused"
        />
        <img
          src="@/assets/start.png"
          alt=""
          style="width: 100%; height: 100%"
          v-on:click="start"
          v-if="!ispaused"
        />
      </div>
      <div class="stop">
        <img src="@/assets/stop.png" alt="" style="width: 100%; height: 100%" 
         v-on:click="reset"/>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  name: "App",
  data() {
    return {
      category: "CS",
      title: "운영체제공부",
      issue : "sass201020",
      time: 0,
      timer: undefined,
      ispaused: false,
    };
  },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.time / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    }
  },
  mounted() {
    this.start()
  },
  methods: {
    start() {
      this.ispaused=!this.ispaused;
      this.timer = setInterval(() => {
        this.time += 1000;
      }, 1000);
    },
    stop() {
      this.ispaused=!this.ispaused;
      clearInterval(this.timer);
    },
    reset() {
      clearInterval(this.timer);
      this.time = 0;
      //리셋하면 카테고리, 완료시간, 제목 디비로 보내기(완료했다고 업데이트)
      //그리고 컴포넌트 변경(원래카드로, 시작대신 완료체크된거뜨게)
    }
  }
};
</script>

<style>
@font-face {
  font-family: "GmarketSansMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff")
    format("woff");
  font-weight: 500;
  font-style: normal;
}

@font-face {
  font-family: "GmarketSansBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff")
    format("woff");
  font-weight: bold;
  font-style: normal;
}

@font-face {
  font-family: "GmarketSansLight";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff")
    format("woff");
  font-weight: 300;
  font-style: normal;
}

.Rectangle {
  width: 338px;
  height: 69px;
  flex-grow: 0;
  border-radius: 15.5px;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.25);
  background-color: #80deea;
}
.Category {
  width: 20%;
  height: 100%;
  font-family: GmarketSansMedium;
  font-size: 7.5px;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: center;
  color: #ffffff;
  position: relative;
  top: 5px;
  float: left;
}

.Category_Line {
  width: 70%;
  height: 0px;
  margin: 0 auto;
  border: solid 0.5px #fda7ff;
}
.title {
  width: 55%;
  height: 100%;
  flex-grow: 0;
  font-family: GmarketSansMedium;
  font-size: 9px;
  font-weight: 500;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: left;
  color: #ffffff;
  float: left;
  position: relative;
  top: 7px;
}

.issue {
  width: 25%;
  height: 100%;
  flex-grow: 0;
  font-family: GmarketSansLight;
  font-size: 6px;
  font-weight: lighter;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: center;
  color: #848484;
  float: left;
  position: relative;
  top: 7px;
}

.time {
  width: 70%;
  height: 39px;
  text-shadow: 0 2px 2px rgba(0, 0, 0, 0.25);
  font-family: GmarketSansBold;
  font-size: 32px;
  font-weight: bold;
  font-stretch: normal;
  font-style: normal;
  line-height: normal;
  letter-spacing: normal;
  text-align: center;
  color: #ffffff;
  float: left;
}

.pause {
  width: 10%;
  flex-grow: 0;
  margin: 0 4px 2px 0;
  padding: 5.2px 0px 5.2px 3px;
  float: left;
  object-fit: cover;
}

.stop {
  width: 10%;
  margin: 0 0 2px 4px;
  padding: 5.2px 0px 5.2px 3px;
  float: left;
  object-fit: cover;
}
</style>
