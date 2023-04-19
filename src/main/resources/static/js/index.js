$(document).ready(function() {
    $("#btn-login").click(function() {
        login();
    })
})

function login(){
    $.ajax({
            url:"api/v1/auth/login",
            type: "POST",
            async: false,
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                  email: $("#username").val(),
                  password:$("#password").val()
            }),
            success: function (res) {
//                 window.location.href = "/view/users/index";
                window.history.go(-1)
            },
            error: function (e) {
                console.log(e);
                 window.history.go(-1)
                 toastr.error(e.responseJSON.message);
            },
    })
}

//const emailEl = document.getElementById("username");
//const passwordEl = document.getElementById("password");
//const btnLogin = document.getElementById("btn-login");
//const inputEles = document.querySelectorAll('.input-row');
//
//// Đăng nhập
//btnLogin && btnLogin.addEventListener("click", async () => {
//    try {
//        let res = await axios.post("api/v1/auth/login",
//            {
//                email: emailEl.value,
//                password: passwordEl.value
//            })
//
////        window.location.href = "/view/index"
//    } catch (e){
//        toastr.error(e.response.data.message)
//    }
//})


//Đăng xuất
//Đăng ký
// create new account
const fullnameInput = document.getElementById("fullName")
const usernameInput = document.getElementById("username")
const phoneInput = document.getElementById("phone")
const emailInput = document.getElementById("email")
const addressInput = document.getElementById("address")
const passwordInput = document.getElementById("password")
//const rolesSelect = document.getElementById("select-roles")
const createBtn = document.getElementById("register")
const registerBtn = document.getElementById("register")

//Thực hiện đăng ký
//
createBtn.addEventListener("click", async () => {
    try {
            await axios.post("/api/v1/auth/register",
                {
                    fullname: fullnameInput.value,
                    username:usernameInput.value,
                    phone: phoneInput.value,
                    address:addressInput.value,
                    email: emailInput.value,
                    password: passwordInput.value,
                    address: null,
                })
              toastr.success("Đăng ký thông tin thành công!")
              setTimeout(function(){
                    window.location.href = "/view/success-register";
              },1000)

    } catch (e){
        toastr.error(e.response.message);
    }
})


// Validate form
// Validate dữ liệu trong các ô input và highlight
function checkValidate() {
    let emailValue = emailForgotInput.value;

    let isCheck = true;

    // Kiểm tra trường email
    if (emailValue === '') {
        setError(emailForgotInput, 'Email không được để trống');
        isCheck = false;
    } else if (!isEmail(emailValue)) {
        setError(emailForgotInput, 'Email không đúng định dạng');
        isCheck = false;
    } else {
        setSuccess(emailForgotInput);
    }
    return isCheck;
}

// Set hiển thị highlight ô input và message error
function setError(ele, message) {
    let parentEle = ele.parentNode;
    parentEle.classList.add('error');
    parentEle.querySelector('small').innerText = message;
}

// Set hiển thị highlight ô input và message success
function setSuccess(ele) {
    ele.parentNode.classList.add('success');
}

// Kiểm tra định dạng email
function isEmail(email) {
    return /^[a-zA-Z][\w]+@([\w]+\.[\w]{2,}|[\w]+\.[\w]{2,}\.[\w]{2,})$/.test(email);
}