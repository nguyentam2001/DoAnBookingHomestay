package nvt.doan.controller.user;

import nvt.doan.dto.RateDTO;
import nvt.doan.service.RoomRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/rate")
public class RoomRateController {
    @Autowired
    @Qualifier("roomRateServiceImpl")
    RoomRateService roomRateService;
    @PostMapping("/save")
    public ResponseEntity<?> saveRate(@RequestBody RateDTO request){
           roomRateService.save(request);
          return ResponseEntity.noContent().build();
    }
}
