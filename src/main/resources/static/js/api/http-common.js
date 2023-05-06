import axios, {AxiosError} from "axios";
import store from "../store/store.js";
import router from "../router/router";

const ax = axios.create({});

ax.interceptors.request.use(async (config) => {
    if (store.getters.getRefreshToken != null) {
        await axios.post("/api/v1/auth/access-token", {
            username: store.getters.getUsername,
            refreshToken: store.getters.getRefreshToken
        }).then((response) => {
            store.commit('setToken', response.data.token)
        }).catch(error => {
            console.log("ERROR IN UPDATE ACCESS-TOKEN")
        })
    }

    const token = localStorage.getItem('token')
    const refreshToken = localStorage.getItem('refreshToken')
    console.log("запрос с токеном: " + token)
    console.log(config.url)
    if (token)
        config.headers.Authorization = 'Bearer_' + token;
    if (refreshToken)
        config.headers.refresh = refreshToken;
    return config;
});

ax.interceptors.response.use(response => response,
    (error) => {
        if (error.response?.status === 401) {
            store.dispatch("onLogout")
            router.push("/login")
        }
    })

export default ax;