import Dev from "@/api/DevTalk";

export default {
  namespaced: true,
  state: {
    // data 가 들어가는 곳
    questionList: [],
    question: {},
    answerList: [],
  },
  mutations: {
    // 여기서 data 를 업데이트
    SET_QUESTION(state, value) {
      state.questionList = value;
    },
    SET_ANSWER(state, value) {
      state.answerList = value;
    },
    ADD_ANSWER(state, value) {
      state.answerList.push(value);
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
    DELETE_ANSWER(state, id) {
      console.log("ejhlsmsep");
      state.answerList = state.answerList.filter(
        (answer) => answer.answerid !== id
      );
    },

    SEARCH(state, text) {
      state.questionList.question = state.questionList.question.filter(
        (question) =>
          question.questiontitle.includes(text) ||
          question.questioncontent.includes(text)
      );
    },

    UPDATE_ANSWER(state, edit) {
      const index = state.answerList.findIndex(
        (answer) => answer.answerid === edit.answerid
      );

      if (index !== -1) {
        state.answerList.splice(index, 1, edit);
      }
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
    setAnswer({ commit }, data) {
      console.log(data);
      //commit("SET_ANSWER", data);
      console.log(commit);
    },
    addQuestion({ commit }, data) {
      Dev.addQuestion(
        data,
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
      Dev.detailQuestion(
        data,
        (res) => {
          console.log(res);
          commit("SET_QUESTION_DETAIL", res.data);
          commit("SET_ANSWER", res.data.answer);
        },
        (err) => {
          alert(err);
        }
      );
    },

    updateQuestion({ commit }, data) {
      Dev.updateQuestion(
        data,
        (res) => {
          console.log(commit);
          console.log(res);
        },
        (err) => {
          alert(err);
        }
      );
    },

    updateComment({ commit }, data) {
      Dev.updateComment(
        data,
        (res) => {
          commit("UPDATE_ANSWER", res.data.answer);
        },
        (err) => {
          alert(err);
        }
      );
    },

    deleteQuestion({ commit }, data) {
      console.log(data);
      Dev.deleteQuestion(
        data,
        (res) => {
          alert("삭제가 완료되었습니다.");
          console.log(commit);
          console.log(res);
        },
        (err) => {
          alert(err);
        }
      );
    },
    addComment({ commit }, data) {
      Dev.addComment(
        data,
        (res) => {
          commit("ADD_ANSWER", res.data.answer);
        },
        (err) => {
          alert(err);
        }
      );
    },

    deleteComment({ commit }, data) {
      console.log(data);
      Dev.deleteComment(
        data,
        (res) => {
          console.log(res);
          alert("삭제가 완료되었습니다.");
          commit("DELETE_ANSWER", data);
        },
        (err) => {
          alert(err);
        }
      );
    },

    search({ commit }, data) {
      console.log(data);
      commit("SEARCH", data);
    },
  },
  getters: {},
};
