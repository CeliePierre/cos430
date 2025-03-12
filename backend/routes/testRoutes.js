const express = require('express');
const mongoose = require('mongoose');

const router = express.Router();

// Test Route  with GET request to Check MongoDB Connection. Can be delete when all routes set up.
router.get('/test-db', async (req, res) => {
    try {
        const dbStatus = mongoose.connection.readyState; // 1 = Connected
        if (dbStatus === 1) {
            return res.status(200).json({ success: true, message: 'MongoDB is connected!' });
        } else {
            return res.status(500).json({ success: false, message: 'MongoDB is not connected.' });
        }
    } catch (error) {
        return res.status(500).json({ success: false, error: error.message });
    }
});

module.exports = router;
