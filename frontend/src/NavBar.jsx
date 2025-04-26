import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { checkAuth, logoutUser } from "../src/services/api"; // adjust path if needed
import "./App.css";

export default function NavBar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userRole, setUserRole] = useState(""); // ✅ New role state
  const navigate = useNavigate();

  useEffect(() => {
    const verifyLogin = async () => {
      const user = await checkAuth();
      if (user) {
        setIsLoggedIn(true);
        setUserRole(user.role); // ✅ Set role
      } else {
        setIsLoggedIn(false);
        setUserRole("");
      }
    };
    verifyLogin();
  }, []);

  const handleLogout = async () => {
    try {
      await logoutUser();
      setIsLoggedIn(false);
      setUserRole("");
      navigate("/");
    } catch (error) {
      console.log("Logout error:", error);
    }
  };

  return (
    <nav className="navbar">
      <Link to="/">
        <img src="/ASMS.png" alt="Animal Shelter Management System Logo" />
      </Link>
      <div className="nav-links">
        <Link to="/" className="home-icon" title="Home">
          Home
        </Link>
        <Link to="/browse">Browse Animals</Link>
        <Link to="/adopt">Apply for Adoption</Link>
        <Link to="/volunteer">Volunteer</Link>

        {/* ✅ Show Dashboard link only when logged in */}
        {isLoggedIn && (
          <Link
            to={userRole === "Staff" ? "/staffDashboard" : "/visitorDashboard"}
          >
            Dashboard
          </Link>
        )}

        {!isLoggedIn ? (
          <>
            <button
              onClick={() => navigate("/signup")}
              className="btn-signup-outline"
            >
              Sign Up
            </button>
            <button onClick={() => navigate("/login")} className="auth-button">
              Login
            </button>
          </>
        ) : (
          <button onClick={handleLogout} className="auth-button">
            Logout
          </button>
        )}
      </div>
    </nav>
  );
}
