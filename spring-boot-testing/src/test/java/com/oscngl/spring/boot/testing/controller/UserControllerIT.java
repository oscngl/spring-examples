package com.oscngl.spring.boot.testing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscngl.spring.boot.testing.exception.EntityAlreadyExistsException;
import com.oscngl.spring.boot.testing.exception.EntityNotFoundException;
import com.oscngl.spring.boot.testing.model.User;
import com.oscngl.spring.boot.testing.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(UserController.class)
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("IT - Should get users is OK")
    void should_get_users_isOK() throws Exception {

        when(userService.getUsers()).thenReturn(List.of(new User()));

        this.mockMvc.perform(
                get("/api/v1/users/")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @DisplayName("IT - Should get user by id when user with id exists is OK")
    void should_get_user_by_id_when_user_with_id_exists_isOK() throws Exception {

        Long id = 1L;

        User user = User
                .builder()
                .id(id)
                .build();

        when(userService.getUserById(id)).thenReturn(user);

        this.mockMvc.perform(
                        get("/api/v1/users/" + id)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));

    }

    @Test
    @DisplayName("IT - Should throw exception get user by id when user with id does not exists is Not Found")
    void should_throw_exception_get_user_by_id_when_user_with_id_does_not_exists_isNotFound() throws Exception {

        Long id = 1L;

        when(userService.getUserById(id)).thenThrow(new EntityNotFoundException(""));

        this.mockMvc.perform(
                        get("/api/v1/users/" + id)).andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("IT - Should get user by email when user with email exists is OK")
    void should_get_user_by_email_when_user_with_email_exists_isOK() throws Exception {

        String email = "email@gmail.com";

        User user = User
                .builder()
                .email(email)
                .build();

        when(userService.getUserByEmail(email)).thenReturn(user);

        this.mockMvc.perform(
                        get("/api/v1/users/email/" + email)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(email));

    }

    @Test
    @DisplayName("IT - Should throw exception get user by email when user with email does not exists is Not Found")
    void should_throw_exception_get_user_by_email_when_user_with_email_does_not_exists_isNotFound() throws Exception {

        String email = "email@gmail.com";

        when(userService.getUserByEmail(email)).thenThrow(new EntityNotFoundException(""));

        this.mockMvc.perform(
                        get("/api/v1/users/email/" + email)).andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("IT - Should create user when user with email does not exists is Created")
    void should_create_user_when_user_with_email_does_not_exists_isCreated() throws Exception {

        Long id = 1L;
        String email = "email@gmail.com";

        User user = User
                .builder()
                .id(id)
                .email(email)
                .build();

        when(userService.createUser(user)).thenReturn(user);

        this.mockMvc.perform(
                post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.email").value(email));

        verify(userService).createUser(user);

    }

    @Test
    @DisplayName("IT - Should update user when user with id exists but user with new email does not exists is OK")
    void should_update_user_when_user_with_id_exists_but_user_with_new_email_does_not_exists_isOK() throws Exception {

        Long id = 1L;
        String email = "email@gmail.com";

        User user = User
                .builder()
                .id(id)
                .email(email)
                .build();

        when(userService.updateUser(id, user)).thenReturn(user);

        this.mockMvc.perform(
                        put("/api/v1/users/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.email").value(email));

        verify(userService).updateUser(id, user);

    }

    @Test
    @DisplayName("IT - Should throw exception update user when user with id does not exists is Not Found")
    void should_throw_exception_update_user_when_user_with_id_does_not_exists_isNotFound() throws Exception {

        Long id = 1L;

        User user = User
                .builder()
                .id(id)
                .build();

        when(userService.updateUser(id, user)).thenThrow(new EntityNotFoundException(""));

        this.mockMvc.perform(
                        put("/api/v1/users/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("IT - Should throw exception update user when user with new email exists is Conflict")
    void should_throw_exception_update_user_when_user_with_new_email_exists_isConflict() throws Exception {

        Long id = 1L;

        User user = User
                .builder()
                .id(id)
                .build();

        when(userService.updateUser(id, user)).thenThrow(new EntityAlreadyExistsException(""));

        this.mockMvc.perform(
                        put("/api/v1/users/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(user))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());

    }

    @Test
    @DisplayName("IT - Should delete user by id when user with id exists is OK")
    void should_delete_user_by_id_when_user_with_id_exists_isOK() throws Exception {

        Long id = 1L;

        doNothing().when(userService).deleteUserById(id);

        this.mockMvc.perform(
                        delete("/api/v1/users/" + id))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Should throw exception delete user by id when user with id does not exists is Not Found")
    void should_throw_exception_delete_user_by_id_when_user_with_id_does_not_exists_isNotFound() throws Exception {

        Long id = 1L;

        doThrow(new EntityNotFoundException("")).when(userService).deleteUserById(id);

        this.mockMvc.perform(
                        delete("/api/v1/users/" + id))
                .andExpect(status().isNotFound());

    }

}