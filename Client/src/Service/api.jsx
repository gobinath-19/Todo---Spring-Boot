import axios from 'axios';
import { getToken } from '../Utils/token';

const api = axios.create({
  baseURL: 'http://localhost:2000/', // Adjust to your backend URL
});

api.interceptors.request.use((config) => {
  const token = getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
