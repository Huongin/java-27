const getCountElement = (num) => {
    let ans = [];
    num.forEach((n) =>{
     ans[n] = (ans[n] || 0 )+1;
    });
    return ans;
}

console.log(getCountElement(["one", "two", "three", "one", "one", "three"]));