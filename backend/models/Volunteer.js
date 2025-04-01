const mongoose = require('mongoose');

// Schema for volunteers at the shelter
const VolunteerSchema = new mongoose.Schema({
  // Link to the base User record
  userID: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },

  // Date they started volunteering
  startDate: { type: Date, default: Date.now },

  // List of skills the volunteer brings (e.g., "Grooming", "Event Planning")
  skills: [{ type: String }],

  // General availability (e.g., "Weekends", "Weekdays after 3PM")
  availability: { type: String }
}, { timestamps: true });

module.exports = mongoose.model('Volunteer', VolunteerSchema);

