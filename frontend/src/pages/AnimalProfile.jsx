import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Link } from 'react-router-dom';

export default function AnimalProfile() {
  const { id } = useParams(); // Get the dynamic 'id' from the URL
  const [animal, setAnimal] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:5001/animals/${id}`) // Fetch the animal data by ID
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
  }, [id]); // The effect will run whenever 'id' changes

  if (loading) return <p>Loading animal profile...</p>;
  if (error) return <p>Error fetching animal: {error}</p>;

  return (
    <div>
      <h1>{animal.name}</h1>
      {/* Apply the wrapper container for the image */}
      <div className="animal-profile-container">
        <img src={animal.photo} alt={animal.name} />
      </div>
      <p>Species: {animal.species}</p>
      <p>Breed: {animal.breed}</p>
      <p>Age: {animal.age} years old</p>
      <p>Sex: {animal.sex}</p>
      <p>Intake Date: {animal.intakeDate}</p>
      <p>Vaccinations: {animal.medicalRecords.vaccinations.join(", ")}</p>
      <p><Link to="/adopt">Apply to adopt {animal.name}</Link></p>
    </div>
  );
}
