import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import DietSelection from './pages/DietSelection';
import CuisineSelection from './pages/CuisineSelection';
import ContentDisplay from './pages/ContentDisplay';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<DietSelection />} />
        <Route path="/cuisines/:dietId" element={<CuisineSelection />} />
        <Route path="/content/:dietId/:cuisineId" element={<ContentDisplay />} />
      </Routes>
    </Router>
  );
}

export default App;