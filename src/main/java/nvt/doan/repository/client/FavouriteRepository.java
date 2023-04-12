package nvt.doan.repository.client;

import nvt.doan.entities.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite,Integer> {
    List<Favourite> findAllByEmail(String email);
    @Query(name = "select * from Favourite where roomId = 1? ",nativeQuery = true)
    Favourite findFavouriteByRoomId(Integer roomId);
}
