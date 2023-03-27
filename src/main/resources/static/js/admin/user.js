$(document).ready(() => {
  get();
});
let status="NORMAL"
const URL = App.URL_USER;
let currentPage = 0;
let totalPages = 0;
let numberOfElements = 0;
function get() {
  const queryString = window.location.search;
  $.ajax({
    url: `${URL}${queryString}`,
    type: App.GET,
    dataType: "json",
    async: false,
    success: function (res) {
      console.log(res);
      currentPage = res.number + 1;
      totalPages = res.totalPages;
      numberOfElements = res.numberOfElements;
      render(res.content);
      renderPagination(totalPages);
      setDisabledPrevNext();
      setActivePage();
    },
    error: function (e) {
      toastr.error(e.responseJSON.message);
    },
  });
}
function render(arr) {
  let html = "";
  arr.forEach((el) => {
    html += `
  <tr>
      <th scope="row">
          <p class="link-color"></i>${el.userId}</p>
      </th>
      <td>${el.fullName}</td>
      <td> ${el.username}</td>
      <td> ${el.email}</td>
      <td>${el.address}</td>
      <td>${el.age}</td>
      <td>${el.phone}</td>
      <td>${el.gender == "male" ? "Nam" : "Nữ"}</td>
      <td>
          <span class="link-color" onclick="showUserDetail('${el.userId}');"></i>View</span>
          ${
            el.bookings.length > 0
              ? `| <span class="link-color" onclick="showUpdate('${el.userId}');"></i>Update</span>`
              : `| <span class="link-color" data-toggle="modal" data-target="#deleteModal${el.userId}" onclick="delete('${el.userId}');"></i>Delete</span>`
          }

          <div class="modal fade" id="deleteModal${el.userId}" tabindex="-1" role="dialog"
              aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                          </button>
                      </div>
                      <div class="modal-body">
                          Do you want delete the ${el.fullName} ?
                      </div>
                      <div class="modal-footer">
                          <button type="button" class="btn btn-primary" onclick="del('${
                            el.userId
                          }');">Ok</button>
                          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                      </div>
                  </div>
              </div>
          </div>

      </td>
  </tr>
  `;
  });
  $("#user-list").html(html);
}

function showUpdate(id) {
  status="";
  isActiveBtnSave();
  let URL = App.URL_USER + "/" + id;
  $.ajax({
    url: `${URL}`,
    type: App.GET,
    async: false,
    dataType: "json",
    success: function (res) {
        $("#modal-user-title").text("Cập nhật thông tin khách hàng");
        $("[name='userId']").val(res.userId),
        $("[name='fullName']").val(res.fullName),
        $("[name='username']").val(res.username),
        $("[name='email']").val(res.email),
        $("[name='address']").val(res.address),
        $("[name='phone']").val(res.phone),
        $("[name='gender']").val(res.gender),
        $("[name='age']").val(res.age);
        showModal();
    },
    error: function (e) {
      toastr.error(e.responseJSON.message);
    },
  });
}


function showUserDetail(id){
    showUpdate(id);
    getListBookings(id);
    status="DETAIL";
    isActiveBtnSave();

}

function isActiveBtnSave(){
console.log(status)
    if(status=="DETAIL"){
         $("#btnSave").hide();
         $("#bookingList").show();
    }else{
        $ ("#btnSave").show();
        $("#bookingList").hide();
    }
}

function getListBookings(id){

let htmlHeader=`
<table class="table table-striped table-bordered table-hover" id="dataBooking">
                                            <thead class="bg-orange">
                                                <tr>
                                                    <th>mã</th>
                                                    <th>Ngày đặt</th>
                                                    <th>Ngày trả</th>
                                                    <th> Homestay</th>
                                                     <th>Giá </th>
                                                    <th>Số phòng</th>
                                                    <th>Địa chỉ</th>
                                                </tr>
                                            </thead>
                                            <tbody id="booking-list">
                                            </tbody>
                                        </table>

`;
$("#bookingList").html(htmlHeader)
$.ajax({
    url: `${BookingURL.ROOT}`+"/"+id,
    type: App.GET,
    dataType: "json",
    async: false,
    success: function (res) {
    console.log(res);
      renderBookingList(res);
    },
    error: function (e) {
      toastr.error(e.responseJSON.message);
    },
  });

}

