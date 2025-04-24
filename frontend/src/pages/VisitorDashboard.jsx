import React from "react";

export default function VisitorDashboard() {
  return (
    <div
      style={{
        minHeight: "100vh",
        padding: "2rem",
        backgroundColor: "#f0f9ff",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <h1 style={{ fontSize: "2rem", color: "#1f2937" }}>
        🐾 Welcome to the Visitor Dashboard! 🐾
      </h1>
      <p style={{ fontSize: "1rem", color: "#4b5563", marginTop: "1rem" }}>
        You’ve successfully signed up and landed on the visitor page.
      </p>
    </div>
  );
}
