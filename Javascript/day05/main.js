//Tạo danh sách todo

const todos = [
    {id: 1, title: "Làm bài tập", status: true},
    {id: 2, title: "Đá bóng", status: true},
    {id: 3, title: "Nấu cơm", status: false},
    {id: 4, title: "Học bài", status: true}
];

const ulEl = document.querySelector("ul");

const renderTodos = (todos) => {
    
    if( todos == 0){
        ulEl.innerHTML = "<li>Không có công việc nào trong danh sách</li>"
        return;
    }

    let html = "";
    todos.forEach(todos => {
        html += `
        <li>
                <input 
                 type="checkbox"
                 ${todos.status ? "checked" : ""} 
                 onchange = "toggleStatus(${todos.id})"

                />
                <span class = ${todos.status ? "active" : ""}>${todos.title}</span>
                <button onclick ="editTodo(${todos.id})">Edit</button>
                <button onclick ="deleteTodo(${todos.id})">Delete</button>
            </li>
        `;
    })
    ulEl.innerHTML = html
}

const deleteTodo = (id) => {
    const isConfirm = window.confirm ("Bạn có chắc chắn muốn xóa không?");
    if(!isConfirm) return;

    const index = todos.findIndex(todo => todo.id === id);
    todos.splice(index,1);
    renderTodos(todos);
}


//Sửa danh sách todo
const editTodo = id => {
  const editTitle = todos.find(todo => todo.id === id);
  if(!editTitle) return;

  const newTitle = window.prompt("Hãy nhập nội dung cần sửa", editTitle.title);
  if(!newTitle) return;

  const existingTodo = todos.find(todo => 
    todo.title.toLocaleLowerCase() === newTitle && todo.id !== id);
  if(existingTodo){
    window.alert("Tiêu đề này đã tồn tại. Vui lòng nhập tiêu đề khác!");
    return;
  }

   editTitle.title = newTitle;

   renderTodos(todos);
};


const inputTodo = document.querySelector("#input-todo");
const btnAdd = document.querySelector("#btn-add");

btnAdd.addEventListener("click", () => {

    //Nhập tiêu đề cần tạo
    const createTitle = inputTodo.value.trim();

    // Kiểm tra tiêu đề tồn tại hay chưa
    const existingTodo = todos.find(todo => todo.title.toLowerCase() === createTitle.toLowerCase());
    if (existingTodo) {
        window.alert("Tiêu đề này đã tồn tại. Vui lòng nhập tiêu đề khác!");
        return;
    }

    
    const newTodo = {
        id: todos.length > 0 ? Math.max(...todos.map(todo => todo.id)) + 1 : 1,
        title: createTitle,
        status: false
    };
    todos.push(newTodo);

    // trả về khoảng trắng và danh sách mới
    inputTodo.value = "";
    renderTodos(todos);
});

renderTodos(todos);