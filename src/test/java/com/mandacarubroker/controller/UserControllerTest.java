package com.mandacarubroker.controller;

import com.mandacarubroker.request.RequestUserDTO;
import com.mandacarubroker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

  @MockBean
  private UserService userService;

  @Autowired
  @InjectMocks
  private UserController userController;

  private RequestUserDTO newUser1;

  @BeforeEach
  void setup(){
    MockitoAnnotations.initMocks(this);
    this.newUser1 = new RequestUserDTO("aaa2",
        "a1Adwwero",
        "some@mail.com",
        "joao",
        "paulo",
        LocalDate.of(2000,1,1));
  }

  @Test
  void getAllUsers() {
    userController.getAllUsers();

    Mockito.verify(userService,Mockito.times(1)).getAllUsers();
  }

  @Test
  void getUserById() {
    userController.getUserById("0");

    Mockito.verify(userService,Mockito.times(1)).getUserById(Mockito.any());
  }

  @Test
  void createUser() {
    userController.createUser(newUser1);

    Mockito.verify(userService,Mockito.times(1)).createUser(Mockito.any());
  }

  @Test
  void updateUser() {
    userController.updateUser("0",newUser1);

    Mockito.verify(userService,Mockito.times(1)).updateUser(Mockito.any(),Mockito.any());
  }

  @Test
  void deleteUser() {
    userController.deleteUser("0");

    Mockito.verify(userService,Mockito.times(1)).deleteUser(Mockito.any());
  }
}