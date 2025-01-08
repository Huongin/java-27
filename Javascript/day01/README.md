## Cách 1: Viết trực tiếp mã Javascript trong file HTML

- Tạo file *index.html*
- Viết mã Javascript in ra dòng chữ “**Hello world**” trên màn hình console của trình
duyệt.

html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <!-- Viết mã Javascript trong cặp thẻ script -->
    <script>
        console.log("Hello world");
    </script>
</body>
</html>


> Lưu ý : Mã javascript nên đặt ở phần cuối cùng của body

Mở file trên trình duyệt, sau đó *Bấm f12* hoặc *chuột phải + inspect* và vào tab *console* để xem kết quả

!https://media.techmaster.vn/api/static/9479/ESi8Vlah

## Cách 2 : Tạo file có đuôi là .js để viết mã Javascript (nên sử dụng)

- Tạo file *main.js* để viết mã Javascript (*main.js* nằm cùng cấp với *index.html*)
- Nhúng file *main.js* vào file *index.html* thông qua thuộc tính *src* trong thẻ *script*

javascript
// file main.js
console.log("Hello world");


html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <!-- Link sang 1 file javascript -->
    <script src="./main.js"></script>
</body>
</html>


Quay lại trình duyệt và xem kết quả

!https://media.techmaster.vn/api/static/9479/W873ag2K