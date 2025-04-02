const express = require('express');
const router = express.Router();
const animalProfileController = require('../controllers/AnimalProfileController');

// Route to get all animal profiles
router.get("/", (req, res, next) => {
    console.log("📌 Route hit: GET /animals");
    next();
}, animalProfileController.getAllAnimals);
// Route to get an animal profile by ID
router.get('/:animalID', animalProfileController.viewAnimalProfile);

// Route to create a new animal profile
router.post('/', animalProfileController.createAnimalProfile);

module.exports = router;
