import http from "@/util/http-common.js";

const loadAllTechList = (data, callback, errorCallback) => {
  http
    .get("/tech/article/list", {
      params: data,
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const loadSubscribeTechList = (data, callback, errorCallback) => {
  http
    .get("/tech/article/subscribe", {
      params: data,
    })
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const scrapTech = (data, callback, errorCallback) => {
  http
    .post("/tech/scrap?articleid=" + data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const loadAllBlogList = (callback, errorCallback) => {
  http
    .get("/tech/blog/list")
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const loadSubscribeBlogList = (callback, errorCallback) => {
  http
    .get("/tech/blog/subscribe")
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const subscribeBlog = (data, callback, errorCallback) => {
  http
    .post("/tech/blog/subscribe", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const unSubscribeBlog = (data, callback, errorCallback) => {
  http
    .put("/tech/blog/subscribe", data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

export default {
  loadAllTechList,
  loadSubscribeTechList,
  scrapTech,
  loadAllBlogList,
  loadSubscribeBlogList,
  subscribeBlog,
  unSubscribeBlog,
};
