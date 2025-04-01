const { v4: uuidv4 } = require('uuid');
const mongoose = require('mongoose');

// Schema for volunteer opportunities or events
const VolunteerOpportunitySchema = new mongoose.Schema({
  // Unique opportunity ID
  opportunityID: { type: String, unique: true, default: uuidv4 },

  // Title of the opportunity (e.g., "Dog Walking Event")
  title: { type: String, required: true },

  // Description of duties or purpose
  description: { type: String },

  // Where the opportunity is located
  location: { type: String },

  // Date of the event or activity
  date: { type: Date, required: true },

  // Time of day (optional)
  time: { type: String },

  // Number of volunteers needed
  volunteersNeeded: { type: Number, default: 1 }
}, { timestamps: true });

module.exports = mongoose.model('VolunteerOpportunity', VolunteerOpportunitySchema);
