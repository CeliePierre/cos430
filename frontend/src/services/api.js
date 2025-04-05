import axios from 'axios';

const API_BASE_URL = 'http://localhost:5001';

const api = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
    }
})


// test getting animal profile from backend
export const getAllAnimalsProfile = () => api.get('/animals');

export default api;