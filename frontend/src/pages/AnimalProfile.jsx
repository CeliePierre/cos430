import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import AdoptionApplication from "./AdoptionApplication";

export default function AnimalProfile() {
  const { id } = useParams();
  const [animal, setAnimal] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

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

  if (loading) return <p>Loading animal profile...</p>;
  if (error) return <p>Error fetching animal: {error}</p>;

  return (
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
      <AdoptionApplication animalID={id} />
    </div>
  );
}
