require('dotenv').config();  // Ensure the .env file is loaded
const mongoose = require('mongoose');
const User = require('../models/User');
const Animal = require('../models/AnimalProfile');

// Manually create 10 users and 10 animals here

const users = [
  { name: 'John Doe', email: 'john.doe@example.com', phone: 1234567890, role: 'Staff' },
  { name: 'Jane Smith', email: 'jane.smith@example.com', phone: 9876543210, role: 'Volunteer' },
  { name: 'Michael Johnson', email: 'michael.johnson@example.com', phone: 1122334455, role: 'Visitor' },
  { name: 'Emily Davis', email: 'emily.davis@example.com', phone: 9988776655, role: 'Staff' },
  { name: 'Chris Brown', email: 'chris.brown@example.com', phone: 1231231234, role: 'Volunteer' },
  { name: 'Patricia Williams', email: 'patricia.williams@example.com', phone: 5675675678, role: 'Visitor' },
  { name: 'Daniel Lee', email: 'daniel.lee@example.com', phone: 5554443333, role: 'Staff' },
  { name: 'Olivia Martinez', email: 'olivia.martinez@example.com', phone: 4445556666, role: 'Volunteer' },
  { name: 'David Wilson', email: 'david.wilson@example.com', phone: 7778889999, role: 'Visitor' },
  { name: 'Sophia Clark', email: 'sophia.clark@example.com', phone: 3332221111, role: 'Volunteer' },
];

const animals = [
  { animalID: 1, name: 'Bella', species: 'Dog', breed: 'Labrador', age: 4, sex: 'Female', medicalRecords: 'Healthy, up-to-date on vaccinations', photo: 'https://placekitten.com/200/200' },
  { animalID: 2, name: 'Max', species: 'Dog', breed: 'Bulldog', age: 5, sex: 'Male', medicalRecords: 'Needs dental cleaning', photo: 'https://placekitten.com/200/200' },
  { animalID: 3, name: 'Luna', species: 'Cat', breed: 'Persian', age: 2, sex: 'Female', medicalRecords: 'Healthy, no medical issues', photo: 'https://placekitten.com/200/200' },
  { animalID: 4, name: 'Charlie', species: 'Dog', breed: 'Beagle', age: 3, sex: 'Male', medicalRecords: 'Neutered, no health concerns', photo: 'https://placekitten.com/200/200' },
  { animalID: 5, name: 'Molly', species: 'Cat', breed: 'Siamese', age: 1, sex: 'Female', medicalRecords: 'Up-to-date on vaccinations', photo: 'https://placekitten.com/200/200' },
  { animalID: 6, name: 'Buddy', species: 'Dog', breed: 'Golden Retriever', age: 7, sex: 'Male', medicalRecords: 'Healthy, active', photo: 'https://placekitten.com/200/200' },
  { animalID: 7, name: 'Rocky', species: 'Cat', breed: 'Maine Coon', age: 4, sex: 'Male', medicalRecords: 'Healthy, no special needs', photo: 'https://placekitten.com/200/200' },
  { animalID: 8, name: 'Daisy', species: 'Rabbit', breed: 'Himalayan', age: 2, sex: 'Female', medicalRecords: 'Healthy, spayed', photo: 'https://placekitten.com/200/200' },
  { animalID: 9, name: 'Oliver', species: 'Bird', breed: 'Parakeet', age: 1, sex: 'Male', medicalRecords: 'Healthy, no issues', photo: 'https://placekitten.com/200/200' },
  { animalID: 10, name: 'Chloe', species: 'Cat', breed: 'Bengal', age: 3, sex: 'Female', medicalRecords: 'Vaccinations up to date', photo: 'https://placekitten.com/200/200' },
];

// Function to seed database with mock data
const seedDatabase = async () => {
  try {
    console.log("MongoDB URI:", process.env.MONGODB_URI);  // Debugging step

    // Connect to MongoDB
    await mongoose.connect(process.env.MONGODB_URI, { useNewUrlParser: true, useUnifiedTopology: true });
    console.log('Connected to MongoDB');

    // Insert users and animals
    await User.insertMany(users);
    console.log('Mock users added');

    await Animal.insertMany(animals);
    console.log('Mock animals added');

    // Close the connection
    mongoose.connection.close();
  } catch (error) {
    console.error('Error seeding database:', error);
  }
};

seedDatabase();
