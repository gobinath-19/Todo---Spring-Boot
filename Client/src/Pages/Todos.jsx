import React, { useEffect, useState } from "react";
import api from "../Service/api";
import { addTodo } from "../Service/AuthService";
import { useNavigate } from "react-router-dom";
import toast from 'react-hot-toast';

const Todos = () => {
  const [todos, setTodos] = useState([]);
  const [data, setData] = useState({
    title: "",
    description: "",
    status: "PENDING",
  });
  const [editId, setEditId] = useState(null);
  const navigate = useNavigate();

  const fetchTodos = async () => {
    try {
      const res = await api.get("/todo");
      setTodos(res.data);
    } catch (err) {
      alert("Failed to load todos");
    }
  };

  const deleteTodo = async (todoId) => {
    try {
      await api.delete(`/todo/${todoId}`);
      toast.success('Todo deleted successfully');
      fetchTodos();
    } catch (err) {
      alert("Failed to delete todo");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editId) {
        await api.put(`/todo/${editId}`, data);
        toast.success('Todo updated successfully');
        setEditId(null);
      } else {
        await addTodo(data);
        toast.success('Todo added successfully');
      }
      setData({ title: "", description: "", status: "PENDING" });
      fetchTodos();
    } catch (err) {
      alert("Failed to save todo");
    }
  };

  const handleEdit = (todo) => {
    setEditId(todo.id);
    setData({
      title: todo.title,
      description: todo.description,
      status: todo.status,
    });
  };

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };

  useEffect(() => {
    fetchTodos();
  }, []);

  return (
    <div className="max-w-6xl mx-auto mt-10 px-4">
      <div className="flex justify-end mb-4">
        <button
          onClick={handleLogout}
          className="bg-red-500 hover:bg-red-600 text-white px-4 py-1 rounded-lg text-sm"
        >
          Logout
        </button>
      </div>

      <h1 className="text-3xl font-bold text-center mb-8 text-gray-800">
        Todo Application
      </h1>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-10">
        <div className="bg-white p-6 rounded-2xl shadow-lg">
          <h2 className="text-2xl font-semibold text-gray-700 mb-4">
            {editId ? "Edit Todo" : "Add Todo"}
          </h2>

          <form onSubmit={handleSubmit} className="space-y-4">
            <div>
              <label className="block text-gray-700 mb-1">Title:</label>
              <input
                type="text"
                placeholder="Todo Title"
                value={data.title}
                onChange={(e) => setData({ ...data, title: e.target.value })}
                required
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
              />
            </div>

            <div>
              <label className="block text-gray-700 mb-1">Description:</label>
              <input
                type="text"
                placeholder="Todo Description"
                value={data.description}
                onChange={(e) =>
                  setData({ ...data, description: e.target.value })
                }
                required
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
              />
            </div>

            <div>
              <label className="block text-gray-700 mb-1">Status:</label>
              <select
                name="status"
                value={data.status}
                onChange={(e) => setData({ ...data, status: e.target.value })}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
              >
                <option value="PENDING">PENDING</option>
                <option value="COMPLETED">COMPLETED</option>
              </select>
            </div>

            <button
              type="submit"
              className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition"
            >
              {editId ? "Update" : "Submit"}
            </button>
          </form>
        </div>

        <div className="bg-white p-6 rounded-2xl shadow-lg">
          <h2 className="text-2xl font-semibold text-gray-700 mb-4">
            Your Todos
          </h2>
          <ul className="space-y-4 max-h-[500px] overflow-y-auto">
            {todos.map((todo) => (
              <li
                key={todo.id}
                className="bg-gray-100 p-4 rounded-lg flex justify-between items-center shadow-sm"
              >
                <div>
                  <p className="font-medium">{todo.title}</p>
                  <p className="text-sm text-gray-600">{todo.description}</p>
                  <p
                    className={`text-xs mt-1 ${
                      todo.status === "COMPLETED"
                        ? "text-green-600"
                        : "text-yellow-600"
                    }`}
                  >
                    {todo.status}
                  </p>
                </div>
                <div className="flex gap-2">
                  <button
                    onClick={() => handleEdit(todo)}
                    className="bg-yellow-400 hover:bg-yellow-500 text-white px-3 py-1 rounded-lg text-sm"
                  >
                    Edit
                  </button>
                  <button
                    onClick={() => deleteTodo(todo.id)}
                    className="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded-lg text-sm"
                  >
                    Delete
                  </button>
                </div>
              </li>
            ))}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Todos;
