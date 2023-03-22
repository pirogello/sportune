import axios from "axios";

const ax =  axios.create({
});

ax.interceptors.request.use(function (config) {
    const token = localStorage.getItem('token')
    const refreshToken = localStorage.getItem('refreshToken')
    if(token)
        config.headers.Authorization = 'Bearer_' + token;
    if(refreshToken)
        config.headers.refresh = refreshToken;
    return config;
});

export default ax;