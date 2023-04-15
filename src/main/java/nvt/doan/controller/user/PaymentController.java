package nvt.doan.controller.user;

import nvt.doan.config.VNPayConfig;
import nvt.doan.dto.BookingRequest;
import nvt.doan.dto.WebPaymentDto;
import nvt.doan.service.vnpay.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
    @RequestMapping("/api/v1/users/pay")
public class PaymentController {

    @Autowired
    private VNPayService vnPayService;
    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody BookingRequest request){
        request.setDepositPrice(request.getTotalPriceDiscount());
        return ResponseEntity.ok(vnPayService.createPayment(request));
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> createDeposit(@RequestBody BookingRequest request){
        request.setDepositPrice(request.getTotalPriceDiscount()/3);
        return ResponseEntity.ok(vnPayService.createPayment(request));
    }

}
