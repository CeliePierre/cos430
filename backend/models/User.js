const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');

const userSchema = new mongoose.Schema({
    userID: { type: Number, required: true, unique: true }, // Fixed: Number instead of Int
    name: { type: String, required: true },
    email: { type: String, required: true, unique: true }, // Unique email
    phone: { type: String, required: true, unique: true }, // Unique phone
    role: { type: String, enum: ['worker', 'volunteer'], default: 'volunteer' }
}, { timestamps: true });

// set email and phone to be unique in MongoDB
userSchema.index({ email: 1, phone: 1 }, { unique: true });

const User = mongoose.model('User', userSchema);
module.exports = User;
