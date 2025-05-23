import api from './api';

export const signup = async (userData) => {
  return api.post('/user', userData);
};

export const login = async (userData) => {
  return api.post('/auth/login', userData);
};

export const addTodo = async (todoData) => {
  return api.post('/todo', todoData);
};

export const deleteTodo = async (todoId) => {
  return api.delete(`/todo/${todoId}`);
}

export const updateTodo = async (todoId, todoData) => {
  return api.put(`/todo/${todoId}`, todoData);
}