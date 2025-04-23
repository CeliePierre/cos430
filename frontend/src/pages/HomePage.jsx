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

  // TODO: Fix type redirect

  return (
    <div className="page-wrapper">
      <h1>🐾 Welcome 🐾</h1>
      <p style={{ marginBottom: '2rem' }}>
        Bringing loving animals and caring humans together.
      </p>

      <div style={{ display: 'flex', flexWrap: 'wrap', gap: '1.5rem', justifyContent: 'center' }}>
        <Link to="/browse?type=dog">
          <button>🐶 Adopt a Dog</button>
        </Link>
        <Link to="/browse?type=cat">
          <button>🐱 Adopt a Cat</button>
        </Link>
        <Link to="/browse">
          <button>🔎 Browse All Animals</button>
        </Link>
      </div>

      <div style={{ marginTop: '3rem' }}>
        <h2>✨ Featured Pet</h2>
        {featuredPet ? (
          <div style={{
            marginTop: '1rem',
            backgroundColor: 'var(--card-bg)',
            padding: '1.5rem',
            borderRadius: 'var(--radius)',
            boxShadow: 'var(--shadow)',
            maxWidth: '400px',
            marginInline: 'auto'
          }}>
            <img
              src={featuredPet.photo}
              alt={featuredPet.name}
              style={{
                width: '100%',
                borderRadius: 'var(--radius)',
                marginBottom: '1rem',
                objectFit: 'cover',
                height: '200px'
              }}
            />
            <h3>{featuredPet.name}</h3>
            <p>{featuredPet.species} • {featuredPet.breed}</p>
            <Link to={`/animalProfile/${featuredPet.animalID}`}>
              <button style={{ marginTop: '1rem' }}>Meet {featuredPet.name}</button>
            </Link>
          </div>
        ) : (
          <p>Loading featured pet...</p>
        )}
      </div>

      <div style={{ marginTop: '3rem' }}>
        <h2>🐾 Get Involved</h2>
        <p>Help us care for animals in need. Volunteer, foster, or donate today!</p>
        <Link to="/volunteer">
          <button>🙋‍♀️ Volunteer</button>
        </Link>
      </div>

      <div style={{ marginTop: '2rem' }}>
        <h2>📄 Ready to Adopt?</h2>
        <p>Fill out our quick application form to start the process.</p>
        <Link to="/adopt">
          <button>Apply for Adoption</button>
        </Link>
      </div>
    </div>
  );
}
