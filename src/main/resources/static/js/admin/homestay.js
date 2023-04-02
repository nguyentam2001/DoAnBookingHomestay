$(document).ready(function () {
  render();
});
function render() {
  $("#dataTables-example").DataTable({
    ajax: {
      url: `${HomestayURL.ROOT}`,
      dataSrc: "",
    },

    columns: [
      { data: "homestayId" },
      { data: "homestayName" },
      { data: "description" },
      { data: "address.addressName" },
    ],

    columnDefs: [
      {
        render: function (data, type, row) {
          var html = "";
          html += `<span class="link-color" id="'${row.homestayId}'" onclick="showHomestayDetail('${row.homestayId}');"></i>View |</span>`;
            html += ` <a href="/view/homestay-form?homestayId=${row.homestayId}"> Update |</a>`;
            html += ` <span class="link-color" data-toggle="modal" data-target="#deleteModal${row.homestayId}" onclick="delete('${row.homestayId}');"></i> Delete</span>
                                          <div class="modal fade" id="deleteModal${row.homestayId}" tabindex="-1" role="dialog"
                                                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    Bạn có muốn xoá homestay ${row.homestayName} ?
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-primary" onclick="del('${row.homestayId}');">Ok</button>
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                </div>
                                                    </div>
                                    </div>
                  </div>
             `;
          return html;
        },
        targets: 4,
      },
    ],
    responsive: true,
  });
}

function del(id) {
  $.ajax({
    url: `${HomestayURL.ROOT}${id}`,
    type: App.DELETE,
    async: false,
    success: function () {
      toastr.success(App.DELETE_SUCCESS_MSG);
      hideModal(`#deleteModal${id}`);
      destroyTable();
      render();
      toastr.error("Xoá homestay thành công!");
    },
    error: function (e) {
      console.log(e);
      if (e.responseJSON.status == 500) {
        toastr.error("Homestay này đã phát sinh hoá đơn, không thể xoá!");
      }
      hideModal(`#deleteModal${id}`);
      destroyTable();
      render();
    },
  });
}

function destroyTable() {
  $("#dataTables-example").DataTable().destroy();
}



function save() {
    $.ajax({
        url: `${HomestayURL.ROOT}`,
        type: App.POST,
        async: false,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
          homestayId: $("[name='homestayId']").val(),
          homestayName: $("[name='homestayName']").val(),
          address: {
                addressId: $("[name='addressId']").val(),
          }
        }),
        success: function (res) {
          hideModal('#homestayForm');
          destroyTable();
            render();
        },
        error: function (e) {
          hideModal('#homestayForm');
          destroyTable();
          render();
        },
      });
}

function hideModal(modalId) {
  $(modalId).modal("hide");
  $("body").removeClass("modal-open");
  $(".modal-backdrop").remove();
}

function update(id) {}
function showHomestayDetail(id) {
let htmlBody="";
    $.ajax({
        url: `${HomestayURL.ROOT}${id}`,
        type: "GET",
        dataType: "json",
        async: false,
        success: function (res) {
           htmlBody+=`
            <div class="container-fluid">
                       <div class="row">
                              <a href="/view/homestay"> <h1 class="page-header" id="homestayName">${res.homestayName}</h1> </a>
                       </div>
                       <div  class="row">
                          <h5 class="ml-16">Địa chỉ: <span id="AddressName" >${res.address.addressName}</span><h5>
                          <h5 class="ml-16">Mô tả: <span id="description">${res.description}</span>
                        </h5>
                       </div>
                       <div class="row">
                           <div class="col-md-12">
                               <div class="panel panel-default">
                                   <div class="panel-heading">
                                       Danh sách homestay
                                   </div>
                                   <div class="panel-body">
                                       <div class="table-responsive">
                                           <table class="table table-striped table-bordered table-hover " id="dataTables-room">
                                               <thead class="bg-orange">
                                               <tr>
                                                   <th>Mã</th>
                                                   <th>Căn hộ</th>
                                                   <th>Số phòng ngủ</th>
                                                   <th>Mô tả</th>
                                                   <th>Diện tích</th>
                                                   <th>Loại căn hộ</th>
                                                   <th>Giá một đêm</th>
                                                   <th>Trạng thái</th>
                                                   <th>Số khách lưu trữ</th>
                                               </tr>
                                               </thead>
                                           </table>
                                       </div>
                                   </div>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>
               </div>
             </div></div></div>
           `
           $("#page-wrapper").html(htmlBody);
        },
        error: function (e) {
            toastr.error(e.responseJSON.message);
        }
    })
    renderRoomByHomestayId(id)

}

//
//function renderTableRoom(arr){
//html='';
//arr.forEach((el) => {
//    html+=`
//            <tr>
//              <td>${el.roomId}</td>
//              <td>${el.roomName}</td>
//              <td>${el.bedNumbers}</td>
//              <td>${el.roomDescription}</td>
//              <td>${el.area} m2</td>
//              <td>${el.roomType}</td>
//                <td>${el.price}</td>
//               <td>${el.status}</td>
//                <td>${el.numberOfPerson}</td>
//            </tr>
//    `
//})
//return html;
//}

function renderRoomByHomestayId(id) {
console.log(id)
  $("#dataTables-room").DataTable({
    ajax: {
      url: `${RoomURL.ROOT}${id}`,
      dataSrc: "",
      async: false

    },
    columns: [
      { data: "roomId" },
      { data: "roomName" },
      { data: "bedNumbers" },
      { data: "roomDescription" },
      { data: "area" },
      { data: "roomType" },
      { data: "price" },
        { data: "status" },
      { data: "numberOfPerson" }
    ],
    columnDefs: [
     {
                render: function (data, type, row) {

                       return `${data} (Phòng)`
                },
                targets: 2,
              },
   {
      render: function (data, type, row) {
                          return `${data} m&sup2`
                   },
                   targets: 4,
                 },
                 {
     render: function (data, type, row) {
                              if(data==0){
                                    return "Thường"
                                }  else{
                                    return "Thương gia"
                                }
                          },
                          targets: 5,
                        },
    {

         render: function (data, type, row) {
                if(data == false){
                console.log(data)
                return "Trống"
              }else{
                return "Đã thuê"
              }
         },
         targets: 7,
       },

       {
                render: function (data, type, row) {
                       return `Tối đa ${data} (Khách)`
                },
                targets: 8,
       }


    ],
    responsive: true,
  })
}
