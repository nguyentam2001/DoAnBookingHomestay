package nvt.doan.utils.repositoryct;

import nvt.doan.dto.BookingDTO;
import nvt.doan.utils.DateUtil;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomBookingRepositoryImpl implements CustomBookingRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BookingDTO> findBookingDetailByUserId(Integer userId) {
        return (List<BookingDTO>) entityManager.createNativeQuery("select request_id as requestId,homestay_name as homestayName,start_date as startDate,end_date as endDate,count(r.room_id) as numberOfRoom, price,address_name as addressName from booking b\n" +
                        "join homestay h on  b.homestay_id = h.homestay_id \n" +
                        "join users u on u.user_id= b.user_id\n" +
                        "join address a on a.address_id= h.address_id\n" +
                        "join room r on h.homestay_id= r.homestay_id\n" +
                        "where u.user_id=:user_id group by request_id").unwrap(Query.class)
                .setParameter("user_id", userId)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
                        return new BookingDTO(
                                ((Number) objects[0]).intValue(),
                                (DateUtil.convertStringToLocalDate(objects[2].toString())),
                                (DateUtil.convertStringToLocalDate(objects[3].toString())),
                                ((String) objects[1]),
                                ((Number) objects[4]).intValue(),
                                ((Number) objects[5]).doubleValue(),
                                ((String) objects[6]));
                    }
                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();
    }
}
