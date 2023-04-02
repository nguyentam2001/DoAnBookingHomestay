package nvt.doan.controller;

import nvt.doan.entities.Room;
import nvt.doan.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    @Qualifier("roomServiceImpl")
    RoomService roomService;
    @GetMapping("/")
    public ResponseEntity<?> getRooms(){
        return ResponseEntity.ok(roomService.getAll());
    }
    @PostMapping("/")
    public ResponseEntity<?> saveRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.save(room));
    }

    @GetMapping("/{homestayId}")
    public ResponseEntity<?> getRoomByHomestayId(@PathVariable Integer homestayId){
        return  ResponseEntity.ok(roomService.getRoomByHomestayId(homestayId));
    }

    @GetMapping("/room")
    public ResponseEntity<?> getRoomById(@RequestParam Integer roomId){
        return  ResponseEntity.ok(roomService.getRoomById(roomId));
    }
    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoomById(@PathVariable Integer roomId){
        roomService.deleteById(roomId);
        return  ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/create",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createRoom(@RequestPart("file[]") MultipartFile[] files,
                                        @RequestPart("applicant") String applicant){
        return  ResponseEntity.ok(roomService.createRoom(files,applicant));
    }
}

