import React from "react";
import { Link } from "react-router-dom";

export default function VisitorDashboard() {
  return (
    <div className="page-wrapper">
      <h1>Visitor Dashboard</h1>
      
      <div className="coming-soon">
        <h2>Application Status</h2>
        <p>Coming soon! Your submitted adoption applications will be available soon. Stay tuned!</p>
        
        <p>In the meantime, feel free to:</p>
        
        <div className="dashboard-buttons">
          <Link to="/browse">
            <button className="button">Browse Animals</button>
          </Link>
          <Link to="/adopt">
            <button className="button" style={{ marginLeft: "10px" }}>Apply for Adoption</button>
          </Link>
        </div>
      </div>
    </div>
  );
}
