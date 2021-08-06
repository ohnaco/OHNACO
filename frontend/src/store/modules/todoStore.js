import Todo from "@/api/Todo";

export default {
  namespaced: true,
  state: {
    // data 가 들어가는 곳
    todoLists: [],
    commitHistory: {},
  },
  mutations: {
    // 여기서 data 를 업데이트
    SET_TODO(state, value) {
      state.todoLists = value;
    },
    CREATE_TODO(state, newTodo) {
      state.todoLists.push(newTodo);
    },
    UPDATE_TODO(state, editTodo) {
      const index = state.todoLists.findIndex(
        (todo) => todo.todoid === editTodo.todoid
      );

      if (index !== -1) {
        state.todoLists.splice(index, 1, editTodo);
      }
    },
    DELETE_TODO(state, todoid) {
      state.todoLists = state.todoLists.filter(
        (todo) => todo.todoid !== todoid
      );
    },
    SET_COMMIT_HISTORY(state, commitHistory) {
      state.commitHistory = commitHistory;
    } 
  },
  actions: {
    // 메소드가 들어가는 곳
    setTodoList({ commit }, data ) {
      Todo.loadTodoList(data,
        (res) => {
          commit("SET_TODO", res.data);
        },
        (err) => {
          alert(err);
        }
      );
    },
    createTodo({ commit }, payload) {
      Todo.createTodo(
        payload,
        (res) => {
          alert(
            res.data.status === "success" ? "create success" : "create fail"
          );
          commit("CREATE_TODO", res.data.todo);
        },
        (err) => {
          alert(err);
        }
      );
    },
    updateTodo({ commit }, payload) {
      Todo.updateTodo(
        payload,
        (res) => {
          alert(
            res.data.status === "success" ? "update success" : "update fail"
          );
          commit("UPDATE_TODO", payload);
        },
        (err) => {
          alert(err);
        }
      );
    },
    deleteTodo({ commit }, payload) {
      Todo.deleteTodo(
        payload,
        (res) => {
          alert(
            res.data.status === "success" ? "delete success" : "delete fail"
          );
          commit("DELETE_TODO", payload);
        },
        (err) => {
          alert(err);
        }
      );
    },
    refreshCommit({ commit }) {
      Todo.getCommitHistory(
        (res) => {
          commit("SET_COMMIT_HISTORY", res.data.commit);
        },
        (err) => {
          alert(err);
        }
      );
    }
  },
  getters: {
    // computer 같은 개념
    getDay1Commit(state) {
      return state.commitHistory["day1"];
    }
  },
};
