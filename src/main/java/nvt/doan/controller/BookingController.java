package nvt.doan.controller;

import nvt.doan.dto.BookingDTO;
import nvt.doan.service.BookingService;
import nvt.doan.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/report")
    public ResponseEntity<?> getReportBookings(@RequestParam(name = "homestayId",required = false) String homestayId ,
                                               @RequestParam(name = "startDate",required = false) String startDate ,
                                               @RequestParam(name = "endDate",required = false) String endDate){
        if(homestayId==null|| ("").equals(homestayId) || startDate==null || endDate==null){
            return ResponseEntity.ok(bookingService.getReportBookings());
        }else{
            Integer homestayIdInt = Integer.parseInt(homestayId);
            LocalDate startDateLD = DateUtil.convertStringToLocalDate(startDate);
            LocalDate endDateLD=DateUtil.convertStringToLocalDate(endDate);
            return ResponseEntity.ok(bookingService.getReportBookings(homestayIdInt,startDateLD,endDateLD));
        }
    }


}
