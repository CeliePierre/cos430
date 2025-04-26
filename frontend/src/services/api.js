import axios from "axios";

const API_BASE_URL = "http://localhost:5001";

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true, // ✅ includes cookies automatically
});

// test getting animal profile from backend
export const getAllAnimalsProfile = () => api.get("/animals");

// sign up user
export const signupUser = (data) => api.post("/auth/signup", data);

// log in user
export const loginUser = (data) => api.post("/auth/login", data);

// verify auth
export const checkAuth = async () => {
  try {
    const res = await api.get("/auth/verify");
    return res.data;
  } catch (err) {
    return null;
  }
};

// ✅ logout user
export const logoutUser = async () => {
  try {
    const res = await api.post("/auth/logout");
    return res.data;
  } catch (err) {
    console.log("Logout error:", err);
    throw err;
  }
};

export default api;
