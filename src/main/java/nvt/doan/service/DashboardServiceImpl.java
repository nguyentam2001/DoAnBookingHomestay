package nvt.doan.service;

import nvt.doan.dto.BookingRequest;
import nvt.doan.dto.CardDTO;
import nvt.doan.dto.ChartDTO;
import nvt.doan.entities.Booking;
import nvt.doan.entities.Homestay;
import nvt.doan.entities.Room;
import nvt.doan.repository.BookingRepository;
import nvt.doan.repository.HomestayRepository;
import nvt.doan.repository.RoomRepository;
import nvt.doan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HomestayRepository homestayRepository;

    @Autowired
    RoomRepository roomRepository;




    public List<ChartDTO> calculateBookingToChart(){
        List<Booking> bookings = bookingRepository.findAll();
        List<Homestay> homestays = homestayRepository.findAll();
        List<ChartDTO> charts = new ArrayList<>();
        for (Homestay homestay: homestays) {
            ChartDTO chart = new ChartDTO();
            chart.setLabel(homestay.getHomestayName());
            double totalPrice=0;
            for(Booking booking : bookings){
                if((homestay.getHomestayId())==(booking.getRoom().getHomestay().getHomestayId())){
                    totalPrice+=booking.getDepositPrice()==null?0:booking.getDepositPrice()/10000.0;
                }
                chart.setData(totalPrice);
            }
            charts.add(chart);

        }
        return charts;
    }



}
