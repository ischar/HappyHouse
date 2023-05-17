const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  // 백엔드 타겟 디렉토리
  outputDir: "../server/src/main/resources/static",

  //
  devServer: {
    // proxy: {
    //   '/': {
    //     target: "http://localhost:80",
    //     changeOrigin: true, 
    //     ws: false,
    //   },
    // }
  }
})
