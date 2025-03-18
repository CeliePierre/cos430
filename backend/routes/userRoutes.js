const express = require('express');
const { getUsers, getUserById, createUser, updateUser, deleteUser } = require('../controllers/userController');

const router = express.Router();

router.get('/', getUsers);              // - Fetch all users
router.get('/:id', getUserById);        // - Fetch a user by ID
router.post('/', createUser);           // - Create a new user
router.put('/:id', updateUser);         // - Update user details
router.delete('/:id', deleteUser);      // - Remove a user

module.exports = router;
