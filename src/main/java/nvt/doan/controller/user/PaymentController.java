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
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static nvt.doan.utils.Constant.DEPOSIT_RATIO;

@RestController
    @RequestMapping("/api/v1/users/pay")
public class PaymentController {
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private VNPayService vnPayService;
    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody BookingRequest request){
        httpSession.setAttribute("request", request);
        httpSession.setMaxInactiveInterval(100000);
//        request.setDepositPrice(request.getTotalPriceDiscount());
        request.setDepositPrice(request.getTotalPriceDiscount()*DEPOSIT_RATIO);
        //Thanh toán hết thì thanh toán thực tế = tổng số tiền thanh toán căn hộ
        request.setActualPayment(request.getTotalPriceDiscount());
        return ResponseEntity.ok(vnPayService.createPayment(request));
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> createDeposit(@RequestBody BookingRequest request){
        request.setDepositPrice(request.getTotalPriceDiscount()*DEPOSIT_RATIO);
        //Thanh toán đặt cọc thì số tiền thanh toán thực tế bằng số tiền đặt cọc
        request.setActualPayment(request.getTotalPriceDiscount()*DEPOSIT_RATIO);
        return ResponseEntity.ok(vnPayService.createPayment(request));
    }

}
