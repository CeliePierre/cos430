const express = require("express");
const router = express.Router();
const authController = require("../controllers/authController");
const auth = require("../middlewares/auth"); // your auth middleware

router.post("/signup", authController.signup); // no auth
router.post("/login", authController.login); // no auth
router.post("/logout", authController.logout); // no auth
router.get("/verify", auth, authController.verifyAuth); // ✅ only /verify has auth check

module.exports = router;
