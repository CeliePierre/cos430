import React, { useEffect, useState } from "react";

export default function BrowseAnimals() {
  const [animals, setAnimals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:5001/animals")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch animals");
        }
        return response.json();
      })
      .then((data) => {
        setAnimals(data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading animals...</p>;
  if (error) return <p>Error fetching animals: {error}</p>;

  return (
    <div>
      <h1>Browse Animals</h1>
      <div>
        {animals.map((animal) => (
          <div key={animal.animalID}>
            <img
              src={animal.photo}
              alt={animal.name}
            />
            <h3>{animal.name}</h3>
            <p>
              {animal.species} - {animal.breed}
            </p>
            <p>Age: {animal.age}</p>
            <p>Sex: {animal.sex}</p>
            <p>Intake Date: {animal.intakeDate}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
