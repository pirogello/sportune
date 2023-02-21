const path = require('path');
const { VueLoaderPlugin } = require('vue-loader');

module.exports = {
  mode: 'development',
  devtool: 'source-map',
  entry: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'main.js'),
  devServer: {
    static: {
      directory: path.join(__dirname, "./dist")
    },
    compress: true,
    host: 'localhost',
    port: 8000,
    allowedHosts: [
      'localhost:9002'
    ]
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      },
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      },
      {
        test: /\.css$/,
        use: [
          'vue-style-loader',
          'css-loader',
        ]
      }
    ]
  },
  plugins: [
    new VueLoaderPlugin()
  ],
  resolve: {
    modules: [
      path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
      path.join(__dirname, 'node_modules'),
    ],
    alias: {
      vue: 'vue/dist/vue.js'
    },
  }
}