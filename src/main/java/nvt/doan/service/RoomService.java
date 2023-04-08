package nvt.doan.service;

import nvt.doan.dto.RoomResponse;
import nvt.doan.entities.Room;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface RoomService extends BaseService<Room,Integer>{
    Room getRoomById(Integer id);

    Room createRoom(MultipartFile[] files, String applicant);

    Collection<Room> getRoomByHomestayId(Integer homestayId);
    Collection<Room> findAllRoomAvailableByHomestayId(LocalDate checkIn, LocalDate checkOut, String numberPersons, String address, String homestayId);
    Collection<RoomResponse> findAllRoomAvailableByHomestayId(LocalDate checkIn, LocalDate checkOut, String numberPersons, String address, String homestayId, String priceSort);
    Double getRoomRate(Integer roomId);
}
