console.log("MongoDB URI:", process.env.MONGODB_URI);
require("dotenv").config();

const express = require("express");
const cors = require("cors");
const connectDB = require("./config/db"); // Import database connection function

const app = express();
// Allow only React Frontend origin for now
app.use(cors({ origin: "http://localhost:3000" }));
app.use(cors());
app.use(express.json()); // Middleware to parse JSON

// Connect to MongoDB
connectDB();

// Define the model once
const mongoose = require("mongoose");
const TestSchema = new mongoose.Schema({ name: String });
const TestModel = mongoose.models.Test || mongoose.model("Test", TestSchema);

// register auth and roleCheck middlewares
const auth = require("./middlewares/auth");
const roleCheck = require("./middlewares/roleCheck");

app.get("/staff-dashboard", auth, roleCheck(["staff"]), (req, res) => {
  res.send("Welcome, Staff!");
});
app.get("/visitor-dashboard", auth, roleCheck(["visitor"]), (req, res) => {
  res.send("Welcome, Visitor!");
});

//Register auth routes
const authRoutes = require("./routes/auth");
app.use("/auth", authRoutes);

// Register the user routes
const userRoutes = require("./routes/userRoutes");
app.use("/users", userRoutes);

// Register the animal routes
const animalProfileRoutes = require("./routes/AnimalProfileRoutes");
app.use("/animals", animalProfileRoutes);

const applicationRoutes = require("./routes/AdoptionApplicationRoutes");
app.use("/applications", applicationRoutes);

// Test MongoDB connection
app.get("/test-db", async (req, res) => {
  try {
    const testData = new TestModel({ name: "Test Data" });
    await testData.save();
    res.send({ message: "Database is connected and test data inserted!" });
  } catch (error) {
    res
      .status(500)
      .send({ error: "Database connection failed!", details: error.message });
  }
});

// Start Server
const PORT = process.env.PORT || 5001;
app.listen(PORT, () => console.log(`🚀 Server running on port ${PORT}`));
