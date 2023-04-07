$(document).ready(function() {

})



function findAddress(event){
        console.log(event.target);
        const parentElement = event.target.parentNode;
        let address = parentElement.querySelector('input').value;
        let checkIn= $("[name='checkIn']").val();
        let checkOut= $("[name='checkOut']").val();
        let numberPersons=$("[name='numberPersons']").val();
        window.location.href = `/view/users/search?address=${address}&checkIn=${checkIn}&checkOut=${checkOut}&numberPersons=${numberPersons}`

}
