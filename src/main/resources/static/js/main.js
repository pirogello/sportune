import Vue from 'vue';
import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css'
import axios from "axios";

import App from 'pages/App.vue'

Vue.use(Vuetify);

Vue.prototype.$http = axios;

/*Vue.component('list', {
    props: ['users'],
    template: '<div>' +
        '<div v-for="user in users">{{user}}</div>' +
        '</div>',
    created: function () {
        this.$http.get('/profile/1').then(
            result => result.data.forEach(u => this.users.push(u))
    )
    }
});*/

new Vue ({
    vuetify : new Vuetify(),
    el: '#app',
    render: a => a(App)

})