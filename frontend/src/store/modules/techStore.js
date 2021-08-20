import Tech from "@/api/Tech";

export default {
  namespaced: true,
  state: {
    // data 가 들어가는 곳
    techList: [],
    blogList: [],
  },
  mutations: {
    // 여기서 data 를 업데이트
    SET_ALL_TECH_LIST(state, list) {
      state.techList = list;
    },
    SET_SUBSCRIBE_TECH_LIST(state, list) {
      state.techList = list;
    },
    SCRAP_TOGGLE_BLOG(state, articleid) {
      const index = state.techList.findIndex(
        (tech) => tech.articleid === articleid
      );
      state.techList[index].scrap = !state.techList[index].scrap;
    },
    SET_ALL_BLOG_LIST(state, list) {
      state.blogList = list;
    },
    SET_SUBSCRIBE_BLOG_LIST(state, list) {
      state.blogList = list;
    },
    SUBSCRIBE_BLOG(state, blogid) {
      const index = state.blogList.findIndex((blog) => blog.blogid === blogid);
      state.blogList[index].issubscribe = true;
    },
    UNSUBSCRIBE_BLOG(state, blogid) {
      const index = state.blogList.findIndex((blog) => blog.blogid === blogid);
      state.blogList[index].issubscribe = false;
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
    setSubscribeTechList({ commit }, data) {
      Tech.loadSubscribeTechList(
        data,
        (res) => {
          const subscribe = res.data.list.content;
          commit("SET_SUBSCRIBE_TECH_LIST", subscribe);
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
          else alert("스크랩에 실패했습니다!");
        },
        (err) => {
          alert(err);
        }
      );
    },
    setAllBlogList({ commit }) {
      Tech.loadAllBlogList(
        (res) => {
          const blog = res.data.list;
          commit("SET_ALL_BLOG_LIST", blog);
        },
        (err) => {
          alert(err);
        }
      );
    },
    setSubscribeBlogList({ commit }) {
      Tech.loadSubscribeBlogList(
        (res) => {
          const blog = res.data.list;
          commit("SET_SUBSCRIBE_BLOG_LIST", blog);
        },
        (err) => {
          alert(err);
        }
      );
    },
    subscribeBlog({ commit }, id) {
      const data = {
        blogid: id,
      };
      Tech.subscribeBlog(
        data,
        (res) => {
          if (res.data.status) commit("SUBSCRIBE_BLOG", id);
          else alert("구독 설정에 실패했습니다!");
        },
        (err) => {
          alert(err);
        }
      );
    },
    unSubscribeBlog({ commit }, id) {
      const data = {
        blogid: id,
      };
      Tech.unSubscribeBlog(
        data,
        (res) => {
          if (res.data.status) commit("UNSUBSCRIBE_BLOG", id);
          else alert("구독 해제에 실패했습니다!");
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
