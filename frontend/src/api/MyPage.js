import http from "@/util/http-common.js";

const requestMypageinfo = (callback, errorCallback) => {
  http
    .get("/mypage/list")
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestMyQuestions = (data, callback, errorCallback) => {
  http
    .get("/mypage/question?pageno=" + data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestMyAnswers = (data, callback, errorCallback) => {
  http
    .get("/mypage/answer?pageno=" + data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestMyScraps = (data, callback, errorCallback) => {
  http
    .get("/mypage/scrap?pageno=" + data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestMyInfo = (callback, errorCallback) => {
  http
    .get("/mypage/info")
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const updateMyInfo = (data, callback, errorCallback) => {
  http
    .put("/mypage/info?body=", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestMypageNicknameCheck = (data, callback, errorCallback) => {
  http
    .get(
      "/mypage/namecheck?nickname=" + data.nickname
    )
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const updateMyPwd = (data, callback, errorCallback) => {
  http
    .put("/mypage/changepwd?body=", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

export default {
  requestMypageinfo,
  requestMyQuestions,
  requestMyAnswers,
  requestMyScraps,
  requestMyInfo,
  updateMyInfo,
  requestMypageNicknameCheck,
  updateMyPwd,
};