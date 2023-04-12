$(document).ready(function() {
            let checkIn= $("[name='checkIn']").val();
            let checkOut= $("[name='checkOut']").val();
            let numberPersons=$("[name='numberPersons']").val();
            let address=$("[name='address']").val();
            let homestayId=$("[name='homestayId']").val();
            $("#selectSort").on("change",function(){
            let sort = $(this).val();
            window.location.href = `/view/users/search/rooms?address=${address}&checkIn=${checkIn}&checkOut=${checkOut}&numberPersons=${numberPersons}&homestayId=${homestayId}&sort=${sort}`
 })
})


 function addRoomFavourite(roomId){
    $.ajax({
        url: FavouritesURL.ROOT+`${roomId}`,
        async: false,
        type: "POST",
           success: function (res) {
            setTimeout(function(){
               toastr.success('Thêm vào mục ưa thích thành công')
            },1000)
          },
          error: function (e) {
             console.log(e);
          },
    })
    console.log(roomId);
 }

 function detailRoomPrice(roomId){
            let checkIn= $("[name='checkIn']").val();
            let checkOut= $("[name='checkOut']").val();
            let numberPersons=$("[name='numberPersons']").val();
            let address=$("[name='address']").val();
            let homestayId=$("[name='homestayId']").val();
            window.location.href =`/view/users/details?address=${address}&checkIn=${checkIn}&checkOut=${checkOut}&numberPersons=${numberPersons}&homestayId=${homestayId}&roomId=${roomId}`
 }

