import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import JwPagination from "jw-vue-pagination";

import vueMoment from "vue-moment";
import moment from "moment";
moment.locale("ko");

// Firebase App (the core Firebase SDK) is always required and must be listed first
import firebase from "firebase/app";
// If you are using v7 or any earlier version of the JS SDK, you should import firebase using namespace import
// import * as firebase from "firebase/app"

// If you enabled Analytics in your project, add the Firebase SDK for Analytics
import "firebase/analytics";

// Add the Firebase products that you want to use
import "firebase/messaging";
import http from "@/util/http-common.js";

const firebaseConfig = {
  apiKey: "AIzaSyAjoO1hK9KSuV3oEsXBjxf0BdSoFwJO6HY",
  authDomain: "ohnaco-notification.firebaseapp.com",
  projectId: "ohnaco-notification",
  storageBucket: "ohnaco-notification.appspot.com",
  messagingSenderId: "1097451483774",
  appId: "1:1097451483774:web:03068b7284ab4b03ea5e2f",
  measurementId: "G-8HLXHVJ0H7",
};

if (!firebase.apps.length) {
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
} else {
  firebase.app();
}

// 지원하는 브라우저인지 확인 후 진행
if (firebase.messaging.isSupported()) {
  // Firebase Messaging 객체 획득
  const messaging = firebase.messaging();
  // 알림 수신을 위한 사용자 권한 요청
  Notification.requestPermission().then((permission) => {
    console.log("permission ", permission);
    if (permission !== "granted") {
      alert("알림을 허용해주세요");
    }
  });

  // Get registration token. Initially this makes a network call, once retrieved
  // subsequent calls to getToken will return from cache.
  messaging
    .getToken({
      vapidKey:
        "BPTWqcfcHmhXfXUte49i3_k5m2Z3jv4N4qrurTrTfTVf6BgiIjtcYYkRMB255sPQNnvqLvYLVku4-sLLO--Nw-4",
    })
    .then((currentToken) => {
      if (currentToken) {
        // Send the token to your server and update the UI if necessary
        const fbToken = {
          'token': currentToken
        }
        http
        .post("/noti/register", fbToken)
        .then(() => {
        })
        .catch(() => {
          console.log("noti token fail");
        });
      } else {
        // Show permission request UI
        console.log(
          "No registration token available. Request permission to generate one."
        );
      }
    })
    .catch((err) => {
      console.log("An error occurred while retrieving token. ", err);
    });

  // Handle received push notification at foreground
  messaging.onMessage((payload) => {
    var notificationTitle = payload.notification.title;
    var notificationOptions = {
      body: payload.notification.body,
      icon: "",
    };

    var notification = new Notification(notificationTitle, notificationOptions);
    notification.onclick = function (event) {
      notification.close();
      console.log(event);
    };

    return notification;
  });
}

Vue.config.productionTip = false;
Vue.use(vueMoment, { moment });
Vue.use(BootstrapVue);
Vue.component("jw-pagination", JwPagination);

new Vue({
  router,
  store,
  vuetify,
  firebase,
  beforeCreate() {
    if (localStorage.getItem("jwt-access-token") !== null) {
      this.$store.dispatch("userStore/getUserInfo");
    }
  },
  render: (h) => h(App),
}).$mount("#app");
