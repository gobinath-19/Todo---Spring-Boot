import React, { useState } from 'react';

const Form = ({ onSave, editingTodo, onUpdate }) => {
  const [title, setTitle] = useState(editingTodo?.title || '');
  const [description, setDescription] = useState(editingTodo?.description || '');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (editingTodo) {
      onUpdate({ ...editingTodo, title, description });
    } else {
      onSave({ title, description });
    }
    setTitle('');
    setDescription('');
  };

  React.useEffect(() => {
    if (editingTodo) {
      setTitle(editingTodo.title);
      setDescription(editingTodo.description);
    }
  }, [editingTodo]);

  return (
    <div className='flex md:w-1/2 w-full h-full justify-center '>
      <div className="border rounded-xl w-3/4 mt-7 ">
        <h1 className='mt-5 flex justify-center text-2xl font-bold text-gray-100'>Todo Form</h1>
        <form className='flex flex-col m-5 text-white' onSubmit={handleSubmit}>
          <label className='font-semibold text-2xl'>Title</label>
          <input
            className=" border rounded-md py-2 text-black"
            type='text'
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          <label className='font-semibold text-2xl'>Description</label>
          <input
            className="border rounded-md py-4 text-black"
            type='text'
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
          <button
            type="submit"
            className="hover:bg-green-500  mt-4 p-2 text-2xl bg-green-600 rounded-md transform scale-100 hover:scale-105 transition duration-300"
          >
            {editingTodo ? 'Update' : 'Save'}
          </button>
        </form>
      </div>
    </div>
  );
};

export default Form;