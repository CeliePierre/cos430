import React, { useState } from "react";
import { signupUser } from "../services/api";
import { useNavigate } from "react-router-dom";

export default function SignUp() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    phone: "",
    password: "",
    confirmPassword: "",
    role: "Visitor",
  });
  const [passwordError, setPasswordError] = useState("");
  const [formError, setFormError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    const updatedForm = { ...form, [name]: value };
    setForm(updatedForm);

    // Check password match in real-time
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
      await signupUser({
        name: form.name,
        email: form.email,
        phone: form.phone,
        password: form.password,
        role: form.role,
      });

      setFormError("");
      console.log("Signup success!");
      navigate("/visitorDashboard");
    } catch (error) {
      console.log("Signup error:", error.response?.data); // ✅ check what’s returned

      // ✅ Safely handle the message
      const message =
        error.response?.data?.message || "Signup failed. Try again.";
      setFormError(message);
    }
  };

  return (
    <main className="signup-container">
      <h2>
        <span role="img" aria-label="paw">
          🐾
        </span>{" "}
        Welcome to ASMS{" "}
        <span role="img" aria-label="paw">
          🐾
        </span>
      </h2>
      <p>Create an account to adopt, volunteer, and more!</p>

      <form onSubmit={handleSubmit} className="signup-form">
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
          placeholder="Password"
          value={form.confirmPassword}
          onChange={handleChange}
          required
        />
        {passwordError && (
          <p
            p
            style={{ color: "red", fontSize: "0.875rem", marginTop: "0.25rem" }}
          >
            {passwordError}
          </p>
        )}
        {formError && (
          <p
            style={{ color: "red", fontSize: "0.875rem", marginTop: "0.25rem" }}
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
    </main>
  );
}
