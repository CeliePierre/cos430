import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; // use Router and Route instead of createBrowserRouter

import './App.css';
import HomePage from './pages/HomePage';
import AdoptionApplication from './pages/AdoptionApplication';
import BrowseAnimals from './pages/BrowseAnimals';
import Layout from './Layout';
import NotFound from './pages/NotFoundPage';
import AnimalProfile from './pages/AnimalProfile';

function App() {
  return (
    <Router>
      <Layout />
      <Routes>
        {/* Define Routes */}
        <Route path="/" element={<HomePage />} />
        <Route path="/adopt" element={<AdoptionApplication />} />
        <Route path="/browse" element={<BrowseAnimals />} />
        <Route path="/animalProfile/:id" element={<AnimalProfile />} />
        
        {/* Catch-all route for 404 */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
