import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { checkAuth } from "../services/api";

export default function AdoptionApplication() {
  const { id: routeAnimalID } = useParams();
  const navigate = useNavigate();
  const [animals, setAnimals] = useState([]);
  const [animalID, setAnimalID] = useState(routeAnimalID || "");
  const [animalName, setAnimalName] = useState("");
  const [user, setUser] = useState(null);
  const [notes, setNotes] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(true);
  const [authorized, setAuthorized] = useState(false);

  useEffect(() => {
    const verify = async () => {
      try {
        const user = await checkAuth(); 
        if (user && user.role === "Visitor") {
          setAuthorized(true);
          setUser(user);
        } else {
          setAuthorized(false);
        }
      } catch (err) {
        console.error("Authorization error", err);
        setAuthorized(false);
      } finally {
        setLoading(false);
      }
    };
    verify();
  }, []);

  useEffect(() => {
    if (!routeAnimalID) {
      fetch("http://localhost:5001/animals")
        .then((res) => res.json())
        .then((data) => setAnimals(data))
        .catch((err) => console.error("Failed to fetch animals:", err));
    }
  }, [routeAnimalID]);

  useEffect(() => {
    if (animalID && routeAnimalID) {
      fetch(`http://localhost:5001/animals/${animalID}`)
        .then((res) => res.json())
        .then((data) => setAnimalName(data.name))
        .catch((err) => console.error("Failed to fetch animal name:", err));
    } else if (animalID && animals.length > 0) {
      const found = animals.find((a) => a._id === animalID);
      setAnimalName(found?.name || "");
    }
  }, [animalID, routeAnimalID, animals]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!user) {
      setMessage("User not loaded. Please try again.");
      return;
    }
    
    if (!animalID || !notes) {
      setMessage("Please fill in all required fields.");
      return;
    }

    try {
      const res = await fetch("http://localhost:5001/application", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          userID: user._id,
          animalID,
          notes,
        }),
      });

      const data = await res.json();

      if (!res.ok) throw new Error(data.message || "Submission failed");
      setMessage("Application submitted successfully!");
    } catch (err) {
      console.error(err);
      setMessage(`Error: ${err.message}`);
    }
  };

  if (loading) return <p>Loading...</p>;

  if (!authorized) {
    return (
      <main className="signup-login-container">
        <h1>Adoption Application</h1>
        <p>You must be logged in as a <strong>Visitor</strong> to apply for adoption.</p>
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
      <h1>Adoption Application</h1>
      <form onSubmit={handleSubmit} className="signup-login-form">

      {animalName && (
          <div>
            <p>
              You are applying to adopt <strong>{animalName}</strong><br></br> (ID #{animalID})
            </p>
          </div>
        )}
      <p>{animalName ? "Change your selection:" : "Select an animal to adopt:"}</p>
        <select
          value={animalID}
          onChange={(e) => setAnimalID(e.target.value)}
          required
        >
          <option value="">-- Choose an animal --</option>
          {animals.map((animal) => (
            <option key={animal._id} value={animal._id}>
              {animal.name} ({animal.species})
            </option>
          ))}
        </select>

        {/* <input
          type="text"
          placeholder="Your Name"
          value={applicantName}
          onChange={(e) => setApplicantName(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="Your Email"
          value={applicantEmail}
          onChange={(e) => setApplicantEmail(e.target.value)}
          required
        /> */}
        <textarea
          placeholder="Tell us why you'd like to adopt..."
          value={notes}
          onChange={(e) => setNotes(e.target.value)}
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
