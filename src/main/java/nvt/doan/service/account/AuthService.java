package nvt.doan.service.account;


import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import nvt.doan.dto.LoginDTO;
import nvt.doan.entities.Role;
import nvt.doan.entities.Token;
import nvt.doan.entities.User;
import nvt.doan.exception.BadRequestException;
import nvt.doan.exception.NotFoundException;
import nvt.doan.repository.UserRepository;
import nvt.doan.repository.account.RoleRepository;
import nvt.doan.repository.account.TokenRepository;
import nvt.doan.request.RegisterRequest;
import nvt.doan.security.JwtUtils;
import nvt.doan.service.mail.MailjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static nvt.doan.utils.Constant.MAX_AGE_COOKIE;


@Service
public class AuthService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository accountRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MailjetService mailjetService;

    // LOGIN USER
    public String login(LoginDTO request, HttpServletResponse httpServletResponse) {
        try {
            // Tạo đối tượng xác thực dựa trên thông tin request
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // Lưu trữ thông tin của đối tượng đã đăng nhập
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

            // Lưu thông tin vào trong cookie (nếu không sử dụng cookie thì trả thẳng token về
            // cho client và mỗi request client gửi lên đều phải kèm token trong header của request)
            Cookie cookie = new Cookie("JWT_COOKIE", token);
            cookie.setPath("/");
            cookie.setMaxAge(MAX_AGE_COOKIE); // Thời gian hết hạn cookie
            cookie.setHttpOnly(true); // Không cho phép client chỉnh sửa thông tin trong cookie (read-only)

            httpServletResponse.addCookie(cookie);

            // Trả về token cho client
            return token;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new BadRequestException("Email hoặc mật khẩu không chính xác");
        }
    }

    // LOGOUT USER
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout success";
    }

    // REGISTER USER
    public String register(RegisterRequest request) {
        // Lấy thông tin user dựa trên email
        Optional<User> accountOptional = accountRepository.findByUsernameOrEmail(request.getEmail(),request.getUsername());

        if (accountOptional.isPresent()) {
            // Nếu user được tìm thấy có trùng các thuộc tính và chưa được kích hoạt
            // Gửi email kích hoạt
            User account = accountOptional.get();
            if (!account.getActive()
                    && account.getUsername().equals(request.getUsername())
                    && account.getEmail().equals(request.getEmail())) {
                return generateTokenAndSendMail(account);
            }

            throw new BadRequestException("Email = " + request.getEmail() + " đã tồn tại");
        }

        // Mã hóa password
        String passwordEncode = passwordEncoder.encode(request.getPassword());

        // Tạo user và lưu vào database
        List<Role> roles =  roleRepository.findByRoleIdIsNot(2);

        User newAccount = new User();
        newAccount.setUsername(request.getUsername());
        newAccount.setPassword(passwordEncode);
        newAccount.setRoles(roles);
        newAccount.setEmail(request.getEmail());
        newAccount.setAddress(request.getAddress());
        newAccount.setPhone(request.getPhone());
        accountRepository.save(newAccount);

        // Sinh ra token
        return generateTokenAndSendMail(newAccount);
    }

    // SINH TOKEN - SEND MAIL
    private String generateTokenAndSendMail(User account) {

        // Sinh ra token
        String tokenString = UUID.randomUUID().toString();

        // Tạo token và lưu token
        Token token = new Token(
                tokenString,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                account
        );
        tokenRepository.save(token);

        // Gửi email
        String link = "<a href=\"" + "http://localhost:8080/view/confirm?token="
                + tokenString + "\"> Kích hoạt tài khoản </a>";
        try {
            mailjetService.sendEmail(account.getEmail(),"Xác thực tài khoản",link);
        } catch ( MailjetException | MailjetSocketTimeoutException e) {
            throw new RuntimeException(e.getMessage());
        }
        return link;
    }

    // VERIFY TOKEN
    public String confirmToken(String tokenString) {
        // Lấy thông tin của token
        Token token = tokenRepository.findByToken(tokenString).orElseThrow(() ->
                new NotFoundException("Không tìm thấy token")
        );

        // Xem token đã được confirm hay chưa
        if (token.getConfirmedAt() != null) {
            throw new BadRequestException("Token đã được xác thực");
        }

        // Xem token đã hết hạn chưa
        LocalDateTime expiredAt = token.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Token đã hết thời gian");
        }

        // Active token
        token.setConfirmedAt(LocalDateTime.now());
        tokenRepository.save(token);

        // Active user
        User account = token.getAccountEntity();
        account.setActive(true);
        accountRepository.save(account);

        return "confirm";
    }

}
