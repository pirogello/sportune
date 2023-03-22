import Vue from 'vue'
import VueRouter from 'vue-router'

import Main from 'pages/Main.vue'
import Appl from 'pages/Appl.vue'
import Login from 'pages/Login.vue'
import Register from 'pages/Register.vue'
import Admin from 'pages/Admin.vue'
import CheckAuth from 'pages/CheckAuth.vue'

import ReadModels from "pages/ReadModels.vue";
import ReadUser from "pages/ReadUser.vue";
import ReadPublication from "pages/ReadPublication.vue";

Vue.use(VueRouter)

const routes = [
    {path: '/', component: Main},
    //{path: '/', component: Appl},
    {path:"/login", component: Login},
    {path:"/test", component: Register},
    {path:"/admin", component: Admin},
    {path:"/models", component: ReadModels},
    {path:"/readUsers", component: ReadUser},
    {path:"/readPublication", component: ReadPublication},
    {
        path:"/haveauth",
        component: CheckAuth,
    },

];
let router = new VueRouter ({
    mode: "history",
    routes
})

export default router
