const Volunteer = require('../models/Volunteer');
const User = require('../models/User');  // Make sure User model is available for linking

// Create a new volunteer record
exports.createVolunteer = async (req, res) => {
  try {
    // Ensure the user exists
    const user = await User.findById(req.body.userID);
    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }

    // Create the volunteer record
    const volunteer = new Volunteer(req.body);
    await volunteer.save();
    res.status(201).json(volunteer);
  } catch (error) {
    res.status(500).json({ message: 'Failed to create volunteer record', error });
  }
};

// Get all volunteers (can filter by skills or availability)
exports.getAllVolunteers = async (req, res) => {
  try {
    const { skills, availability } = req.query;
    const query = {};

    if (skills) query.skills = { $in: skills.split(',') }; 
    if (availability) query.availability = availability; 

    const volunteers = await Volunteer.find(query).populate('userID', 'name email');
    res.status(200).json(volunteers);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch volunteers', error });
  }
};

// Get a specific volunteer by ID
exports.getVolunteerById = async (req, res) => {
  try {
    const volunteer = await Volunteer.findById(req.params.id).populate('userID', 'name email');
    if (!volunteer) {
      return res.status(404).json({ message: 'Volunteer not found' });
    }
    res.status(200).json(volunteer);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch volunteer', error });
  }
};

// Update a volunteer's information
exports.updateVolunteer = async (req, res) => {
  try {
    const volunteer = await Volunteer.findByIdAndUpdate(req.params.id, req.body, { new: true });
    if (!volunteer) {
      return res.status(404).json({ message: 'Volunteer not found' });
    }
    res.status(200).json(volunteer);
  } catch (error) {
    res.status(500).json({ message: 'Failed to update volunteer record', error });
  }
};

// Delete a volunteer record
exports.deleteVolunteer = async (req, res) => {
  try {
    const volunteer = await Volunteer.findByIdAndDelete(req.params.id);
    if (!volunteer) {
      return res.status(404).json({ message: 'Volunteer not found' });
    }
    res.status(200).json({ message: 'Volunteer deleted successfully' });
  } catch (error) {
    res.status(500).json({ message: 'Failed to delete volunteer record', error });
  }
};
