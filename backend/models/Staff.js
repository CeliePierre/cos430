const mongoose = require('mongoose');

// Schema for staff members at the shelter
const StaffSchema = new mongoose.Schema({
  // Link to the base User record
  userID: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },

  // Position or role title (e.g., "Veterinarian", "Caretaker")
  position: { type: String, required: true },

  // Date the staff member started
  startDate: { type: Date, default: Date.now },

  // Whether the staff member is currently active
  isActive: { type: Boolean, default: true }
}, { timestamps: true });

module.exports = mongoose.model('Staff', StaffSchema);
