const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const animalSchema = new Schema({
  animalID: {type: Number },
  name: { type: String, required: true },
  species: {type: String },
  breed: { type: String, required: true },
  age: { type: Number, required: true },
  sex: {type: String },
  intakeDate: { type: Date, default: Date.now },
  medicalRecords: { type: String },
  photo: { type: String },
});

module.exports = mongoose.model('Animal', animalSchema);
