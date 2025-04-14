import React, { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";

export default function AdoptionApplication() {
  const { id: routeAnimalID } = useParams(); // animal ID from /animalProfile/:id -> /adopt
  const [animals, setAnimals] = useState([]);
  const [animalID, setAnimalID] = useState(routeAnimalID || "");
  const [animalName, setAnimalName] = useState("");
  const [applicantName, setApplicantName] = useState("");
  const [applicantEmail, setApplicantEmail] = useState("");
  const [notes, setNotes] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  // Fetch animals if no animalID from route
  useEffect(() => {
    if (!routeAnimalID) {
      fetch("http://localhost:5001/animals")
        .then((res) => res.json())
        .then((data) => setAnimals(data))
        .catch((err) => console.error("Failed to fetch animals:", err));
    }
  }, [routeAnimalID]);

  // Fetch animal name when ID is present
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
    if (!animalID || !applicantName || !applicantEmail) {
      setMessage("Please fill in all required fields.");
      return;
    }

    try {
      const res = await fetch("http://localhost:5001/adoptionApplications", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          animalID,
          applicantName,
          applicantEmail,
          notes,
        }),
      });

      const data = await res.json();

      if (!res.ok) throw new Error(data.message || "Submission failed");
      setMessage("Application submitted successfully!");
      // Optional: redirect or clear form
    } catch (err) {
      console.error(err);
      setMessage(`Error: ${err.message}`);
    }
  };

  return (
    <div className="adoption-form">
      <h2>Adoption Application</h2>
      {animalName ? (
        <p>
          You are applying to adopt <strong>{animalName}</strong>, ID #
          {animalID}
        </p>
      ) : (
        <div>
          <label>Select an animal to adopt: </label>
          <select
            value={animalID}
            onChange={(e) => setAnimalID(e.target.value)}
          >
            <option value="">-- Choose an animal --</option>
            {animals.map((animal) => (
              <option key={animal._id} value={animal._id}>
                {animal.name} ({animal.species})
              </option>
            ))}
          </select>
        </div>
      )}

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Your name"
          value={applicantName}
          onChange={(e) => setApplicantName(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="Your email"
          value={applicantEmail}
          onChange={(e) => setApplicantEmail(e.target.value)}
          required
        />
        <textarea
          placeholder="Tell us why you'd like to adopt..."
          value={notes}
          onChange={(e) => setNotes(e.target.value)}
        />
        <button type="submit">Submit Application</button>
      </form>
      {message && <p className={message.includes("successfully") ? "success-message" : "error-message"}>{message}</p>}
    </div>
  );
}
