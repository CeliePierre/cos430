import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import "./App.css";
import HomePage from "./pages/HomePage";
import AdoptionApplication from "./pages/AdoptionApplication";
import BrowseAnimals from "./pages/BrowseAnimals";
import Layout from "./Layout";
import NotFound from "./pages/NotFoundPage";
import AnimalProfile from "./pages/AnimalProfile";
import VolunteerApplication from "./pages/VolunteerApplication";
import SignUp from "./pages/SignupPage";
import VisitorDashboard from "./pages/VisitorDashboard";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <Router>
      <Layout />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/adopt" element={<AdoptionApplication />} />
        <Route path="/browse" element={<BrowseAnimals />} />
        <Route path="/animalProfile/:id" element={<AnimalProfile />} />
        <Route path="/volunteer" element={<VolunteerApplication />} />
        <Route path="/signup" element={<SignUp />} />
        <Route
          path="/visitorDashboard"
          element={
            <ProtectedRoute>
              <VisitorDashboard />
            </ProtectedRoute>
          }
        />

        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
