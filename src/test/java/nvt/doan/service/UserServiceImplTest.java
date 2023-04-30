package nvt.doan.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import nvt.doan.entities.User;
import nvt.doan.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private JpaRepository jpaRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#getUser(Integer)}
     */
    @Test
    void testGetUser() {
        User user = new User();
        user.setActive(true);
        user.setAddress("42 Main St");
        user.setAge(1);
        user.setBookings(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setGender("Gender");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRoles(new ArrayList<>());
        user.setUserId(1);
        user.setUserImageUrl("https://example.org/example");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById((Integer) any())).thenReturn(ofResult);
        assertSame(user, userServiceImpl.getUser(1));
        verify(userRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUser(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUser2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at nvt.doan.service.UserServiceImpl.getUser(UserServiceImpl.java:18)
        //   See https://diff.blue/R013 to resolve this issue.

        when(userRepository.findById((Integer) any())).thenReturn(Optional.empty());
        userServiceImpl.getUser(1);
    }
}

