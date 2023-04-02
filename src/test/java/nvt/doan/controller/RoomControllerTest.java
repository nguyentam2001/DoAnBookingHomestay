package nvt.doan.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import nvt.doan.service.RoomService;
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

@ContextConfiguration(classes = {RoomController.class})
@ExtendWith(SpringExtension.class)
class RoomControllerTest {
    @Autowired
    private RoomController roomController;

    @MockBean(name = "roomServiceImpl")
    private RoomService roomService;

    /**
     * Method under test: {@link RoomController#getRoomByHomestayId(Integer)}
     */
    @Test
    void testGetRoomByHomestayId() throws Exception {
        when(roomService.getRoomByHomestayId((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/rooms/{homestayId}", 1);
        MockMvcBuilders.standaloneSetup(roomController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }



}

