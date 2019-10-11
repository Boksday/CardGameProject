import { apiClient } from '../network/HttpClientFactory'

export default {
  async insertRecord (data) {
    const res = await apiClient.post('/insertRecord', data)
    return res
  },
  async selectRecordsByTime () {
    const res = await apiClient.post('/selectRecordsByTime')
    return res
  },
  async selectRecordsByTries () {
    const res = await apiClient.post('/selectRecordsByTries')
    return res
  },
  async personalRecord (data) {
    const res = await apiClient.post('/personalRecord', data)
    return res
  }
}
