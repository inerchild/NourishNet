import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { getContent, getDietById, getCuisineById } from '../services/api';

function ContentDisplay() {
  const [content, setContent] = useState([]);
  const [diet, setDiet] = useState(null);
  const [cuisine, setCuisine] = useState(null);
  const [loading, setLoading] = useState(true);
  const { dietId, cuisineId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    fetchData();
  }, [dietId, cuisineId]);

  const fetchData = async () => {
    try {
      const [contentRes, dietRes, cuisineRes] = await Promise.all([
        getContent(dietId, cuisineId),
        getDietById(dietId),
        getCuisineById(cuisineId)
      ]);
      setContent(contentRes.data);
      setDiet(dietRes.data);
      setCuisine(cuisineRes.data);
      setLoading(false);
    } catch (err) {
      console.error('Error fetching content:', err);
      setLoading(false);
    }
  };

  const handleBack = () => {
    navigate(`/cuisines/${dietId}`);
  };

  const renderContent = (item) => {
    switch (item.contentType) {
      case 'IMAGE':
        return (
          <div className="bg-white rounded-xl shadow-lg overflow-hidden">
            <div className="h-64 bg-gradient-to-br from-green-100 to-green-200 flex items-center justify-center">
              <div className="text-6xl">🖼️</div>
            </div>
            <div className="p-6">
              <h3 className="text-2xl font-bold text-gray-800 mb-2">
                {item.title}
              </h3>
              <p className="text-gray-600">{item.description}</p>
            </div>
          </div>
        );

      case 'VIDEO':
        return (
          <div className="bg-white rounded-xl shadow-lg overflow-hidden">
            <div className="h-64 bg-gradient-to-br from-red-100 to-red-200 flex items-center justify-center">
              <div className="text-6xl">🎥</div>
            </div>
            <div className="p-6">
              <h3 className="text-2xl font-bold text-gray-800 mb-2">
                {item.title}
              </h3>
              <p className="text-gray-600">{item.description}</p>
            </div>
          </div>
        );

      case 'RECIPE':
        return (
          <div className="bg-white rounded-xl shadow-lg overflow-hidden">
            <div className="h-64 bg-gradient-to-br from-yellow-100 to-yellow-200 flex items-center justify-center">
              <div className="text-6xl">📖</div>
            </div>
            <div className="p-6">
              <h3 className="text-2xl font-bold text-gray-800 mb-2">
                {item.title}
              </h3>
              <p className="text-gray-600">{item.description}</p>
            </div>
          </div>
        );

      case 'INGREDIENT_LIST':
        return (
          <div className="bg-white rounded-xl shadow-lg overflow-hidden">
            <div className="h-64 bg-gradient-to-br from-purple-100 to-purple-200 flex items-center justify-center">
              <div className="text-6xl">🛒</div>
            </div>
            <div className="p-6">
              <h3 className="text-2xl font-bold text-gray-800 mb-2">
                {item.title}
              </h3>
              <p className="text-gray-600">{item.description}</p>
            </div>
          </div>
        );

      default:
        return null;
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <div className="text-2xl text-gray-600">Loading content...</div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 to-purple-50 py-12 px-4">
      <div className="max-w-6xl mx-auto">
        <button
          onClick={handleBack}
          className="mb-6 px-6 py-2 bg-white text-gray-700 rounded-lg shadow hover:shadow-md transition-all flex items-center gap-2"
        >
          ← Back to Cuisines
        </button>

        <div className="text-center mb-12">
          <h1 className="text-5xl font-bold text-gray-800 mb-2">
            {diet?.name} × {cuisine?.name}
          </h1>
          <p className="text-xl text-gray-600">
            Explore your personalized content
          </p>
        </div>

        {content.length === 0 ? (
          <div className="text-center text-gray-600 text-xl">
            No content available for this combination yet.
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
            {content.map((item) => (
              <div key={item.id}>
                {renderContent(item)}
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default ContentDisplay;