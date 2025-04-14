const express = require("express");
const router = express.Router();
const { createApplication, getAllApplications, getApplicationById, updateApplicationStatus, deleteApplication  } = require("../controllers/AdoptionApplicationController");

// Route to get all application
router.get("/", getAllApplications);
// Route to get an application by ID
router.get("/:applicationID", getApplicationById);

// Route to create a new application
router.post("/", createApplication);

router.put("/:applicationID", updateApplicationStatus);

// Route to delete an application      
router.delete("/:applicationID", deleteApplication);

module.exports = router;
