
function getStringHasMaxLength (StringsArray) {
    let ans = [];
    if(!Array.isArray(StringsArray) || StringsArray.length === 0){
        return[];
    }
    const maxLength = Math.max(...StringsArray.map(str => str.length));

    for(const str of StringsArray){
        if(str.length === maxLength){
            ans.push(str);
        }
    }
    return ans;
}
const findStringMax = getStringHasMaxLength(['aba', 'aa', 'ad', 'c', 'vcd']);
console.log(findStringMax);