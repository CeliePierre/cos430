import React, { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { checkAuth } from "../services/api"; // adjust if needed

const ProtectedRoute = ({ children }) => {
  const [loading, setLoading] = useState(true);
  const [authorized, setAuthorized] = useState(false);

  useEffect(() => {
    const verify = async () => {
      const result = await checkAuth();
      setAuthorized(!!result);
      setLoading(false);
    };
    verify();
  }, []);

  if (loading) return <p>Loading...</p>;

  // 🔥 Redirect to /login if not authorized
  return authorized ? children : <Navigate to="/login" replace />;
};

export default ProtectedRoute;
