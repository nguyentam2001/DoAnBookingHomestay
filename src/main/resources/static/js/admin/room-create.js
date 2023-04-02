// Tạo phòng
const createBtn = document.getElementById("submitButton");
const createIdInput = document.getElementById("roomId");
const createNameInput = document.getElementById("roomName");
const homestaySelect = document.getElementById("homestay");
const statusSelect = document.getElementById("status");
const roomTypeSelect = document.getElementById("homestayType");
const areaInput = document.getElementById("area");
const bedNumbersInput= document.getElementById("bedNumbers");
const numberOfPersonInput= document.getElementById("numberOfPerson");
const descriptionInput = document.getElementById("roomDescription");
const priceInput = document.getElementById("price");



createBtn.addEventListener("click", async () => {
    try {
        formData.append("applicant", JSON.stringify(
            {
                roomId: createIdInput.value,
                roomName: createNameInput.value,
                homestayId: homestaySelect.value,
                status: statusSelect.value,
                roomType: roomTypeSelect.value,
                area: areaInput.value,
                bedNumbers: bedNumbersInput.value,
                numberOfPerson: numberOfPersonInput.value,
                price: priceInput.value,
                roomDescription: descriptionInput.value
            }
        ))
        console.log(formData.getAll("file[]"));
         await axios.post("/api/v1/rooms/create", formData);
         console.log("successful");
         formData.delete("file[]")
         formData.delete("applicant")
         window.location.href="/view/room"
    } catch (error) {
        console.log(error.response.data.message)
    }
})

// Xử lý image
const inputFileEl = document.getElementById("input-file");
const output = document.getElementById("result");
const formData = new FormData();

// Add event lắng nghe sự kiện thay đổi ô input
inputFileEl.addEventListener("change", (e) => {
    const files = e.target.files;
    console.log(files)
    for (const file of files) {
        file.preview = URL.createObjectURL(file)
        formData.append("file[]", file)
    }
    previewFiles(formData);
})

// Hiển thị ảnh preview
const previewFiles = files => {
    output.innerHTML = "";

    let html = "";
    files.getAll("file[]").forEach((file, index) => {
        html += `
                <div class="m-2">
                    <div class="m-r-2">
                        <div class="max-width-180">
                            <img class="b-r-8" src="${file.preview}" alt="">
                        </div>
                        <div class="col-12 mt-2 flex-basic justify-content-center">
                            <button class="btn-delete-img" onclick=deleteImagePreview(${index})>Delete</button>
                        </div>
                    </div>
                </div>
                `
    });

    output.innerHTML = html;
}

// Xóa ảnh preview
const deleteImagePreview = (index) => {
    const files = formData.getAll("file[]")
        .filter((file, i) => i !== index);

    formData.delete("file[]");
    for (const file of files) {
        formData.append("file[]", file)
    }

    previewFiles(formData);
}



