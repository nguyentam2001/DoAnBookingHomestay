package nvt.doan.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/apartment")
public class ApartmentController {

    @GetMapping("/favourites")
    public ResponseEntity<?> getFavourites(@RequestParam("userId") Integer userId,
                                           @RequestParam("roomId") Integer roomId) {
        return ResponseEntity.noContent().build();
    }


}
