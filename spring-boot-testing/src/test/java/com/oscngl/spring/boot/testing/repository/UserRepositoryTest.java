package com.oscngl.spring.boot.testing.repository;

import com.oscngl.spring.boot.testing.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find by email when user with email exists")
    void should_find_by_email_when_user_with_email_exists() {

        String email = "email@gmail.com";
        User user = User.builder()
                .email(email)
                .build();
        userRepository.save(user);

        Optional<User> expected = userRepository.findByEmail(email);

        assertThat(expected).isPresent();
        assertThat(expected.get()).isEqualTo(user);

    }

    @Test
    @DisplayName("Should not find by email when user with email does not exists")
    void should_not_find_by_email_when_user_with_email_does_not_exists() {

        Optional<User> expected = userRepository.findByEmail("email@gmail.com");

        assertThat(expected).isEmpty();

    }

}