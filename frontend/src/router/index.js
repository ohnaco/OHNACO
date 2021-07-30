import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/user/Login.vue";
import Join from "../views/user/Join.vue";
import JoinEmail from "../views/user/JoinEmail.vue";
import JoinProfile from "../views/user/JoinProfile.vue";
import JoinSuccess from "../views/user/JoinSuccess.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/login/",
    name: "Login",
    component: Login,
  },
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

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => {
		if (err.name !== 'NavigationDuplicated') throw err;
	});
};


const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;