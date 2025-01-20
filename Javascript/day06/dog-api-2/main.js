const btn = document.getElementById('btn');
const image = document.getElementById('image');
const select = document.getElementById('select');

// // Vừa load trang phải gọi API để render danh sách breed
// // API : https://dog.ceo/api/breeds/list/all



async function getBreedList() {
    try {
        // Gọi API để lấy danh sách giống loài
        let res = await axios.get("https://dog.ceo/api/breeds/list/all");

        // Sau khi có data thì hiển thị kết quả trên giao diện
        renderBreed(res.data.message);
    } catch (error) {
        console.error("Lỗi khi gọi API:", error);
    }
}

// function renderBreed(breeds) {
//     // Duyệt qua object breeds -> tạo thẻ option -> gắn vào DOM
//     select.innerHTML = '<option value="">Select a breed</option>';
//     // Xóa các option cũ nếu có
//     select.innerHTML = '<option value="">Select a breed</option>';

//     // Cách 1: Sử dụng for ... in
    for (let breed in breeds) {
        const option = document.createElement('option');
        option.value = breed; // Giá trị của option
        option.textContent = breed; // Hiển thị giống chó
        select.appendChild(option);
    }
//     // Cách 2 : Lấy ra danh sách keys của objec (Object.keys) => Duyệt mảng
//     // Object.keys(breeds).forEach(breed => {
//     //     const option = document.createElement("option");
//     //     option.value = breed;
//     //     option.textContent = breed;
//     //     select.appendChild(option);
//     // });
// }
// // Fetch ảnh khi người dùng bấm nút
btn.addEventListener('click', async function () {
    const selectedBreed = select.value;

    if (!selectedBreed) {
        alert("Please select a breed first!");
        return;
    }

    try {
        // Gọi API để lấy ảnh giống chó
        const res = await axios.get(`https://dog.ceo/api/breed/${selectedBreed}/images/random`);
        image.src = res.data.message; // Hiển thị ảnh giống chó
    } catch (error) {
        console.error("Lỗi khi lấy ảnh giống chó:", error);
    }
});
// // Gọi hàm để tải danh sách giống chó khi trang vừa load
// getBreedList()
