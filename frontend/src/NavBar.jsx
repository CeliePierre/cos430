import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { Home } from 'lucide-react';
import './App.css';

export default function NavBar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  const handleAuthClick = () => {
    if (isLoggedIn) {
      setIsLoggedIn(false);
      navigate('/');
    } else {
      navigate('/login');
    }
  };

  return (
    <nav className="navbar">
      <h2>🐾 Animal Shelter Management System</h2>
      <div className="nav-links">
        <Link to="/" className="home-icon" title="Home">
          <Home size={20} />
        </Link>
        <Link to="/browse">Browse Animals</Link>
        <Link to="/adopt">Apply for Adoption</Link>
        <Link to="/volunteer">Volunteer</Link>
        <button className="auth-button" onClick={handleAuthClick}>
          {isLoggedIn ? 'Logout' : 'Login'}
        </button>
      </div>
    </nav>
  );
}
