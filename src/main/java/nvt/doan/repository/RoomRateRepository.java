package nvt.doan.repository;

import nvt.doan.entities.Promotion;
import nvt.doan.entities.RoomRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRateRepository  extends JpaRepository<RoomRate,Integer> {

    @Query(value ="select rr.rate_id from room r join booking b on r.room_id = b.room_id join room_rate rr on b.request_id = rr.booking_id where r.room_id = ?1 and b.user_id = ?2",nativeQuery = true)
   Integer findRateIdByRoomIdAndUserId(Integer roomId, Integer userId);


}
