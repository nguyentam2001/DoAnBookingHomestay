package nvt.doan.controller;


import nvt.doan.entities.Homestay;
import nvt.doan.entities.Promotion;
import nvt.doan.service.HomestayService;
import nvt.doan.service.PromotionService;
import nvt.doan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/promotion")
public class PromotionController {
    @Autowired
    @Qualifier("promotionServiceImpl")
    PromotionService promotionService;

    @Autowired
    private StorageService service;
    @GetMapping("/")
    public ResponseEntity<?> getPromotions(){
        return  ResponseEntity.ok(promotionService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> savePromotion(@RequestBody Promotion promotion){
        return  ResponseEntity.ok(promotionService.save(promotion));
    }
    @DeleteMapping("/{promotionId}")
    public ResponseEntity<?> deleteHomestay(@PathVariable("promotionId") Integer promotionId){
        promotionService.deleteById(promotionId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping ("/{promotionId}")
    public ResponseEntity<?> getById(@PathVariable Integer promotionId){
        return ResponseEntity.ok( promotionService.getPromotionById(promotionId));
    }

}
