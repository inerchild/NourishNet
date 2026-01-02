import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { getCuisines, getDietById } from '../services/api';

function CuisineSelection() {
  const [cuisines, setCuisines] = useState([]);
  const [diet, setDiet] = useState(null);
  const [loading, setLoading] = useState(true);
  const { dietId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    fetchData();
  }, [dietId]);

  const fetchData = async () => {
    try {
      const [cuisinesRes, dietRes] = await Promise.all([
        getCuisines(),
        getDietById(dietId)
      ]);
      setCuisines(cuisinesRes.data);
      setDiet(dietRes.data);
      setLoading(false);
    } catch (err) {
      console.error('Error fetching data:', err);
      setLoading(false);
    }
  };

  const handleCuisineClick = (cuisineId) => {
    navigate(`/content/${dietId}/${cuisineId}`);
  };

  const handleBack = () => {
    navigate('/');
  };

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <div className="text-2xl text-gray-600">Loading cuisines...</div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-orange-50 to-pink-50 py-12 px-4">
      <div className="max-w-6xl mx-auto">
        <button
          onClick={handleBack}
          className="mb-6 px-6 py-2 bg-white text-gray-700 rounded-lg shadow hover:shadow-md transition-all flex items-center gap-2"
        >
          ← Back to Diets
        </button>

        <div className="text-center mb-12">
          <h1 className="text-5xl font-bold text-gray-800 mb-2">
            {diet?.name} Diet
          </h1>
          <p className="text-xl text-gray-600 mb-4">
            {diet?.description}
          </p>
          <p className="text-lg text-gray-500">
            Choose a cuisine to explore recipes
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          {cuisines.map((cuisine) => (
            <div
              key={cuisine.id}
              onClick={() => handleCuisineClick(cuisine.id)}
              className="bg-white rounded-xl shadow-lg p-8 cursor-pointer transform transition-all duration-300 hover:scale-105 hover:shadow-2xl border-2 border-transparent hover:border-orange-400"
            >
              <div className="text-center">
                <div className="text-5xl mb-4">
                  {cuisine.name === 'Indian' && '🇮🇳'}
                  {cuisine.name === 'Thai' && '🇹🇭'}
                  {cuisine.name === 'Japanese' && '🇯🇵'}
                  {cuisine.name === 'African' && '🌍'}
                </div>
                <h2 className="text-2xl font-bold text-gray-800 mb-3">
                  {cuisine.name}
                </h2>
                <p className="text-sm text-gray-600 mb-2">
                  {cuisine.description}
                </p>
                <p className="text-xs text-gray-500">
                  📍 {cuisine.region}
                </p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default CuisineSelection;