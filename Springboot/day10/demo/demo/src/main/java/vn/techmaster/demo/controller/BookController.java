package vn.techmaster.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.model.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller // Áp dụng cho các controller trả cề view (temlate). Ngoài ra cũng có thể trả về Json/Xml giong @RestController(bổ sung thêm thông tin)
/*
@RestController = @Controller + @ResponseBody
@RestController //Dánh dấu lên class --> Class này sẽ xử lý request và response (controller)
*Class ResponseEntity<?>: Class đại diện cho 1 ối tượng HTTP Respnse, có thể chứ body, header, status code
 */
@RequestMapping("/books") // Nếu sử dụng ResquestMapping thì getmapping bên dưới có thể lượt bỏ books.
                          //Có thể thay thể cho Getmapping về cơ bản như nhau nhưng use sẽ phức tạp hơn, khó đọc hơn

public class BookController {
    private List<Book> books = new ArrayList<>(List.of(
            new Book ("1", "Dế mèn phiêu lưu ký", "Tô Hoài", 2000),
            new Book ("2", "Doremon", "Fujiko F. Fujio", 1970),
            new Book ("3", "Conan", "Gosho Aoyama", 1994)
    ));

    //Ví dụ với controller trả về view
    @GetMapping("/home")  // "/home" ->URL
    public  String getHome(){
        return "home"; //ten template
    }

//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED) //201: tôi đã tạo mới tài nguyên thành công/ 204 tôi đã thực hiện thành công nhưng ko có dữ liệu trả về
//    //ResponseStatus thêm status mã trạng thái
//    @GetMapping("/books") // HTTP method + API URL
//    public List<Book> getAllBooks(){
//        return books;
//    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
//        return new ResponseEntity.ok(books);
        return new ResponseEntity<>(books,HttpStatus.CREATED);
    }

    //@GetMapping("/{id}")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Book getBookById(@PathVariable String id){
        for (Book book: books){
            if(book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }
  /*
    1. Viết API để trả về danh sachs book. Sắp xếp theo năm giảm dần
    GET: /books/sortByYear

    2. Viết API để tìm kiếm các cuốn sách mà trong title có chứa keyword, không phân biệt hoa thường
    GET: /books/search/{keyword}

    3. Viết API để tìm kiếm các cuốn sách có year được sản xuất từ năm A -> năm B
    GET: /books/startYear/{startYear}/endYear/{endYear}

   */

    @GetMapping("/sortByYear")
    public ResponseEntity<List<Book>> sortByYear(){
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getYear() - o1.getYear();
            }
        });

        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Book>> getBookByKeyword(@PathVariable String keyword){
        List<Book> rs = new ArrayList<>();
        for(Book book : books){
            if(book.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                rs.add(book);
            }
        }
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/startYear/{startYear}/endYear/{endYear}")
    public ResponseEntity<List<Book>> getBookByYear(@PathVariable int startYear, @PathVariable int endYear){
        List<Book> rs = new ArrayList<>();
        for (Book book: books){
            if(book.getYear() >= startYear && book.getYear() <= endYear){
                rs.add(book);
            }
        }
        return ResponseEntity.ok(rs);
    }
}
