const express = require("express");
const router = express.Router();
const {
  getAllAnimals,
  viewAnimalProfile,
  createAnimalProfile,
} = require("../controllers/AnimalProfileController");

// Route to get all animal profiles
router.get("/", getAllAnimals);
// Route to get an animal profile by ID
router.get("/:animalID", viewAnimalProfile);

// Route to create a new animal profile
router.post("/animal", createAnimalProfile);

module.exports = router;
