import React, { useState } from "react";
import { signupUser, loginUser } from "../services/api";
import { useNavigate } from "react-router-dom";
import "../App.css"; // ✅ make sure your paw CSS is inside App.css

export default function SignUp() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    phone: "",
    password: "",
    confirmPassword: "",
    role: "",
  });
  const [passwordError, setPasswordError] = useState("");
  const [formError, setFormError] = useState("");
  const [isLoading, setIsLoading] = useState(false); // ✅ add loading state
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    const updatedForm = { ...form, [name]: value };
    setForm(updatedForm);

    if (name === "password" || name === "confirmPassword") {
      if (
        updatedForm.confirmPassword &&
        updatedForm.password !== updatedForm.confirmPassword
      ) {
        setPasswordError("Passwords do not match");
      } else {
        setPasswordError("");
      }
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (form.password !== form.confirmPassword) {
      setFormError("Passwords do not match");
      return;
    }

    try {
      setIsLoading(true); // ✅ show loader
      await signupUser({
        name: form.name,
        email: form.email,
        phone: form.phone,
        password: form.password,
        role: form.role,
      });

      const loginResponse = await loginUser({
        email: form.email,
        password: form.password,
      });

      const { role } = loginResponse.data;

      // ✅ add tiny delay to show loader nicely
      setTimeout(() => {
        if (role === "Staff") {
          navigate("/staffDashboard");
        } else if (role === "Volunteer") {
          navigate("/volunteerDashboard");
        } else {
          navigate("/visitorDashboard");
        }
        window.location.reload();
      }, 1500); // 1.5 seconds delay to enjoy paw animation
    } catch (error) {
      console.log("Signup or Auto-login error:", error.response?.data);
      const message =
        error.response?.data?.message ||
        "Signup or auto-login failed. Try again.";
      setFormError(message);
      setIsLoading(false); // ✅ stop loading if error
    }
  };

  return (
    <main className="signup-login-container">
      {isLoading ? (
        <div className="pet-loader">
          <div className="frame frame-1" />
          <div className="frame frame-2" />
        </div>
      ) : (
        <>
          <h1>Account Sign Up</h1>
          <p>Create an account to adopt, volunteer, and more!</p>

          <form onSubmit={handleSubmit} className="signup-login-form">
            <select
              id="role"
              name="role"
              value={form.role}
              onChange={handleChange}
              required
            >
            <option value="">-- Select an Account Type --</option>
            <option value="Visitor">Visitor</option>
            <option value="Volunteer">Volunteer</option>
            <option value="Staff">Staff</option>
            </select>
            <input
              name="name"
              type="text"
              placeholder="Name"
              value={form.name}
              onChange={handleChange}
              required
            />
            <input
              name="email"
              type="email"
              placeholder="Email"
              value={form.email}
              onChange={handleChange}
              required
            />
            <input
              name="phone"
              type="tel"
              placeholder="Phone"
              value={form.phone}
              onChange={handleChange}
            />
            <input
              name="password"
              type="password"
              placeholder="Password"
              value={form.password}
              onChange={handleChange}
              required
            />
            <input
              name="confirmPassword"
              type="password"
              placeholder="Confirm Password"
              value={form.confirmPassword}
              onChange={handleChange}
              required
            />

            {passwordError && (
              <p
                style={{
                  color: "red",
                  fontSize: "0.875rem",
                  marginTop: "0.25rem",
                }}
              >
                {passwordError}
              </p>
            )}
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
              disabled={
                !form.name ||
                !form.email ||
                !form.password ||
                !form.confirmPassword ||
                passwordError
              }
              className={`w-full py-2 rounded text-white ${
                !form.name ||
                !form.email ||
                !form.password ||
                !form.confirmPassword ||
                passwordError
                  ? "bg-gray-300 cursor-not-allowed"
                  : "bg-yellow-400 hover:bg-yellow-500"
              }`}
            >
              Sign Up
            </button>
          </form>

          <p>
            Already have an account? <a href="/login">Login here</a>
          </p>
        </>
      )}
    </main>
  );
}
