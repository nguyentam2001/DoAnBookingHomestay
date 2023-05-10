package nvt.doan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nvt.doan.dto.RoomDTO;
import nvt.doan.dto.RoomResponse;
import nvt.doan.entities.Address;
import nvt.doan.entities.FileData;
import nvt.doan.entities.Homestay;
import nvt.doan.entities.Room;
import nvt.doan.repository.AddressRepository;
import nvt.doan.repository.HomestayRepository;
import nvt.doan.repository.RoomRepository;
import nvt.doan.utils.Constant;
import nvt.doan.utils.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
                .bedNumbers(request.getBedNumbers())
                .area(request.getArea())
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
//        long totalDate = (ChronoUnit.DAYS.between(checkIn, checkOut));
//        Collection<Room> rooms= roomRepository.findAllRoomAvailableByHomestayId(checkIn,checkOut,address,numberPersons,homestayId);
//        ModelMapper modelMapper = new ModelMapper();
//        List<RoomResponse> roomResponses = new ArrayList<>();
//        for (Room room : rooms){
//            RoomResponse newRoom = modelMapper.map(room,RoomResponse.class);
//            newRoom.setTotalPrice((long) (room.getPrice()*totalDate));
//            roomResponses.add(newRoom);
//        }
        return roomRepository.findAllRoomAvailableByHomestayId(checkIn,checkOut,address,numberPersons,homestayId);
    }





    @Override
        public Collection<RoomResponse> findAllRoomAvailableByHomestayId(LocalDate checkIn, LocalDate checkOut, String numberPersons, String address, String homestayId, String Sort) {
        List<Room> rooms = (List<Room>) roomRepository.findAllRoomAvailableByHomestayId(checkIn,checkOut,address,numberPersons,homestayId);
        List<RoomResponse> roomResponses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        rooms.forEach(room -> {
            Double roomRate= getRoomRate(room.getRoomId());
            RoomResponse newRoom = modelMapper.map(room,RoomResponse.class);
            newRoom.setRatePoint(roomRate==null?0:roomRate);
            roomResponses.add(newRoom);
        });
        switch(Sort) {
            case "asc":
                Collections.sort(roomResponses, Comparator.comparingDouble(RoomResponse::getPrice));
                break;
            case "desc":
                Collections.sort(roomResponses, Comparator.comparingDouble(RoomResponse::getPrice).reversed());
                break;
            case "descRate":
                Collections.sort(roomResponses, Comparator.comparingDouble(RoomResponse::getRatePoint));
                break;
            default:
                Collections.sort(roomResponses, Comparator.comparingDouble(RoomResponse::getRatePoint).reversed());
                break;
        }
        return roomResponses;
    }



    @Override
    public Double getRoomRate(Integer roomId) {
        return roomRepository.getAVGroomRate(roomId);
    }

    @Override
    public List<Room> getRoomListByIds(List<Integer> roomIds) {
        List<Room> roomList = new ArrayList<>();
        for (Integer roomId : roomIds) {
            Room room = getRoomById(roomId);
            roomList.add(room);
        }
        return roomList;
    }

    @Override
    public Collection<RoomResponse> findAllRoomFavorites(LocalDate checkIn, LocalDate checkOut, String numberPersons, String email) {
        List<Room> rooms = (List<Room>) roomRepository.findAllRoomFavorites(checkIn, checkOut, numberPersons,email);
        List<RoomResponse> roomResponses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        rooms.forEach(room -> {
            Double roomRate= getRoomRate(room.getRoomId());
            RoomResponse newRoom = modelMapper.map(room,RoomResponse.class);
            newRoom.setRatePoint(roomRate==null?0:roomRate);
            roomResponses.add(newRoom);
        });
        return roomResponses;
    }

    @Override
    public Collection<RoomResponse> findAllRoomFavorites(String email) {
        List<Room> rooms = (List<Room>) roomRepository.findAllRoomFavorites(email);
        List<RoomResponse> roomResponses = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        rooms.forEach(room -> {
            Double roomRate= getRoomRate(room.getRoomId());
            RoomResponse newRoom = modelMapper.map(room,RoomResponse.class);
            newRoom.setRatePoint(roomRate==null?0:roomRate);
            roomResponses.add(newRoom);
        });
        return roomResponses;
    }

}
