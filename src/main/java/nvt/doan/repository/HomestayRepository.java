package nvt.doan.repository;

import nvt.doan.dto.HomestayClientDTO;
import nvt.doan.dto.HomestayDTO;
import nvt.doan.entities.Homestay;
import nvt.doan.utils.repositoryct.CustomHomestayRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HomestayRepository extends JpaRepository<Homestay,Integer>, CustomHomestayRepository {


    List<HomestayClientDTO> getHomestaysAndRoomAvailable(LocalDate checkIn, LocalDate checkOut, String numberPersons, String address);
}
