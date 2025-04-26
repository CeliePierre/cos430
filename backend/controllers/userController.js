const User = require("../models/User");
const bcrypt = require("bcrypt");

// ✅ Get all users
const getUsers = async (req, res) => {
  try {
    const users = await User.find();
    res.json(users);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// ✅ Get a single user by ID
const getUserById = async (req, res) => {
  try {
    const user = await User.findById(req.params.id);
    if (!user) return res.status(404).json({ message: "User not found" });
    res.json(user);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// ✅ Create a new user
const createUser = async (req, res) => {
  try {
    const { name, email, password, phone, role } = req.body;

    // Hash the password before saving
    const hashedPassword = await bcrypt.hash(password, 10);

    const newUser = new User({
      name,
      email,
      phone,
      role,
      password: hashedPassword, // store hashed password
    });

    await newUser.save();

    res.status(201).json(newUser);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// ✅ Update an existing user
const updateUser = async (req, res) => {
  try {
    const updatedUser = await User.findOneAndUpdate(
      { userID: req.params.id },
      req.body,
      { new: true }
    );
    if (!updatedUser)
      return res.status(404).json({ message: "User not found" });
    res.json(updatedUser);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// ✅ Delete a user
const deleteUser = async (req, res) => {
  try {
    const deletedUser = await User.findOneAndDelete({ userID: req.params.id });
    if (!deletedUser)
      return res.status(404).json({ message: "User not found" });
    res.json({ message: "User deleted successfully" });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

module.exports = {
  getUsers,
  getUserById,
  createUser,
  updateUser,
  deleteUser,
};
