package nvt.doan.service;
import nvt.doan.entities.User;
import org.springframework.data.domain.Page;

public interface UserService extends BaseService<User, Integer> {
    public User getUser(Integer id);

    Page<User> findAll(int page, int size);
}


