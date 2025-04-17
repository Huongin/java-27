const favoriteMoviesContainer = document.getElementById('favoriteMoviesContainer');
const paginationContainer = document.getElementById('paginationContainer');
const deleteAllBtn = document.getElementById('deleteAllBtn');

// Hàm render danh sách phim yêu thích
const renderFavorites = (favorites) => {
    if (!favorites || favorites.length === 0) {
        favoriteMoviesContainer.innerHTML = '<p class="text-center">Không có phim yêu thích nào.</p>';
        return;
    }

    let html = favorites.map(fav => {
        const movie = fav.movie;
        return `
            <div class="col-3">
                <a class="text-decoration-none text-dark" href="/phim/${movie.id}/${movie.slug}">
                    <div class="movie-item">
                        <div class="movie-poster rounded overflow-hidden">
                            <img src="${movie.thumbnail}" alt="${movie.name}">
                        </div>
                        <div class="movie-info mt-3 d-flex justify-content-between align-items-center">
                            <p class="mb-0">${movie.name}</p>
                            <button class="btn btn-sm btn-danger" onclick="removeFavorite(${movie.id})">Xóa</button>
                        </div>
                    </div>
                </a>
            </div>
        `;
    }).join('');

    favoriteMoviesContainer.innerHTML = html;
};

// Hàm render phân trang
const renderPagination = (totalPages, currentPage) => {
  // Nếu chỉ có 1 trang hoặc 0 trang, có thể ẩn đi cho gọn
    if (totalPages <= 1) {
        paginationContainer.innerHTML = '';
        return;
    }

    let paginationHTML = `
      <ul class="pagination justify-content-center mt-4">
        ${currentPage <= 1
           ? `<li class="page-item disabled"><button class="page-link" disabled>Previous</button></li>`
           : `<li class="page-item"><button class="page-link" onclick="getFavorites(${currentPage - 1})">Previous</button></li>`
        }
    `;

    for (let i = 1; i <= totalPages; i++) {
        paginationHTML += `
            <li class="page-item ${i === currentPage ? 'active' : ''}">
                <button class="page-link" onclick="getFavorites(${i})">${i}</button>
            </li>
        `;
    }

    paginationHTML += `
            ${currentPage >= totalPages
                ? `<li class="page-item disabled"><button class="page-link" disabled>Next</button></li>`
                : `<li class="page-item"><button class="page-link" onclick="getFavorites(${currentPage + 1})">Next</button></li>`
            }
        </ul>
    `;

     paginationContainer.innerHTML = paginationHTML;
};

 //Hàm gọi API lấy danh sách phim yêu thích theo trang
const getFavorites = async (page = 1) => {
    try {
        const pageSize = 12;
        const res = await axios.get(`/api/favorites`,{
          params:{page, pageSize}
        });

        const data = res.data;
        const { content, totalPages, number } = data;
        if (!content || content.length === 0) {
                // Ẩn pagination + Ẩn nút xóa tất cả
                paginationContainer.innerHTML = '';
                deleteAllBtn.style.display = 'none';

                // Hiển thị thông báo
                favoriteMoviesContainer.innerHTML = `
                <div class="col-12">
                   <p>Danh sách yêu thích trống.</p>
                </div>
              `;
                return;
           }
         // Có dữ liệu => hiển thị
              deleteAllBtn.style.display = 'inline-block'; // hoặc 'block' tùy ý
              renderFavorites(content);
              renderPagination(totalPages, number + 1); // number+1 để hiển thị trang hiện tại cho user
        } catch (error) {
              console.error(error);
              alert('Lỗi khi lấy danh sách phim yêu thích');
        }
};


// Hàm xóa một phim yêu thích
const removeFavorite = async (movieId) => {
    if (!window.confirm("Bạn có chắc chắn muốn gỡ bỏ phim này khỏi danh sách yêu thích không?")) {
        return;
    }
    try {
        await axios.delete(`/api/favorites/remove`, {
                    data: { movieId}
        });
        getFavorites(1);
    } catch (error) {
        console.error("Lỗi khi gỡ bỏ phim yêu thích:", error);
    }
};

// Hàm xóa toàn bộ phim yêu thích
const removeAllFavorites = async () => {
    if (!window.confirm("Bạn có chắc chắn muốn gỡ tất cả các phim yêu thích không?")) {
        return;
    }
    try {
        await axios.delete('/api/favorites/removeAll');
        getFavorites(1);
    } catch (error) {
        console.error("Lỗi khi gỡ tất cả phim yêu thích:", error);
    }
}

// Gọi khi load trang
getFavorites(1);
