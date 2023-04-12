package nvt.doan.service;

import nvt.doan.entities.Favourite;
import nvt.doan.repository.client.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component("favouriteServiceImpl")
public class FavoriteServiceImpl extends BaseServiceImpl<Favourite,Integer> implements FavoriteService{
    @Autowired
    FavouriteRepository favouriteRepository;
    @Override
    public void saveFavourite(String username, Integer roomId) {
        List<Favourite> favourites = favouriteRepository.findAll();
        for (Favourite favourite : favourites) {
            if(favourite.getEmail().equals(username)&&favourite.getRoomId().equals(roomId)){
                return;
            }
        }
        Favourite favourite = new Favourite(null, username, roomId);
        favouriteRepository.save(favourite);
    }

    @Override
    public List<Favourite> findAllByEmail(String username) {
        return favouriteRepository.findAllByEmail(username);
    }

    @Override
    public void deleteFavouriteRoom(Integer roomId) {
        Favourite favourite= favouriteRepository.findFavouriteByRoomId(roomId);
        favouriteRepository.delete(favourite);
    }
}
