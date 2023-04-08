package nvt.doan.repository;

import nvt.doan.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
    @Query("SELECT p FROM Promotion p WHERE p.homestay.homestayId = :homestayId")
    List<Promotion> findAllPromotionByHomestayId(@Param("homestayId") Integer homestayId);
}
