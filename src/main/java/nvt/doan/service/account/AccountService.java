package nvt.doan.service.account;

import io.jsonwebtoken.Claims;
import nvt.doan.request.PasswordRequest;
import nvt.doan.entities.User;
import nvt.doan.exception.BadRequestException;
import nvt.doan.exception.NotFoundException;
import nvt.doan.repository.UserRepository;
import nvt.doan.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MailService mailService;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nameOrEmail) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findByUsernameOrEmail(nameOrEmail,nameOrEmail);
        System.out.println(user);
        return  userRepository.findByUsernameOrEmail(nameOrEmail,nameOrEmail).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found user with  = " + nameOrEmail);
        });
    }


    // Lấy danh sách các trang
    public List<Integer> getPageNumbers(int totalPages){
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }


    // Reset mật khẩu
    public void resetPassword(String nameOrEmail) {
        System.out.println(nameOrEmail);
        User account = userRepository.findByUsernameOrEmail(nameOrEmail,nameOrEmail).orElseThrow(() -> {
            throw new BadRequestException("Email or Username chưa được đăng kí");
        });
        String newPassword = UUID.randomUUID().toString().substring(0, 10);
        try {
            mailService.send(nameOrEmail, "Mật khẩu đăng nhập mới", newPassword);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            account.setPassword(encoder.encode(newPassword));
            userRepository.save(account);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // ================ ACCOUNT ================



    // Tìm nvien theo id
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Tài khoản không tồn tại");
        });
    }

    // Cập nhật thông tin nhân viên
    public User updateAccount(Integer id,  User request) {
        User account = getById(id);
        account.setFullName(request.getFullName());
        account.setPhone(request.getPhone());
        account.setRoles(request.getRoles());
        userRepository.save(account);
        return account;
    }

    // Xóa tài khoản nhân viên
    public void deleteAccount(Integer id) {
        userRepository.deleteById(id);
    }

    // ================ CUSTOMER ================

//    // Lấy danh sách khách hàng + phân trang
//    public Page<AccountDto> getAllCustomers(String phone, Pageable pageable){
//        return accountRepository.findCustomers(phone, pageable);
//    }

    // Lấy thông tin khách hàng của đơn hàng
//    public User getByOrder(String id) {
//        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> {
//            throw new NotFoundException("Đơn hàng không tồn tại");
//        });
//        return order.getAccountEntity();
//    }

    public User updateCustomer(Integer id, User request) {
        User account = getById(id);
        account.setFullName(request.getFullName());
        account.setPhone(request.getPhone());
        account.setAddress(request.getAddress());
        account.setAddress(request.getAddress());
        userRepository.save(account);
        return account;
    }

    // ================ DETAIL ================
    public User getDetail(HttpServletRequest request){
        // Lấy token từ trong header của request
        String token = jwtUtils.getTokenFromCookie(request);

        // Parse thông tin từ token
        Claims claims = jwtUtils.getClaimsFromToken(token);

        // Lấy username (email khách hàng)
        String userNameOrEmail = claims.getSubject();

        // Lấy thông tin khách hàng qua email
        return userRepository.findByUsernameOrEmail(userNameOrEmail,userNameOrEmail).orElseThrow(() -> {
            throw new NotFoundException("Không tồn tại tài khoản có email " + userNameOrEmail);
        });
    }

    // CHANGE PASSWORD
    public User changePassword(HttpServletRequest request, PasswordRequest passwordRequest){
        // Lấy thông tin khách hàng qua email
        User account = getDetail(request);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String oldPass = passwordRequest.getOldPassword();
        String newPass = passwordRequest.getNewPassword();
        if(oldPass.length() >= 3 && newPass.length() >= 3 && encoder.matches(oldPass, account.getPassword())){
            account.setPassword(encoder.encode(passwordRequest.getNewPassword()));
            userRepository.save(account);
            return account;
        } else {
            throw new BadRequestException("Mật khẩu không chính xác");
        }
    }

//    public WebCustomerOrder getOrderByCustomer(AccountEntity account, Pageable pageable, int currentPage) {
//        Page<OrderEntity> orderPage = orderRepository.findByCustomerPagination(account.getId(), pageable);
//        int totalPages = orderPage.getTotalPages();
//
//        List<Integer> pageNumbers = new ArrayList<>();
//        if(totalPages > 0){
//            pageNumbers = getPageNumbers(totalPages);
//        }
//        return WebCustomerOrder.builder()
//                .orderPage(orderPage)
//                .currentPage(currentPage)
//                .pageNumbers(pageNumbers).build();
//    }

}
