const sayHello = () => {
    alert("Xin chao cac ban 1");
}
console.log("Hello");
const btn2 = document.getElementById("btn-2");
// btn2.onclick = () => {
//     alert("Xin chao cac ban 2");
// }

const sayHello2 = () => {
    alert("Xin chao cac ban 2");
}
btn2.onclick = sayHello2;

const btn3 = document.getElementById("btn-3");
btn3.addEventListener("click",(event) =>{
    console.log(Event);
})
// btn3.addEventListener("click", () => {
//     alert("Xin chao cac ban 3");
// })

// const sayHello3 = () => {
//     alert("Xin chao cac ban 3");
// }
// btn3.addEventListener("click", sayHello3)
document.addEventListener("keydown", function (event){
    console.log(event);
});

const btnTop = document.getElementById("top");
window.addEventListener("scroll",function (event){
    if (document.documentElement.scrollTop > 200){
        btnTop.classList.remove("hide");
    }else{
        btnTop.classList.add("hide");
    }
});

btnTop.addEventListener("click", () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
})

// Tim kiem user
const inputEl = document.getElementById("input");
const btnShowAll = document.getElementById("btn-show-all");
const listEl = document.getElementById("list");

const users = [
    { id: 1, name: "Nguyen Van Tra" },
    { id: 2, name: "Tran Hung Anh" },
    { id: 3, name: "Nguyen Thu Hang" },
    { id: 4, name: "Bui Van Hien" },
    { id: 5, name: "Ngo Xuan Tuan" },
    { id: 6, name: "Nguyen Minh Duy" },
    { id: 7, name: "Truong Cong Dinh" },
]

const renderUsers = (users) => {
    let html = "";
    users.forEach(user => {
        html += `<li>${user.name}</li>`;
    })
    listEl.innerHTML = html;
};

btnShowAll.addEventListener("click", () => {
    renderUsers(users);
})

inputEl.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
        const keyword = inputEl.value;
        if (keyword.trim().length === 0) {
            alert("Vui long nhap tu khoa tim kiem");
            return;
        }

        const result = users.filter(user => user.name.toLowerCase().includes(keyword.toLowerCase()));

        if (result.length === 0) {
            listEl.innerHTML = "<li>Khong tim thay ket qua</li>";
            return;
        }
        renderUsers(result);
    }
})

renderUsers(users);