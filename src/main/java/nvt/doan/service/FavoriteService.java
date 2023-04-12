package nvt.doan.service;

import nvt.doan.entities.Favourite;

import java.util.List;

public interface FavoriteService extends BaseService<Favourite,Integer> {
    public void saveFavourite(String username, Integer roomId);
    public List<Favourite> findAllByEmail(String username);

    void  deleteFavouriteRoom(Integer roomId);
}
