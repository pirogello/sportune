import Vue from 'vue';
import Vuetify from 'vuetify';
import '@babel/polyfill'
import 'vuetify/dist/vuetify.min.css'
import App from 'pages/App.vue'

Vue.use(Vuetify);

new Vue ({
    vuetify : new Vuetify(),
    el: '#app',
    render: a => a(App)

})