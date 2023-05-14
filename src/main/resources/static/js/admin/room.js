$(document).ready(function () {
  render();
  renderModal();

  $("#submitButton").click(function(event) {
          // Stop default form Submit.
          event.preventDefault();
          // Call Ajax Submit.
          save();

      });

});

function renderModal() {
  $("#homestayFormModal").on("show.bs.modal", function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var recipient = button.data("whatever"); // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this);
    modal.find(".modal-title").text("Nhập hông tin homstay");
    modal.find(".modal-body input").val(recipient);
  });
}


function render() {
  $("#dataTables-example").DataTable({
    ajax: {
      url: RoomURL.ROOT,
      dataSrc: "",
//      success: function (data) {
//        console.log(data);
//      }

    },

    columns: [
      { data: "roomId" },
      { data: "roomName" },
      { data: "homestay.homestayName" },
      {
        data: "bedNumbers",
      },
      { data: "price" },
      { data: "area" },
      { data: "roomType" },
      { data: "status" },
      { data: "numberOfPerson" },
    ],

    createdRow: function (row, data, dataIndex) {
      if (data["status"] == false) {
        $(row).addClass("row-color");
      }
    },

    columnDefs: [
      {
        render: function (data, type, row) {
          if (data == 2) {
            return "Thương gia";
          } else {
            return "Thường";
          }
        },
        targets: 6,
      },
      {
        render: function (data, type, row) {
            if(data<10){
                return `CH0${data}`;
            }else{
                return `CH${data}`;
            }
        },
        targets:0
      },
      {
              render: function (data, type, row) {
                      return `${data}&#13217;`;
              },
              targets:5
      },
      {
        targets:4,
        render: function(data,type,row) {
             let formattedNumber = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data);
             return formattedNumber;
        }
      },
      {
        targets:[3,4,5],
        className: 'dt-right'
      },
      {
        render: function (data, type, row) {
          if (data == false) {
            return "Hoạt động";
          } else {
            return "Dừng";
          }
        },

        targets: 7,
      },

       {
         render: function (data, type, row) {
                  return `${data} (Khách)`;
              },
              targets: 8,
            },

      {
        render: function (data, type, row) {
          var html = "";
          if (row["status"] == true||row["bookingList"].length!=0) {
            html += ` <a href="/view/room/update?roomId=${row.roomId}">Sửa</a>`;
          } else {
            html += ` <a href="/view/room/update?roomId=${row.roomId}">Sửa</a>`;
            html += ` <span class="link-color" data-toggle="modal" data-target="#deleteModal${row.roomId}" onclick="delete('${row.roomId}');"></i> | Xoá</span>
                                          <div class="modal fade" id="deleteModal${row.roomId}" tabindex="-1" role="dialog"
                                                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    Bạn có muốn xoá homestay ${row.roomName} ?
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-primary" onclick="del('${row.roomId}');">Ok</button>
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                                </div>
                                                            </div>
                                           </div>
                  </div>
             `;
          }
          return html;
        },
        targets: 9,
      },
    ],
    responsive: true,
  });
}

function del(id) {
  $.ajax({
    url: `${RoomURL.ROOT}${id}`,
    type: App.DELETE,
    async: false,
    success: function () {
      toastr.success(App.DELETE_SUCCESS_MSG);
      hideModal(`#deleteModal${id}`);
      destroyTable();
      render();
    },
    error: function (e) {
      console.log(e);
      if (e.responseJSON.status == 500) {
        toastr.error("Căn hộ này đã phát sinh hoá đơn, không thể xoá!");
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

function getRooms() {
  var rows = $("#roomBody").find("tr");
  var rooms = [];
  for (var i = 0; i < rows.length; i++) {
    var room = {};
    room.bedNumbers = $(rows[i]).find("[name='roomName']").val();
    room.bedNumbers = $(rows[i]).find("[name='roomId']").val();
    room.bedNumbers = $(rows[i]).find("[name='bedNumbers']").val();
    room.area = $(rows[i]).find("[name='area']").val();
    room.roomDescription = $(rows[i]).find("[name='roomDescription']").val();
    rooms.push(room);
  }
  return rooms;
}
function save() {
// Stop default form Submit.
 var form = $('#uploadHomestay')[0];
 var data = new FormData(form);
 $("#submitButton").prop("disabled", true);
  $.ajax({
    url: `${HomestayURL.POST_HOMESTAY}`,
    type: App.POST,
    enctype: 'multipart/form-data',
    data:data,
    // prevent jQuery from automatically transforming the data into a query string
    processData: false,
    contentType: false,
    timeout: 1000000,
    cache: false,
    success: function (res) {
      toastr.success(App.SUCCESS_MSG);
      hideModal("#homestayFormModal");
      destroyTable();
      render();
      setTimeout(function () {
        window.history.back();
      }, 500);
    },
    error: function (e) {
      toastr.error(e.responseJSON.message);
      hideModal("'#homestayFormModal'");
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
function showRoomDetail(id) {}
