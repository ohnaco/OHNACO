import axios from 'axios';

const requestLogin = (data, callback, errorCallback) => {
  axios.get('http://localhost:8080/login', data)
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
