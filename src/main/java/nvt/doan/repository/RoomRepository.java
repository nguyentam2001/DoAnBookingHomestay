package nvt.doan.repository;


import nvt.doan.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface RoomRepository  extends JpaRepository<Room,Integer> {
    @Query(value = "SELECT * FROM Room r WHERE r.homestay_id = ?1",nativeQuery = true)
    Collection<Room> findAllByHomestayId(Integer homestay_id);
    @Query(value = "SELECT * FROM Room r\n" +
            "WHERE r.room_id IN (\n" +
            "  SELECT room_id FROM Room\n" +
            "  WHERE status = 0\n" +
            "  AND room_id NOT IN (\n" +
            "    SELECT room_id FROM Booking\n" +
            "    WHERE start_date <= ?1 AND end_date >= ?2\n" +
            "    AND booking_status = 0\n" +
            "  )\n" +
            ")\n" +
            "AND r.homestay_id = (\n" +
            "  SELECT h.homestay_id FROM Homestay h\n" +
            "  JOIN Address a ON h.address_id = a.address_id\n" +
            "  WHERE a.address_id = ?3\n" +
            "  AND h.homestay_id = ?5\n" +
            ")\n" +
            "AND r.number_of_person >= ?4",nativeQuery = true)
    Collection<Room> findAllRoomAvailableByHomestayId(LocalDate checkIn, LocalDate checkOut, String address,String numberPersons, String homestayId);

    @Query(value = "select avg(rr.rate_points)\n" +
            "from room r join booking b on r.room_id=b.room_id join room_rate rr on rr.booking_id= b.request_id\n" +
            "where r.room_id=?1 group by (r.room_id);",nativeQuery = true)
    Double getAVGroomRate(Integer roomId);
}
