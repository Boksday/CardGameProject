import { apiClient } from '../network/HttpClientFactory'

export default {
    getUserList() {
        return apiClient.get('/getUsers').then((res) => res.data)
    },
    async loginValidCheck(params){
        const res = await apiClient.get('/loginValidCheck', { params });
        return res;
    },
    async createUser(params) {
        const res = await apiClient.get('/createUser', { params })
    },
    async idCheck(params) {
        const res = await apiClient.get('/idCheck', { params })
        return res;
    }
}