import Vue from 'vue'
import Vuex from 'vuex'
import {AuthApi} from '../api/AuthHttpApi.js'
import http from '../api/http-common.js'
import router from "../router/router";

Vue.use(Vuex)
localStorage.setItem('fillDB', false)

export default new Vuex.Store({
    state: {
        token: localStorage.getItem('token') || null,
        refreshToken: localStorage.getItem('refreshToken') || null,
        username: localStorage.getItem('username') || null,
        userRoles: localStorage.getItem('userRoles') || null,
    },

    getters: {
        getToken: (state) => state.token,
        getRefreshToken: (state) => state.refreshToken,
        getUsername: (state) => state.username,
        getRoles: (state) => state.userRoles,
    },
    mutations: {
        setToken(state, token) {
            state.token = token
            localStorage.setItem('token', token)
        },
        setRefreshToken(state, refresh) {
            state.refreshToken = refresh
            localStorage.setItem('refreshToken', refresh)
        },
        setUsername(state, username) {
            state.username = username
            localStorage.setItem('username', username)
        },
        setUserRoles(state, roles) {
            state.userRoles = roles
            localStorage.setItem('userRoles', roles)
        },

        deleteToken(state) {
            state.token = null
            localStorage.removeItem('token')
        },
        deleteRefreshToken(state) {
            state.refreshToken = null
            localStorage.removeItem('refreshToken')
        },

        clearUserData(state) {
            state.token = null
            state.refreshToken = null
            state.userRoles = null
            state.username = null

            localStorage.removeItem('token')
            localStorage.removeItem('refreshToken')
            localStorage.removeItem('username')
            localStorage.removeItem('userRoles')
        }
    },
    actions: {
        onLogin({commit}, {username, password}) {
            return new Promise((resolve, reject) => {
                AuthApi.login(username, password).then((res) => {
                    commit('setToken', res.data.token)
                    commit('setRefreshToken', res.data.refreshtoken)
                    commit('setUsername', res.data.username)
                    commit('setUserRoles', res.data.roles)
                    resolve()
                }).catch(function (error) {
                    reject(error.response.status)
                    // if(error.response.status === 401){
                    //     router.push('/login');
                    // }
                })
            });
        },
        onLogout({commit}) {
            commit('clearUserData')
            delete http.defaults.headers['authorization']
            delete http.defaults.headers['refresh']
        },

    }
})