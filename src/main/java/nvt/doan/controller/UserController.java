package nvt.doan.controller;

import nvt.doan.entities.User;
import nvt.doan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;
    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody User user){
            return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/users")
    public ResponseEntity<?> update(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "1") int currentPage,
                                      @RequestParam(value = "size", defaultValue = "3") int size){
        Page<User> users= userService.findAll(currentPage,size);
        return ResponseEntity.ok(users);
    }

    @GetMapping ("/users/{userId}")
    public ResponseEntity<?> getById(@PathVariable int userId){
        return ResponseEntity.ok( userService.getUser(userId));
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> delete(@PathVariable int userId){
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }


}
