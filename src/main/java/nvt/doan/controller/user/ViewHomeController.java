package nvt.doan.controller.user;

import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import nvt.doan.dto.*;
import nvt.doan.entities.*;
import nvt.doan.service.*;
import nvt.doan.service.account.AccountService;
import nvt.doan.service.mail.MailjetService;
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
import java.util.*;
import java.util.stream.Collectors;

import static nvt.doan.utils.Constant.*;

@Controller
@RequestMapping("/view/users")
public class ViewHomeController {
    @Autowired
    AddressService addressService;

    @Autowired
    MailjetService mailjetService;

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
    @GetMapping("/payment-success/{requestId}")
    public String getPagePaymentSuccess(Model model,@RequestParam String vnp_ResponseCode,@PathVariable Integer requestId) throws MailjetSocketTimeoutException, MailjetException {
       Optional<Booking> optional = bookingService.findById(requestId);
       Booking booking =optional.get();
        if(PAYMENT_SUCCESS_STATUS.equals(vnp_ResponseCode)){
            //get booking by requestId;
            booking.setBookingStatus(RENTING_STATUS);
            bookingService.save(booking);
            mailjetService.sendEmail("2001tambh@gmail.com",booking.getRoom().getHomestay().getHomestayName()+" xin chào quý khách!","<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "   <!-- basic -->\n" +
                    "   <meta charset=\"utf-8\">\n" +
                    "   <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "   <!-- mobile metas -->\n" +
                    "   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                    "   <meta name=\"viewport\" content=\"initial-scale=1, maximum-scale=1\">\n" +
                    "   <!-- site metas -->\n" +
                    "   <title>felicity</title>\n" +
                    "   <meta name=\"keywords\" content=\"\">\n" +
                    "   <meta name=\"description\" content=\"\">\n" +
                    "   <meta name=\"author\" content=\"\">\n" +
                    "   <!-- bootstrap css -->\n" +
                    "\n" +
                    "   <!-- fevicon -->\n" +
                    "   <link rel=\"icon\" href=\"images/fevicon.png\" type=\"image/gif\" />\n" +
                    "   <!-- Scrollbar Custom CSS -->\n" +
                    "   <link rel=\"stylesheet\" href=\"css/jquery.mCustomScrollbar.min.css\">\n" +
                    "   <!-- Tweaks for older IEs-->\n" +
                    "   <link rel=\"stylesheet\" href=\"https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\">\n" +
                    "   <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css\"\n" +
                    "      media=\"screen\">\n" +
                    "   <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css\"\n" +
                    "      integrity=\"sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==\"\n" +
                    "      crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />\n" +
                    "\n" +
                    "   <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"\n" +
                    "      integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n" +
                    "   <!--[if lt IE 9]>\n" +
                    "      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n" +
                    "      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script><![endif]-->\n" +
                    "</head>\n" +
                    "<!-- body -->\n" +
                    "\n" +
                    "<body class=\"main-layout\">\n" +
                    "               <div class=\"row\" style=\"width: 800px;\" >\n" +
                    "                  <div class=\"col-md-12 \">\n" +
                    "                     <div class=\"card pl-0 ml-0 mr-0\">\n" +
                    "                        <div class=\"card-body\">\n" +
                    "                           <h2 class=\"card-title\">Chi tiết đặt căn hộ của bạn</h2>\n" +
                    "                           <h3 class=\"mb-2 text-muted\">Homestay: "+booking.getRoom().getHomestay().getHomestayName()+"</h2>\n" +
                    "                           <h3 class=\"mb-2 text-muted\">Địa chỉ: Nha Trang</h2>\n" +

                    "                           <div class=\"row\">\n" +
                    "                                 <div class=\"col-md-6\">\n" +
                    "                                    <p>Nhận căn hộ:</p>\n" +
                    "                                    <strong>"+booking.getStartDate()+"</strong>\n" +
                    "                                 </div>\n" +
                    "                                 <div class=\"col-md-6 border-left\">\n" +
                    "                                    <p>Trả căn hộ:</p>\n" +
                    "                                    <strong>"+booking.getEndDate()+"</strong>\n" +
                    "                                 </div>\n" +
                    "                           </div>\n" +
                    "                           <p>Tổng thời gian lưu trú:</p>\n" +
                    "                           <p>"+booking.getTotalDate()+": đêm</p>\n" +
                    "                           <div class=\"row\">\n" +
                    "                              <div class=\"col-md-12 border-top\">\n" +
                    "                                 <p>Bạn đã chọn</p>\n" +
                    "                                 <p>1 căn hộ cho <strong>"+booking.getNumberPersons()+"</strong> người lớn</p>\n" +
                    "                                 <p>Tiền thanh toán: <strong style=\"color: red;\">" +booking.getActualPayment()+"</strong> VND</p>\n" +
                    "                                 <p>Tiền thuê căn hộ: <strong style=\"color: red;\">"+booking.getTotalPriceDiscount()+"</strong>VND</p>\n" +
                    "                                 <p>Mô tả căn hộ: "+ booking.getRoom().getRoomDescription()+"</p>\n" +
                    "                              </div>\n" +
                    "                           </div>\n" +
                    "                        </div>\n" +
                    "                     </div>\n" +
                    "                  </div>\n" +
                    "               </div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>");
            model.addAttribute("successUrl","/view/users/list-receipts");
            return "component/payment-success-page";
        }else {
            booking.setBookingStatus(PAYMENT_FAIL);
            booking.setDepositPrice(0.0);
            bookingService.save(booking);
            model.addAttribute("failUrl","/view/users/index");
            return "component/payment-fail-page";
        }
    }

    @GetMapping("/list-receipts")
    public String geCartBooking(Model model,
                                @RequestParam (defaultValue ="1") Integer currentPage,
                                @RequestParam (defaultValue ="4") Integer pageSize){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null&& auth.isAuthenticated() && ! (auth instanceof AnonymousAuthenticationToken)){
            User user= (User) accountService.loadUserByUsername(auth.getName());
            Page<Booking> bookingListPage = bookingService.findBookingByUserId(user.getUserId(),currentPage,pageSize);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("totalPages", bookingListPage.getTotalPages());
            model.addAttribute("bookingListPage", bookingListPage);
        }else{
            return "redirect:/admin";
        }
        return "users/apartment-cart";
    }

}
