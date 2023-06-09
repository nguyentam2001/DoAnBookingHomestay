package nvt.doan.service;

import nvt.doan.dto.*;
import nvt.doan.entities.Booking;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingService extends BaseService<Booking, Integer> {
    public Page<Booking> findBookingByUserId(Integer userId, int currentPage, int pageSize);
    public List<BookingDTO> findBookingDetailByUserId(Integer userId);

    public BookingResponse getBookingResponse(LocalDate checkIn, LocalDate checkOut,  String numberPersons,String address,  String homestayId, String email,Integer roomId);

    void checkOutBooking(Integer bookingId);

    void cancelBooking(CancelReasonDTO cancelReason);

    Optional<Booking> findById(Integer bookId);

    public List<BookingRequest> getAllBookingsResponse();

    public BookingRequest getBookingRequestById( Integer requestId );


    void confirmCancelBooking(CancelReasonDTO cancelReason);

    ReportDTO getReportBookings(Integer homestayId, LocalDate startDate, LocalDate endDate);
    ReportDTO getReportBookings();
}
