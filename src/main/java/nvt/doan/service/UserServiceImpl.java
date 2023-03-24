package nvt.doan.service;
import nvt.doan.entities.User;
import nvt.doan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class UserServiceImpl extends BaseServiceImpl<User,Integer> implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User getUser(Integer id) {
        Optional<User> optional= userRepository.findById(id);
        return optional.get();
    }
}
