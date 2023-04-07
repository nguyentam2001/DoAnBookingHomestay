package nvt.doan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nvt.doan.dto.RoomDTO;
import nvt.doan.entities.FileData;
import nvt.doan.entities.Homestay;
import nvt.doan.entities.Room;
import nvt.doan.repository.HomestayRepository;
import nvt.doan.repository.RoomRepository;
import nvt.doan.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("roomServiceImpl")
public class RoomServiceImpl extends BaseServiceImpl<Room,Integer> implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HomestayRepository homestayRepository;


    @Autowired
    ImageService imageService;
    public Room getRoomById(Integer id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.get();
    }

    @Override
    public Room createRoom(MultipartFile[] files, String applicant) {
        try {
        ObjectMapper objectMapper = new ObjectMapper();
        RoomDTO request = objectMapper.readValue(applicant,RoomDTO.class);
        Optional<Homestay> homestay = homestayRepository.findById(request.getHomestayId());

        Room room=Room.builder()
                .roomName(request.getRoomName())
                .roomDescription(request.getRoomDescription())
                .numberOfPerson(request.getNumberOfPerson())
                .price(request.getPrice())
                .status(request.getStatus())
                .roomType(request.getRoomType())
                .homestay(homestay.get())
                .build();
        imageService.uploadImageForRoom(roomRepository.save(room),files);
        return room;
        } catch (RuntimeException | JsonProcessingException e){
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Room> getRoomByHomestayId(Integer homestayId) {
        return  roomRepository.findAllByHomestayId(homestayId);
    }

    @Override
    public Collection<Room> findAllRoomAvailableByHomestayId(LocalDate checkIn, LocalDate checkOut, String numberPersons, String address, String homestayId) {

        return roomRepository.findAllRoomAvailableByHomestayId(checkIn,checkOut,numberPersons,address,homestayId);
    }
}
