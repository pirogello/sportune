import Vue from 'vue';
import Vuetify from 'vuetify';
import '@babel/polyfill'
import 'vuetify/dist/vuetify.min.css'
import store from "./store/store";
import router from "./router/router";
import App from 'pages/App.vue'
import http from "./api/http-common.js";

http.get('/test/api/fillDB', {})
    .then(function (response) {
    })
    .catch(function (error) {
        console.log(error)
    });


Vue.use(Vuetify);

new Vue ({
    vuetify : new Vuetify(),
    el: '#app',
    store,
    router,
    render: a => a(App),

})