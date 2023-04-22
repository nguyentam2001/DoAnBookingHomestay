package nvt.doan.service;

import nvt.doan.dto.*;
import nvt.doan.entities.Booking;
import nvt.doan.entities.Promotion;
import nvt.doan.entities.Room;
import nvt.doan.entities.User;
import nvt.doan.repository.BookingRepository;
import nvt.doan.repository.RoomRepository;
import nvt.doan.repository.UserRepository;
import nvt.doan.service.account.AccountService;
import nvt.doan.utils.Constant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static nvt.doan.utils.Constant.*;

@Service
@Component("bookingServiceImpl")
public class BookingServiceImpl  implements BookingService{


    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    RoomRepository roomRepository;

    @Override
    public Page<Booking> findBookingByUserId(Integer userId,int currentPage, int pageSize) {
        return bookingRepository.findBookingByUserId(userId, PageRequest.of(currentPage - 1, pageSize));
    }

    @Override
    public List<BookingDTO> findBookingDetailByUserId(Integer userId) {
        return bookingRepository.findBookingDetailByUserId(userId);
    }

    @Override
    public BookingResponse getBookingResponse(LocalDate checkIn, LocalDate checkOut,  String numberPersons,String address,  String homestayId, String email,Integer roomId) {
        //Lấy ra phòng trống bởi roomId, checkin, checkout,...
         List<Room> rooms = (List<Room>) roomRepository.findAllRoomAvailableByHomestayId(checkIn,checkOut,address,numberPersons,homestayId);
            List<RoomResponse> roomResponseList = new ArrayList<>();
             ModelMapper modelMapper = new ModelMapper();
                rooms.forEach(room -> {
                    RoomResponse newRoom = modelMapper.map(room,RoomResponse.class);
                    roomResponseList.add(newRoom);
                });
                RoomResponse roomResponse = roomResponseList.stream()
                .filter(element -> element.getRoomId() == roomId)
                .findFirst()
                .orElse(new RoomResponse());
        //Map roomResponse to BookingResponse
        BookingResponse newBooking = modelMapper.map(roomResponse,BookingResponse.class);
        //Lấy ra mã giảm giá hiện tại của room nếu booking vào thời điểm trong khoản thời gian giảm giá
        List<Promotion> promotions = newBooking.getHomestay().getPromotions();
        //Thời gian hiện tại
        Double percentDiscount = 0.0;
        LocalDate currentDate = Constant.DATE_NOW;
        Optional<Promotion> matchingPromotion = promotions.stream()
                .filter(promotion -> currentDate.isAfter(promotion.getStartDate()) && currentDate.isBefore(promotion.getEndDate()))
                .findFirst();
        if (matchingPromotion.isPresent()) {
            percentDiscount = matchingPromotion.get().getPercentDiscount();
            newBooking.setPromotion(matchingPromotion.get());
        }
        //tính mã tổng giá theo số đêm thuê và giảm giá
        double totalPrice= getTotalPrice(checkIn,checkOut,newBooking.getPrice());
        newBooking.setTotalPrice((long) totalPrice);
        double totalPriceDiscount= getTotalPriceDiscount(totalPrice, percentDiscount);
        newBooking.setTotalPriceDiscount(totalPriceDiscount);
        //Tổng số ngày thuê
        newBooking.setTotalDate((int) ChronoUnit.DAYS.between(checkIn, checkOut));
        //Tính ngày huỷ phòng.
        //trước ngày thuê 5 ngày thì huỷ không mất  tiền, trong khoảg 5 ngày đó thì mất tiền full.
        newBooking.setLastDayCancel(checkIn.minusDays(Constant.DAY_CANCEL));
        //Tính đánh giá
        Double roomRatePoint=roomRepository.getAVGroomRate(roomId);
        newBooking.setRatePoint(roomRatePoint==null?0:roomRatePoint);
        //ngày đặt phòng
        newBooking.setStartDate(checkIn);
        newBooking.setEndDate(checkOut);
        newBooking.setNumberPersons(Integer.parseInt(numberPersons));
        return newBooking;
    }

    @Override
    public void checkOutBooking(Integer bookingId) {
        Optional<Booking> bookingOptional=bookingRepository.findById(bookingId);
        Booking booking=bookingOptional.get();
        booking.setBookingStatus(CHECKOUT_STATUS);
        bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(CancelReasonDTO cancelReason) {
        Optional<Booking> bookingOptional=bookingRepository.findById(cancelReason.getRequestId());
        Booking booking=bookingOptional.get();
        booking.setBookingStatus(WAIT_CANCEL);
        //Check if cancel day > checkIn date - 2day => set depositPrice=0
        if(DATE_NOW.isAfter(booking.getLastDayCancel())){
            booking.setDepositPrice(0.0);
        }
        booking.setReason(cancelReason.getReason());
        bookingRepository.save(booking);
    }
    //get list booking response
    @Override
    public List<BookingRequest> getAllBookingsResponse() {
        List<Booking> bookings= bookingRepository.findAll();
        List<BookingRequest> bookingsResponse=new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        bookings.forEach(booking -> {
            BookingRequest newBookingRequest = modelMapper.map(booking,BookingRequest.class);
            newBookingRequest.setUser(userRepository.findUserByBookingId(booking.getRequestId()).get());
            newBookingRequest.setRoom(roomRepository.findRoomByRequestId(booking.getRequestId()).get());
            bookingsResponse.add(newBookingRequest);
        });
        return bookingsResponse;
    }

    @Override
    public BookingRequest getBookingRequestById(Integer requestId) {
        Optional<Booking>bookingOptional=bookingRepository.findById(requestId);
        Booking booking= bookingOptional.get();
        ModelMapper modelMapper = new ModelMapper();
        BookingRequest newBookingRequest = modelMapper.map(booking,BookingRequest.class);
        newBookingRequest.setUser(userRepository.findUserByBookingId(booking.getRequestId()).get());
        newBookingRequest.setRoom(roomRepository.findRoomByRequestId(booking.getRequestId()).get());
        return newBookingRequest;
    }

    @Override
    public void confirmCancelBooking(CancelReasonDTO cancelReason) {
        Optional<Booking> bookingOptional=bookingRepository.findById(cancelReason.getRequestId());
        Booking booking=bookingOptional.get();
        booking.setBookingStatus(CANCEL_STATUS);
        bookingRepository.save(booking);
    }

    @Override
    public List<BookingRequest> getReportBookings() {
        return getAllBookingsResponse();
    }


    private double getTotalPriceDiscount(double totalPrice, Double percentDiscount) {
        return totalPrice-(totalPrice*(percentDiscount/100.0));
    }

    private Double getTotalPrice(LocalDate checkIn, LocalDate checkOut, double price) {
        int totalDate = Math.toIntExact(ChronoUnit.DAYS.between(checkIn, checkOut));
        return  price*totalDate;
    }

}
