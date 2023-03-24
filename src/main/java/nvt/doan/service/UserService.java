package nvt.doan.service;
import nvt.doan.entities.User;
public interface UserService extends BaseService<User, Integer> {
    public User getUser(Integer id);
}


//
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    UserRepository userRepository;
//
//    public User save(User user) {
//        return userRepository.save(user);
//    }
//
//    @Override
//    public List<User> getAll() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public void delete(Integer id) {
//        userRepository.delete(userRepository.findById(id).get());
//    }
//
//}
