const url = "/api/purchase/save/list";
const urlSession = "/api/user/current-user";

function sessionCurrent() {
  axios
    .get(urlSession, { withCredentials: true })
    .then((response) => {
      console.log("데이터:", response.data);
      const userId = response.data.userName;
      const authority = response.data.role;
      let cartItems = JSON.parse(localStorage.getItem(userId));
      if (cartItems && cartItems.length > 0) {
        displayCart(cartItems, userId);
        const data = cartItems.map((game) => {
          // PurchaseDTO 객체를 만들어서 리턴
          return {
            game: game,
            user: { userName: userId, password: "", email: "", realName: "", role: authority },
          };
        });
        document
          .querySelector(".purchaseBtn")
          .addEventListener("click", () => {
            if (confirm("구매하시겠습니까?")) {
              axios
                .post(url, data, { withCredentials: true })
                .then((response) => {
                  console.log("데이터:", response.data);
                  localStorage.removeItem(userId);
                  window.location.reload();
                })
                .catch((error) => {
                  console.log("에러 발생:", error.response.data);
                });
            }
          });
      }
    })
    .catch((error) => {
      console.log("에러 발생:", error.response.data);
      alert("로그인해주세요.");
    });
}

function displayCart(games, userId) {
  const tbody = document.querySelector(".cart-body");
  let totalPrice = 0;
  games.forEach((data, index) => {
    // 태그 요소 생성
    const tr = document.createElement("tr");
    const imgtd = document.createElement("td");
    const title = document.createElement("td");
    const genre = document.createElement("td");
    const price = document.createElement("td");
    const remove = document.createElement("td");
    const img = document.createElement("img");
    const removeBtn = document.createElement("div");
    // 클래스이름 생성
    imgtd.classList.add("imgtd");
    img.classList.add("image");
    removeBtn.classList.add("removeBtn");
    // 태그속성추가
    img.src = data.imageUrl;
    title.textContent = data.title;
    genre.textContent = data.genre;
    price.textContent = data.price + "원";
    removeBtn.textContent = "삭제";
    // appendChild 부모자식 위치 설정
    imgtd.appendChild(img);
    remove.appendChild(removeBtn);
    tr.appendChild(imgtd);
    tr.appendChild(title);
    tr.appendChild(genre);
    tr.appendChild(price);
    tr.appendChild(remove);
    tbody.appendChild(tr);

    totalPrice = totalPrice + data.price;

    removeBtn.addEventListener("click", () => {
      const gameList = JSON.parse(localStorage.getItem(userId));
      const newGameList = gameList.splice(index, 1);
      localStorage.setItem(userId, JSON.stringify(gameList));
      window.location.reload();
    });
  });
  document.querySelector(".totalprice").textContent = "총 " + totalPrice + "원";
}

// 페이지 로딩시에 즉시 세션여부 확인
sessionCurrent();
