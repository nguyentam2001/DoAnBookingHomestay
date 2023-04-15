package nvt.doan.service;

import nvt.doan.dto.BookingResponse;
import nvt.doan.dto.RateDTO;
import nvt.doan.entities.Booking;
import nvt.doan.entities.Room;
import nvt.doan.entities.RoomRate;
import nvt.doan.repository.BookingRepository;
import nvt.doan.repository.RoomRateRepository;
import nvt.doan.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Component("roomRateServiceImpl")
public class RoomRateServiceImpl extends BaseServiceImpl<RoomRate,Integer> implements RoomRateService  {
    @Autowired
    RoomRateRepository roomRateRepository;
    @Autowired
    BookingRepository bookRepository;
       @Override
    public Integer findRoomRateByRoomIdAndUserId(Integer roomId, Integer userId) {
        return roomRateRepository.findRateIdByRoomIdAndUserId(roomId, userId);
    }

//    @Override
//    public void save(RateDTO rateDTO) {
//       Integer rateId = findRoomRateByRoomIdAndUserId(rateDTO.getRoomId(),rateDTO.getUserId());
//        if(rateId!=null) {
//            //update Rate if rate is present
//           Optional<Booking> booking = bookRepository.findById(rateDTO.getRequestId());
//           if(booking.isPresent()){
//               RoomRate oldRate= booking.get().getRoomRate();
//               oldRate.setRatePoints(rateDTO.getRatePoints());
//               oldRate.setDescription(rateDTO.getDescription());
//               roomRateRepository.save(oldRate);
//           }
//        }else{
//            ModelMapper modelMapper = new ModelMapper();
//            RoomRate newRate = modelMapper.map(rateDTO,RoomRate.class);
//            Optional<Booking> booking = bookRepository.findById(rateDTO.getRequestId());
//            newRate.setBooking(booking.get());
//            roomRateRepository.save(newRate);
//        }
//    }
    @Override
    public void save(RateDTO rateDTO) {
            ModelMapper modelMapper = new ModelMapper();
            RoomRate newRate = modelMapper.map(rateDTO,RoomRate.class);
            Optional<Booking> booking = bookRepository.findById(rateDTO.getRequestId());
            newRate.setCreatedAt(LocalDateTime.now());
            newRate.setBooking(booking.get());
            roomRateRepository.save(newRate);
    }

}
