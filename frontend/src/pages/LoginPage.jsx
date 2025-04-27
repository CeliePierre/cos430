import React, { useState, useEffect } from "react";
import { loginUser, checkAuth } from "../services/api";
import { useNavigate } from "react-router-dom";
import dogCatRun1 from "../assets/dog-cat-run-1.png";
import dogCatRun2 from "../assets/dog-cat-run-2.png";
import "../App.css"; // Make sure your pet-loader styles are here

export default function Login() {
  const [form, setForm] = useState({
    email: "",
    password: "",
  });
  const [formError, setFormError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userRole, setUserRole] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const verify = async () => {
      const user = await checkAuth();
      if (user) {
        setIsLoggedIn(true);
        setUserRole(user.role);
      }
    };
    verify();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      setIsLoading(true); // Start loading
      const response = await loginUser(form);
      const user = response.data;
  
      setFormError("");
      console.log("Login success!", user);
  
      setTimeout(() => {
        if (user.role === "Staff") {
          navigate("/staffDashboard");
        } else if (user.role === "Volunteer") {
          navigate("/volunteerDashboard");
        } else if (user.role === "Visitor") {
          navigate("/visitorDashboard");
        } else {
          navigate("/");
        }
        window.location.reload();
      }, 1500); // Show loader for a bit
    } catch (error) {
      setIsLoading(false); // Stop loading
      console.log("Login error:", error.response?.data);
      const message =
        error.response?.data?.message || "Login failed. Try again.";
      setFormError(message);
    }
  };
  
  return (
    <main className="signup-login-container">
      {isLoading ? (
        <div className="pet-loader">
          <div
            className="frame frame-1"
            style={{ backgroundImage: `url(${dogCatRun1})` }}
          />
          <div
            className="frame frame-2"
            style={{ backgroundImage: `url(${dogCatRun2})` }}
          />
        </div>
      ) : (
        <>
          <h1>Account Login</h1>
          <p>Sign in to adopt, volunteer, and explore more!</p>

          <form onSubmit={handleSubmit} className="signup-login-form">
            <input
              name="email"
              type="email"
              placeholder="Email"
              value={form.email}
              onChange={handleChange}
              required
            />

            <input
              name="password"
              type="password"
              placeholder="Password"
              value={form.password}
              onChange={handleChange}
              required
            />

            {formError && (
              <p
                style={{
                  color: "red",
                  fontSize: "0.875rem",
                  marginTop: "0.25rem",
                }}
              >
                {formError}
              </p>
            )}

            <button
              type="submit"
              disabled={!form.email || !form.password}
              className={`w-full py-2 rounded text-white ${
                !form.email || !form.password
                  ? "bg-gray-300 cursor-not-allowed"
                  : "bg-yellow-400 hover:bg-yellow-500"
              }`}
            >
              Log In
            </button>
          </form>

          {isLoggedIn ? (
            <p>
              You're already logged in. Go to your{" "}
              <a
                href={
                  userRole === "Staff" ? "/staffDashboard" : 
                  userRole === "Volunteer" ? "/volunteerDashboard" :
                  userRole === "Visitor" ? "/visitorDashboard" :
                  "/"
                }
              >
                Dashboard
              </a>
              .
            </p>
          ) : (
            <p>
              Don’t have an account? <a href="/signup">Sign up here</a>.
            </p>
          )}
        </>
      )}
    </main>
  );
}
