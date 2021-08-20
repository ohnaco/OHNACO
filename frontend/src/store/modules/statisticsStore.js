import Statistics from "@/api/Statistics";

export default {
  namespaced: true,
  state: {
    statsInfo: [],
  },
  mutations: {
    GET_STATS: function (state, statsInfo) {
      state.statsInfo = statsInfo;
    },
  },
  actions: {
    getStats: function ({ commit }, data) {
      Statistics.requestStatistics(
        data,
        (res) => {
          commit("GET_STATS", res.data);
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
  getters: {},
};
