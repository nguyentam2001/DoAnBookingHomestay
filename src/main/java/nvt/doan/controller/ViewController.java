package nvt.doan.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String adminHome(){
            return "admin/index";
    }

    @GetMapping("/users")
    public String userList(){
        return "admin/management-user-view";
    }
}
