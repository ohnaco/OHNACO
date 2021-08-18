import MyPage from "@/api/MyPage";

export default {
  namespaced: true,
  state: {
    mypageInfo: [],
  },
  mutations: {
    GET_MY_PAGE: function (state, mypageInfo) {
      state.mypageInfo = mypageInfo;
    },
  },
  actions: {
    getMyPage: function ({ commit }) {
      MyPage.requestMypageinfo(
        (res) => {
          commit("GET_MY_PAGE", res.data);
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
  getters: {},
};
