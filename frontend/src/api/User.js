import axios from 'axios';

const requestLogin = (data, callback, errorCallback) => {
  axios.post('http://i5a101.p.ssafy.io:8197/user/signIn', data)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const requestSignup = (data, callback, errorCallback) => {
  axios.post('http://i5a101.p.ssafy.io:8197/user/join', data)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const requestSignupEmail = (data, callback, errorCallback) => {
  axios.post('http://i5a101.p.ssafy.io:8197/user/join/codecheck', data)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const requestSignupEmailAgain = (data, callback, errorCallback) => {
  axios.get('http://i5a101.p.ssafy.io:8197/user/join/resend?email=' + data.email)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const requestSignupProfile = (data, callback, errorCallback) => {
  axios.post('http://i5a101.p.ssafy.io:8197/user/join/profile', data)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const requestSignupIdCheck = (data, callback, errorCallback) => {
  axios.get('http://i5a101.p.ssafy.io:8197/user/join/idcheck?email=' + data.email)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const requestSignupNicknameCheck = (data, callback, errorCallback) => {
  axios.get('http://i5a101.p.ssafy.io:8197/user/namecheck?nickname=' + data.nickname)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const UserApi = {
  requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
  requestSignup: (data, callback, errorCallback) => requestSignup(data, callback, errorCallback),
  requestSignupEmail: (data, callback, errorCallback) => requestSignupEmail(data, callback, errorCallback),
  requestSignupEmailAgain: (data, callback, errorCallback) => requestSignupEmailAgain(data, callback, errorCallback),
  requestSignupProfile: (data, callback, errorCallback) => requestSignupProfile(data, callback, errorCallback),
  requestSignupIdCheck: (data, callback, errorCallback) => requestSignupIdCheck(data, callback, errorCallback),
  requestSignupNicknameCheck: (data, callback, errorCallback) => requestSignupNicknameCheck(data, callback, errorCallback),
};

export default UserApi;
