const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const User = require("../models/User");

// Signup Controller
const signup = async (req, res) => {
  const { name, email, phone, password, role } = req.body;

  try {
    const existingUser = await User.findOne({ email });
    if (existingUser)
      return res.status(400).json({ message: "Email already exists" });

    const hashedPassword = await bcrypt.hash(password, 10);

    const user = await User.create({
      name,
      email,
      phone,
      role,
      password: hashedPassword,
    });

    const token = jwt.sign(
      { userID: user.userID, role: user.role },
      process.env.JWT_SECRET,
      { expiresIn: "1d" }
    );

    res.cookie("token", token, {
      httpOnly: true,
      secure: false, // make this secure to false for local testing
      // secure: process.env.NODE_ENV === "production",
      sameSite: "Lax",
      maxAge: 24 * 60 * 60 * 1000,
    });

    res.status(201).json({ message: "Signup successful" });
  } catch (err) {
    console.log(err, ":this is err");
    if (err.name === "ValidationError") {
      return res.status(400).json({ message: err.message });
    }
    res.status(500).json({ message: "Server error" });
  }
};

// Login Controller
const login = async (req, res) => {
  const { email, password } = req.body;
  console.log(email, password, ": this is email and password");
  try {
    const user = await User.findOne({ email });
    if (!user) return res.status(400).json({ message: "Invalid credentials" });

    const isMatch = await bcrypt.compare(password, user.password);
    if (!isMatch)
      return res.status(400).json({ message: "Invalid credentials" });

    const token = jwt.sign(
      { id: user._id, role: user.role },
      process.env.JWT_SECRET,
      { expiresIn: "1d" }
    );
    res.json({ token });
  } catch (err) {
    console.log(err, ": this is err");
    res.status(500).json({ message: "Server error" });
  }
};

// Logout Controller
const logout = (req, res) => {
  res.clearCookie("token", {
    httpOnly: true,
    secure: process.env.NODE_ENV === "production",
    sameSite: "Lax",
  });

  res.status(200).json({ message: "Logged out successfully" });
};

module.exports = {
  signup,
  login,
  logout,
};

module.exports = {
  signup,
  login,
  logout,
};
