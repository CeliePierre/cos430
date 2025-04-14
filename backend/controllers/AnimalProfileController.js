//Mock animal Model will delete when all DB and Model is setup
let mockAnimalProfiles = [
  {
    animalID: 1,
    name: "Buddy",
    species: "Dog",
    breed: "Golden Retriever",
    age: 5,
    sex: "Male",
    intakeDate: "2023-06-15",
    medicalRecords: { vaccinations: ["Rabies", "Parvo"] },
    photo: "https://upload.wikimedia.org/wikipedia/commons/e/e7/At_Sticker_Lumis_Golden.JPG",
  },
  {
    animalID: 2,
    name: "Mittens",
    species: "Cat",
    breed: "Maine Coon",
    age: 3,
    sex: "Female",
    intakeDate: "2024-01-10",
    medicalRecords: { vaccinations: ["FVRCP", "Rabies"] },
    photo: "https://upload.wikimedia.org/wikipedia/commons/f/f1/Maine_Coon_Guardian_Angel_of_Canadian_Summer_8_months_c.jpg",
  },
  {
    animalID: 3,
    name: "Charlie",
    species: "Dog",
    breed: "Labrador Retriever",
    age: 2,
    sex: "Male",
    intakeDate: "2023-09-22",
    medicalRecords: { vaccinations: ["Rabies", "Distemper"] },
    photo: "https://upload.wikimedia.org/wikipedia/commons/2/26/YellowLabradorLooking_new.jpg",
  },
  {
    animalID: 4,
    name: "Luna",
    species: "Cat",
    breed: "Siamese",
    age: 4,
    sex: "Female",
    intakeDate: "2022-11-03",
    medicalRecords: { vaccinations: ["FVRCP", "Leukemia"] },
    photo: "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/A_classic_seal_point_Siamese_cat.jpg/1920px-A_classic_seal_point_Siamese_cat.jpg",
  },
  {
    animalID: 5,
    name: "Max",
    species: "Dog",
    breed: "Beagle",
    age: 6,
    sex: "Male",
    intakeDate: "2024-02-14",
    medicalRecords: { vaccinations: ["Rabies", "Bordetella"] },
    photo: "https://upload.wikimedia.org/wikipedia/commons/5/55/Beagle_600.jpg",
  },
  {
    animalID: 6,
    name: "Cleo",
    species: "Cat",
    breed: "Persian",
    age: 2,
    sex: "Female",
    intakeDate: "2023-07-07",
    medicalRecords: { vaccinations: ["FVRCP", "Rabies"] },
    photo: "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Floofstare.png/1920px-Floofstare.png",
  },
  {
    animalID: 7,
    name: "Rocky",
    species: "Dog",
    breed: "German Shepherd",
    age: 4,
    sex: "Male",
    intakeDate: "2023-05-20",
    medicalRecords: { vaccinations: ["Parvo", "Rabies"] },
    photo: "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/German-shepherd-4040871920.jpg/1920px-German-shepherd-4040871920.jpg",
  },
  {
    animalID: 8,
    name: "Whiskers",
    species: "Cat",
    breed: "Bengal",
    age: 1,
    sex: "Male",
    intakeDate: "2024-03-12",
    medicalRecords: { vaccinations: ["FVRCP", "Rabies"] },
    photo: "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/BEN_Russicats_White_Angel_%285274194975%29.jpg/1920px-BEN_Russicats_White_Angel_%285274194975%29.jpg",
  },
  {
    animalID: 9,
    name: "Daisy",
    species: "Dog",
    breed: "Poodle",
    age: 7,
    sex: "Female",
    intakeDate: "2023-04-05",
    medicalRecords: { vaccinations: ["Rabies", "Canine Influenza"] },
    photo: "https://upload.wikimedia.org/wikipedia/en/d/da/Cafe-au-lait_standard_poodle.jpg",
  },
  {
    animalID: 10,
    name: "Shadow",
    species: "Cat",
    breed: "British Shorthair",
    age: 5,
    sex: "Male",
    intakeDate: "2024-01-22",
    medicalRecords: { vaccinations: ["FVRCP", "Rabies", "Feline Leukemia"] },
    photo: "https://upload.wikimedia.org/wikipedia/commons/5/51/BKH-kitten-blue.jpg",
  },
];

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
