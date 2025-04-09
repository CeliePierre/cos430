const express = require('express');
const router = express.Router();
const volunteerOpportunityController = require('../controllers/VolunteerOpportunityController');

// Create a new volunteer opportunity
router.post('/', volunteerOpportunityController.createOpportunity);

// Get all volunteer opportunities (optional: filter by location, date, or title)
router.get('/', volunteerOpportunityController.getAllOpportunities);

// Get a specific volunteer opportunity by ID
router.get('/:id', volunteerOpportunityController.getOpportunityById);

// Update a volunteer opportunity
router.put('/:id', volunteerOpportunityController.updateOpportunity);

// Delete a volunteer opportunity
router.delete('/:id', volunteerOpportunityController.deleteOpportunity);

module.exports = router;
