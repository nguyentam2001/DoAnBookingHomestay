package nvt.doan.utils.repositoryct;

import nvt.doan.dto.HomestayClientDTO;
import nvt.doan.dto.HomestayDTO;
import nvt.doan.entities.Homestay;

import java.time.LocalDate;
import java.util.List;

public interface CustomHomestayRepository {
    List<HomestayClientDTO> getHomestaysAndRoomAvailable(LocalDate checkIn, LocalDate checkOut, String numberPersons, String address);
}
