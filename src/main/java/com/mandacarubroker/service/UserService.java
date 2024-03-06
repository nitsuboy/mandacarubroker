package com.mandacarubroker.service;

import com.mandacarubroker.model.User;
import com.mandacarubroker.repository.UserRepository;
import com.mandacarubroker.request.RequestUserDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * Service User.
 */
@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Buscar User.
   */
  public Optional<User> getUserById(String id) {
    return userRepository.findById(id);
  }

  /**
   * Criar User.
   */
  public User createUser(RequestUserDTO data) {
    User newUser = new User(data);
    validateRequestUserDTO(data);
    return userRepository.save(newUser);
  }

  /**
   * Atualizar User.
   */
  public Optional<User> updateUser(String id, RequestUserDTO data) {
    User updatedUser = new User(data);
    validateRequestUserDTO(data);
    return userRepository.findById(id)
        .map(user -> {
          user.setUsername(updatedUser.getUsername());
          user.setPassword(updatedUser.getPassword());
          user.setEmail(updatedUser.getEmail());
          user.setFirstName(updatedUser.getFirstName());
          user.setLastName(updatedUser.getLastName());
          user.setEmail(updatedUser.getEmail());

          return userRepository.save(user);
        });
  }

  /**
   * Excluir User.
   */
  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }

  /**
   * Validar User.
   */
  public static void validateRequestUserDTO(RequestUserDTO data) {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      Validator validator = factory.getValidator();
      Set<ConstraintViolation<RequestUserDTO>> violations = validator.validate(data);

      if (!violations.isEmpty()) {
        StringBuilder errorMessage = new StringBuilder("Validation failed. Details: ");

        for (ConstraintViolation<RequestUserDTO> violation : violations) {
          errorMessage.append(
              String.format("[%s: %s], ", violation.getPropertyPath(), violation.getMessage()));
        }

        errorMessage.delete(errorMessage.length() - 2, errorMessage.length());

        throw new ConstraintViolationException(errorMessage.toString(), violations);
      }
    }
  }

}
