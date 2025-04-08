import React, { useState, useEffect } from 'react';
import Navbar from './components/Navbar.jsx';
import Form from './components/Form.jsx';
import Lists from './components/Lists.jsx';

function App() {
  const [todos, setTodos] = useState([]);
  const [editingTodo, setEditingTodo] = useState(null);

  const fetchData = async () => {
    try {
      const response = await fetch('https://dependable-bravery-production.up.railway.app/todos');
      const data = await response.json();
      setTodos(data);
    } catch (error) {
      console.error('Error fetching data:', error.message);
    }
  };

  const createTodo = async (todo) => {
    try {
      await fetch('https://dependable-bravery-production.up.railway.app/todos', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(todo),
      });
      fetchData();
    } catch (error) {
      console.error('Error creating todo:', error.message);
    }
  };

  const updateTodo = async (todo) => {
    try {
      await fetch(`https://dependable-bravery-production.up.railway.app/todos/${todo.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(todo),
      });
      fetchData();
      setEditingTodo(null);
    } catch (error) {
      console.error('Error updating todo:', error.message);
    }
  };

  const deleteTodo = async (id) => {
    try {
      await fetch(`https://dependable-bravery-production.up.railway.app/todos/${id}`, {
        method: 'DELETE',
      });
      fetchData();
    } catch (error) {
      console.error('Error deleting todo:', error.message);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <div className='bg-gradient-to-bl from-[#0f172a] via-[#1e1a78] to-[#0f172a] min-h-screen'>
        <Navbar />
        <div className="md:flex md:flex-row flex-col  w-full ">
          <Form
            onSave={createTodo}
            editingTodo={editingTodo}
            onUpdate={updateTodo}
          />
          <Lists todos={todos} onEdit={setEditingTodo} onDelete={deleteTodo} />
        </div>
      </div>
    </>
  );
}

export default App;