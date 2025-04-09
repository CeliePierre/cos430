const Staff = require('../models/staff');
const User = require('../models/User');

// Create a new staff member
exports.createStaff = async (req, res) => {
  try {
    const user = await User.findById(req.body.userID);
    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }
    const staff = new Staff(req.body);
    await staff.save();
    res.status(201).json(staff);
  } catch (error) {
    res.status(500).json({ message: 'Failed to create staff member', error });
  }
};

// Get all staff members 
exports.getAllStaff = async (req, res) => {
  try {
    const { isActive, position } = req.query;
    
    const query = {};
    if (isActive) query.isActive = isActive === 'true';
    if (position) query.position = position;
    
    const staff = await Staff.find(query).populate('userID', 'name email');
    res.status(200).json(staff);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch staff members', error });
  }
};

// Get a specific staff member by ID
exports.getStaffById = async (req, res) => {
  try {
    const staff = await Staff.findById(req.params.id).populate('userID', 'name email');
    if (!staff) {
      return res.status(404).json({ message: 'Staff member not found' });
    }
    res.status(200).json(staff);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch staff member', error });
  }
};

// Update a staff member's information
exports.updateStaff = async (req, res) => {
  try {
    const staff = await Staff.findByIdAndUpdate(req.params.id, req.body, { new: true });
    if (!staff) {
      return res.status(404).json({ message: 'Staff member not found' });
    }
    res.status(200).json(staff);
  } catch (error) {
    res.status(500).json({ message: 'Failed to update staff member', error });
  }
};

// Delete a staff member
exports.deleteStaff = async (req, res) => {
  try {
    const staff = await Staff.findByIdAndDelete(req.params.id);
    if (!staff) {
      return res.status(404).json({ message: 'Staff member not found' });
    }
    res.status(200).json({ message: 'Staff member deleted successfully' });
  } catch (error) {
    res.status(500).json({ message: 'Failed to delete staff member', error });
  }
};

