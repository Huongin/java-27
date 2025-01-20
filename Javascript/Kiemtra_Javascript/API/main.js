const apiData = {
    Posts: "https://jsonplaceholder.typicode.com/posts",
    Albums: "https://jsonplaceholder.typicode.com/albums",
    Photos: "https://jsonplaceholder.typicode.com/photos",
  };
  
  const dataList = document.getElementById("data-list");
  const typeDisplay = document.getElementById("type");
  const menuButtons = document.querySelectorAll(".menu");
  
  
  async function loadData(type) {
    try {
      const res = await fetch(apiData[type]);
      const data = await res.json();
  
      dataList.innerHTML = data.map((item) => `<li>${item.title || item.name}</li>`).join("");
      typeDisplay.textContent = type;
    } catch (error) {
      console.error("Lỗi tải dữ liệu:", error);
      dataList.innerHTML = "<li>Không thể tải dữ liệu</li>";
    }
  }
  
  // Định nghĩa các hành động cho từng nút
  const funarray = {
    Posts: () => loadData("Posts"),
    Albums: () => loadData("Albums"),
    Photos: () => loadData("Photos"),
  };
  
  menuButtons.forEach((button) => {
    button.addEventListener("click", (event) => {
      const buttonId = event.target.id;
      if (funarray[buttonId]) {
        
        menuButtons.forEach((btn) => btn.classList.remove("active"));
        i
        event.target.classList.add("active");
  
       
        funarray[buttonId]();
      }
    });
  });
  
  // Hiển thị mặc định Posts
  funarray.Posts();