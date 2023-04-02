package nvt.doan.repository;


import nvt.doan.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RoomRepository  extends JpaRepository<Room,Integer> {
    @Query(value = "SELECT * FROM Room r WHERE r.homestay_id = ?1",nativeQuery = true)
    Collection<Room> findAllByHomestayId(Integer homestay_id);
}
