import http from "@/util/http-common.js";

const requestStatistics = (data, callback, errorCallback) => {
  http
    .get("/statistics/info?option=" + data)
    .then((res) => {
      callback(res);
    })
    .catch((err) => {
      errorCallback(err);
    });
};

const StatisticsApi = {
  requestStatistics: (data, callback, errorCallback) =>
    requestStatistics(data, callback, errorCallback),
};

export default StatisticsApi;
