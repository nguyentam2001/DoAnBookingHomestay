package nvt.doan.controller;

import nvt.doan.entities.Room;
import nvt.doan.service.ImageService;
import nvt.doan.service.RoomService;
import org.hibernate.hql.internal.ast.tree.IdentNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @Autowired
    RoomService roomService;

    @PostMapping(value = "/api/v1/rooms/{id}/images", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadImage(@PathVariable Integer id,
                                         @RequestPart("file[]") MultipartFile[] files) throws IOException {
        Room room= roomService.getRoomById(id);
        return ResponseEntity.ok(imageService.uploadImageForRoom(room, files));
    }

    // Xóa ảnh
    @DeleteMapping("/api/v1/images/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Integer id){
        imageService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
