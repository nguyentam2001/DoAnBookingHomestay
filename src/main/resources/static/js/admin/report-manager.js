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
         columnDefs:[
                {
                    targets:[6],
                    className: "dt-right"
                },
                {
                    targets:[4,5],
                     render: $.fn.dataTable.render.moment('YYYY-MM-DD','DD/MM/YYYY' )
                },
                 {
                              targets: 6,
                              render: function(data, type,row){
                                let formattedNumber = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data);
                                return formattedNumber;
                              }
                 },
            ]

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
        let formattedNumber = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data.totalPrice);
             $("#totalPrice").text(`${formattedNumber}`)
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
                    columnDefs:[
                                    {
                                        targets:[6],
                                        className: "dt-right"
                                    },
                                    {
                                        targets:[4,5],
                                         render: $.fn.dataTable.render.moment('YYYY-MM-DD','DD/MM/YYYY' )
                                    },
                                     {
                                                  targets: 6,
                                                  render: function(data, type,row){
                                                    let formattedNumber = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data);
                                                    return formattedNumber;
                                                  }
                                     },
                                ]
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