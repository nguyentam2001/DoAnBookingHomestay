package nvt.doan.controller;

import nvt.doan.entities.Homestay;
import nvt.doan.service.HomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/homestays")
public class HomestayController {
    @Autowired
    @Qualifier("homestayServiceImpl")
    HomestayService homestayService;
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


}
