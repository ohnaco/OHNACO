import User from "@/api/User";
import router from "@/router";

export default {
  namespaced: true,
  state: {
    // data 가 들어가는 곳
    user: {
      image: "",
      githubid: "",
      nickname: "",
      userid: "",
    },
  },
  mutations: {
    // 여기서 data 를 업데이트
    SET_USER(state, value) {
      state.user.image = value.image;
      state.user.githubid = value.githubid;
      state.user.nickname = value.nickname;
      state.user.userid = value.userid;
    },
    DELETE_USER(state) {
      state.user = {
        image: "",
        githubid: "",
        nickname: "",
        userid: "",
      };
    },
  },
  actions: {
    // 메소드가 들어가는 곳
    login({ dispatch }, data) {
      User.requestLogin(
        data,
        () => {
          dispatch("getUserInfo");
          router.push({ name: "Todo" });
        },
        (err) => {
          console.log(err);
          alert("이메일과 비밀번호를 다시 확인해주세요.");
        }
      );
    },
    getUserInfo({ commit }) {
      User.requestUserInfo(
        (res) => {
          commit("SET_USER", res.data.user);
        },
        (err) => {
          console.log(err);
          alert("사용자 정보를 불러오는 데에 실패했습니다.");
        }
      );
    },
    logout({ commit }) {
      localStorage.removeItem("jwt-access-token");
      commit("DELETE_USER");
      router.push({ name: "Login" });
    },
  },
  getters: {
    // computer 같은 개념
    GET_USER: (state) => state.user,
  },
};
