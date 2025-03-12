require("dotenv").config();
const express = require("express");
const mongoose = require("mongoose");

const app = express();
app.use(express.json()); // Middleware to parse JSON

// Connect to MongoDB
mongoose
  .connect(process.env.MONGO_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  })
  .then(() => console.log("MongoDB Connected"))
  .catch((err) => console.error("MongoDB connection error:", err));

// Define the model once
const TestSchema = new mongoose.Schema({ name: String });
const TestModel = mongoose.models.Test || mongoose.model("Test", TestSchema);

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
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
