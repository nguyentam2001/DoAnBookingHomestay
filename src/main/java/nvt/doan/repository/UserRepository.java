package nvt.doan.repository;

import nvt.doan.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
    @Query(value = "select u.* from users u join booking b on u.user_id = b.user_id where request_id=?1 ",nativeQuery = true)
    Optional<User> findUserByBookingId(Integer bookingId);
}
