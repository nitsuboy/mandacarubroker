package com.mandacarubroker.controller;

import com.mandacarubroker.model.User;
import com.mandacarubroker.request.RequestUserDTO;
import com.mandacarubroker.service.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller User.
 */
@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable String id) {
    return userService.getUserById(id).orElse(null);
  }

  @PostMapping
  public ResponseEntity<String> createUser(@RequestBody RequestUserDTO data) {
    userService.createUser(data);
    return ResponseEntity.ok("User registred");
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable String id, @RequestBody RequestUserDTO data) {
    return userService.updateUser(id, data).orElse(null);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id) {
    userService.deleteUser(id);
  }

}
