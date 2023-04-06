package nvt.doan.controller;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import lombok.AllArgsConstructor;
import nvt.doan.dto.LoginDTO;
import nvt.doan.request.RegisterRequest;
import nvt.doan.service.account.AuthService;
import nvt.doan.service.mail.MailjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @Autowired
    private MailjetService mailjetService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request, HttpServletResponse httpServletResponse) {
        return ResponseEntity.ok(authService.login(request, httpServletResponse));
    }
    @GetMapping("/logout-handle")
    public String logout(HttpSession session) {
        return authService.logout(session);
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
//    @GetMapping("/send-email")
//    public void sendEmail() throws MailjetException, MailjetSocketTimeoutException {
//        mailjetService.sendEmail("2001tambh@gmail.com", "060601tambh@gmail.com", "Test email", "Hello, this is a test email!");
//    }

//    @GetMapping("/confirm")
//    public String confirm(@RequestParam("token") String token) {
//        return authService.confirmToken(token);
//    }
}
