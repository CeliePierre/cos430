import React, { useEffect, useState } from "react";
import { Link, useSearchParams } from "react-router-dom"; // Import Link from react-router-dom

export default function BrowseAnimals() {
  const [animals, setAnimals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchParams] = useSearchParams();
  const type = searchParams.get('type'); 

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

        if(type){
          const filteredAnimal = data.filter((animal) => animal.species.toLowerCase() === type.toLowerCase());
          setAnimals(filteredAnimal);
        } else {
          setAnimals(data);
        }
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, [type]);

  if (loading) return <p>Loading animals...</p>;
  if (error) return <p>Error fetching animals: {error}</p>;

  return (
    <div className="page-wrapper">
      <h1>Browse Animals Available for Adoption</h1>
      <div className="animal-gallery">
        {animals.map((animal) => (
          <Link key={animal.animalID} to={`/animalProfile/${animal.animalID}`} className="animal-card">
            <img src={animal.photo} alt={animal.name} />
            <h3>{animal.name}</h3>
            <p>
              {animal.species}, {animal.breed}
            </p>
            <p>{animal.sex}, {animal.age} years old</p>
          </Link>
        ))}
      </div>
    </div>
  );
}
