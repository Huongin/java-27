let users =  [
    {
        name: "Bùi Công Sơn",
        age: 30,
        isStatus: true
    },
    {
        name: "Nguyễn Thu Hằng",
        age: 27,
        isStatus: false
    },
    {
        name: "Phạm Văn Dũng",
        age: 20,
        isStatus: false
    }
];

//Viết function truyền vào 1 mảng các object user. Trả về mảng các user có age > 25 và isStatus = true
const printUser = users => {
    users.forEach((user) => {
        console.log (`${user.name} - ${user.age} - ${user.isStatus}`);  
    })
}
printUser(users);

const findUser = (users) => {
    return users.filter(user => user.age > 25 && user.isStatus === true);
};

printUser(findUser(users));

//Viết function truyền vào 1 mảng các object user. Trả về mảng các user có age tăng dần
const findAgeArs = (users) => {
    return users.sort((a, b) => a.age - b.age);
};

printUser(findAgeArs(users));
