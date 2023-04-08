package nvt.doan.controller.user;

import nvt.doan.dto.HomestayClientDTO;
import nvt.doan.dto.Priority;
import nvt.doan.dto.RoomResponse;
import nvt.doan.entities.Address;
import nvt.doan.entities.Homestay;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        Long totalDate = ChronoUnit.DAYS.between(checkInDate,checkOutDate);
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
                                @RequestParam("homestayId") String homestayId,
                                @RequestParam(name = "sort", defaultValue = "asc") String sort
                ,Model model) {
        List<Priority> priorityList = new ArrayList<>();
        priorityList.add(new Priority("asc","Giá từ thấp đến cao"));
        priorityList.add(new Priority("desc","Giá từ cao đến thấp"));
        priorityList.add(new Priority("ascRate","Đánh giá từ cao đến thấp"));
        priorityList.add(new Priority("descRate","Đánh giá từ thấp lên cao"));
        model.addAttribute("priorityList", priorityList);
        model.addAttribute("sort",sort);
        LocalDate checkInDate = DateUtil.convertStringToLocalDate(checkIn);
        LocalDate checkOutDate=DateUtil.convertStringToLocalDate(checkOut);
        Integer totalDate = Math.toIntExact(ChronoUnit.DAYS.between(checkInDate, checkOutDate));
        model.addAttribute("checkIn",checkInDate);
        model.addAttribute("checkOut",checkOutDate);
        model.addAttribute("totalDate",totalDate);
        model.addAttribute("numberPersons",numberPersons);
        Address selectedAddress= addressService.getAddressById(Integer.parseInt(address));
        model.addAttribute("selectedAddress",selectedAddress);
        model.addAttribute("address",addressService.getAll());
        Homestay homestay= homestayService.getHomestayById(Integer.parseInt(homestayId));
        Collection<RoomResponse> roomList = roomService.findAllRoomAvailableByHomestayId(checkInDate,checkOutDate,numberPersons,address,homestayId,sort);
        model.addAttribute("numberOfRoom", roomList.size());
        model.addAttribute("homestay", homestay);
        model.addAttribute("roomList",roomList);
        return "users/list-apartment";
    }
}
