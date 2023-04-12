package nvt.doan.service;

import nvt.doan.dto.HomestayClientDTO;
import nvt.doan.entities.Address;
import nvt.doan.entities.Homestay;
import nvt.doan.entities.Promotion;
import nvt.doan.repository.AddressRepository;
import nvt.doan.repository.HomestayRepository;
import nvt.doan.repository.PromotionRepository;
import nvt.doan.repository.RoomRepository;
import nvt.doan.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HomestayServiceImpl extends BaseServiceImpl<Homestay,Integer> implements HomestayService  {
    @Autowired
    HomestayRepository homestayRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    PromotionRepository promotionRepository;

    @Override
    public Homestay getHomestayById(int homestayId) {
        Optional<Homestay> homestay = homestayRepository.findById(homestayId);
        return  homestay.get();
    }
    @Override
    public List<HomestayClientDTO> getHomestays(LocalDate checkIn, LocalDate checkOut, String numberPersons, String address) {
        List<HomestayClientDTO> result = homestayRepository.getHomestaysAndRoomAvailable(checkIn, checkOut, numberPersons,address);
        result.forEach(homestayClientDTO -> {
            List<Promotion> promotionList = promotionRepository.findAllPromotionByHomestayId(homestayClientDTO.getHomestayId());
            homestayClientDTO.setPromotionList(promotionList);
            homestayClientDTO.setMinRoomPrice(roomRepository.getMinPriceOfRoom(homestayClientDTO.getHomestayId()));
            homestayClientDTO.setMaxRoomPrice(roomRepository.getMaxPriceOfRoom(homestayClientDTO.getHomestayId()));
        });
        return result;
    }

    @Override
    public Homestay save(Homestay homestay) {
        Optional<Address> address = addressRepository.findById(homestay.getAddress().getAddressId());
        homestay.setAddress(address.get());
        return homestayRepository.save(homestay);
    }
}
