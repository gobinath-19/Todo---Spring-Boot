import React, { useState } from 'react';
import { signup } from '../Service/AuthService';
import { useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';

const Signup = () => {
  const [formData, setFormData] = useState({ username: '', email: '', password: '' });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await signup(formData);
      toast.success('Sign Up successfully');
      navigate('/login');
    } catch (err) {
      alert("Signup failed");
    } 
  };

  return (
    <form
  onSubmit={handleSubmit}
  className="max-w-md mx-auto mt-10 bg-white p-6 rounded-2xl shadow-lg space-y-4"
>
  <h2 className="text-2xl font-bold text-center text-gray-800">Sign Up</h2>

  <input
    type="text"
    placeholder="Username"
    onChange={e => setFormData({ ...formData, username: e.target.value })}
    required
    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
  />

  <input
    type="text"
    placeholder="Email"
    onChange={e => setFormData({ ...formData, email: e.target.value })}
    required
    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
  />

  <input
    type="password"
    placeholder="Password"
    onChange={e => setFormData({ ...formData, password: e.target.value })}
    required
    className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
  />

  <button
    type="submit"
    className="w-full bg-green-600 text-white py-2 rounded-lg hover:bg-green-700 transition"
  >
    Sign Up
  </button>

  
</form>

  );
};

export default Signup;
