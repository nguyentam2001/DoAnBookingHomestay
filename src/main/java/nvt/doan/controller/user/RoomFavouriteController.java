package nvt.doan.controller.user;

import nvt.doan.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/favourites")
public class RoomFavouriteController {
    @Autowired
    @Qualifier("favouriteServiceImpl")
    FavoriteService favoriteService;

    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteFavoriteById(@PathVariable int roomId ) {
        favoriteService.deleteFavouriteRoom(roomId);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping("/{roomId}")
    public ResponseEntity<?> getFavourites(@PathVariable("roomId") Integer roomId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name= auth.getName();
        favoriteService.saveFavourite(name,roomId);
        return  ResponseEntity.noContent().build();
    }

}
