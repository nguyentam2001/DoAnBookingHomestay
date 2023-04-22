package nvt.doan.controller;

import lombok.Getter;
import nvt.doan.dto.BookingRequest;
import nvt.doan.service.BookingService;
import nvt.doan.service.HomestayService;
import nvt.doan.service.account.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private  AuthService authService;
    @GetMapping("/index")
    public String index(){
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
        BookingRequest receipt=bookingService.getBookingRequestById(requestId);
        model.addAttribute("receipt",receipt);
        return "admin/detail-receipt";
    }


    @GetMapping("/report-manager.js")
    public String reportManager(){
        return "admin/report-manager.js";
    }
}
