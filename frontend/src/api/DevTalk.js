import http from "@/util/http-common.js";

const setQuestion = (callback, errorCallback) => {
  http
    .get("/devtalk/listall", {})
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const addQuestion = (data, callback, errorCallback) => {
  console.log(data);
  http
    .post("/devtalk/question", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const detailQuestion = (data, callback, errorCallback) => {
  http
    .get("/devtalk/question", {
      params: {
        questionid: data,
      },
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const updateQuestion = (data, callback, errorCallback) => {
  console.log(data);
  console.log(typeof data);
  http
    .put("/devtalk/question", data)
    .then((res) => {
      alert("정상적으로 수정되었습니다.");
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const deleteQuestion = (data, callback, errorCallback) => {
  http
    .delete("/devtalk/question", {
      params: {
        questionid: data,
      },
    })
    .then((res) => {
      console.log("삭제성공!!");
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const addComment = (data, callback, errorCallback) => {
  http
    .post("/devtalk/answer", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const deleteComment = (data, callback, errorCallback) => {
  http
    .delete("/devtalk/answer", {
      params: {
        answerid: data,
      },
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const updateComment = (data, callback, errorCallback) => {
  console.log(data);
  http
    .put("/devtalk/answer", data)
    .then((res) => {
      alert("정상적으로 수정되었습니다.");
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const questionLike = (data, callback, errorCallback) => {
  http
    .post("/devtalk/question/like", {
      questionid: data,
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const answerLike = (data, callback, errorCallback) => {
  console.log(data);
  http
    .post("/devtalk/answer/like", {
      answerid: data,
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

export default {
  setQuestion,
  addQuestion,
  detailQuestion,
  updateQuestion,
  deleteQuestion,
  addComment,
  deleteComment,
  questionLike,
  updateComment,
  answerLike,
};
