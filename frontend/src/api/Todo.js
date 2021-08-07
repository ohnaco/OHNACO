import http from "@/util/http-common.js";

const loadTodoList = (data, callback, errorCallback) => {

  http
    .get("/todo/item", {
      params: {
        date: data,
      },
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const createTodo = (data, callback, errorCallback) => {
  http
    .post("/todo/item", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const updateTodo = (data, callback, errorCallback) => {
  http
    .put("/todo/item", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const deleteTodo = (data, callback, errorCallback) => {
  http
    .delete("/todo/item", {
      params: { todoid: data },
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const loadByMonth = (data, callback, errorCallback) => {
  http
    .get("/todo/month", {
      params: {
        date: data,
      },
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const getCommitHistory = (callback, errorCallback) => {
  http
  .get("/todo/commit")
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

export default {
  loadTodoList,
  createTodo,
  updateTodo,
  deleteTodo,
  loadByMonth,
  getCommitHistory
};
