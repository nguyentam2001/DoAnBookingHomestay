package nvt.doan.controller;

import nvt.doan.entities.User;
import nvt.doan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody User user){
            return ResponseEntity.ok(userService.save(user));
    }
    @GetMapping("/users")
    public ResponseEntity<?>  getAll(){
            return ResponseEntity.ok(userService.getAll());
    }
    @PutMapping("/users")
    public ResponseEntity<?> update(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }
    @PutMapping("/users/{userId}")
    public ResponseEntity<?> delete(@PathVariable int userId){
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }


}
