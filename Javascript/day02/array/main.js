console.log("Hello World!");

// Khai báo array rỗng
//let arr = [];

// Khai báo array
let number = [];

// Gán giá trị cho các phần tử của array thông qua chỉ số
number[0] = 1;
number[1] = "Bùi Hiên";
number[2] = true;
console.log(number);

// Khai báo và khởi tạo giá trị cho array
let myArr = [1, 2, 3, 4, true, false, "Nguyễn Văn A"];
console.log(myArr);

myArr[3] = 400;
console.log(myArr);

console.log(myArr[6]);

for(let i = 0; i < myArr.length; i++){
    if(i % 2 == 0){
        continue;
    }
    console.log(myArr[i]);
}

// ele = myArr[i]
myArr.forEach((ele, index) => {
    if(index % 2 == 0){
        return;
    }
    console.log(ele);
});

let numbers = [1, 2, 3, 4, 5, 6]

// In ra cac gia tri cua mang
for(const v of numbers){
    console.log(v);
}

// Tinh tong cac gia tri trong mang
//let sum = 0;
//for(const value of numbers){
//    sum += value
//}
//console.log(sum);


console.log(numbers.push(10));
console.log(numbers);

console.log(numbers.pop());
console.log(numbers);

numbers.splice(2, 2, 30, 40);
console.log(numbers);

let numbers2 = numbers.splice(2, 4); // copy array
console.log(numbers2);

//numbers2[1] = 100;
//console.log(numbers);
//console.log(numbers2);

// Sort
function compareNumeric(a, b){
    if ( a > b) return 1;
    if ( a == b) return 0;
    if ( a < b) return -1;
}
//let arr = [2, 1, 5, 12, 24];
//arr.sort(compareNumeric);
////arr.sort((a, b) => a - b);
//console.log(arr);

let arr = [1, 2, 3];
console.log(arr == '1,2,3');
console.log(arr === '1,2,3');

console.log(arr.toString());

// Tim so lon nhat
function MaxNumber(array){
    let max = array[0];
    for (let i = 1; i < array.length; i++) {
        if (array[i] > max) {
            max = array[i];
        }
    }
    return max;
}
console.log(MaxNumber([2, 1, 5, 12, 24]));

// Tim so nho nhat
function MinNumber(array){
    let min = array[0];
    for (let i = 1; i < array.length; i++) {
        if (array[i] < min) {
            min = array[i];
        }
    }
    return min;
}
console.log(MinNumber([2, 1, 5, 12, 24]));

// bai so 3
//let arr1 = [4, 2, 5, 6, 2, 7];
//let arr2 = []; 

//for (let i = 0; i < arr1.length; i++) {
 //   arr2.push(arr1[i] % 2); 
//}

//console.log(arr2); 

const module2 = (arr) => {
    let ans = [];
    for (let i = 0; i < arr.length; i++) {
        ans.push(arr[i] % 2); 
    }
    return ans;
}
console.log(module2([4, 2, 5, 6, 2, 7]));

//bai s0 4
let arr4 = []; 
    for (let i = 0; i < 10; i++) {
        arr4.push('a'); 
    }
   
console.log(arr4); 



// bai so 5

