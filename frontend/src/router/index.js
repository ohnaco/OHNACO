import Vue from "vue";
import VueRouter from "vue-router";

import Main from "@/views/Main.vue";

import Login from "@/views/user/Login.vue";
import Join from "@/views/user/Join.vue";
import JoinEmail from "@/views/user/JoinEmail.vue";
import JoinProfile from "@/views/user/JoinProfile.vue";
import JoinSuccess from "@/views/user/JoinSuccess.vue";

import FindPwd from "@/views/user/FindPwd.vue";
import FindPwdEmail from "@/views/user/FindPwdEmail.vue";
import FindPwdNewPwd from "@/views/user/FindPwdNewPwd.vue";
import FindPwdSuccess from "@/views/user/FindPwdSuccess.vue";

import Todo from "@/views/todo/TodoHome.vue";
import Statistics from "@/views/statistics/Statistics.vue";
import Tech from "@/views/tech/TechNews.vue";
import Subscribe from "@/views/tech/TechSubscribe.vue";

import DevTalk from "@/views/devtalk/QuestionList.vue";
import QuestionDetail from "@/views/devtalk/QuestionDetail.vue";
import AddQuestion from "@/views/devtalk/QuestionAdd.vue";
import QuestionUpdate from "@/views/devtalk/QuestionAddUpdate.vue";

import MyPage from "@/views/mypage/MyPage.vue";
import MyAnswers from "@/views/mypage/MyAnswers.vue";
import MyQuestions from "@/views/mypage/MyQuestions.vue";
import MyTechScraps from "@/views/mypage/MyTechScraps.vue";
import ChangeInfo from "@/views/mypage/ChangeInfo.vue";
import ChangePwd from "@/views/mypage/ChangePwd.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
    beforeEnter: (to, from, next) => {
      const token = localStorage.getItem("jwt-access-token");
      // 로그인 정보가 있으면 Todo로 이동.
      if (token !== null) next({ name: "Todo" });
      else next();
    },
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
    beforeEnter: (to, from, next) => {
      const token = localStorage.getItem("jwt-access-token");
      // 로그인 정보가 있으면 Todo로 이동.
      if (token !== null) next({ name: "Todo" });
      else next();
    },
  },
  {
    path: "/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/joinemail",
    name: "JoinEmail",
    component: JoinEmail,
  },
  {
    path: "/joinprofile",
    name: "JoinProfile",
    component: JoinProfile,
  },
  {
    path: "/joinsuccess",
    name: "JoinSuccess",
    component: JoinSuccess,
  },
  {
    path: "/findpwd",
    name: "FindPwd",
    component: FindPwd,
  },
  {
    path: "/findpwdemail",
    name: "FindPwdEmail",
    component: FindPwdEmail,
  },
  {
    path: "/findpwdnewpwd",
    name: "FindPwdNewPwd",
    component: FindPwdNewPwd,
  },
  {
    path: "/findpwdsuccess",
    name: "FindPwdSuccess",
    component: FindPwdSuccess,
  },
  {
    path: "/todo",
    name: "Todo",
    component: Todo,
  },
  {
    path: "/statistics",
    name: "Statistics",
    component: Statistics,
  },
  {
    path: "/tech",
    name: "Tech",
    component: Tech,
  },
  {
    path: "/tech/subscribe",
    name: "Subscribe",
    component: Subscribe,
  },
  {
    path: "/devtalk",
    name: "DevTalk",
    component: DevTalk,
  },
  {
    path: "/questiondetail",
    name: "QuestionDetail",
    component: QuestionDetail,
    props: true,
  },

  {
    path: "/addquestion",
    name: "AddQuestion",
    component: AddQuestion,
  },
  {
    path: "/questionupdate",
    name: "QuestionUpdate",
    component: QuestionUpdate,
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/myanswers",
    name: "MyAnswers",
    component: MyAnswers,
  },
  {
    path: "/myquestions",
    name: "MyQuestions",
    component: MyQuestions,
  },
  {
    path: "/mytechscraps",
    name: "MyTechScraps",
    component: MyTechScraps,
  },
  {
    path: "/changeinfo",
    name: "ChangeInfo",
    component: ChangeInfo,
  },
  {
    path: "/changepwd",
    name: "ChangePwd",
    component: ChangePwd,
  },
];

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => {
    if (err.name !== "NavigationDuplicated") throw err;
  });
};

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
