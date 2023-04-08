$(document).ready(function() {
 $("#selectSort").on("change",function(){
            let sort = $(this).val();
            let checkIn= $("[name='checkIn']").val();
            let checkOut= $("[name='checkOut']").val();
            let numberPersons=$("[name='numberPersons']").val();
            let address=$("[name='address']").val();
            let homestayId=$("[name='homestayId']").val();
            window.location.href = `/view/users/search/rooms?address=${address}&checkIn=${checkIn}&checkOut=${checkOut}&numberPersons=${numberPersons}&homestayId=${homestayId}&sort=${sort}`
 })
})
