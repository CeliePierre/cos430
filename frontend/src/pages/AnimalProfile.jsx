import { getAllAnimalsProfile } from "../services/api";
import { useEffect, useState } from "react";
export default function AnimalProfile() {
  const [animals, setAnimals] = useState([]);
  useEffect(() => {
    getAllAnimalsProfile().then((res) => setAnimals([...res.data]));
  }, []);
  return (
    <div>
      <h1>Animal Profile</h1>
      {animals.map((animal) => (
        <div
          key={animal.animalID}
          style={{ border: "2px solid white", padding: "10px", margin: "10px" }}
        >
          <h3>name: {animal.name}</h3>
          <h4>species: {animal.species}</h4>
          <h4>breed: {animal.breed}</h4>
          <h4>age: {animal.age}</h4>
        </div>
      ))}
    </div>
  );
}
