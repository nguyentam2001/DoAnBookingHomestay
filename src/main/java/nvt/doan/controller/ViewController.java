package nvt.doan.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/index")
    public String index(){
            return "admin/index";
    }

    @GetMapping("/users")
    public String users(){
        return "admin/user";
    }

    @GetMapping("/homestay")
    public String homestay(){
        return "admin/homestay";
    }

    @GetMapping("/homestay-form")
    public String homestayForm(){
        return "admin/homestay-form";
    }
}
