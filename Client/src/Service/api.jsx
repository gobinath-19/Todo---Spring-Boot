import axios from 'axios';
import { getToken } from '../Utils/token';

const api = axios.create({

  baseURL: 'http://localhost:2000',

  //https://todo-spring-boot-t3w6.onrender.com

});

api.interceptors.request.use((config) => {
  const token = getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
