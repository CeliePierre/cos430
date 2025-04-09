const express = require('express');
const router = express.Router();
const visitorController = require('../controllers/VisitorController');

// Create a new visitor record
router.post('/', visitorController.createVisitor);

// Get all visitors
router.get('/', visitorController.getAllVisitors);

// Get a specific visitor by ID
router.get('/:id', visitorController.getVisitorById);

// Update a visitor's purpose for visiting
router.put('/:id', visitorController.updateVisitor);

// Delete a visitor record
router.delete('/:id', visitorController.deleteVisitor);

module.exports = router;
