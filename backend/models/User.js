const { v4: uuidv4 } = require('uuid');
const mongoose = require('mongoose');

const UserSchema = new mongoose.Schema({
  userID: { type: String, unique: true, default: uuidv4 },
  name: { type: String, required: true },
  email: { type: String, unique: true, required: true },
  phone: { type: Number },
  role: { type: String, enum: ['Staff', 'Volunteer', 'Visitor'], required: true }
}, { timestamps: true });

module.exports = mongoose.model('User', UserSchema);
