package nvt.doan.controller.user;

import nvt.doan.dto.HomestayClientDTO;
import nvt.doan.entities.Address;
import nvt.doan.entities.Homestay;
import nvt.doan.entities.Room;
import nvt.doan.service.AddressService;
import nvt.doan.service.HomestayService;
import nvt.doan.service.PromotionService;
import nvt.doan.service.RoomService;
import nvt.doan.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/view/users")
public class ViewHomeController {
    @Autowired
    AddressService addressService;

    @Autowired
    HomestayService homestayService;
    @Autowired
    PromotionService promotionService;

    @Autowired
    RoomService roomService;

    @GetMapping("/index")
    public  String homePage(Model model){
        model.addAttribute("address",addressService.getAll());

        model.addAttribute("promotions",promotionService.getAll());
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(1);
        model.addAttribute("checkIn",checkIn);
        model.addAttribute("checkOut",checkOut);
        return "users/index";
    }

    @GetMapping("/search")
    public String homePageSearch(@RequestParam("checkIn") String checkIn,
                                 @RequestParam("checkOut") String checkOut,
                                 @RequestParam("numberPersons") String numberPersons,
                                 @RequestParam("address") String address,Model model) {
        LocalDate checkInDate = DateUtil.convertStringToLocalDate(checkIn);
        LocalDate checkOutDate=DateUtil.convertStringToLocalDate(checkOut);
        model.addAttribute("checkIn",checkInDate);
        model.addAttribute("checkOut",checkOutDate);
        model.addAttribute("numberPersons",numberPersons);
        Address selectedAddress= addressService.getAddressById(Integer.parseInt(address));
        model.addAttribute("selectedAddress",selectedAddress);
        model.addAttribute("address",addressService.getAll());
        List<HomestayClientDTO> homestayClientDTOList = homestayService.getHomestays(checkInDate,checkOutDate,numberPersons,address);
        model.addAttribute("homestayClientDTOList",homestayClientDTOList);
        model.addAttribute("numberOfHomestays",homestayClientDTOList.size());
        return "users/list-homestay";
    }


    @GetMapping("/search/rooms")
    public String getRoomsSearch(@RequestParam("checkIn") String checkIn,
                                 @RequestParam("checkOut") String checkOut,
                                 @RequestParam("numberPersons") String numberPersons,
                                @RequestParam("address") String address,
                                @RequestParam("homestayId") String homestayId
                ,Model model) {
        LocalDate checkInDate = DateUtil.convertStringToLocalDate(checkIn);
        LocalDate checkOutDate=DateUtil.convertStringToLocalDate(checkOut);
        model.addAttribute("checkIn",checkInDate);
        model.addAttribute("checkOut",checkOutDate);
        model.addAttribute("numberPersons",numberPersons);
        Address selectedAddress= addressService.getAddressById(Integer.parseInt(address));
        model.addAttribute("selectedAddress",selectedAddress);
        model.addAttribute("address",addressService.getAll());
        Collection<Room> roomList = roomService.findAllRoomAvailableByHomestayId(checkInDate,checkOutDate,numberPersons,address,homestayId);
        model.addAttribute("roomList",roomList);
        return "users/list-apartment";
    }
}
