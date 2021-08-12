import Tech from "@/api/Tech";

export default {
  namespaced: true,
  state: {
    // data 가 들어가는 곳
    techList: [],
    blogList: [],
    pageno: 0,
    keyword: "",
  },
  mutations: {
    // 여기서 data 를 업데이트
    SET_ALL_TECH_LIST(state, list) {
      state.techList = list;
    },
    SCRAP_TOGGLE_BLOG(state, articleid) {
      const index = state.techList.findIndex((tech) => tech.articleid === articleid);
      state.techList[index].scrap = !state.techList[index].scrap;
    },
  },
  actions: {
    // 메소드가 들어가는 곳
    setAllTechList({ commit }, data) {
      Tech.loadAllTechList(
        data,
        (res) => {
          const tech = res.data.list.content;
          commit("SET_ALL_TECH_LIST", tech);
        },
        (err) => {
          alert(err);
        }
      );
    },
    scrapTech({ commit }, id) {
      Tech.scrapTech(
        id,
        (res) => {
          if (res.data.status) commit("SCRAP_TOGGLE_BLOG", id);
          else alert("scrap fail");
        },
        (err) => {
          alert(err);
        }
      );
    },
  },
  getters: {
    // computer 같은 개념
  },
};
