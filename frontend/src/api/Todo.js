import http from "@/util/http-common.js";

const loadTodoList = (callback, errorCallback) => {
  let today = new Date();

  let year = today.getFullYear(); // 년도
  let month = ("0" + (today.getMonth() + 1)).slice(-2); // 월
  let day = ("0" + today.getDate()).slice(-2); // 날짜

  let queryDateString = year + "-" + month + "-" + day;

  http
    .get("/todo/item", {
      params: {
        date: queryDateString,
      },
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};


export default {
  loadTodoList,
};
