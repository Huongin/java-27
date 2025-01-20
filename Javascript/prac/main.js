let tuoi = 0;
// let co_hang_xom = {
//     ten:"Lan",
//     tuoi: 18,
//     hon_nhan: false,
//     dia_chi: {
//         quan:"Cau Giay",
//         phuong:"Mai Dich"
//     }
// };
// tuoi = parseInt(window.prompt("Nhap tuoi can nhap: "))
// for( let i = 0; i < 4; i++){
//     console.log("Nam nay em", tuoi + i, "tuoi");
// }
 const tinh_tuoi = function(nam_sinh){
    // let nam_sinh = parseInt(window.prompt("Nhap nam sinh"))
    tuoi = 2025 - nam_sinh
    console.log(tuoi)
 }
 tinh_tuoi(2004);
 tinh_tuoi(1992);
