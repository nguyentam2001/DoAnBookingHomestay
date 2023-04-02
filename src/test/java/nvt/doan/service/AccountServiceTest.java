package nvt.doan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nvt.doan.entities.Booking;

import nvt.doan.entities.Role;
import nvt.doan.entities.User;
import nvt.doan.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountService.class})
@ExtendWith(SpringExtension.class)
class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AccountService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Role Name");
        role.setUser(new ArrayList<>());

        User user = new User();
        user.setAddress("42 Main St");
        user.setAge(1);
        user.setBookings(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setGender("Gender");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(role);
        user.setUserId(1);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = accountService.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByUsernameOrEmail((String) any(), (String) any());
    }

    /**
     * Method under test: {@link AccountService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Role Name");
        role.setUser(new ArrayList<>());

        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setRoleName("Role Name");
        role1.setUser(new ArrayList<>());
        User user = mock(User.class);
        when(user.getEmail()).thenThrow(new UsernameNotFoundException("Msg"));
        when(user.getPassword()).thenThrow(new UsernameNotFoundException("Msg"));
        when(user.getRole()).thenReturn(role1);
        doNothing().when(user).setAddress((String) any());
        doNothing().when(user).setAge(anyInt());
        doNothing().when(user).setBookings((List<Booking>) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFullName((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhone((String) any());
        doNothing().when(user).setRole((Role) any());
        doNothing().when(user).setUserId(anyInt());
        doNothing().when(user).setUsername((String) any());
        user.setAddress("42 Main St");
        user.setAge(1);
        user.setBookings(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setGender("Gender");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(role);
        user.setUserId(1);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(ofResult);
        assertThrows(UsernameNotFoundException.class, () -> accountService.loadUserByUsername("janedoe"));
        verify(userRepository).findByUsernameOrEmail((String) any(), (String) any());
        verify(user).getEmail();
        verify(user).getRole();
        verify(user).setAddress((String) any());
        verify(user).setAge(anyInt());
        verify(user).setBookings((List<Booking>) any());
        verify(user).setEmail((String) any());
        verify(user).setFullName((String) any());
        verify(user).setGender((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhone((String) any());
        verify(user).setRole((Role) any());
        verify(user).setUserId(anyInt());
        verify(user).setUsername((String) any());
    }

    /**
     * Method under test: {@link AccountService#loadUserByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Cannot pass null or empty values to constructor
        //       at nvt.doan.service.AccountService.loadUserByUsername(AccountService.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Role Name");
        role.setUser(new ArrayList<>());

        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setRoleName("Role Name");
        role1.setUser(new ArrayList<>());
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn(role1);
        doNothing().when(user).setAddress((String) any());
        doNothing().when(user).setAge(anyInt());
        doNothing().when(user).setBookings((List<Booking>) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFullName((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhone((String) any());
        doNothing().when(user).setRole((Role) any());
        doNothing().when(user).setUserId(anyInt());
        doNothing().when(user).setUsername((String) any());
        user.setAddress("42 Main St");
        user.setAge(1);
        user.setBookings(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setGender("Gender");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(role);
        user.setUserId(1);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(ofResult);
        accountService.loadUserByUsername("janedoe");
    }

    /**
     * Method under test: {@link AccountService#loadUserByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: A granted authority textual representation is required
        //       at nvt.doan.service.AccountService.loadUserByUsername(AccountService.java:29)
        //   See https://diff.blue/R013 to resolve this issue.

        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Role Name");
        role.setUser(new ArrayList<>());
        Role role1 = mock(Role.class);
        when(role1.getRoleName()).thenReturn("");
        doNothing().when(role1).setRoleId(anyInt());
        doNothing().when(role1).setRoleName((String) any());
        doNothing().when(role1).setUser((List<User>) any());
        role1.setRoleId(1);
        role1.setRoleName("Role Name");
        role1.setUser(new ArrayList<>());
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn(role1);
        doNothing().when(user).setAddress((String) any());
        doNothing().when(user).setAge(anyInt());
        doNothing().when(user).setBookings((List<Booking>) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFullName((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhone((String) any());
        doNothing().when(user).setRole((Role) any());
        doNothing().when(user).setUserId(anyInt());
        doNothing().when(user).setUsername((String) any());
        user.setAddress("42 Main St");
        user.setAge(1);
        user.setBookings(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setGender("Gender");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(role);
        user.setUserId(1);
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(ofResult);
        accountService.loadUserByUsername("janedoe");
    }

    /**
     * Method under test: {@link AccountService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername5() throws UsernameNotFoundException {
        when(userRepository.findByUsernameOrEmail((String) any(), (String) any())).thenReturn(Optional.empty());

        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("Role Name");
        role.setUser(new ArrayList<>());
        Role role1 = mock(Role.class);
        when(role1.getRoleName()).thenReturn("Role Name");
        doNothing().when(role1).setRoleId(anyInt());
        doNothing().when(role1).setRoleName((String) any());
        doNothing().when(role1).setUser((List<User>) any());
        role1.setRoleId(1);
        role1.setRoleName("Role Name");
        role1.setUser(new ArrayList<>());
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn(role1);
        doNothing().when(user).setAddress((String) any());
        doNothing().when(user).setAge(anyInt());
        doNothing().when(user).setBookings((List<Booking>) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFullName((String) any());
        doNothing().when(user).setGender((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPhone((String) any());
        doNothing().when(user).setRole((Role) any());
        doNothing().when(user).setUserId(anyInt());
        doNothing().when(user).setUsername((String) any());
        user.setAddress("42 Main St");
        user.setAge(1);
        user.setBookings(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setFullName("Dr Jane Doe");
        user.setGender("Gender");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRole(role);
        user.setUserId(1);
        user.setUsername("janedoe");
        assertThrows(UsernameNotFoundException.class, () -> accountService.loadUserByUsername("janedoe"));
        verify(userRepository).findByUsernameOrEmail((String) any(), (String) any());
        verify(user).setAddress((String) any());
        verify(user).setAge(anyInt());
        verify(user).setBookings((List<Booking>) any());
        verify(user).setEmail((String) any());
        verify(user).setFullName((String) any());
        verify(user).setGender((String) any());
        verify(user).setPassword((String) any());
        verify(user).setPhone((String) any());
        verify(user).setRole((Role) any());
        verify(user).setUserId(anyInt());
        verify(user).setUsername((String) any());
        verify(role1).setRoleId(anyInt());
        verify(role1).setRoleName((String) any());
        verify(role1).setUser((List<User>) any());
    }
}

