$(document).ready(function() {
    getRoomById();
    $("#submitButton").on("click", function(){
        updateRoomById();
    })
})

let roomObj;

function getRoomById(){
    const queryString= window.location.search;
    const urlParams = new URLSearchParams(queryString);
    console.log(urlParams);
    $.ajax({
           url: `${RoomURL.GET_ROOM}?${urlParams}`,
           type: "GET",
           dataType: "json",
           success: function(response) {
           roomObj=response;
            mappingRoom(response)
           },
           error: function(response) {
            console.log(response)
          }
    })
}


function updateRoomById(){
//get value of room
    let newRoom={}
    let inputElements= $(document).find("input[name]");
    for(let i=0;i<inputElements.length;i++){
        newRoom[$(inputElements[i]).attr("name")]=$(inputElements[i]).val();
    }
    newRoom["roomDescription"]=$("#roomDescription").val();
    newRoom["homestay"]=
    {
       homestayId:$("#homestay").val()
    }
    console.log(newRoom)
      $.ajax({
                url: `${RoomURL.ROOT}`,
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data:JSON.stringify(newRoom),
                success: function(response) {
                  toastr.success("Update Căn hộ thành công");
                  setTimeout(function(){
                         window.location.href="/view/room"
                  },500)
                },
                error: function(response) {
                 toastr.success("Update Căn hộ thất bại");
               }
      })
}



function mappingRoom(el){
    var inputElements= $(document).find("input[name]");
    for(let i=0;i<inputElements.length;i++){
        $(inputElements[i]).val(el[$(inputElements[i]).attr("name")])
    }
    console.log(el.images)
    renderImg(el.images)
}




// 1. Hiển thị danh sách ảnh
const output = document.getElementById("result");

const renderImg = (imgs) => {
    let html = "";
    imgs.forEach(img => {
        html += `
            <div class= "m-2" id="${img.id}">
                            <div class="m-r-2">
                                    <div class="max-width-180">
                                        <img class="b-r-8" src="/images/${img.name}" alt="">
                                    </div>
                                    <div class="col-12 mt-2 flex-basic justify-content-center">
                                       <span class="btn-delete-img" onclick="removeImgRoom(${img.id})">Delete</span>
                              </div>
                  </div>
            </div>
        `
    })
    output.innerHTML = html;
}
//Them anh
const inputFileEl = document.getElementById("input-file");
inputFileEl.addEventListener("change", async (e) => {
    try {
        const files = e.target.files;
        console.log(files);
        let formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            formData.append("file[]" ,files[i]);
        }
        console.log(formData.getAll("file[]"))
        let newImgArr = await axios.post(`/api/v1/rooms/${roomObj.roomId}/images`, formData);
        console.log(newImgArr.data)

        newImgArr.data.forEach(newimg => {
            roomObj.images.push(newimg);
        })

        console.log(roomObj.images)
        renderImg(roomObj.images)
        toastr.success("Upload thành công");
    } catch (e){
        toastr.error(e.response.data.message)
    }
})


// 2. Xóa ảnh của sp
const removeImgRoom = async (ids) => {
    try {
        await axios.delete(`/api/v1/images/${ids}`)
        roomObj.images = roomObj.images.filter(el => el.id !== ids)
        renderImg(roomObj.images)
        toastr.success("Xóa ảnh thành công");
    } catch (e){
        toastr.error(e.response.data.message)
    }
}