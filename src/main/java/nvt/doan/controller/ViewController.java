package nvt.doan.controller;

import lombok.Getter;
import nvt.doan.dto.BookingRequest;
import nvt.doan.dto.CardDTO;
import nvt.doan.entities.Booking;
import nvt.doan.repository.BookingRepository;
import nvt.doan.repository.HomestayRepository;
import nvt.doan.repository.RoomRepository;
import nvt.doan.repository.UserRepository;
import nvt.doan.service.BookingService;
import nvt.doan.service.DashboardServiceImpl;
import nvt.doan.service.HomestayService;
import nvt.doan.service.account.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

import static nvt.doan.utils.Constant.DATE_NOW;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    @Qualifier("homestayServiceImpl")
    HomestayService homestayService;

    @Autowired
    @Qualifier("bookingServiceImpl")
    BookingService bookingService;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HomestayRepository homestayRepository;

    @Autowired
    RoomRepository roomRepository;


    @Autowired
    private  AuthService authService;
    @GetMapping("/index")
    public String index(Model model){
        Long totalBooking = bookingRepository.count();
        Long totalHomestay = homestayRepository.count();
        Long totalRoom = roomRepository.count();
        Long totalUser = userRepository.count();
        CardDTO cardOne = CardDTO.builder().icon("font-size-5 fa-solid fa-house").Title("Homestay").number(totalHomestay).build();
        CardDTO cardTwo = CardDTO.builder().icon("font-size-5 fa-solid fa-building-user").Title("Căn hộ").number(totalRoom).build();
        CardDTO cardThree = CardDTO.builder().icon("font-size-5 fa-solid fa-receipt").Title("Hoá đơn").number(totalBooking).build();
        CardDTO cardFour = CardDTO.builder().icon("font-size-5 fa-solid fa-users").Title("Người dùng").number(totalUser).build();
        model.addAttribute("cardOne", cardOne);
        model.addAttribute("cardTwo", cardTwo);
        model.addAttribute("cardThree", cardThree);
        model.addAttribute("cardFour", cardFour);
            return "admin/index";
    }
    @GetMapping("/users")
    public String users(){
        return "admin/user";
    }
    @GetMapping("/homestay")
    public String homestay(){
        return "admin/homestay";
    }

    @GetMapping("/homestay-form")
    public String homestayForm(){
        return "admin/homestay-form";
    }
    @GetMapping("/room")
    public String room(){
        return "admin/room";
    }

    @GetMapping("/promotion")
    public String promotion(Model model){
        model.addAttribute("homestays",homestayService.getAll());
        return "admin/promotion";
    }

    @GetMapping("/room/create")
    public String roomFormCreate(Model model){
        model.addAttribute("homestays",homestayService.getAll());
        return "admin/room-form";
    }

    @GetMapping("/room/update")
    public String roomFormUpdate(Model model){
        model.addAttribute("homestays",homestayService.getAll());
        return "admin/room-form-update";
    }
    @GetMapping("/booking-manager")
    public String bookingManager(){
        return "admin/booking-manager";
    }

    @GetMapping("/register")
    public String registerForm(){
        return "users/register";
    }




    @GetMapping("/success-register")
    public String successRegister(){
        return "component/success-page";
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return "component/success-page-confirm";
    }

    @GetMapping("/view-receipt/{requestId}")
    public String viewReceipt(@PathVariable Integer requestId,Model model){
        Optional<Booking> booking = bookingService.findById(requestId);
        BookingRequest receipt=bookingService.getBookingRequestById(requestId);
        receipt.setCancellationCost(booking.get().getCancellationCost());
        receipt.setCancelTime(booking.get().getCancelTime());
        receipt.setBookingTime(booking.get().getBookingTime());
        model.addAttribute("receipt",receipt);
        return "admin/detail-receipt";
    }


    @GetMapping("/report-manager")
    public String reportManager(@RequestParam(name = "homestayId",defaultValue = "1") String homestayId ,
                                @RequestParam(name = "startDate",required = false) String startDate ,
                                @RequestParam(name = "endDate",required = false) String endDate, Model model){
        //get all homestays
        model.addAttribute("selectedHomestay",null);
        model.addAttribute("homestays",homestayService.getAll());
        model.addAttribute("startDate",DATE_NOW.minusMonths(1));
        model.addAttribute("endDate",DATE_NOW.plusDays(1));
        return "admin/report-manager";
    }
}
