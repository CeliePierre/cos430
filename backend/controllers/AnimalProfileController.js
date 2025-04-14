
// Create a new animal profile
const AnimalProfile = require("../models/AnimalProfile");
const createAnimalProfile = async (req, res) => {
  console.log("body req:", req.body); ;
  try {
    const {name, species, breed, age, sex, medicalRecords, photo } = req.body;
    const newAnimalProfile = new AnimalProfile({ name, species, breed, age, sex, medicalRecords, photo });
    const savedAnimalProfile = await newAnimalProfile.save();
    res.status(201).json(savedAnimalProfile);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};
    
// Get all animal profiles
// @route GET /animals
// @access Public
const getAllAnimals = async (req, res) => {
    console.log("📌 Controller hit: getAllAnimals()");
    
    try {
      const animals = await AnimalProfile.find();
        res.json(animals);
    } catch (error) {
        console.error("❌ Server Error:", error.message);
        res.status(500).json({ message: "Server Error", error: error.message });
    }
};

// @desc View an animal profile by ID
// @route GET /api/animal-profile/:animalID
// @access Public
const viewAnimalProfile = async (req, res) => {
  try {
    const animalID = parseInt(req.params.animalID);
    const animal = AnimalProfile.findById(animalID);

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

}
