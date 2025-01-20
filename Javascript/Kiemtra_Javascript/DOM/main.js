const quizzes = [
    {
      id: 1,
      question: "Câu 1: 1 + 1 = ?",
      answers: [1, 2, 3, 4],
    },
    {
      id: 2,
      question: "Câu 2: 2 + 2 = ?",
      answers: [2, 3, 4, 5],
    },
    {
      id: 3,
      question: "Câu 3: 3 + 3 = ?",
      answers: [3, 4, 5, 6],
    },
  ];
  
  const quizContainer = document.getElementById("quiz-container");
  const btnbtn = document.getElementById("btn");
  
 
  function renderQuizzes() {
    quizContainer.innerHTML = ""; 
    quizzes.forEach((quiz) => {
      const quizDiv = document.createElement("div");
      quizDiv.className = "quiz";
  
      // Thêm tiêu đề 
      const questionTitle = document.createElement("h3");
      questionTitle.textContent = quiz.question;
      quizDiv.appendChild(questionTitle);
  
      // Thêm danh sách các câu trả lời và xuống hàng mỗi đáp ánán
      quiz.answers.forEach((answer, index) => {
        const ansList = document.createElement("div"); 
        ansList.style.display = "block"; 
  
     // các câu trả lời cho cùng 1 câu hỏi có name giống nhau
        const input = document.createElement("input");
        input.type = "radio";
        input.name = `quiz-${quiz.id}`; 
        input.value = answer;
  
        ansList.appendChild(input);
        ansList.appendChild(document.createTextNode(answer));
        quizDiv.appendChild(ansList);
      });
  
      quizContainer.appendChild(quizDiv);
    });
  }
  
  // Hàm random câu trả lời
  function randomAns() {
    quizzes.forEach((quiz) => {
      const answerInputs = document.getElementsByName(`quiz-${quiz.id}`);
      const randomIndex = Math.floor(Math.random() * answerInputs.length);
      answerInputs[randomIndex].checked = true; 
    });
  }
  
  // Khi bấm vào nút “Random Answer” thì random câu trả lời cho từng câu hỏi và tự động được check vào (Mỗi câu hỏi chỉ check 1 câu trả lời)
  btnbtn.addEventListener("click", randomAns);
  
  //Vừa vào trang hiển thị danh sách quiz (bao gồm tiêu đề và danh sách các câu trả lời)
  renderQuizzes();
