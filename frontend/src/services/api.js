import axios from "axios";

const API_BASE_URL = "http://localhost:5001";

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

// test getting animal profile from backend
export const getAllAnimalsProfile = () => api.get("/animals");
export const signupUser = (data) => api.post("/auth/signup", data);
export const checkAuth = async () => {
    try {
      const res = await api.get("/auth/verify");
      return res.data;
    } catch (err) {
      return null;
    }
  };
export default api;
