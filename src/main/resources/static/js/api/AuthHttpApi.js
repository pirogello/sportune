import http from './http-common.js'

export const AuthApi = {
    login(username, password) {
        const url = '/api/v1/auth/login';
        const data = {
            username: username,
            password: password
        }
        return http.post(url, data)
    },

    logout() {
        const url = 'api/v1/auth/logout';
        return http.get(url);
    }
}