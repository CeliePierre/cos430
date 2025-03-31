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
    photo: "https://example.com/buddy.jpg",
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
    photo: "https://example.com/mittens.jpg",
  },
];

// Create a new animal profile
const createAnimalProfile = async (req, res) => {
  try {
    const newAnimalProfile = new animalProfile(req.body);
    const savedAnimal = await newAnimalProfile.save();
    res.status(201).json(savedAnimalProfile);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};
    
// Get all animal profiles
// @route GET /animals
// @access Public
exports.getAllAnimals = async (req, res) => {
    console.log("📌 Controller hit: getAllAnimals()");
    
    try {
        res.status(200).json(mockAnimalProfiles);
    } catch (error) {
        console.error("❌ Server Error:", error.message);
        res.status(500).json({ message: "Server Error", error: error.message });
    }
};

// @desc View an animal profile by ID
// @route GET /api/animal-profile/:animalID
// @access Public
exports.viewAnimalProfile = async (req, res) => {
  try {
    const animalID = parseInt(req.params.animalID);
    const animal = mockAnimalProfiles.find((a) => a.animalID === animalID);

    if (!animal) {
      return res.status(404).json({ message: "Animal profile not found" });
    }

    res.status(200).json(animal);
  } catch (error) {
    res.status(500).json({ message: "Server Error", error: error.message });
  }
};
