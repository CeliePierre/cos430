const mongoose = require('mongoose');

// Schema for visitors to the shelter
const VisitorSchema = new mongoose.Schema({
  // Link to the base User record
  userID: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },

  // Date the visitor came in
  visitDate: { type: Date, default: Date.now },

  // Purpose of the visit (e.g., "Tour", "Meet animal")
  purpose: { type: String }
}, { timestamps: true });

module.exports = mongoose.model('Visitor', VisitorSchema);
