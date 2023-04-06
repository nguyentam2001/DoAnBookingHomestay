package nvt.doan.service;

import nvt.doan.dto.HomestayClientDTO;
import nvt.doan.entities.Homestay;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public interface HomestayService extends BaseService<Homestay,Integer> {
    Homestay getHomestayById(int homestayId);

    List<HomestayClientDTO> getHomestays(LocalDate checkIn, LocalDate checkOut, String numberPersons, String address);
}
