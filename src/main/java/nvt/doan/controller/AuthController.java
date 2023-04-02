package nvt.doan.controller;

import lombok.AllArgsConstructor;
import nvt.doan.dto.LoginDTO;
import nvt.doan.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO request, HttpServletResponse httpServletResponse) {
        return authService.login(request, httpServletResponse);
    }

    @GetMapping("/logout-handle")
    public String logout(HttpSession session) {
        return authService.logout(session);
    }

//    @PostMapping("/register")
//    public String register(@RequestBody RegisterRequest request) {
//        return authService.register(request);
//    }
}
