package nvt.doan.service;

import nvt.doan.dto.RateDTO;
import nvt.doan.entities.RoomRate;

import java.util.Optional;

public interface RoomRateService extends BaseService<RoomRate,Integer> {
    Integer findRoomRateByRoomIdAndUserId(Integer roomId, Integer userId);
    void  save(RateDTO rateDTO);
}
