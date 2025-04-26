const jwt = require("jsonwebtoken");

const auth = (req, res, next) => {
  const token = req.cookies.token; // ✅ read from cookie

  if (!token) {
    return res.status(401).json({ message: "Access denied. No token." });
  }

  try {
    const decoded = jwt.verify(token, process.env.JWT_SECRET);
    req.user = decoded;
    next();
  } catch {
    res.status(400).json({ message: "Invalid token" });
  }
};

module.exports = auth;
