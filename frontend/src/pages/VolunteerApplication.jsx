import React, { useState, useEffect } from "react"; // Don't forget to import useEffect
import { useNavigate } from "react-router-dom";
import { checkAuth } from "../services/api"; // Import your checkAuth API service

export default function VolunteerApplication() {
  const [volunteerName, setVolunteerName] = useState("");
  const [volunteerEmail, setVolunteerEmail] = useState("");
  const [availability, setAvailability] = useState("");
  const [interests, setInterests] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(true); // Track loading state
  const [authorized, setAuthorized] = useState(false); // Track if user is authorized
  const navigate = useNavigate();

  useEffect(() => {
    // Check if user is logged in and verify role
    const verify = async () => {
      try {
        const user = await checkAuth(); // Check if the user is authenticated
        if (user && user.role === "Volunteer") {
          setAuthorized(true); // If user is a Volunteer, set authorized to true
        } else {
          setAuthorized(false); // If user is not a Volunteer, set authorized to false
        }
      } catch (err) {
        console.error("Authorization error", err);
        setAuthorized(false); // In case of error, deny access
      } finally {
        setLoading(false); // Set loading to false when done
      }
    };
    verify();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!volunteerName || !volunteerEmail || !availability) {
      setMessage("Please fill in all required fields.");
      return;
    }

    try {
      const res = await fetch("http://localhost:5001/volunteerApplications", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          volunteerName,
          volunteerEmail,
          availability,
          interests,
        }),
      });

      const data = await res.json();

      if (!res.ok) throw new Error(data.message || "Submission failed");
      setMessage("Volunteer application submitted successfully!");
    } catch (err) {
      console.error(err);
      setMessage(`Error: ${err.message}`);
    }
  };

  // If it's still loading, show a loading state
  if (loading) {
    return <p>Loading...</p>;
  }

  if (!authorized) {
    // If not authorized (not a Volunteer), show message and buttons for login/signup
    return (
      <main className="signup-login-container">
        <h1>Volunteer Application</h1>
        <p>You must be logged in as a <strong>Volunteer</strong> to apply.</p>
        <div style={{ marginTop: "20px" }}>
          <button onClick={() => navigate("/login")} className="button">
            Login
          </button>
          <button onClick={() => navigate("/signup")} className="button" style={{ marginLeft: "10px" }}>
            Sign Up
          </button>
        </div>
      </main>
    );
  }

  return (
    <main className="signup-login-container">
      <h1>Volunteer Application</h1>
      <form onSubmit={handleSubmit} className="signup-login-form">
        <input
          type="text"
          placeholder="Your Name"
          value={volunteerName}
          onChange={(e) => setVolunteerName(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="Your Email"
          value={volunteerEmail}
          onChange={(e) => setVolunteerEmail(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Your Availability (e.g., weekends, weekdays)"
          value={availability}
          onChange={(e) => setAvailability(e.target.value)}
          required
        />
        <textarea
          placeholder="Tell us about your interests or experience"
          value={interests}
          onChange={(e) => setInterests(e.target.value)}
        /><br />
        <button type="submit">Submit Application</button>
      </form>
      {message && (
        <p className={message.includes("successfully") ? "success-message" : "error-message"}>
          {message}
        </p>
      )}
    </main>
  );
}
