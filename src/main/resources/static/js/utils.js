class App{
    static POST='POST'
    static GET='GET'
    static PUT='PUT'
    static DELETE='DELETE'
    static URL_USER="/api/v1/user/users"
    static SUCCESS_MSG="Cập nhật dữ liệu thành công!"
    static GET_SUCCESS_MSG="Get user successfully"
    static DELETE_SUCCESS_MSG="Xoá thành công!"
    static UPDATE_SUCCESS_MSG="Update user successfully"
    static ID_HAS_ALREADY_BEEN_USED="ID has already been used"
}

class BookingURL{
     static ROOT="/api/v1/booking/bookings";
     static REPORT="/api/v1/booking/report";
}

class DashboardURL{
     static ROOT="/api/v1/dashboard/detail";
}

class HomestayURL{
     static ROOT="/api/v1/homestays/"
}


class RoomURL{
     static ROOT="/api/v1/rooms/"
      static GET_ROOM="/api/v1/rooms/room"
}

class PromotionURL{
     static ROOT="/api/v1/promotion/"
}

class FavouritesURL{
    static ROOT="/api/v1/users/favourites/"
}

