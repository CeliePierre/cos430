import React, { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";
import { checkAuth } from "../services/api"; // you already have this!

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

  return authorized ? children : <Navigate to="/" replace />;
};

export default ProtectedRoute;
