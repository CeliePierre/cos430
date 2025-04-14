const AdoptionApplication = require('../models/AdoptionApplication');
const Animal = require('../models/AnimalProfile');  

// Create a new adoption application
exports.createApplication = async (req, res) => {
  try {
    // Ensure the animal exists
    const animal = await Animal.findById(req.body.animalID);
    console.log(animal, ': this is animal id');
    if (!animal) {
      return res.status(404).json({ message: 'Animal not found' });
    }

    // Create the adoption application
    const application = new AdoptionApplication(req.body);
    await application.save();
    res.status(201).json(application);
  } catch (error) {
    res.status(500).json({ message: 'Failed to create adoption application', error });
  }
};

// Get all adoption applications (optional: filter by status or animal ID)
exports.getAllApplications = async (req, res) => {
  try {
    const { status, animalID } = req.query;
    const query = {};

    if (status) query.status = status;
    if (animalID) query.animalID = animalID;

    const applications = await AdoptionApplication.find(query)
      .populate('userID') 
    .populate('animalID', 'name species');
    res.status(200).json(applications);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch adoption applications', error });
  }
};

// Get a specific adoption application by ID
exports.getApplicationById = async (req, res) => {
  try {
    const application = await AdoptionApplication.findOne({ applicationID: req.params.applicationID})
    .populate('userID')
    .populate('animalID', 'name species');
    if (!application) {
      return res.status(404).json({ message: 'Adoption application not found' });
    }
    res.status(200).json(application);
  } catch (error) {
    res.status(500).json({ message: 'Failed to fetch adoption application', error });
  }
};

// Update the status of an adoption application
exports.updateApplicationStatus = async (req, res) => {
  try {
    const { status } = req.body;
    if (!['Pending', 'Approved', 'Rejected'].includes(status)) {
      return res.status(400).json({ message: 'Invalid status' });
    }

    const application = await AdoptionApplication.findByIdAndUpdate(req.params.id, { status }, { new: true });
    if (!application) {
      return res.status(404).json({ message: 'Adoption application not found' });
    }
    res.status(200).json(application);
  } catch (error) {
    res.status(500).json({ message: 'Failed to update adoption application', error });
  }
};

// Delete an adoption application
exports.deleteApplication = async (req, res) => {
  try {
    const application = await AdoptionApplication.findOneAndDelete({applicationID: req.params.application});
    if (!application) {
      return res.status(404).json({ message: 'Adoption application not found' });
    }
    res.status(200).json({ message: 'Adoption application deleted successfully' });
  } catch (error) {
    res.status(500).json({ message: 'Failed to delete adoption application', error });
  }
};

