import Todo from "@/api/Todo";

export default {
  namespaced: true,
  state: {
    // data 가 들어가는 곳
    todoLists: [],
    todoListsByMonth: [],
    todayCommit: null,
    exitTime: "",
    goingTime: "",
    ongoingId: "",
    ongoingIdFQ: "",
    goingTimeFG: "",
    exitTimeFQ: "",
  },
  mutations: {
    // 여기서 data 를 업데이트

    SET_TIME(state, value) {
      state.exitTime = value;
    }, //페이지 벗어난 시간
    SET_TIMEFQ(state, value) {
      state.exitTimeFQ = value;
    }, //시작했을때 시간
    SET_GOINGTIME(state, value) {
      state.goingTime = value;
    }, //벗어낫을때 진행시간
    SET_GOINGTIMEFQ(state, value) {
      state.goingTimeFQ = value;
    }, //시작했을때의 경과시간

    SET_ID(state, value) {
      state.ongoingId = value;
    }, //진행중이던 todoid
    SET_IDFQ(state, value) {
      state.ongoingIdFQ = value;
    }, //시작한 todoid

    SET_TODO(state, value) {
      state.todoLists = value;
    },
    SET_TODO_MONTH(state, value) {
      state.todoListsByMonth = value;
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
    SET_TODAY_COMMIT(state, flag) {
      state.todayCommit = flag;
    },
  },
  actions: {
    // 메소드가 들어가는 곳
    setTodoList({ commit }, data) {
      Todo.loadTodoList(
        data,
        (res) => {
          commit("SET_TODO", res.data.list);
        },
        (err) => {
          alert(err);
        }
      );
    },
    loadByMonth({ commit }, data) {
      Todo.loadByMonth(
        data,
        (res) => {
          commit("SET_TODO_MONTH", res.data.list);
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
          if (res.data.status === "fail") alert("To Do 추가에 실패했습니다.");
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
          if (res.data.status === "fail") alert("To Do 수정에 실패했습니다!");
          commit("UPDATE_TODO", res.data.todo);
        },
        (err) => {
          alert(err);
        }
      );
    },
    // eslint-disable-next-line no-unused-vars
    updateTime({ commit }, payload) {
      Todo.updateTime(
        payload,
        () => {},
        (err) => {
          alert(err);
        }
      );
    },
    deleteTodo({ commit }, payload) {
      Todo.deleteTodo(
        payload,
        (res) => {
          if (res.data.status === "fail") alert("To Do 삭제에 실패했습니다!");
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
          const day1 = res.data.commit.day1;
          commit("SET_TODAY_COMMIT", day1 > 0 ? true : false);
        },
        (err) => {
          console.log(err);
        }
      );
    },
    // eslint-disable-next-line no-unused-vars
    stateChange({ commit }, payload) {
      Todo.stateChange(
        payload,
        () => {},
        (err) => {
          alert(err);
        }
      );
    },
    // eslint-disable-next-line no-unused-vars
    forceQuit({ commit }, payload) {
      Todo.forceQuit(
        payload,
        () => {},
        (err) => {
          alert(err);
        }
      );
    },
    // eslint-disable-next-line no-unused-vars
    moveTodayAdd({ commit }, payload) {
      Todo.createTodo(
        payload,
        () => {
          alert("이전이 완료되었습니다.");
        },
        (err) => {
          alert(err);
        }
      );
    },
    setTime({ commit }, payload) {
      commit("SET_TIME", payload);
    },
    setGoingTime({ commit }, payload) {
      commit("SET_GOINGTIME", payload);
    },
    setId({ commit }, payload) {
      commit("SET_ID", payload);
    },

    setTimeFQ({ commit }, payload) {
      commit("SET_TIMEFQ", payload);
    },
    setGoingTimeFQ({ commit }, payload) {
      commit("SET_GOINGTIMEFQ", payload);
    },
    setIdFQ({ commit }, payload) {
      commit("SET_IDFQ", payload);
    },
  },
  getters: {},
};
