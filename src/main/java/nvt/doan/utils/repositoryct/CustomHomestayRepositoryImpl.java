package nvt.doan.utils.repositoryct;

import nvt.doan.dto.BookingDTO;
import nvt.doan.dto.HomestayClientDTO;
import nvt.doan.utils.DateUtil;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomHomestayRepositoryImpl implements CustomHomestayRepository{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<HomestayClientDTO> getHomestaysAndRoomAvailable(LocalDate checkIn, LocalDate checkOut, String numberPersons, String address) {
        return (List<HomestayClientDTO>) entityManager.createNativeQuery("select h.homestay_id as homestayId,h.description as description,h.homestay_name as homestayName, count(r.room_id) as roomAvailable, h.address_details as addressDetails, h.image as image\n" +
                        "  from homestay h \n" +
                        "  join address a on h.address_id = a.address_id\n" +
                        "  join room r on r.homestay_id= h.homestay_id \n" +
                        "  where r.room_id in (\n" +
                        "  select room_id from room where status=0 and room_id\n" +
                        " not in (select room_id from booking where ((start_date < :checkIn AND end_date > :checkOut) OR (start_date < :checkIn AND  :checkIn<end_date) OR (:checkIn<start_date AND :checkOut>start_date))\n" +
                        " AND  room_id  IN (SELECT room_id FROM Booking WHERE booking_status= 1) )\n" +
                        "  ) and a.address_id=:address and r.number_of_person >= :numberPersons\n" +
                        "  group by h.homestay_id").unwrap(Query.class)
                .setParameter("checkIn", checkIn)
                .setParameter("checkOut", checkOut)
                .setParameter("numberPersons",numberPersons)
                .setParameter("address",address)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return new HomestayClientDTO(
                                ((Number) objects[0]).intValue(),
                                (objects[2].toString()),
                                (objects[1].toString()),
                                ((Number) objects[3]).intValue(),new ArrayList<>(),
                                0.0,
                                0.0,
                                (objects[4].toString()),
                                (objects[5]==null?"homestay1.jpg":objects[5].toString())
                            );
                    }
                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();
    }
}
