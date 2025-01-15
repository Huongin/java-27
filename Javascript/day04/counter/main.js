const counterEl = document.getElementById("counter");
const prevBtn = document.querySelector(".prevBtn");
const nextBtn = document.querySelector(".nextBtn");

let counter = 0;

const updateCounter = () => {
    counterEl.textContent = counter;
    if(counter > 0){
        counterEl.style.color = "green";
    }else if (counter < 0){
        counterEl.style.color = "red";
    }else{
        counterEl.style.color = "#333333";
    }
}

prevBtn.addEventListener("click", () => {
    counter--;
    updateCounter();
});

nextBtn.addEventListener("click", () => {
    counter++;
    updateCounter();
});
