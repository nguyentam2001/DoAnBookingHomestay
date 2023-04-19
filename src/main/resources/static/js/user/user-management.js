
const formData = new FormData();
const inputFileEl = document.getElementById("imageUpload");

const createBtn = document.getElementById("submitButton");
createBtn.addEventListener("click", async () => {
    try {
        formData.append("applicant", JSON.stringify(
            {
                    fullName: $("[name='fullName']").val(),
                    email: $("[name='email']").val(),
                    address: $("[name='address']").val(),
                    gender: $("[name='gender']").val()
            }
        ))
            await axios.post("/api/v1/users/account/saveUser", formData);
           setTimeout(() => {
            toastr.success("Thay đổi thông tin thành công");
            formData.remove("applicant");
            formData.remove("file[]");

            },500)
             window.location.href="/view/users/profile";

    } catch (error) {
        console.log(error.response)
    }
})


// Add event lắng nghe sự kiện thay đổi ô input
inputFileEl.addEventListener("change", (e) => {
    const file = e.target.files;
    console.log(file[0])
    file[0].preview = URL.createObjectURL(file[0])
    formData.append("file[]", file[0])
    previewFile(formData);
})

function previewFile(formData) {
formData.getAll("file[]").forEach((file, index) => {
 $("#parentImagePreview").html(` <img id="imagePreview" class="rounded-circle t-image-cover t-image-profile" src="${file.preview}" alt="" srcset="">`);
})
}
