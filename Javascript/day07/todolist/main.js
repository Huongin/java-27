// Tạo danh sách todo
// id, title, status

const BASE_URL = "http://localhost:3000/todo"
let todos = [];

const getAllTodos = async () => {
    try {
        const res = await axios.get(BASE_URL)
        console.log(res);

        todos = res.data;
        renderTodos(todos);

    }catch(error){
        console.log(error);
    }
}


const ulEl = document.querySelector("ul");
const renderTodos = (todos) => {
    if (todos.length == 0) {
        ulEl.innerHTML = "<li>Không có công việc nào trong danh sách</li>";
        return;
    }
    let html = "";
    todos.forEach(todo => {
        html += `
            <li>
                <input 
                    type="checkbox" 
                    ${todo.status ? "checked" : ""}
                    onchange="toggleStatus(${todo.id})"
                />
                <span class=${todo.status ? "active" : ""}>${todo.title}</span>
                <button onclick ="editTodo(${todo.id})">Edit</button>
                <button onclick="deleteTodo(${todo.id})">Delete</button>
            </li>
        `;
    })
    ulEl.innerHTML = html;
    // Lấy ra toàn bộ button delete
    // For -> addEventListener cho từng button
}
const deleteTodo = async (id) => {
    const isConfirm = window.confirm("bạn có chắc chắn muốn xóa không?");
    if(!isConfirm) return;

   
    try{
         //Xoa o server
        await axios.delete(`${BASE_URL}/${id}`);

        //Xoas client
        todos = todos.filter(todo => todo.id !== id);

        //render lai giao dien
        renderTodos(todos);

    }catch(error){
        console.log(error);
    }
};
// Thêm công việc
const inputTodo = document.querySelector("#input-todo");
const btnAdd = document.querySelector("#btn-add");

btnAdd.addEventListener("click", async () => {
    //Nhap tieu de can tao
    const creatTitle = inputTodo.value.trim();
    //kiem tra xem nguoi dung da nhap tieu de hay chua

    if(creatTitle ===""){
        window.alert("Vui lòng nhập tiêu đề công việc!");
        return;
    }

    //kiem tra tieu de da ton tai chua (tren client)
    const existingTodo = todos.find(todo => todo.title.toLowerCase() === creatTitle.toLocaleLowerCase());
    if(existingTodo){
        window.alert("Tiêu đề này đã tồn tại. Vui lòng nhập tiêu đề khác!");
        return;
    }
    //tao cong viec moi
    const newTodo = {
        title: creatTitle,
        status: false,
    };

    try{
        
        const res = await axios.post(BASE_URL,newTodo);
        console.log(res);
        // them cv moi vao danh sach hien tai va cap nhat giao dien
        todos.push(res.data);
        renderTodos(todos);
        // tra ve o trong
        inputTodo.value = "";
    }catch(error){
        console.log(error);
    }
});


// sửa công việc
const editTodo = async (id) => {
    const editTitle = todos.find(todo => todo.id === id);
    if (!editTitle) {
        return;
    }

    const newTitle = window.prompt("Hãy nhập nội dung cần sửa", editTitle.title);
    console.log("Tiêu đề mới:", newTitle);
   

    const existingTodo = todos.find(todo =>
        todo.title.toLocaleLowerCase() === newTitle.toLocaleLowerCase() && todo.id !== id);
    if (existingTodo) {
        window.alert("Tiêu đề này đã tồn tại. Vui lòng nhập tiêu đề khác!");
        return;
    }

    try {
        
        const response = await axios.patch(`${BASE_URL}/${id}`, { title: newTitle });

        editTitle.title = newTitle;

        renderTodos(todos);
    } catch (error) {
        console.error("Lỗi khi edit todolist");
    }
};
getAllTodos()