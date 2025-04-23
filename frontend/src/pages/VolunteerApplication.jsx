import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function VolunteerApplication() {
  const [volunteerName, setVolunteerName] = useState("");
  const [volunteerEmail, setVolunteerEmail] = useState("");
  const [availability, setAvailability] = useState("");
  const [interests, setInterests] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

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

  return (
    <div className="page-wrapper">
      <h1>Volunteer Application</h1>
      <form onSubmit={handleSubmit} className="adoption-form">
        <input
          type="text"
          placeholder="Your name"
          value={volunteerName}
          onChange={(e) => setVolunteerName(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="Your email"
          value={volunteerEmail}
          onChange={(e) => setVolunteerEmail(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Your availability (e.g., weekends, weekdays)"
          value={availability}
          onChange={(e) => setAvailability(e.target.value)}
          required
        />
        <textarea
          placeholder="Tell us about your interests or experience"
          value={interests}
          onChange={(e) => setInterests(e.target.value)}
        />
        <button type="submit">Submit Application</button>
      </form>
      {message && (
        <p
          className={
            message.includes("successfully") ? "success-message" : "error-message"
          }
        >
          {message}
        </p>
      )}
    </div>
  );
}
