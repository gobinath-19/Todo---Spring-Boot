import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './Components/Login';
import Signup from './Components/Signup';
import Home from './Pages/Home';
import Todos from './Pages/Todos';
import ProtectedRoute from './Components/ProtectedRoute';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/signup" element={<Signup />} />
        <Route path="/login" element={<Login />} />
        <Route
          path="/todos"
          element={
            <ProtectedRoute>
              <Todos />
            </ProtectedRoute>
          }
        />
        <Route
          path = "/Home"
          element={
            <ProtectedRoute>
              <Home />
            </ProtectedRoute>
          }
          />
        <Route path="/" element={<Login />} />
      </Routes>
    </Router>
  );
};

export default App;
