import React, { useState } from 'react';
import { login } from '../Service/AuthService';
import { saveToken } from '../Utils/token';
import { useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';

const Login = () => {
  const [formData, setFormData] = useState({ username: '', password: '' });
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await login(formData);
      saveToken(res.data.token);
      navigate('/Home');
    } catch (err) {
      toast.error('Incorrect Username or Password!');
    }
  };

  const gotoSignup = (e) => {
    e.preventDefault();
    navigate('/signup');
  }

  return (
   <form
  onSubmit={handleSubmit}
  className="max-w-sm mx-auto mt-10 bg-white p-6 rounded-2xl shadow-lg space-y-4"
>
  <h2 className="text-2xl font-bold text-center text-gray-800">Login</h2>

  <input
    type="text"
    placeholder="Username"
    onChange={e => setFormData({ ...formData, username: e.target.value })}
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

  <div className="flex justify-between">
    <button
      type="submit"
      className="w-1/2 mr-2 bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition"
    >
      Login
    </button>

    <button
      type="button" // Important: change to type="button" to avoid double-submit
      onClick={gotoSignup}
      className="w-1/2 ml-2 bg-gray-200 text-gray-800 py-2 rounded-lg hover:bg-gray-300 transition"
    >
      Signup
    </button>
  </div>
</form>

  );
};

export default Login;
