const emailEl = document.getElementById("username");
const passwordEl = document.getElementById("password");
const btnLogin = document.getElementById("btn-login");
const inputEles = document.querySelectorAll('.input-row');

// Đăng nhập
btnLogin && btnLogin.addEventListener("click", async () => {
    try {
        let res = await axios.post("api/v1/auth/login",
            {
                email: emailEl.value,
                password: passwordEl.value
            })
        window.location.href = "/view/index"
    } catch (e){
        console.log(e);
    }
})
