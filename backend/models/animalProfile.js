const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const animalSchema = new Schema({
  name: { type: String, required: true },
  breed: { type: String, required: true },
  age: { type: Number, required: true },
  image: { type: String },
  dateAdded: { type: Date, default: Date.now },
  medicalHistory: { type: String },
});

module.exports = mongoose.model('Animal', animalSchema);
