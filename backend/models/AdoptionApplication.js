const { v4: uuidv4 } = require('uuid');
const mongoose = require('mongoose');

// Schema for adoption applications submitted by users
const AdoptionApplicationSchema = new mongoose.Schema({
  
  // Unique application ID
  applicationID: { type: String, unique: true, default: uuidv4 },

  // Name and email of the applicant
  userID: { type: mongoose.Schema.Types.ObjectId, ref: 'User', required: true },
  // Reference to the animal being adopted
  animalID: { type: mongoose.Schema.Types.ObjectId, ref: 'Animal', required: true },

  // Status of the application (Pending, Approved, or Rejected)
  status: { type: String, enum: ['Pending', 'Approved', 'Rejected'], default: 'Pending' },

  // Submission timestamp
  submittedAt: { type: Date, default: Date.now },

  // Optional notes from the applicant
  notes: { type: String }
}, { timestamps: true });

module.exports = mongoose.model('AdoptionApplication', AdoptionApplicationSchema);
