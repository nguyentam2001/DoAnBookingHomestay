$(document).ready(function() {

})

let ratePoints=0;

 $('.rating').starRating(
{
        starSize: 1.5,
});

$(document).on('change', '.rating',
            function (e, stars, index) {
            ratePoints=stars;
            console.log(ratePoints)

 });

function showCancelBooking(requestId) {
   $(`#modalCancel${requestId}`).modal('show');
}

function showRateRoom(requestId){
  $(`#modalRate${requestId}`).modal('show');
}

function saveRateRoom(requestId,userId,roomId,currentPage) {

    $.ajax({
        url: '/api/v1/users/rate/save',
        type: 'POST',
          dataType:"json",
           contentType: "application/json",
            data: JSON.stringify({
                    requestId: requestId,
                    userId: userId,
                    roomId: roomId,
                    description:$(`#description${requestId}`).val(),
                    ratePoints:ratePoints
                }),
                success:function(data){
                    window.location.href=`/view/users/list-receipts?currentPage=${currentPage}`
                },
                error:function(data){
                    console.log(data);
                }
    })
}

function returnBookingRoom(bookingId){
 $.ajax({
        url: `/api/v1/users/booking/checkOutBooking?bookingId=${bookingId}`,
        type: 'GET',
        dataType: "json",
        async: false,
        success:function(data){
                   $(`#modalReturn${bookingId}`).modal('show');
        },
        error:function(data){
                 console.log(data);
        }
    })

}


function cancelBooking(bookingId,currentPage){
 $.ajax({
        url: `/api/v1/users/booking/cancelBooking`,
        type: 'POST',
       dataType:"json",
                  contentType: "application/json",
                   data: JSON.stringify({
                           requestId: bookingId,
                           reason:$(`#reason${bookingId}`).val(),
                       }),
        success:function(data){
                    window.location.href=`/view/users/list-receipts?currentPage=${currentPage}`
        },
        error:function(data){
                 console.log(data);
        }
    })

}