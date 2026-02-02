import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getDiets } from '../services/api';

function DietSelection() {
  const [diets, setDiets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showViewAll, setShowViewAll] = useState(false);
  const [hoveredDiet, setHoveredDiet] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    fetchDiets();
    const hasSelected = localStorage.getItem('hasSelectedDiet');
    if (hasSelected) {
      setShowViewAll(true);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
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
    localStorage.setItem('hasSelectedDiet', 'true');
    setShowViewAll(true);
    navigate(`/cuisines/${dietId}`);
  };

  const getDietColor = (dietName) => {
    const colors = {
      'Vegetarian': 'from-green-400 to-green-600',
      'Pescatarian': 'from-blue-400 to-blue-600',
      'Keto': 'from-purple-400 to-purple-600',
      'Alkaline': 'from-yellow-400 to-yellow-600',
    };
    return colors[dietName] || 'from-gray-400 to-gray-600';
  };

  const getDietIcon = (dietName) => {
    const icons = {
      'Vegetarian': '🥗',
      'Pescatarian': '🐟',
      'Keto': '🥑',
      'Alkaline': '🍋',
    };
    return icons[dietName] || '🍽️';
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
          <p className="text-sm text-gray-500 mt-2">
            💡 Hover over cards for quick info
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {diets.map((diet) => (
            <div
              key={diet.id}
              onClick={() => handleDietClick(diet.id)}
              onMouseEnter={() => setHoveredDiet(diet.id)}
              onMouseLeave={() => setHoveredDiet(null)}
              className={`bg-gradient-to-br ${getDietColor(diet.name)} text-white rounded-xl shadow-lg p-8 cursor-pointer transform transition-all duration-300 hover:scale-105 hover:shadow-2xl relative overflow-hidden`}
            >
              {/* Normal Content */}
              <div className={`text-center transition-opacity duration-300 ${hoveredDiet === diet.id ? 'opacity-0' : 'opacity-100'}`}>
                <div className="text-6xl mb-4">{getDietIcon(diet.name)}</div>
                <h2 className="text-2xl font-bold mb-3">{diet.name}</h2>
                <p className="text-sm opacity-90">{diet.description}</p>
              </div>

              {/* Hover Content */}
              {hoveredDiet === diet.id && (
                <div className="absolute inset-0 flex items-center justify-center p-6 bg-black bg-opacity-95">
                  <div className="text-center text-white">
                    <div className="text-5xl mb-4">{getDietIcon(diet.name)}</div>
                    <h3 className="text-xl font-bold mb-3">{diet.name}</h3>
                    {diet.healthBenefits && (
                      <p className="text-xs mb-3 leading-relaxed">
                        <span className="text-green-400 font-bold">💚 Health Benefits:</span>
                        <br />
                        {diet.healthBenefits.substring(0, 120)}...
                      </p>
                    )}
                    <div className="mt-4 pt-4 border-t border-gray-600">
                      <p className="text-sm font-semibold text-yellow-300">
                        👉 Click to explore cuisines
                      </p>
                    </div>
                  </div>
                </div>
              )}
            </div>
          ))}
        </div>

        {showViewAll && (
          <div className="mt-12 text-center animate-fade-in">
            <div className="mb-4">
              <span className="text-sm text-gray-600 bg-white px-4 py-2 rounded-full shadow">
                ✨ You've unlocked the content library!
              </span>
            </div>
            <button
              onClick={() => navigate('/view-all')}
              className="px-8 py-4 bg-gradient-to-r from-purple-500 to-pink-500 text-white rounded-xl shadow-lg hover:shadow-2xl hover:from-purple-600 hover:to-pink-600 transform transition-all duration-300 hover:scale-105 text-lg font-semibold"
            >
              📚 View All Content Library (32 items)
            </button>
          </div>
        )}

        <div className="mt-12 text-center text-gray-600">
          <p className="text-sm">
            {showViewAll 
              ? "Select a diet card to explore cuisines, or view all content above"
              : "Select a diet card to explore cuisines and unlock the full content library"
            }
          </p>
        </div>
      </div>
    </div>
  );
}

export default DietSelection;