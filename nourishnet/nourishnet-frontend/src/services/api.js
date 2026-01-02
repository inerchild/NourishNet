import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getDiets = () => api.get('/diets');
export const getDietById = (id) => api.get(`/diets/${id}`);
export const getCuisines = () => api.get('/cuisines');
export const getCuisineById = (id) => api.get(`/cuisines/${id}`);
export const getContent = (dietId, cuisineId) => 
  api.get('/content', { params: { dietId, cuisineId } });
export const getContentById = (id) => api.get(`/content/${id}`);

export default api;