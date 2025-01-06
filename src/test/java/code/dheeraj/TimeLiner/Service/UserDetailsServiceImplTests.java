package code.dheeraj.TimeLiner.Service;

import code.dheeraj.TimeLiner.Entity.User;
import code.dheeraj.TimeLiner.Repository.UserRepository;
import code.dheeraj.TimeLiner.Services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUserNameTest() {
        User mockUser = new User();
        mockUser.setUserName("ram");
        mockUser.setPassword("qwerty");
        mockUser.setRoles(Collections.singletonList("USER"));
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);

        UserDetails user = userDetailsService.loadUserByUsername("raj");
        System.out.println(user.getUsername());
        Assertions.assertNotNull(user.getPassword());
    }

}
