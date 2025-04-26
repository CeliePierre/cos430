// Create a new animal profile
const AnimalProfile = require("../models/AnimalProfile");
const createAnimalProfile = async (req, res) => {
  console.log("body req:", req.body);
  try {
    const { name, species, breed, age, sex, medicalRecords, photo } = req.body;
    const newAnimalProfile = new AnimalProfile({
      name,
      species,
      breed,
      age,
      sex,
      medicalRecords,
      photo,
    });
    const savedAnimalProfile = await newAnimalProfile.save();
    res.status(201).json(savedAnimalProfile);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};

const getAllAnimals = async (req, res) => {
  try {
    const animals = await AnimalProfile.find();
    res.json(animals);
  } catch (error) {
    console.error("❌ Server Error:", error.message);
    res.status(500).json({ message: "Server Error", error: error.message });
  }
};

const viewAnimalProfile = async (req, res) => {
  try {
    const animal = await AnimalProfile.findOne({ animalID: req.params.id });

    if (!animal) {
      return res.status(404).json({ message: "Animal profile not found" });
    }

    res.status(200).json(animal);
  } catch (error) {
    res.status(500).json({ message: "Server Error", error: error.message });
  }
};

module.exports = {
  createAnimalProfile,
  getAllAnimals,
  viewAnimalProfile,
};
