package nvt.doan.controller;

import nvt.doan.dto.BookingDTO;
import nvt.doan.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    @Autowired
    @Qualifier("bookingServiceImpl")
    BookingService bookingService;
    @GetMapping("/bookings/{userId}")
    public ResponseEntity<?> findBookingDetailByUserId(@PathVariable("userId") Integer userId){
            return ResponseEntity.ok(bookingService.findBookingDetailByUserId(userId));
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> getAllBookings(){
        return ResponseEntity.ok(bookingService.getAllBookingsResponse());
    }

}
