package nvt.doan.controller.user;

import nvt.doan.dto.CancelReasonDTO;
import nvt.doan.dto.RateDTO;
import nvt.doan.service.BookingService;
import nvt.doan.service.RoomRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/booking")
public class BookingRestController {
    @Autowired
    @Qualifier("bookingServiceImpl")
    BookingService bookingService;
    @GetMapping("/checkOutBooking")
    public ResponseEntity<?> checkOutBooking(@RequestParam Integer bookingId){
        bookingService.checkOutBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cancelBooking")
    public ResponseEntity<?> cancelBooking(@RequestBody CancelReasonDTO cancelReason){
        bookingService.cancelBooking(cancelReason);
        return ResponseEntity.noContent().build();
    }
}
