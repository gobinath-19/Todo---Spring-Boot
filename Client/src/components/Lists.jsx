import React from 'react';

const Lists = ({ todos, onEdit, onDelete }) => {
  return (
    <div className='flex md:w-1/2 w-full h-full justify-center '>
      <div className="border rounded-xl w-3/4 mt-7 ">
        <h1 className='mt-5 flex justify-center text-2xl font-bold text-gray-100'>Todo List</h1>
        {todos.length === 0 ? (
          <p className="text-center text-gray-300 mt-5">No todos available. Add some tasks!</p>
        ) : (
        <ul id="todoList">
          {todos.map((item) => (
            <li key={item.id} className="text-white flex justify-between items-center p-2 border-b">
              <span>{item.title} : {item.description}</span>
              <div>
                <button
                  className="mx-2 p-1 bg-yellow-500 hover:bg-yellow-700 text-white rounded"
                  onClick={() => onEdit(item)}
                >
                  Edit
                </button>
                <button
                  className="p-1 bg-red-500 hover:bg-red-700 text-white rounded"
                  onClick={() => onDelete(item.id)}
                >
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
        )}
      </div>
    </div>
  );
};

export default Lists;