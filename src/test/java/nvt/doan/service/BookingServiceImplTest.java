package nvt.doan.service;

import java.util.ArrayList;
import java.util.List;

import nvt.doan.dto.BookingDTO;
import nvt.doan.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {

    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @MockBean
    private JpaRepository jpaRepository;

    /**
     * Method under test: {@link BookingServiceImpl#findBookingDetailByUserId(Integer)}
     */
    @Test
    void testFindBookingDetailByUserId() {
        ArrayList<BookingDTO> bookingDTOList = new ArrayList<>();
        when(bookingRepository.findBookingDetailByUserId((Integer) any())).thenReturn(bookingDTOList);
        List<BookingDTO> actualFindBookingDetailByUserIdResult = bookingServiceImpl.findBookingDetailByUserId(1);
        assertSame(bookingDTOList, actualFindBookingDetailByUserIdResult);
        assertTrue(actualFindBookingDetailByUserIdResult.isEmpty());
        verify(bookingRepository).findBookingDetailByUserId((Integer) any());
    }

}