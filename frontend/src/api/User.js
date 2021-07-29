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

const UserApi = {
  requestLogin: (data, callback, errorCallback) => requestLogin(data, callback, errorCallback),
  };

export default UserApi;
