import axios from 'axios';

const requestSignup = (data, callback, errorCallback) => {
  axios.post('http://localhost:8080//join', data)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const requestSignupEmail = (data, callback, errorCallback) => {
  axios.post('http://localhost:8080/join/codecheck', data)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const requestSignupEmailAgain = (data, callback, errorCallback) => {
  axios.post('http://localhost:8080/join/resend', data)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const requestSignupProfile = (data, callback, errorCallback) => {
  axios.post('http://localhost:8080/join/profile', data)
  .then((res) => {
    callback(res);
  })
  .catch((err) => {
    errorCallback(err);
  });
};

const UserApi = {
  requestSignup: (data, callback, errorCallback) => requestSignup(data, callback, errorCallback),
  requestSignupEmail: (data, callback, errorCallback) => requestSignupEmail(data, callback, errorCallback),
  requestSignupEmailAgain: (data, callback, errorCallback) => requestSignupEmailAgain(data, callback, errorCallback),
  requestSignupProfile: (data, callback, errorCallback) => requestSignupProfile(data, callback, errorCallback),
};

export default UserApi;