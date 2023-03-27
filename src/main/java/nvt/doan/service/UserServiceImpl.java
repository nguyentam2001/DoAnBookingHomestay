package nvt.doan.service;
import nvt.doan.entities.User;
import nvt.doan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User,Integer> implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public User getUser(Integer id) {
        Optional<User> optional= userRepository.findById(id);
        return optional.get();
    }

    @Override
    public Page<User> findAll(int page, int size) {
        return userRepository.findAll(PageRequest.of(page-1, size));
    }

}
