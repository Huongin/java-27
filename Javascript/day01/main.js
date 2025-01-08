console.log('Hello World!');

// alert("Hello World!");

// Khai báo biến và không gán giá trị cho biến
let age;
console.log(typeof age);
age = 35;
console.log(typeof age);

// Khai báo biến và gán giá trị cho biến
let email = "hien@techmaster.vn";
console.log(typeof email);

const PI = 3.14;
//PI = 3.14159; // Error

let firstName = "Hien";
let lastName = "Nguyen";

let fullName = "Hien Nguyen";

// C1: REGULAR FUNCTION
function sum(a, b){
    return a + b;
}

console.log(sum(10, 20));

// C2: Function expression
let sum1 = function(a ,b){
    return a + b;
}

console.log(sum(100, 20));

// C3: Arrow function
let sum2 = (a, b) => {
    return a + b;
}
