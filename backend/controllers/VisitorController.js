const Visitor = require('../models/visitor');
const User = require('../models/User');  // Ensure User model is available to link with the visitor

// Create a new visitor record
exports.createVisitor = async (req, res) => {
  try {
    const user = await User.findById(req.body.userID);
    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }
    const visitor = new Visitor(req.body);
    await visitor.save();
    res.status(201).json(visitor);
  } catch (error) {
    res.status(500).json({ message: 'Failed to create visitor record', error });
  }
};

// Get all visitors
exports.getAllVisitors = async (req, res) => {
  try {
    const { purpose, startDate, endDate } = req.query;  // Filters for purpose or date range
    const query = {};

    if (purpose) query.purpose = purpose;
    if (startDate && endDate) {
      query.visitDate = { $gte: new Date(startDate), $lte: new Date(endDate) };
    }

    const visitors = await Visitor.find(query).populate('userID', 'name email');
    res.status(200).json(visitors);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch visitors', error });
  }
};

// Get a specific visitor by ID
exports.getVisitorById = async (req, res) => {
  try {
    const visitor = await Visitor.findById(req.params.id).populate('userID', 'name email');
    if (!visitor) {
      return res.status(404).json({ message: 'Visitor not found' });
    }
    res.status(200).json(visitor);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch visitor', error });
  }
};

// Update a visitor's purpose for visiting
exports.updateVisitor = async (req, res) => {
  try {
    const visitor = await Visitor.findByIdAndUpdate(req.params.id, req.body, { new: true });
    if (!visitor) {
      return res.status(404).json({ message: 'Visitor not found' });
    }
    res.status(200).json(visitor);
  } catch (error) {
    res.status(500).json({ message: 'Failed to update visitor record', error });
  }
};

// Delete a visitor record
exports.deleteVisitor = async (req, res) => {
  try {
    const visitor = await Visitor.findByIdAndDelete(req.params.id);
    if (!visitor) {
      return res.status(404).json({ message: 'Visitor not found' });
    }
    res.status(200).json({ message: 'Visitor deleted successfully' });
  } catch (error) {
    res.status(500).json({ message: 'Failed to delete visitor record', error });
  }
};
