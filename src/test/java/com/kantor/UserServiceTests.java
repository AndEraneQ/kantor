package com.kantor;
import com.kantor.model.User;
import com.kantor.repository.UserRepository;
import com.kantor.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserServiceTests {

    private final UserRepository userRepository;
    private final UserService userService;
    private User mockUser;

    @Autowired
    public UserServiceTests(UserRepository userRepository, UserService userService){
        this.userService=userService;
        this.userRepository=userRepository;
        this.mockUser = new User("anderan", "anderan123", "Piotr",
                "Trojan", "trojanpiotrek@interia.pl", "698491315", "13 07 2003");
    }

    @Test
    public void shouldAddUserToDataBase(){
        userService.register(mockUser);
        Optional<User> userFromDatabase = userRepository.findById("anderan");
        assertTrue(userFromDatabase.isPresent());
        User user = userFromDatabase.get();
        assertThat(user)
                .isEqualToComparingFieldByField(mockUser);
    }

    @Test
    public void shouldNotAddUserWithSameLoginToDataBase(){
        userService.register(mockUser);

        ResponseEntity<?> response = userService.register(mockUser);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Login already exists.");
    }
}
