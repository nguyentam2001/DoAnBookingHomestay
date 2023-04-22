package nvt.doan.repository;

import nvt.doan.dto.BookingDTO;
import nvt.doan.entities.Booking;
import nvt.doan.utils.repositoryct.CustomBookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>, CustomBookingRepository {
    List<BookingDTO> findBookingDetailByUserId(Integer userId);
    @Query(value = "select * from Booking b where b.user_id=?1 order by b.created_at desc",nativeQuery = true)
    Page<Booking> findBookingByUserId(Integer userId,Pageable pageable);

}
