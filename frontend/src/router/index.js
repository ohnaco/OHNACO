import Vue from "vue";
import VueRouter from "vue-router";
import Join from "../views/user/Join.vue";
import JoinEmail from "../views/user/JoinEmail.vue";
import JoinProfile from "../views/user/JoinProfile.vue";
import JoinSuccess from "../views/user/JoinSuccess.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/join/",
    name: "Join",
    component: Join,
  },
  {
    path: "/joinemail/",
    name: "JoinEmail",
    component: JoinEmail,
  },
  {
    path: "/joinprofile/",
    name: "JoinProfile",
    component: JoinProfile,
  },
  {
    path: "/joinsuccess/",
    name: "JoinSuccess",
    component: JoinSuccess,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
