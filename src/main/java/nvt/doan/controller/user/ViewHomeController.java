package nvt.doan.controller.user;

import nvt.doan.dto.BookingResponse;
import nvt.doan.dto.HomestayClientDTO;
import nvt.doan.dto.Priority;
import nvt.doan.dto.RoomResponse;
import nvt.doan.entities.*;
import nvt.doan.service.*;
import nvt.doan.service.account.AccountService;
import nvt.doan.utils.Constant;
import nvt.doan.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/view/users")
public class ViewHomeController {
    @Autowired
    AddressService addressService;

    @Autowired
    HomestayService homestayService;
    @Autowired
    PromotionService promotionService;

    @Autowired
    @Qualifier("favouriteServiceImpl")
    FavoriteService favoriteService;
    @Autowired
    AccountService accountService;

    @Autowired
    RoomService roomService;

    @Autowired
    @Qualifier("bookingServiceImpl")
    BookingService bookingService;

    @GetMapping("/index")
    public  String homePage(Model model){
        model.addAttribute("address",addressService.getAll());
        model.addAttribute("promotions",promotionService.getAll());
        LocalDate checkIn =  Constant.DATE_NOW;
        LocalDate checkOut = checkIn.plusDays(1);
        model.addAttribute("checkIn",checkIn);
        model.addAttribute("checkOut",checkOut);
        return "users/index";
    }

    @GetMapping("/search")
    public String homePageSearch(@RequestParam("checkIn") String checkIn,
                                 @RequestParam("checkOut") String checkOut,
                                 @RequestParam("numberPersons") String numberPersons,
                                 @RequestParam("address") String address,Model model, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        request.getSession().setAttribute("targetUrl", referer);
        LocalDate checkInDate = DateUtil.convertStringToLocalDate(checkIn);
        LocalDate checkOutDate=DateUtil.convertStringToLocalDate(checkOut);
        Long totalDate = ChronoUnit.DAYS.between(checkInDate,checkOutDate);
        model.addAttribute("checkIn",checkInDate);
        model.addAttribute("checkOut",checkOutDate);
        model.addAttribute("numberPersons",numberPersons);
        Address selectedAddress= addressService.getAddressById(Integer.parseInt(address));
        model.addAttribute("selectedAddress",selectedAddress);
        model.addAttribute("address",addressService.getAll());
        List<HomestayClientDTO> homestayClientDTOList = homestayService.getHomestays(checkInDate,checkOutDate,numberPersons,address);
        model.addAttribute("homestayClientDTOList",homestayClientDTOList);
        model.addAttribute("numberOfHomestays",homestayClientDTOList.size());
        return "users/list-homestay";
    }


    @GetMapping("/search/rooms")
    public String getRoomsSearch(@RequestParam("checkIn") String checkIn,
                                 @RequestParam("checkOut") String checkOut,
                                 @RequestParam("numberPersons") String numberPersons,
                                 @RequestParam("address") String address,
                                 @RequestParam("homestayId") String homestayId,
                                 @RequestParam(name = "sort", defaultValue = "asc") String sort
                , Model model,HttpServletRequest request) {
        String url=request.getHeader("Referer");
//        HttpSession session = request.getSession();
//        session.setAttribute("targetUrl", request.getHeader("Referer"));

        List<Priority> priorityList = new ArrayList<>();
        priorityList.add(new Priority("asc","Giá từ thấp đến cao"));
        priorityList.add(new Priority("desc","Giá từ cao đến thấp"));
        priorityList.add(new Priority("ascRate","Đánh giá từ cao đến thấp"));
        priorityList.add(new Priority("descRate","Đánh giá từ thấp lên cao"));
        model.addAttribute("priorityList", priorityList);
        model.addAttribute("sort",sort);
        LocalDate checkInDate = DateUtil.convertStringToLocalDate(checkIn);
        LocalDate checkOutDate=DateUtil.convertStringToLocalDate(checkOut);
        Integer totalDate = Math.toIntExact(ChronoUnit.DAYS.between(checkInDate, checkOutDate));
        model.addAttribute("checkIn",checkInDate);
        model.addAttribute("checkOut",checkOutDate);
        model.addAttribute("totalDate",totalDate);
        model.addAttribute("numberPersons",numberPersons);
        Address selectedAddress= addressService.getAddressById(Integer.parseInt(address));
        model.addAttribute("selectedAddress",selectedAddress);
        model.addAttribute("address",addressService.getAll());
        Homestay homestay= homestayService.getHomestayById(Integer.parseInt(homestayId));
        Collection<RoomResponse> roomList = roomService.findAllRoomAvailableByHomestayId(checkInDate,checkOutDate,numberPersons,address,homestayId,sort);
        model.addAttribute("numberOfRoom", roomList.size());
        model.addAttribute("homestay", homestay);
        model.addAttribute("roomList",roomList);
        return "users/list-apartment";
    }

//    @GetMapping("/favourites")
//    public ResponseEntity<?> getFavourites() {
//        return ResponseEntity.noContent().build();
//    }

//    @PostMapping("/favourites")
//    public String getFavourites(@RequestParam("roomId") Integer roomId) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String name= auth.getName();
//        String url=requestGlobal.getHeader("Referer");
//        favoriteService.saveFavourite(name,roomId);
//        return "redirect:"+url;
//    }

