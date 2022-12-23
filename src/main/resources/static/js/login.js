import Vue from 'vue';
import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css'
import axios from "axios";

import App from 'pages/Login.vue'

Vue.use(Vuetify);

Vue.prototype.$http = axios;

new Vue ({
    vuetify : new Vuetify(),
    el: '#login',
    render: a => a(login)

})