function renderBookingList(arr){
html='';
arr.forEach((el) => {
    html+=`
            <tr>
              <td>${el.requestId}</td>
              <td>${el.startDate}</td>
              <td>${el.endDate}</td>
              <td>${el.homestayName}</td>
              <td>${el.price}</td>
              <td>${el.numberOfRoom}</td>
              <td>${el.addressName}</td>
            </tr>;
    `
})
$("#booking-list").html(html);
}

function save() {
  isActiveBtnSave();
  let URL = App.URL_USER;
  $.ajax({
    url: `${URL}`,
    type: App.POST,
    async: false,
    dataType: "json",
    contentType: "application/json",
    data: JSON.stringify({
      userId: $("[name='userId']").val(),
      fullName: $("[name='fullName']").val(),
      username: $("[name='username']").val(),
      email: $("[name='email']").val(),
      address: $("[name='address']").val(),
      phone: $("[name='phone']").val(),
      gender: $("[name='gender']").val(),
      age: $("[name='age']").val(),
    }),
    success: function (res) {
      hideModal();
      toastr.success(App.SUCCESS_MSG);
      get();
    },
    error: function (e) {
      toastr.error(e.responseJSON.message);
      hideModal();
      get();
    },
  });
}

function showFormAdd() {
  $("#modal-user-title").text("Thêm mới thông tin khách hàng");
  $("[name='userId']").val(null),
    $("[name='fullName']").val(""),
    $("[name='username']").val(""),
    $("[name='email']").val(""),
    $("[name='address']").val(""),
    $("[name='phone']").val(""),
    $("[name='gender']").val("male"),
    $("[name='age']").val("");
  status =""
  isActiveBtnSave();
  showModal();
}

function hideModal() {
  $("#modal-user").hide();
}

function showModal() {
  $("#modal-user").show();
}

function view(id) {
  let URL = App.URL_CERT + id;
  $.ajax({
    url: `${URL}`,
    type: App.GET,
    dataType: "json",
    success: function (res) {
      $("[name='id']").val(res.id),
        $("[name='certName']").val(res.certName),
        $("[name='cost']").val(res.cost),
        $("[name='category']").val(
          res.category == null ? "no" : res.category.name
        ),
        //set status =1 of update
        (status = App.PUT);
      //set input is read only
      setInputReadOnly(status);
    },
    error: function (e) {
      toastr.error(e.responseJSON.message);
    },
  });
}

function setInputReadOnly(status) {
  if (status == App.PUT) {
    $("[name='id']").attr("disabled", true);
    $("#saveBtn").text("Update");
  } else {
    $("[name='id']").attr("disabled", false);
    $("#saveBtn").text("Save");
    status = App.post;
  }
}

function del(id, url) {
  $.ajax({
    url: `${App.URL_USER}/${id}`,
    type: App.DELETE,
    async: false,
    success: function () {
      toastr.success(App.DELETE_SUCCESS_MSG);
      //change page to prev page when numberOfElements of page ==1
      if (numberOfElements == 1) {
        changePage(currentPage - 1);
      } else {
        get();
      }
    },
    error: function (e) {
      toastr.error(e.responseJSON.message);
    },
  });
}

function renderPagination(el) {
  let html = `<li class="page-item" id="prev-page">
      <a class="page-link" href="javascript:void(0)" onclick="changePage(${
        currentPage - 1
      })"><i
              class="fa-solid fa-chevron-left"></i></a>
  </li>`;
  let page = 1;
  while (page <= el) {
    html += `<li class="page-item" id="page-${page}">
      <a class="page-link" href="javascript:void(0)" onclick="changePage(${page})">
          ${page}</a>
      </li>`;
    page++;
  }
  html += `<li class="page-item" id="next-page">
          <a class="page-link" href="javascript:void(0)" onclick="changePage(${
            currentPage + 1
          })"><i
                  class="fa-solid fa-chevron-right"></i></a>
      </li>`;
  $(".pagination").html(html);
}

function changePage(page) {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  urlParams.set("page", page);
  window.history.replaceState(page, "page", `?${urlParams}`);
  get();
}

function setDisabledPrevNext() {
  $(".pagination .disabled").removeClass("disabled");

  if (currentPage === 1) {
    $("#prev-page").addClass("disabled");
  }
  if (currentPage === totalPages) {
    $("#next-page").addClass("disabled");
  }
}

function setActivePage() {
  $(".pagination .active").removeClass("active");
  $(`#page-${currentPage}`).addClass("active");
}
