import http from "@/util/http-common.js";

const loadAllTechList = (data, callback, errorCallback) => {
  http
    .get("/tech/article/list", {
      params: {
        pageno: data.pageno,
        keyword: data.keyword,
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
  loadAllTechList,
};
