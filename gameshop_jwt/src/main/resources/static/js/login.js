const urlLogin = "/api/user/login";
const urlLogout = "/api/user/logout";
const urlSignup = "/api/user/register";
const urlSession = "/api/user/current-user";
let userId = "";
let password = "";
let userNameSignup = "";
let passwordSignup = "";
let realName = "";
let userEmail = "";

document.querySelector("#userId").addEventListener("change", (e) => {
  console.log(e.target.value);
  userId = e.target.value;
});
document.querySelector("#password").addEventListener("change", (e) => {
  console.log(e.target.value);
  password = e.target.value;
});
document.querySelector("#userNameSignup").addEventListener("change", (e) => {
  console.log(e.target.value);
  userNameSignup = e.target.value;
});
document.querySelector("#passwordSignup").addEventListener("change", (e) => {
  console.log(e.target.value);
  passwordSignup = e.target.value;
});
document.querySelector("#realName").addEventListener("change", (e) => {
  console.log(e.target.value);
  realName = e.target.value;
});
document.querySelector("#userEmail").addEventListener("change", (e) => {
  console.log(e.target.value);
  userEmail = e.target.value;
});

document.querySelector(".loginBtn").addEventListener("click", () => {
  const data = {
    userName: userId,
    password: password,
  };
  axios
    .post(urlLogin, data, { withCredentials: true })
    .then((response) => {
      console.log("데이터: ", response.data);
      sessionCurrent();
    })
    .catch((error) => {
      console.log("에러 발생: ", error.response.data);
    });
});
document.querySelector(".logoutBtn").addEventListener("click", () => {
  if (confirm("로그아웃하시겠습니까?")) {
    axios
      .post(urlLogout, {}, { withCredentials: true })
      .then((response) => {
        console.log("데이터:", response.data);
        document.querySelector(".login-box").classList.remove("hidden");
        document.querySelector(".user-box").classList.add("hidden");
      })
      .catch((error) => {
        console.log("에러 발생:", error.response.data);
      });
  }
});
document.querySelector(".signupBtn").addEventListener("click", () => {
  const data = {
    userName: userNameSignup,
    password: passwordSignup,
    realName: realName,
    email: userEmail,
    role: "",
  };
  axios
    .post(urlSignup, data, { withCredentials: true })
    .then((response) => {
      console.log("데이터: ", response.data);
      alert("회원가입이 완료되었습니다. 로그인해주세요.");
      window.location.reload();
    })
    .catch((error) => {
      console.log("에러 발생: ", error.response.data);
    });
});

function signup() {
  document.querySelector(".login-box").classList.add("hidden");
  document.querySelector(".user-box").classList.add("hidden");
  document.querySelector(".signup-box").classList.remove("hidden");
}

function sessionCurrent() {
  axios
    .get(urlSession, { withCredentials: true })
    .then((response) => {
      console.log("데이터:", response.data);
      console.log("세션 유지");
      document.querySelector(".login-box").classList.add("hidden");
      document.querySelector(".user-box").classList.remove("hidden");
      document.querySelector(".user-box p").textContent =
        response.data.userName + "님, 환영합니다.";
    })
    .catch((error) => {
      console.log("에러 발생:", error.response.data);
    });
}

// js 파일이 로드될때 호출됨
sessionCurrent();
