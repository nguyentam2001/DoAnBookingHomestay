package nvt.doan.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import nvt.doan.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BookingController.class})
@ExtendWith(SpringExtension.class)
class BookingControllerTest {
    @Autowired
    private BookingController bookingController;

    @MockBean(name = "bookingServiceImpl")
    private BookingService bookingService;

    /**
     * Method under test: {@link BookingController#findBookingDetailByUserId(Integer)}
     */
    @Test
    void testFindBookingDetailByUserId() throws Exception {
        when(bookingService.findBookingDetailByUserId((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/booking/bookings/{userId}", 1);
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link BookingController#findBookingDetailByUserId(Integer)}
     */
    @Test
    void testFindBookingDetailByUserId2() throws Exception {
        when(bookingService.findBookingDetailByUserId((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/booking/bookings/{userId}", 1);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

