import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

export default function HomePage() {
  const [featuredPet, setFeaturedPet] = useState(null);

  useEffect(() => {
    fetch('http://localhost:5001/animals')
      .then(res => res.json())
      .then(data => {
        if (data.length > 0) {
          const randomIndex = Math.floor(Math.random() * data.length);
          setFeaturedPet(data[randomIndex]);
        }
      })
      .catch(err => console.error('Error fetching animals:', err));
  }, []);

  return (
    <div className="page-wrapper">
      <h1>🐾 Welcome 🐾</h1>
      <p style={{ marginBottom: '2rem' }}>
        Bringing loving animals and caring humans together.
      </p>

      <div
        style={{
          display: 'flex',
          gap: '2rem',
          marginTop: '2rem',
          justifyContent: 'center',
          flexWrap: 'wrap',
        }}
      >
        {/* Left: Info Panels */}
        <div style={{ width: '450px' }}>
          <div style={{ marginBottom: '2rem' }}>
            <h2>🔎 Browse</h2>
            <p>Find your new best friend!</p>
            <Link to="/browse">
              <button>Browse All Animals</button>
            </Link>
          </div>

          <div style={{ marginBottom: '2rem' }}>
            <h2>🐾 Get Involved</h2>
            <p>
              Help us care for animals in need. <br />
              Volunteer, foster, or donate today!
            </p>
            <Link to="/volunteer">
              <button>🙋‍♀️ Volunteer</button>
            </Link>
          </div>

          <div>
            <h2>📄 Ready to Adopt?</h2>
            <p>Fill out our quick application form to start the process.</p>
            <Link to="/adopt">
              <button>Apply for Adoption</button>
            </Link>
          </div>
        </div>

        {/* Right: Featured Pet */}
        <div style={{ width: '350px' }}>
          <h2>✨ Featured Pet</h2>
          {featuredPet ? (
            <div
              style={{
                marginTop: '1rem',
                backgroundColor: 'var(--card-bg)',
                padding: '1.5rem',
                borderRadius: 'var(--radius)',
                boxShadow: 'var(--shadow)',
              }}
            >
              <img
                src={featuredPet.photo}
                alt={featuredPet.name}
                style={{
                  width: '100%',
                  borderRadius: 'var(--radius)',
                  marginBottom: '1rem',
                  objectFit: 'cover',
                  height: '350px',
                }}
              />
              <h3>{featuredPet.name}</h3>
              <p>{featuredPet.species} • {featuredPet.breed}</p>
              <Link to={`/animalProfile/${featuredPet._id}`}>
                <button style={{ marginTop: '1rem' }}>
                  Meet {featuredPet.name}
                </button>
              </Link>
            </div>
          ) : (
            <p>Loading featured pet...</p>
          )}
        </div>
      </div>
    </div>
  );
}
