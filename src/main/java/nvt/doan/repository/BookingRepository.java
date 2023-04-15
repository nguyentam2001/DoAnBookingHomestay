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
//    @Query(value = "select request_id as requestId,homestay_name as homestayName,start_date as startDate,end_date as endDate,count(room_id) as numberOfRoom, price,address_name as addressName from booking b\n" +
//            "join homestay h on  b.homestay_id = h.homestay_id \n" +
//            "join users u on u.user_id= b.user_id\n" +
//            "join address a on a.address_id= h.address_id\n" +
//            "join room r on h.homestay_id= r.room_id\n" +
//            "where u.user_id=:user_id",nativeQuery = true)
//    <T> List<T> findBookingDetailByUserId(@Param("user_id") Integer userId,Class<T> type);

    List<BookingDTO> findBookingDetailByUserId(Integer userId);
    @Query(value = "select * from Booking b where b.user_id=?1 group by b.request_id",nativeQuery = true)
    Page<Booking> findBookingByUserId(Integer userId,Pageable pageable);
}
