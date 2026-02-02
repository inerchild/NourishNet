import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function ViewAllContent() {
  const [content, setContent] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [filterType, setFilterType] = useState('ALL');
  const [searchTerm, setSearchTerm] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    fetchAllContent();
  }, []);

  const fetchAllContent = async () => {
    try {
      setLoading(true);
      const response = await axios.get('http://localhost:8080/api/content');
      setContent(response.data);
      setLoading(false);
    } catch (err) {
      console.error('Error fetching content:', err);
      setError('Failed to load content');
      setLoading(false);
    }
  };

  const handleSearch = (term) => {
    setSearchTerm(term);
  };

  const filteredContent = content.filter(item => {
    const matchesType = filterType === 'ALL' || item.contentType === filterType;
    const matchesSearch = searchTerm === '' || 
      item.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      item.description.toLowerCase().includes(searchTerm.toLowerCase());
    return matchesType && matchesSearch;
  });

  const getCardColor = (type) => {
    const colors = {
      'IMAGE': 'from-green-100 to-green-200',
      'VIDEO': 'from-red-100 to-red-200',
      'RECIPE': 'from-yellow-100 to-yellow-200',
      'INGREDIENT_LIST': 'from-purple-100 to-purple-200',
    };
    return colors[type] || 'from-gray-100 to-gray-200';
  };

  const getIcon = (type) => {
    const icons = {
      'IMAGE': '🖼️',
      'VIDEO': '🎥',
      'RECIPE': '📖',
      'INGREDIENT_LIST': '🛒',
    };
    return icons[type] || '📄';
  };

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <div className="text-center">
          <div className="text-6xl mb-4">🔄</div>
          <div className="text-2xl text-gray-600">Loading all content...</div>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50">
        <div className="text-center">
          <div className="text-2xl text-red-600 mb-4">{error}</div>
          <button 
            onClick={fetchAllContent}
            className="px-6 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
          >
            Try Again
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-purple-50 to-pink-50 py-12 px-4">
      <div className="max-w-7xl mx-auto">
        {/* Header with Navigation */}
        <div className="flex justify-between items-center mb-8">
          <button
            onClick={() => navigate('/')}
            className="px-6 py-2 bg-white text-gray-700 rounded-lg shadow hover:shadow-md transition-all flex items-center gap-2"
          >
            ← Back to Home
          </button>
          <button
            onClick={() => navigate('/')}
            className="px-6 py-2 bg-green-500 text-white rounded-lg shadow hover:shadow-md hover:bg-green-600 transition-all flex items-center gap-2"
          >
            🏠 Home
          </button>
        </div>

        {/* Title */}
        <div className="text-center mb-8">
          <h1 className="text-5xl font-bold text-gray-800 mb-2">
            📚 All Content
          </h1>
          <p className="text-xl text-gray-600">
            Browse all {content.length} items in our collection
          </p>
        </div>

        {/* Search Bar */}
        <div className="max-w-2xl mx-auto mb-8">
          <div className="relative">
            <input
              type="text"
              placeholder="🔍 Search by title or description..."
              value={searchTerm}
              onChange={(e) => handleSearch(e.target.value)}
              className="w-full px-6 py-4 text-lg rounded-xl border-2 border-gray-300 focus:border-purple-500 focus:outline-none shadow-lg"
            />
            {searchTerm && (
              <button
                onClick={() => handleSearch('')}
                className="absolute right-4 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-gray-600"
              >
                ✕
              </button>
            )}
          </div>
          {searchTerm && (
            <p className="text-sm text-gray-600 mt-2">
              Found {filteredContent.length} result{filteredContent.length !== 1 ? 's' : ''} for "{searchTerm}"
            </p>
          )}
        </div>

        {/* Filter Buttons */}
        <div className="flex flex-wrap justify-center gap-3 mb-8">
          <button
            onClick={() => setFilterType('ALL')}
            className={`px-6 py-2 rounded-lg font-semibold transition-all ${
              filterType === 'ALL'
                ? 'bg-gray-800 text-white shadow-lg'
                : 'bg-white text-gray-700 hover:bg-gray-100 shadow'
            }`}
          >
            All ({content.length})
          </button>
          <button
            onClick={() => setFilterType('IMAGE')}
            className={`px-6 py-2 rounded-lg font-semibold transition-all ${
              filterType === 'IMAGE'
                ? 'bg-green-500 text-white shadow-lg'
                : 'bg-white text-gray-700 hover:bg-gray-100 shadow'
            }`}
          >
            🖼️ Images ({content.filter(i => i.contentType === 'IMAGE').length})
          </button>
          <button
            onClick={() => setFilterType('VIDEO')}
            className={`px-6 py-2 rounded-lg font-semibold transition-all ${
              filterType === 'VIDEO'
                ? 'bg-red-500 text-white shadow-lg'
                : 'bg-white text-gray-700 hover:bg-gray-100 shadow'
            }`}
          >
            🎥 Videos ({content.filter(i => i.contentType === 'VIDEO').length})
          </button>
          <button
            onClick={() => setFilterType('RECIPE')}
            className={`px-6 py-2 rounded-lg font-semibold transition-all ${
              filterType === 'RECIPE'
                ? 'bg-yellow-500 text-white shadow-lg'
                : 'bg-white text-gray-700 hover:bg-gray-100 shadow'
            }`}
          >
            📖 Recipes ({content.filter(i => i.contentType === 'RECIPE').length})
          </button>
          <button
            onClick={() => setFilterType('INGREDIENT_LIST')}
            className={`px-6 py-2 rounded-lg font-semibold transition-all ${
              filterType === 'INGREDIENT_LIST'
                ? 'bg-purple-500 text-white shadow-lg'
                : 'bg-white text-gray-700 hover:bg-gray-100 shadow'
            }`}
          >
            🛒 Lists ({content.filter(i => i.contentType === 'INGREDIENT_LIST').length})
          </button>
        </div>

        {/* Content Grid */}
        {filteredContent.length === 0 ? (
          <div className="text-center text-gray-600 text-xl mt-12">
            No items found.
          </div>
        ) : (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
            {filteredContent.map(item => (
              <div 
                key={item.id}
                className="bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-2xl transition-all duration-300 transform hover:scale-105"
              >
                <div className={`h-48 bg-gradient-to-br ${getCardColor(item.contentType)} flex items-center justify-center`}>
                  <div className="text-6xl">{getIcon(item.contentType)}</div>
                </div>
                <div className="p-4">
                  <div className="flex items-center justify-between mb-2">
                    <span className="text-xs font-semibold text-gray-500 uppercase">
                      {item.contentType.replace('_', ' ')}
                    </span>
                    <span className="text-xs text-gray-400">ID: {item.id}</span>
                  </div>
                  <h3 className="text-lg font-bold text-gray-800 mb-2">
                    {item.title}
                  </h3>
                  <p className="text-sm text-gray-600 line-clamp-3">
                    {item.description}
                  </p>
                  <div className="mt-3 flex gap-2 flex-wrap">
                    <span className="text-xs px-2 py-1 bg-green-100 text-green-700 rounded">
                      Diet: {item.dietId}
                    </span>
                    <span className="text-xs px-2 py-1 bg-blue-100 text-blue-700 rounded">
                      Cuisine: {item.cuisineId}
                    </span>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default ViewAllContent;