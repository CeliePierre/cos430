const express = require('express');
const router = express.Router();
const staffController = require('../controllers/StaffController');

// Create a new staff member
router.post('/', staffController.createStaff);

// Get all staff members (can filter by position, active status)
router.get('/', staffController.getAllStaff);

// Get a specific staff member by ID
router.get('/:id', staffController.getStaffById);

// Update a staff member
router.put('/:id', staffController.updateStaff);

// Delete a staff member
router.delete('/:id', staffController.deleteStaff);

// Update staff's active status
router.patch('/:id/status', staffController.updateStaffStatus);

module.exports = router;