    @GetMapping("/favourites/list")
    public String listGetFavourites(
            @RequestParam(name = "checkIn",required = false) String checkIn,
            @RequestParam(name = "checkOut",required = false) String checkOut,
            @RequestParam(name = "numberPersons", defaultValue = "1") String numberPersons,
         HttpServletRequest request, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()  && !(auth instanceof AnonymousAuthenticationToken)) {
            String email= auth.getName();
            //Tìm danh sách các phòng favourtie
            //kiểm tra date có tồn tại hay không
            LocalDate checkInDate = null;
            LocalDate checkOutDate= null;
            List<RoomResponse> roomResponsesFavorite=null;
            if(checkIn==null && checkOut==null){
                roomResponsesFavorite= (List<RoomResponse>) roomService.findAllRoomFavorites(email);
            }else{
                 checkInDate = DateUtil.convertStringToLocalDate(checkIn);
                 checkOutDate=DateUtil.convertStringToLocalDate(checkOut);
                 roomResponsesFavorite = (List<RoomResponse>) roomService.findAllRoomFavorites(checkInDate, checkOutDate, numberPersons, email);
            }
            model.addAttribute("checkIn",checkInDate);
            model.addAttribute("checkOut",checkOutDate);
            model.addAttribute("numberPersons",numberPersons);
            model.addAttribute("roomFavoriteList", roomResponsesFavorite);
        } else {
            // Nếu chưa đăng nhập thì chuyển hướng đến trang đăng nhập
            return "redirect:/admin";
        }
        return "users/favoriteList";
    }

    @GetMapping("/details")
    public String detailsRoom(@RequestParam("roomId") String roomId,
                              @RequestParam(name = "checkIn",required = false) String checkIn,
                              @RequestParam(name = "checkOut",required = false) String checkOut,
                              @RequestParam("homestayId") String homestayId,
                              @RequestParam(name = "numberPersons", required = false) String numberPersons,
                              @RequestParam("address") String address,Model model
                              ){
        //get user account
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null&& auth.isAuthenticated()  && !(auth instanceof AnonymousAuthenticationToken) ){
            //Lấy thông tin user
            User user= (User) accountService.loadUserByUsername(auth.getName());
            //Lấy thông tin Room details
            LocalDate checkInDate = DateUtil.convertStringToLocalDate(checkIn);
            LocalDate checkOutDate=DateUtil.convertStringToLocalDate(checkOut);
            BookingResponse bookingResponse = bookingService.getBookingResponse( checkInDate,  checkOutDate,   numberPersons, address,   homestayId,  auth.getName(), Integer.parseInt(roomId) );
            bookingResponse.setUser(user);
            //Lấy ngày tháng.
            model.addAttribute("bookingResponse", bookingResponse);
        }else{
            return "redirect:/admin";
        }
        return "users/apartment-price";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null&& auth.isAuthenticated()  && !(auth instanceof AnonymousAuthenticationToken) ){
        User user= (User) accountService.loadUserByUsername(auth.getName());
            model.addAttribute("user", user);
        }else{
            return "redirect:/admin";
        }
        return "users/user-management";
    }
    @GetMapping("/payment-success")
    public String getPagePaymentSuccess(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null&& auth.isAuthenticated()  && !(auth instanceof AnonymousAuthenticationToken) ){
        model.addAttribute("successUrl","/view/users/list-receipts");
        }else{
            return "redirect:/admin";
        }
        return "component/payment-success-page";
    }

    @GetMapping("/list-receipts")
    public String geCartBooking(Model model,
                                @RequestParam (defaultValue ="1") Integer currentPage,
                                @RequestParam (defaultValue ="4") Integer pageSize){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null&& auth.isAuthenticated() && ! (auth instanceof AnonymousAuthenticationToken)){
            User user= (User) accountService.loadUserByUsername(auth.getName());
            Page<Booking> bookingListPage = bookingService.findBookingByUserId(user.getUserId(),currentPage,pageSize);
            model.addAttribute("bookingListPage", bookingListPage);
        }else{
            return "redirect:/admin";
        }
        return "users/apartment-cart";
    }

}
