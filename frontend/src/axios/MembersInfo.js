import { apiClient } from '../network/HttpClientFactory'

export default {
  getUserList () {
    return apiClient.get('/getUsers').then((res) => res.data)
  },
  async loginValidCheck (data) {
    console.log(data)
    const res = await apiClient.post('/loginValidCheck', data)
    return res
  },
  async createUser (params) {
    const res = await apiClient.post('/createUser', params)
    return res
  },
  async idCheck (params) {
    const res = await apiClient.get('/idCheck', { params })
    return res
  }
}
