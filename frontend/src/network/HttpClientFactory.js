import axios from 'axios'

const config = {
  // Sample URL
  baseURL: 'http://localhost:8090'
}

export const apiClient = axios.create(config)
