import Dev from "@/api/DevTalk";

export default {
  namespaced: true,
  state: {
    // data 가 들어가는 곳
    questionList: [],
    question:{},
  },
  mutations: {
    // 여기서 data 를 업데이트
    SET_QUESTION(state, value) {
      state.questionList = value;
    },
    SET_QUESTION_DETAIL(state, value) {
      state.question = value;
    },
    CREATE_QUESTION(state, newQuestion) {
      state.questionList.push(newQuestion);
    },
    UPDATE_QUESTION(state, editTodo) {
      const index = state.todoLists.findIndex(
        (todo) => todo.todoid === editTodo.todoid
      );

      if (index !== -1) {
        state.questionLists.splice(index, 1, editTodo);
      }
    },
    DELETE_QUESTION(state, questionid) {
      state.questionList.question = state.questionList.question.filter(
        (question) => question.questionid !== questionid
      );
    },
  },
  actions: {
    // 메소드가 들어가는 곳
    setQuestion({ commit }) {
      Dev.setQuestion(
        (res) => {
          commit("SET_QUESTION", res.data);
        },
        (err) => {
          alert(err);
        }
      );
    },
    addQuestion({ commit }, data) {
      Dev.addQuestion(data,
        (res) => {
          console.log(commit);
          console.log(res);
          alert("정상적으로 등록되었습니다!");
        },
        (err) => {
          alert(err);
        }
      );
    },

    detailQuestion({ commit }, data) {
      console.log(data);
      Dev.detailQuestion(data,
        (res) => {
          commit("SET_QUESTION_DETAIL", res.data);
        },
        (err) => {
          alert(err);
        }
      );
    },

    updateQuestion({ commit }, data) {
      console.log(data);
      Dev.updateQuestion(data,
        (res) => {
          console.log(commit);
          console.log(res);
        },
        (err) => {
          alert(err);
        }
      );
    },

    deleteQuestion({ commit }, data) {
      console.log(data);
      Dev.deleteQuestion(data,
        (res) => {
          console.log(commit);
          console.log(res);
        },
        (err) => {
          alert(err);
        }
      );
    },

    addComment({ commit }, data) {
      console.log(data);
      Dev.addComment(data,
        (res) => {
          console.log(commit);
          console.log(res);
        },
        (err) => {
          alert(err);
        }
      );
    },
  },
  //   updateTodo({ commit }, payload) {
  //     Todo.updateTodo(
  //       payload,
  //       (res) => {
  //         alert(
  //           res.data.status === "success" ? "update success" : "update fail"
  //         );
  //         commit("UPDATE_TODO", payload);
  //       },
  //       (err) => {
  //         alert(err);
  //       }
  //     );
  //   },
  //   deleteTodo({ commit }, payload) {
  //     Todo.deleteTodo(
  //       payload,
  //       (res) => {
  //         alert(
  //           res.data.status === "success" ? "delete success" : "delete fail"
  //         );
  //         commit("DELETE_TODO", payload);
  //       },
  //       (err) => {
  //         alert(err);
  //       }
  //     );
  //   },
  //   refreshCommit({ commit }) {
  //     Todo.getCommitHistory(
  //       (res) => {
  //         commit("SET_COMMIT_HISTORY", res.data.commit);
  //       },
  //       (err) => {
  //         alert(err);
  //       }
  //     );
  //   }
  // },
  getters: {
  },
};