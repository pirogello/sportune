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
import UserPosts from "../pages/user/UserPosts.vue";
import UserTrainings from "../pages/user/UserTrainings.vue";
import UserCoaches from "../pages/user/UserCoaches.vue";
import UserCompetitions from "../pages/user/UserCompetitions.vue";
import UserTrophies from "../pages/user/UserTrophies.vue";
import TrainerProfile from "../pages/trainer/TrainerProfile.vue";
import TrainerTrainings from "../pages/trainer/TrainerTrainings.vue";
import UserProfile from "../pages/user/UserProfile.vue";
import CreateTrainForm from "../pages/trainer/CreateTrainForm.vue"
import store from "../store/store.js"

Vue.use(VueRouter)

const routes = [
    {path: '/', component: Appl},
    {path: "/login", component: Login},
    {path: "/test", component: Register},
    {path: "/admin", component: Admin},
    {path: "/models", component: ReadModels},
    {path: "/readUsers", component: ReadUser},
    {
        path: '/sportsman/:username', component: UserProfile,
        children: [
            {
                path: 'posts',
                component: UserPosts,
            },
            {
                path: 'trainings',
                component: UserTrainings,
            },
            {
                path: 'trophies',
                component: UserTrophies,
            },
            {
                path: 'coaches',
                component: UserCoaches,
            },
            {
                path: 'competitions',
                component: UserCompetitions,
            }
        ]
    },

    {
        path: '/trainer/:username', component: TrainerProfile,
        children: [
            {
                path: 'posts',
                component: UserPosts,
            },
            {
                path: 'trainings',
                component: TrainerTrainings,
            },
            {
                path: 'trophies',
                component: UserTrophies,
            },
            {
                path: 'coaches',
                component: UserCoaches,
            },
            {
                path: 'competitions',
                component: UserCompetitions,
            },
            {
                path: 'createTrain',
                component: CreateTrainForm,
            },
        ]

    },
    {
        path: "/haveauth",
        component: CheckAuth,
    },

];
let router = new VueRouter({
    mode: "history",
    routes
})

// router.beforeEach((to, from, next) => {
//     store.dispatch("onRequest")
//     next()
// });
export default router
