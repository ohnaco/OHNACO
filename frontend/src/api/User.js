import http from "@/util/http-common.js";

const requestLogin = (data, callback, errorCallback) => {
  http
    .post("/user/signIn", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestUserInfo = (callback, errorCallback) => {
  http
    .get("/user/info")
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestSignup = (data, callback, errorCallback) => {
  http
    .post("/user/join", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestSignupEmail = (data, callback, errorCallback) => {
  http
    .post("/user/join/codecheck", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestSignupEmailAgain = (data, callback, errorCallback) => {
  http
    .get("/user/join/resend?email=" + data.email)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestSignupProfile = (data, callback, errorCallback) => {
  http
    .post("/user/join/profile", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestSignupIdCheck = (data, callback, errorCallback) => {
  http
    .get("/user/join/idcheck?email=" + data.email)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestSignupNicknameCheck = (data, callback, errorCallback) => {
  http
    .get("/user/namecheck?nickname=" + data.nickname)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestFindPwdSendEmail = (data, callback, errorCallback) => {
  http
    .post("/user/findpwd", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const requestFindPwd = (data, callback, errorCallback) => {
  http
    .put("/user/findpwd", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

export default {
  requestLogin,
  requestUserInfo,
  requestSignup,
  requestSignupEmail,
  requestSignupEmailAgain,
  requestSignupProfile,
  requestSignupIdCheck,
  requestSignupNicknameCheck,
  requestFindPwdSendEmail,
  requestFindPwd,
};
