const express = require('express');
const router = express.Router();
const volunteerController = require('../controllers/VolunteerController');

// Create a new volunteer record
router.post('/', volunteerController.createVolunteer);

// Get all volunteers (optional: filter by skills or availability)
router.get('/', volunteerController.getAllVolunteers);

// Get a specific volunteer by ID
router.get('/:id', volunteerController.getVolunteerById);

// Update a volunteer record (e.g., skills, availability)
router.put('/:id', volunteerController.updateVolunteer);

// Delete a volunteer record
router.delete('/:id', volunteerController.deleteVolunteer);

module.exports = router;
