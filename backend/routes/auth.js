const express = require("express");
const router = express.Router();
const authController = require("../controllers/authController");
const auth = require("../middlewares/auth");

router.post("/signup", authController.signup);
router.post("/login", authController.login);
router.post("/logout", authController.logout);
router.get("/verify", auth, (req, res) => {
  res.json({ message: "Authorized", role: req.user.role });
});

module.exports = router;
