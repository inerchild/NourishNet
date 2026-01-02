import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getDiets } from '../services/api';

function DietSelection() {
  const [diets, setDiets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetchDiets();
  }, []);

  const fetchDiets = async () => {
    try {
      const response = await getDiets();
      setDiets(response.data);
      setLoading(false);
    } catch (err) {
      setError('Failed to load diets. Make sure the backend is running!');
      setLoading(false);
      console.error('Error fetching diets:', err);
    }
  };

  const handleDietClick = (dietId) => {
    navigate(`/cuisines/${dietId}`);
  };

  const getDietColor = (dietName) => {
    const colors = {
      'Vegetarian': 'bg-green-500 hover:bg-green-600',
      'Pescatarian': 'bg-blue-500 hover:bg-blue-600',
      'Keto': 'bg-purple-500 hover:bg-purple-600',
      'Alkaline': 'bg-yellow-500 hover:bg-yellow-600',
    };
    return colors[dietName] || 'bg-gray-500 hover:bg-gray-600';
  };

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <div className="text-2xl text-gray-600">Loading diets...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <div className="text-center">
          <div className="text-2xl text-red-600 mb-4">{error}</div>
          <button 
            onClick={fetchDiets}
            className="px-6 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
          >
            Try Again
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-green-50 to-blue-50 py-12 px-4">
      <div className="max-w-6xl mx-auto">
        <div className="text-center mb-12">
          <h1 className="text-5xl font-bold text-gray-800 mb-4">
            🥗 NourishNet
          </h1>
          <p className="text-xl text-gray-600">
            Choose your diet type to get started
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {diets.map((diet) => (
            <div
              key={diet.id}
              onClick={() => handleDietClick(diet.id)}
              className={`${getDietColor(diet.name)} text-white rounded-xl shadow-lg p-8 cursor-pointer transform transition-all duration-300 hover:scale-105 hover:shadow-2xl`}
            >
              <div className="text-center">
                <div className="text-5xl mb-4">
                  {diet.name === 'Vegetarian' && '🌱'}
                  {diet.name === 'Pescatarian' && '🐟'}
                  {diet.name === 'Keto' && '🥑'}
                  {diet.name === 'Alkaline' && '🍋'}
                </div>
                <h2 className="text-2xl font-bold mb-3">{diet.name}</h2>
                <p className="text-sm opacity-90">{diet.description}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default DietSelection;