$(document).ready(function () {
  render();
});
function render() {
  $("#dataTables-example").DataTable({
    ajax: {
      url: `${BookingURL.ROOT}`,
      dataSrc: "",
    },
    dom: "Bfrtip",
    buttons: ["excel", "pdf"],
    columns: [
      { data: "requestId" },
      { data: "user.fullName" },
      { data: "user.phone" },
      { data: "user.gender" },
      { data: "startDate" },
      { data: "endDate" },
      { data: "depositPrice" },
      { data: "bookingStatus" },
    ],

    columnDefs: [
      {
        render: function (data, type, row) {
          var html = "";
          html += `<a class="link-color" href="/view/view-receipt/${row.requestId}" ></i>Chi tiết </a>`;
          return html;
        },
        targets: 8,
      },
      {
        render: function (data, type, row) {
          if (data < 10) {
            return `MHD0${data}`;
          } else {
            return `MHD${data}`;
          }
        },
        targets: 0,
      },
      {
        render: function (data, type, row) {
          if (data == 0) {
            return `Nữ`;
          } else {
            return `Nam`;
          }
        },
        targets: 3,
      },

      {
        render: function (data, type, row) {
          if (data == 0) {
            return `Hoàn thành`;
          } else if (data == 1) {
            return `Đang thuê`;
          } else if (data == 2) {
            return `Đã huỷ`;
          } else if (data == 3) {
            return `Chờ xác nhận`;
          }
        },
        targets: 7,
      },
    ],
    responsive: true,
  });
}

function destroyTable() {
  $("#dataTables-example").DataTable().destroy();
}

function confirmCancelBooking(bookingId,currentPage){
 $.ajax({
        url: `/api/v1/users/booking/confirmCancelBooking`,
        type: 'POST',
       dataType:"json",
                  contentType: "application/json",
                   data: JSON.stringify({
                           requestId: bookingId,
                           reason:$(`#reason${bookingId}`).val(),
                       }),
        success:function(data){
                    window.location.href=`/view/booking-manager`
        },
        error:function(data){
                 console.log(data);
        }
    })

}