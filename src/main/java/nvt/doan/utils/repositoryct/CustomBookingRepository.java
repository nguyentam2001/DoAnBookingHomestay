package nvt.doan.utils.repositoryct;

import nvt.doan.dto.BookingDTO;

import java.util.List;

public interface CustomBookingRepository {
    List<BookingDTO> findBookingDetailByUserId(Integer userId);
}
