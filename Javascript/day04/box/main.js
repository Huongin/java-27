const btn = document.getElementById("btn");
const boxes = document.querySelector(".boxes");
const points = document.querySelector(".points");

let colors = [
    '#3498db',
    '#9b59b6', 
    '#e74c3c',
    '#2c3e50',
    '#d35400',
];

const createBox = () => {
    
    for (let i = 0; i < colors.length; i++) {
        const colorBox = document.createElement("div");
        colorBox.classList.add("box");
    //chọn màu trong bảng màu
    colorBox.style.backgroundColor = colors[i % colors.length];

    //Xóa khi nhấp ô bất kỳ
    colorBox.addEventListener("click", () =>{
        colorBox.remove();
        updatePoints(); 
    });

    boxes.appendChild(colorBox);
}
updatePoints();

};

const updatePoints = () => {
    const totalBox = boxes.children.length;
    points.textContent = totalBox;
};
window.onload = createBox; //Khi mới load hiện 5 ô màu
btn.addEventListener("click", createBox); // mỗi lần nhấp morebox thì hiện lên 5 