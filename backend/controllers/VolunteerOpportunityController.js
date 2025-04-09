const VolunteerOpportunity = require('../models/VolunteerOpportunity');

// Create a new volunteer opportunity
exports.createOpportunity = async (req, res) => {
  try {
    const opportunity = new VolunteerOpportunity(req.body);
    await opportunity.save();
    res.status(201).json(opportunity);
  } catch (error) {
    res.status(500).json({ message: 'Failed to create volunteer opportunity', error });
  }
};

// Get all volunteer opportunities (can filter by location, date, or title)
exports.getAllOpportunities = async (req, res) => {
  try {
    const { location, date, title } = req.query;
    const query = {};

    if (location) query.location = location;
    if (date) query.date = { $gte: new Date(date) };
    if (title) query.title = new RegExp(title, 'i');

    const opportunities = await VolunteerOpportunity.find(query);
    res.status(200).json(opportunities);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch volunteer opportunities', error });
  }
};

// Get a specific volunteer opportunity by ID
exports.getOpportunityById = async (req, res) => {
  try {
    const opportunity = await VolunteerOpportunity.findById(req.params.id);
    if (!opportunity) {
      return res.status(404).json({ message: 'Opportunity not found' });
    }
    res.status(200).json(opportunity);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch volunteer opportunity', error });
  }
};

// Update a volunteer opportunity (example: change description, location, or volunteers needed)
exports.updateOpportunity = async (req, res) => {
  try {
    const opportunity = await VolunteerOpportunity.findByIdAndUpdate(req.params.id, req.body, { new: true });
    if (!opportunity) {
      return res.status(404).json({ message: 'Opportunity not found' });
    }
    res.status(200).json(opportunity);
  } catch (error) {
    res.status(500).json({ message: 'Failed to update volunteer opportunity', error });
  }
};

// Delete a volunteer opportunity
exports.deleteOpportunity = async (req, res) => {
  try {
    const opportunity = await VolunteerOpportunity.findByIdAndDelete(req.params.id);
    if (!opportunity) {
      return res.status(404).json({ message: 'Opportunity not found' });
    }
    res.status(200).json({ message: 'Opportunity deleted successfully' });
  } catch (error) {
    res.status(500).json({ message: 'Failed to delete volunteer opportunity', error });
  }
};
