const express = require("express");
const router = express.Router();
const auth = require("../middleware/auth");
const dashboardController = require("../controllers/dashboardController");

router.get("/staff-dashboard", auth, dashboardController.staffDashboard);
router.get("/visitor-dashboard", auth, dashboardController.visitorDashboard);

module.exports = router;
