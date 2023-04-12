 function deleteRoomFavourite(roomId){
    $.ajax({
        url: FavouritesURL.ROOT+`${roomId}`,
        async: false,
        type: "DELETE",
           success: function (res) {
            setTimeout(function(){
               toastr.success('Xoá căn hộ khỏi mục ưa thích thành công')
            },2000)
             window.location.href = "/view/users/favourites/list";
          },
          error: function (e) {
             console.log(e);
          },
    })
    console.log(roomId);
 }


