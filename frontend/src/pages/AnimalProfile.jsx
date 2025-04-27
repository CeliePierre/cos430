import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { checkAuth } from "../services/api";
import AdoptionApplication from "./AdoptionApplication";

export default function AnimalProfile() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [animal, setAnimal] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [authLoading, setAuthLoading] = useState(true);
  const [authorized, setAuthorized] = useState(false);

  useEffect(() => {
    fetch(`http://localhost:5001/animals/${id}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch animal data");
        }
        return response.json();
      })
      .then((data) => {
        setAnimal(data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, [id]);

  useEffect(() => {
    const verify = async () => {
      try {
        const user = await checkAuth();
        if (user && user.role === "Visitor") {
          setAuthorized(true);
        } else {
          setAuthorized(false);
        }
      } catch (err) {
        console.error("Authorization error", err);
        setAuthorized(false);
      } finally {
        setAuthLoading(false);
      }
    };
    verify();
  }, []);

  if (loading || authLoading) return <p>Loading...</p>;
  if (error) return <p>Error fetching animal: {error}</p>;

  return (
    <div className="animal-profile-page">
      <div className="animal-profile">
        <div className="animal-photo">
          <img src={animal.photo} alt={animal.name} />
        </div>

        <div className="animal-info">
          <h1>{animal.name}</h1>
          <p>Species: {animal.species}</p>
          <p>Breed: {animal.breed}</p>
          <p>Age: {animal.age} years old</p>
          <p>Sex: {animal.sex}</p>
          <p>
            Intake Date:{" "}
            {new Date(animal.intakeDate).toLocaleString("en-US", {
              dateStyle: "long",
              timeStyle: "short",
            })}
          </p>
          <p>Vaccinations: {animal.medicalRecords}</p>
        </div>
      </div>

      <div className="adoption-section">
        {authorized ? (
          <AdoptionApplication animalID={id} />
        ) : (
          <div className="signup-login-container">
            <h2>Interested in Adopting?</h2>
            <p>You must be logged in as a <strong>Visitor</strong> to submit an adoption application.</p>
            <div style={{ marginTop: "20px" }}>
              <button onClick={() => navigate("/login")} className="button">
                Login
              </button>
              <button onClick={() => navigate("/signup")} className="button" style={{ marginLeft: "10px" }}>
                Sign Up
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
