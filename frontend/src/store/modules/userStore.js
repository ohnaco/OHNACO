export default {
  namespaced: true,
  state: {
    // data 가 들어가는 곳
    user: {
      image: null,
      githubId: "",
      nickname: "",
      userId: "",
    },
  },
  mutations: {
    // 여기서 data 를 업데이트
    SET_USER(state, value) {
      state.user.image = value.image;
      state.user.githubId = value.githubId;
      state.user.nickname = value.nickname;
      state.user.userId = value.userId;
    },
    DELETE_USER(state) {
      state.user = {
        image: null,
        githubId: "",
        nickname: "",
        userId: "",
      };
    },
  },
  actions: {
    // 메소드가 들어가는 곳
    setUser({ commit }, payload) {
      commit("SET_USER", payload.data);
    },
  },
  getters: {
    // computer 같은 개념
    GET_USER: (state) => state.user,
  },
};
