// Dữ liệu sản phẩm mẫu
const productsData = [
    { id: 1, name: "PRODUCT ITEM NUMBER 1", description: "Description for product item number 1", price: 5.99, quantity: 2 },
    { id: 2, name: "PRODUCT ITEM NUMBER 2", description: "Description for product item number 2", price: 9.99, quantity: 1 },
    { id: 3, name: "PRODUCT ITEM NUMBER 3", description: "Description for product item number 3", price: 6.99, quantity: 2 },
    { id: 4, name: "PRODUCT ITEM NUMBER 4", description: "Description for product item number 4", price: 4.99, quantity: 1 },
  ];
  
  // Khởi tạo trạng thái giỏ hàng
  let cart = [...productsData];
  let discountValue = 0; // Giá trị mã giảm giá
  
  // Hàm render danh sách sản phẩm
  function renderProducts() {
    const productsList = document.querySelector(".products");
    productsList.innerHTML = ""; // Xóa danh sách cũ
  
    cart.forEach((product) => {
      const productHTML = `
        <li class="row" data-id="${product.id}">
          <div class="col left">
            <div class="thumbnail">
              <a href="#"><img src="https://via.placeholder.com/200x150" alt="${product.name}" /></a>
            </div>
            <div class="detail">
              <div class="name"><a href="#">${product.name}</a></div>
              <div class="description">${product.description}</div>
              <div class="price">$${product.price.toFixed(2)}</div>
            </div>
          </div>
          <div class="col right">
            <div class="quantity">
              <input type="number" class="quantity-input" value="${product.quantity}" min="1" />
            </div>
            <div class="remove">
              <span class="close"><i class="fa fa-times" aria-hidden="true"></i></span>
            </div>
          </div>
        </li>
      `;
      productsList.insertAdjacentHTML("beforeend", productHTML);
    });
  
    updateSummary();
    attachEventListeners();
  }
  
  // Hàm cập nhật tổng tiền
  function updateSummary() {
    const subtotal = cart.reduce((sum, product) => sum + product.price * product.quantity, 0);
    const vat = 5.00; // VAT cố định
    const total = subtotal + vat - discountValue;
  
    document.querySelector(".subtotal span").textContent = `$${subtotal.toFixed(2)}`;
    document.querySelector(".total span").textContent = `$${total.toFixed(2)}`;
    if (discountValue > 0) {
      document.querySelector(".discount").classList.remove("hide");
      document.querySelector(".discount span").textContent = `-$${discountValue.toFixed(2)}`;
    } else {
      document.querySelector(".discount").classList.add("hide");
    }
  
    document.querySelector(".count").textContent = `${cart.length} items in the bag`;
  }
  
  // Hàm xóa sản phẩm khỏi giỏ hàng
  function removeProduct(productId) {
    cart = cart.filter((product) => product.id !== productId);
    renderProducts();
  }
  
  // Hàm thay đổi số lượng sản phẩm
  function updateQuantity(productId, newQuantity) {
    const product = cart.find((product) => product.id === productId);
    if (product) {
      product.quantity = newQuantity;
    }
    updateSummary();
  }
  
  // Hàm xử lý mã giảm giá
  function applyPromoCode() {
    const promoCodeInput = document.getElementById("promo-code").value.trim();
    if (promoCodeInput === "DISCOUNT5") {
      discountValue = 5.00; // Ví dụ: mã giảm $5
      alert("Promo code applied successfully!");
    } else {
      discountValue = 0;
      alert("Invalid promo code!");
    }
    updateSummary();
  }
  
  // Gắn các sự kiện vào nút và input
  function attachEventListeners() {
    document.querySelectorAll(".quantity-input").forEach((input) => {
      input.addEventListener("change", (event) => {
        const productId = parseInt(event.target.closest(".row").dataset.id, 10);
        const newQuantity = parseInt(event.target.value, 10);
        if (newQuantity > 0) {
          updateQuantity(productId, newQuantity);
        } else {
          alert("Quantity must be at least 1.");
          renderProducts();
        }
      });
    });
  
    document.querySelectorAll(".remove .close").forEach((button) => {
      button.addEventListener("click", (event) => {
        const productId = parseInt(event.target.closest(".row").dataset.id, 10);
        removeProduct(productId);
      });
    });
  
    document.querySelector(".promotion button").addEventListener("click", applyPromoCode);
  }
  
  // Khởi chạy ứng dụng
  document.addEventListener("DOMContentLoaded", () => {
    renderProducts();
  });
  