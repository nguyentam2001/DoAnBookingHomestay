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
        return (List<BookingDTO>) entityManager.createNativeQuery("select request_id as requestId,room_name as roomName,start_date as startDate,end_date as endDate,sum(price) as totalPrice from booking b\n" +
                        "join room r on  r.room_id = b.room_id\n" +
                        "where b.user_id=:user_id group by request_id").unwrap(Query.class)
                .setParameter("user_id", userId)
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] objects, String[] strings) {
//BookingDTO(int requestId, LocalDate startDate, LocalDate endDate, String roomName, double totalPrice)
                        return new BookingDTO(
                                ((Number) objects[0]).intValue(),
                                (DateUtil.convertStringToLocalDate(objects[2].toString())),
                                (DateUtil.convertStringToLocalDate(objects[3].toString())),
                                ((String) objects[1]),
                                ((Number) objects[4]).intValue());
                    }
                    @Override
                    public List transformList(List list) {
                        return list;
                    }
                })
                .getResultList();
    }
}
