$(document).ready(function() {

})

function cancelBooking(requestId) {
    console.log(requestId);
   $(`#modalCancel${requestId}`).modal('show');
}

function rateRoom(requestId,userId){
 console.log(requestId,userId);
  $(`#modalRate${requestId}`).modal('show');
}

function returnBookingRoom(requestId){
    console.log(requestId);
     $(`#modalCancel${requestId}`).modal('show');
}