package com.mandacarubroker.service;

import com.mandacarubroker.model.Stock;
import com.mandacarubroker.model.User;
import com.mandacarubroker.repository.StockRepository;
import com.mandacarubroker.repository.UserRepository;
import com.mandacarubroker.request.RequestStockDTO;
import com.mandacarubroker.request.RequestUserDTO;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
  @Mock
  private UserRepository repository;

  @InjectMocks
  private UserService service;

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
    service.getAllUsers();

    Mockito.verify(repository, Mockito.times(1)).findAll();
  }

  @Test
  void getUserById() {
    service.getUserById("0");

    Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
  }

  @Test
  void createUserCase1() {
    service.createUser(newUser1);

    Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
  }

  @Test
  void createUserCase2() {
    newUser1 = new RequestUserDTO("aaa2",
        "a1Ad",
        "some@mail.com",
        "joao",
        "paulo",
        LocalDate.of(2000,1,1));

    Exception exception = assertThrows(ConstraintViolationException.class, () -> {service.createUser(newUser1);});

    Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    Assertions.assertEquals("Validation failed. Details: [password: Password must contain a uppercase letter, one digit and 8 or more characters]", exception.getMessage());
  }

  @Test
  void createUserCase3() {
    newUser1 = new RequestUserDTO("aaa2",
        "a1Adaaaaaaaa",
        "somemail.com",
        "joao",
        "paulo",
        LocalDate.of(2000,1,1));

    Exception exception = assertThrows(ConstraintViolationException.class, () -> {service.createUser(newUser1);});

    Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    Assertions.assertEquals("Validation failed. Details: [email: Email must be valid]", exception.getMessage());
  }

  @Test
  void createUserCase4() {
    newUser1 = new RequestUserDTO("aaa2",
        "a1Adaaaaaaa",
        "some@mail.com",
        "joao",
        "paulo",
        LocalDate.of(2020,1,1));

    Exception exception = assertThrows(ConstraintViolationException.class, () -> {service.createUser(newUser1);});

    Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    Assertions.assertEquals("Validation failed. Details: [birthDate: User must be over 18]", exception.getMessage());
  }

  @Test
  void createUserCase5() {
    newUser1 = new RequestUserDTO("",
        "a1Adaaaaaaaa",
        "some@mail.com",
        "joao",
        "paulo",
        LocalDate.of(2000,1,1));

    Exception exception = assertThrows(ConstraintViolationException.class, () -> {service.createUser(newUser1);});

    Mockito.verify(repository, Mockito.times(0)).save(Mockito.any());
    Assertions.assertEquals("Validation failed. Details: [username: Username cannot be blank]", exception.getMessage());
  }

  @Test
  void updateUser() {
    User user = new User("0",
        "aaa",
        "aaa",
        "aaa",
        "aaa",
        "aaa",
        LocalDate.of(2000,2,1),
        0);

    Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(user));
    service.updateUser("0",newUser1);

    Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    Mockito.verify(repository, Mockito.times(1)).findById(Mockito.any());
  }

  @Test
  void deleteUser() {
    service.deleteUser("0");

    Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.any());
  }

}