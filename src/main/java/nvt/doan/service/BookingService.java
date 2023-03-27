package nvt.doan.service;

import nvt.doan.dto.BookingDTO;
import nvt.doan.entities.Booking;

import java.util.List;

public interface BookingService {
    public List<BookingDTO> findBookingDetailByUserId(Integer userId);
}
