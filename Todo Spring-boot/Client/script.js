// Function to fetch all todos from the backend
async function fetchData() {
    try {
        console.log("Fetching all todos...");
        const response = await fetch('http://localhost:2000/todos');
        const data = await response.json();
        console.log('Fetched todos:', data);

        // Clear the existing list items
        const todol = document.getElementById('todoList');
        todol.innerHTML = '';

        // Dynamically create list items
        data.forEach(item => {
            console.log("Processing item:", item); // Log each item
        
            const listItem = document.createElement('li');
            listItem.textContent = `${item.title} : ${item.description}`;
        
            // Edit Button
            const editButton = document.createElement('button');
            editButton.textContent = "Edit";
            editButton.onclick = () => clickedit(item); // Pass the entire item
            listItem.appendChild(editButton);
        
            // Delete Button
            const deleteButton = document.createElement('button');
            deleteButton.textContent = "Delete";
            deleteButton.onclick = () => clickdelete(item.id); // Ensure _id exists
            listItem.appendChild(deleteButton);
        
            todol.appendChild(listItem);
        });
        
    } catch (error) {
        console.error('Error fetching data:', error.message);
    }
}

// Function to create a new todo (POST request)
async function createtodo() {
    try {
        console.log("Creating a new todo...");
        const title = document.getElementById('title').value;
        const description = document.getElementById('description').value;

        const data = { title, description };

        const response = await fetch('http://localhost:2000/todos', {
            method: "POST",
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error(`Failed to create todo. Status: ${response.status}`);
        }

        console.log("New todo created successfully.");
        fetchData(); // Refresh the list
        document.getElementById('title').value = '';
        document.getElementById('description').value = '';
    } catch (error) {
        console.error('Error creating todo:', error.message);
    }
}

// Function to edit a todo (PUT request)
async function clickedit(item) {
    try {
        console.log("Editing todo:", item);

        const saveButton = document.getElementById('btn');
        saveButton.textContent = "Update";

        const titleBox = document.getElementById('title');
        titleBox.value = item.title;

        const descriptionBox = document.getElementById('description');
        descriptionBox.value = item.description;

        saveButton.onclick = async () => {
            try {
                console.log("Updating todo with ID:", item.id);
                const title = titleBox.value;
                const description = descriptionBox.value;
                const data = { title, description };

                const response = await fetch(`http://localhost:2000/todos/${item.id}`, {
                    method: "PUT",
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(data)
                });

                if (!response.ok) {
                    throw new Error(`Failed to update todo. Status: ${response.status}`);
                }

                console.log("Todo updated successfully.");
                fetchData(); // Refresh the list
                saveButton.textContent = "Save"; // Reset button text
                titleBox.value = '';
                descriptionBox.value = '';
                saveButton.onclick = createtodo; // Reset save action to create
            } catch (error) {
                console.error('Error updating todo:', error.message);
            }
        };
    } catch (error) {
        console.error('Error preparing todo for editing:', error.message);
    }
}

// Function to delete a todo (DELETE request)
async function clickdelete(id) {
    let response =  await fetch(`http://localhost:2000/todos/${id}`,{
        method :"DELETE",
    }).then((res) =>{
        console.log(res,"res")
        fetchData();
    })
    
}

// Call fetchData when the page loads
fetchData();
