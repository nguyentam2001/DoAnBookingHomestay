package nvt.doan.service;

import nvt.doan.dto.BookingDTO;
import nvt.doan.entities.Booking;
import nvt.doan.entities.User;
import nvt.doan.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("bookingServiceImpl")
public class BookingServiceImpl  implements BookingService{

    @Autowired
    BookingRepository bookingRepository;
    @Override
    public List<BookingDTO> findBookingDetailByUserId(Integer userId) {
        return bookingRepository.findBookingDetailByUserId(userId);
    }
}
