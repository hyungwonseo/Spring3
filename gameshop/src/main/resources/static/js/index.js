const url = "/api/game/all";

axios
  .get(url)
  .then((response) => {
    console.log("응답 Response: ", response);
    displayProducts(response.data);
  })
  .catch((error) => {
    console.log("에러 발생: ", error.response.data);
  });

function displayProducts(gameData) {
  console.log(gameData.length);
  if (gameData.length > 0) {
    const content = document.querySelector(".content");
    gameData.forEach((data) => {
      const game = document.createElement("div");
      game.classList.add("game");
      const img = document.createElement("img");
      img.classList.add("image");
      img.src = data.imageUrl;
      game.appendChild(img);
      const title = document.createElement("p");
      const genre = document.createElement("p");
      const price = document.createElement("p");
      title.textContent = "게임 타이틀 : " + data.title;
      genre.textContent = "게임 장르 : " + data.genre;
      price.textContent = "게임 가격 : " + data.price + "원";
      game.appendChild(title);
      game.appendChild(genre);
      game.appendChild(price);
      game.addEventListener("click", () => {
        window.location.href = "singleProduct.html?id=" + data.id;
      });
      content.appendChild(game);
    });
  }
}
