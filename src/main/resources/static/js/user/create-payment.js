$(document).ready(function() {
    $("#createPayment").click(function(){
       createPayment();
    })
    $("#createDeposit").click(function(){
           createDeposit();
    })
})

function createDeposit(){
 $.ajax({
        url:"/api/v1/users/pay/deposit",
        type:"POST",
        dataType:"json",
        contentType: "application/json",
        data: JSON.stringify({
            user:{
                username:$("[name='username']").val(),
                email:$("[name='email']").val(),
            },
            room:{
                roomId:$("[name='roomId']").val(),
            },
            startDate:$("[name='startDate']").val(),
            endDate:$("[name='endDate']").val(),
            totalPriceDiscount:$("[name='totalPriceDiscount']").val(),

        }),
        success:function(data){
            window.location.href=data.url
        },
        error:function(data){
            console.log(data);
        }
    })
}


function createPayment(){
    $.ajax({
        url:"/api/v1/users/pay/create",
        type:"POST",
        dataType:"json",
        contentType: "application/json",
        data: JSON.stringify({
            user:{
                username:$("[name='username']").val(),
                email:$("[name='email']").val(),
            },
            room:{
                roomId:$("[name='roomId']").val(),
            },
            startDate:$("[name='startDate']").val(),
            endDate:$("[name='endDate']").val(),
            totalPriceDiscount:$("[name='totalPriceDiscount']").val(),

        }),
        success:function(data){
            window.location.href=data.url
        },
        error:function(data){
            console.log(data);
        }
    })

}

