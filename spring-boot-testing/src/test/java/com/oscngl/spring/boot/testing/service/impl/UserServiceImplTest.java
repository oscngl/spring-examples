package com.oscngl.spring.boot.testing.service.impl;

import com.oscngl.spring.boot.testing.exception.EntityAlreadyExistsException;
import com.oscngl.spring.boot.testing.exception.EntityNotFoundException;
import com.oscngl.spring.boot.testing.model.User;
import com.oscngl.spring.boot.testing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl underTestService;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Mock
    private Clock clock;

    private static final ZonedDateTime NOW = ZonedDateTime.of(
            2022,
            6,
            15,
            12,
            30,
            30,
            0,
            ZoneId.of("GMT")
    );

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(clock.getZone()).thenReturn(NOW.getZone());
        when(clock.instant()).thenReturn(NOW.toInstant());
        underTestService = new UserServiceImpl(
                userRepository,
                clock
        );
    }

    @Test
    @DisplayName("Should get users")
    void should_get_users() {

        underTestService.getUsers();

        verify(userRepository).findAll();

    }

    @Test
    @DisplayName("Should get user by id when user with id exists")
    void should_get_user_by_id_when_user_with_id_exists() {

        Long id = 1L;
        User user = User
                .builder()
                .id(id)
                .build();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User expected = underTestService.getUserById(id);

        assertThat(expected).isEqualTo(user);

    }

    @Test
    @DisplayName("Should throw exception get user by id when user with id does not exists")
    void should_throw_exception_get_user_by_id_when_user_with_id_does_not_exists() {

        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> underTestService.getUserById(id));

    }

    @Test
    @DisplayName("Should get user by email when user with email exists")
    void should_get_user_by_email_when_user_with_email_exists() {

        String email = "email@gmail.com";
        User user = User
                .builder()
                .email(email)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User expected = underTestService.getUserByEmail(email);

        assertThat(expected).isEqualTo(user);

    }

    @Test
    @DisplayName("Should throw exception get user by email when user with email does not exists")
    void should_throw_exception_get_user_by_email_when_user_with_email_does_not_exists() {

        String email = "email@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> underTestService.getUserByEmail(email));

    }

    @Test
    @DisplayName("Should create user when user with email does not exists")
    void should_create_user_when_user_with_email_does_not_exists() {

        String email = "email@gmail.com";
        User user = User
                .builder()
                .email(email)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        underTestService.createUser(user);
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

    }

    @Test
    @DisplayName("Should create user when user with email already exists")
    void should_throw_exception_create_user_when_user_with_email_already_exists() {

        String email = "email@gmail.com";
        User user = User
                .builder()
                .email(email)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        assertThrows(EntityAlreadyExistsException.class,
                () -> underTestService.createUser(user));

    }

    @Test
    @DisplayName("Should update user when user with id exists but user with new email does not exists")
    void should_update_user_when_user_with_id_exists_but_user_with_new_email_does_not_exists() {

        Long id = 1L;
        String email = "email@gmail.com";
        String newEmail = "newEmail@gmail.com";

        User user = User
                .builder()
                .id(id)
                .email(email)
                .build();

        User newUser = User
                .builder()
                .id(id)
                .email(newEmail)
                .build();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(newEmail)).thenReturn(Optional.empty());

        underTestService.updateUser(id, newUser);
        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertThat(capturedUser).isEqualTo(newUser);

    }

    @Test
    @DisplayName("Should throw exception update user when user with id does not exists")
    void should_throw_exception_update_user_when_user_with_id_does_not_exists() {

        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> underTestService.updateUser(id, new User()));

    }

    @Test
    @DisplayName("Should throw exception update user when user with new email exists")
    void should_throw_exception_update_user_when_user_with_new_email_exists() {

        Long id = 1L;
        String email = "email@gmail.com";
        String newEmail = "newEmail@gmail.com";

        User user = User
                .builder()
                .id(id)
                .email(email)
                .build();

        User newUser = User
                .builder()
                .id(id)
                .email(newEmail)
                .build();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail(newEmail)).thenReturn(Optional.of(newUser));

        assertThrows(EntityAlreadyExistsException.class,
                () -> underTestService.updateUser(id, newUser));

    }

    @Test
    @DisplayName("Should delete user by id when user with id exists")
    void should_delete_user_by_id_when_user_with_id_exists() {

        Long id = 1L;

        User user = User
                .builder()
                .id(id)
                .build();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        underTestService.deleteUserById(id);
        verify(userRepository).deleteById(id);

    }

    @Test
    @DisplayName("Should throw exception delete user by id when user with id does not exists")
    void should_throw_exception_delete_user_by_id_when_user_with_id_does_not_exists() {

        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> underTestService.deleteUserById(id));

    }

    @Test
    @DisplayName("Should return true when birth date is before")
    void should_return_true_when_birth_date_is_before() {

        // One second before from now
        LocalDateTime birthDate = LocalDateTime.of(
                2022,
                6,
                15,
                12,
                30,
                29
        );

        User user = User
                .builder()
                .birthDate(birthDate)
                .build();

        boolean expected = underTestService.checkIfBirthDateIsValid(user);

        assertThat(expected).isTrue();

    }

    @Test
    @DisplayName("Should return false when birth date is after")
    void should_return_false_when_birth_date_is_after() {

        // One second after from now
        LocalDateTime birthDate = LocalDateTime.of(
                2022,
                6,
                15,
                12,
                30,
                31
        );

        User user = User
                .builder()
                .birthDate(birthDate)
                .build();

        boolean expected = underTestService.checkIfBirthDateIsValid(user);

        assertThat(expected).isFalse();

    }

}