module.exports = {
  transpileDependencies: ["vuetify"],
  chainWebpack: (config) => {
    if (process.env.NODE_ENV === "development") {
      config.output.filename("[name].[hash].js").end();
    }
    config.plugin("html").tap((args) => {
      args[0].title = "오늘도 나는 코딩한다";
      return args;
    });
  },
};
