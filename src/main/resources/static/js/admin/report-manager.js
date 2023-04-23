$(document).ready(function () {
  render();

  $("#homestay").change(function () {
    searchReport();
  })

   $("#endDate").change(function () {
      searchReport();
    })

});
function render() {
  $("#dataTables-example").DataTable({
    ajax: {
      url: `${BookingURL.REPORT}`,
      dataSrc: 'bookingRequestList',
    },
    dom: "Bfrtip",
    buttons: ["excel", "pdf"],
    columns: [
      { data: "room.homestay.homestayName" },
      { data: "room.roomName" },
      { data: "user.fullName" },
      { data: "user.phone" },
      { data: "startDate" },
      { data: "endDate" },
      { data: "depositPrice" },
        ],
    })
}

function searchReport(){
    $.ajax({
        url: `${BookingURL.REPORT}`,
        type: "GET",
        data:{
            homestayId:$("#homestay").val(),
            startDate:$("#startDate").val(),
            endDate:$("#endDate").val(),
        },
        success: function (data) {
             $("#totalPrice").text(`${data.totalPrice} VND`)
             destroyTable();
            $("#dataTables-example").DataTable({
                data: data.bookingRequestList,
                dom: "Bfrtip",
                buttons: ["excel", "pdf"],
                columns: [
                  { data: "room.homestay.homestayName" },
                  { data: "room.roomName" },
                  { data: "user.fullName" },
                  { data: "user.phone" },
                  { data: "startDate" },
                  { data: "endDate" },
                  { data: "depositPrice" },
                    ],
                })
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(jqXHR)
        }


    })
}

function destroyTable() {
  $("#dataTables-example").DataTable().destroy();
}

//function confirmCancelBooking(bookingId,currentPage){
// $.ajax({
//        url: `/api/v1/users/booking/confirmCancelBooking`,
//        type: 'POST',
//       dataType:"json",
//                  contentType: "application/json",
//                   data: JSON.stringify({
//                           requestId: bookingId,
//                           reason:$(`#reason${bookingId}`).val(),
//                       }),
//        success:function(data){
//                    window.location.href=`/view/booking-manager`
//        },
//        error:function(data){
//                 console.log(data);
//        }
//    })
//
//}