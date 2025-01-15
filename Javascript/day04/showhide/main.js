// Khi người dùng nhập password và bấm nút “Show”, lúc này hiển thị password và nội dung button chuyển từ “Show” --> “Hide”
// Ngược lại khi người dùng bấm vào nút “Hide”, lúc này ẩn password và nội dung button chuyển từ “Hide” --> “Show”

const passwordInput = document.querySelector('input[type = "password"]');
const eyeButton = document.querySelector('#eyeButton');


eyeButton.addEventListener("click",()=>{
    if(passwordInput.type === 'password'){
        passwordInput.type = 'text';
        eyeButton.textContent = 'Hide';
        eyeButton.innerHTML = '<i class="fas fa-eye-slash"></i>';
    } else {
        passwordInput.type = 'password';
        eyeButton.textContent = 'Show';
        eyeButton.innerHTML = '<i class="fas fa-eye"></i>';
    }
});