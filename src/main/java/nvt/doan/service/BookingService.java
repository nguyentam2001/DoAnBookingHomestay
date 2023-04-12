package nvt.doan.service;

import nvt.doan.dto.BookingDTO;
import nvt.doan.dto.BookingResponse;
import nvt.doan.entities.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    public List<BookingDTO> findBookingDetailByUserId(Integer userId);

    public BookingResponse getBookingResponse(LocalDate checkIn, LocalDate checkOut,  String numberPersons,String address,  String homestayId, String email,Integer roomId);
}
