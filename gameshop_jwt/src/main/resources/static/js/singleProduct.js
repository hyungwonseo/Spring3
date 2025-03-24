const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get("id");
console.log("Game ID: ", id);

const url = "/api/game/id/" + id;
const urlSession = "/api/user/current-user";

axios
  .get(url)
  .then((response) => {
    console.log("데이터: ", response.data);
    displaySingleProduct(response.data);
  })
  .catch((error) => {
    console.log("에러 발생: ", error.response.data);
  });

function displaySingleProduct(data) {
  const product = document.querySelector(".product");
  // 태그 요소 생성
  const game = document.createElement("div");
  const img = document.createElement("img");
  const title = document.createElement("p");
  const genre = document.createElement("p");
  const price = document.createElement("p");
  const text = document.createElement("p");
  const lowBox = document.createElement("div");
  const left = document.createElement("div");
  const right = document.createElement("div");
  const cartBtn = document.createElement("div");
  // 클래스이름 생성
  game.classList.add("game");
  img.classList.add("image");
  lowBox.classList.add("low-box");
  cartBtn.classList.add("cartBtn");
  // 태그속성추가
  img.src = data.imageUrl;
  title.textContent = "게임 타이틀 : " + data.title;
  genre.textContent = "게임 장르 : " + data.genre;
  price.textContent = "게임 가격 : " + data.price + "원";
  text.textContent = data.text;
  game.style.setProperty("box-shadow", "initial", "important");
  game.style.setProperty("transform", "initial", "important");
  game.style.setProperty("cursor", "initial", "important");
  cartBtn.textContent = "장바구니담기";
  // appendChild 부모자식 위치 설정
  right.appendChild(cartBtn);
  left.appendChild(title);
  left.appendChild(genre);
  left.appendChild(price);
  left.appendChild(text);
  lowBox.appendChild(left);
  lowBox.appendChild(right);
  game.appendChild(img);
  game.appendChild(lowBox);
  product.appendChild(game);

  document.querySelector(".cartBtn").addEventListener("click", () => {
    sessionCurrent(data);
  });
}

function sessionCurrent(data) {
  axios
    .get(urlSession, { withCredentials: true })
    .then((response) => {
      console.log("데이터:", response.data);
      const userName = response.data.userName;
      let cartItems = JSON.parse(localStorage.getItem(userName));
      if (!cartItems) {
        cartItems = [];
      }
      cartItems.push(data);
      localStorage.setItem(userName, JSON.stringify(cartItems));
    })
    .catch((error) => {
      console.log("에러 발생:", error.response.data);
      alert("로그인해주세요.");
    });
}
