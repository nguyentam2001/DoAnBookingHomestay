package nvt.doan.service.vnpay;

import nvt.doan.config.VNPayConfig;
import nvt.doan.dto.BookingRequest;
import nvt.doan.dto.WebPaymentDto;
import nvt.doan.entities.Booking;
import nvt.doan.entities.Room;
import nvt.doan.entities.User;
import nvt.doan.repository.BookingRepository;
import nvt.doan.repository.RoomRepository;
import nvt.doan.repository.UserRepository;
import nvt.doan.utils.Constant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static nvt.doan.utils.Constant.CHECKOUT_STATUS;
import static nvt.doan.utils.Constant.PAYMENT_SUCCESS_STATUS;

@Service
public class VNPayService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;

    @Transactional
    public WebPaymentDto createPayment(BookingRequest request){
        Booking booking= saveBooking(request);
        return createPaymentVNPay(booking.getRequestId(),request.getDepositPrice());
    }

    public Booking saveBooking(BookingRequest request){
        //Lấy thông tin user.
        Optional<User> user = userRepository.findByUsernameOrEmail(request.getUser().getEmail(),request.getUser().getEmail());
        //get room info
        Optional<Room> room = roomRepository.findById(request.getRoom().getRoomId());
        //instance booking
        ModelMapper mapper = new ModelMapper();
        Booking  booking = mapper.map(request,Booking.class);
        //set user, room,created date,status for booking
        booking.setUser(user.get());
        booking.setRoom(room.get());
        booking.setCreatedAt(LocalDateTime.now());//booking now
        booking.setBookingStatus(CHECKOUT_STATUS);//booking room is empty because user unpaid orders
        return  bookingRepository.save(booking);
    }

    public WebPaymentDto createPaymentVNPay(Integer requestId, Double totalPrice){
        //Save order
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_OrderInfo = "Thanh toan don hang";
        String orderType = "200000";
        String vnp_TxnRef = requestId.toString();
        String vnp_IpAddr = "0:0:0:0:0:0:0:1";
        String vnp_TmnCode = "8FJFOZEN";
        double amount = totalPrice* 100;
        Map vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf((int)amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        String bank_code = "NCB";
        if (bank_code != null && !bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");

//        vnp_Params.put("vnp_ReturnUrl", "http://localhost:8080/shop/checkout/order/" + request.getOrderId());
        vnp_Params.put("vnp_ReturnUrl", "http://localhost:8080/view/users/payment-success/"+requestId);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512("OLXAMRXDOXOFTRVTOWSHMDGBWMMPOLKJ", hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        WebPaymentDto paymentDto = WebPaymentDto.builder()
                .status(PAYMENT_SUCCESS_STATUS)
                .message("success")
                .url(paymentUrl)
                .build();
        return paymentDto;
    }
}
