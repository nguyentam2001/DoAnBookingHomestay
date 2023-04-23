package nvt.doan.controller;

import nvt.doan.service.DashboardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    DashboardServiceImpl dashboardService;

    @GetMapping("/detail")
    public ResponseEntity<?> getDashBoard(){
            return ResponseEntity.ok(dashboardService.calculateBookingToChart());
    }
}
