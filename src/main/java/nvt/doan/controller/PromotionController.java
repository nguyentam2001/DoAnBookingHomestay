package nvt.doan.controller;


import nvt.doan.entities.Homestay;
import nvt.doan.service.HomestayService;
import nvt.doan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PromotionController {
    @Autowired
    @Qualifier("homestayServiceImpl")
    HomestayService homestayService;

    @Autowired
    private StorageService service;
    @GetMapping("/")
    public ResponseEntity<?> getHomestays(){
        return  ResponseEntity.ok(homestayService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> saveHomestay(@RequestBody Homestay homestay){
        return  ResponseEntity.ok(homestayService.save(homestay));
    }
    @DeleteMapping("/{homestayId}")
    public ResponseEntity<?> deleteHomestay(@PathVariable("homestayId") Integer homestayId){
        homestayService.deleteById(homestayId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping ("/{homestayId}")
    public ResponseEntity<?> getById(@PathVariable int homestayId){
        return ResponseEntity.ok( homestayService.getHomestayById(homestayId));
    }
    @PostMapping("/image")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
}
