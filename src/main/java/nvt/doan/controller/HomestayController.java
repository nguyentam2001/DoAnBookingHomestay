package nvt.doan.controller;

import nvt.doan.entities.Homestay;
import nvt.doan.service.HomestayService;
import nvt.doan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/homestays")
public class HomestayController {
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
    @PostMapping("/images")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("image") MultipartFile[] files) {
        return  ResponseEntity.ok( Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return uploadImageToFIleSystem(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()));
    }

    @GetMapping("/images/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(service.downloadImageFromFileSystem(fileName));

    }
}
