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
      { data: "actualPayment" },
      { data: "bookingStatus" },
    ],
    order: [[0,'desc']],
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
              targets: 6,
              render: function(data, type,row){
                let formattedNumber = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data);
                return formattedNumber;
              }
       },
       { targets: [ 6 ], className: 'dt-right' },
       { targets: [ 2,4,5,8 ], className: 'dt-center' },
       {
             targets: [4,5],
             render: $.fn.dataTable.render.moment('YYYY-MM-DD','DD/MM/YYYY' )
         },
      {
        render: function (data, type, row) {
          if (data == 0) {
            return `Hoàn thành`;
          } else if (data == 1) {
            return `Đang thuê`;
          } else if (data == 2) {
            return `Đã huỷ`;
          } else if (data == 4) {
            return `Chờ xác nhận`;
          }else{
            return `Thanh toán không thành công`;
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

function confirmBooking(bookingId,currentPage){
 $.ajax({
        url: `/api/v1/users/booking/checkOutBooking?bookingId=${bookingId}`,
        type: 'GET',
        dataType: "json",
        async: false,
        success:function(data){
                  window.location.href=`/view/booking-manager`
        },
        error:function(data){
                  console.log(data);
        }
    })
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
