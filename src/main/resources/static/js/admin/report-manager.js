$(document).ready(function () {
  render();
});
function render() {
  $("#dataTables-example").DataTable({
    ajax: {
      url: `${BookingURL.REPORT}`,
      dataSrc: "",
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

//function destroyTable() {
//  $("#dataTables-example").DataTable().destroy();
//}

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