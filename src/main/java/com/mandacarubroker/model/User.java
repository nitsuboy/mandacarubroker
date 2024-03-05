package com.mandacarubroker.model;

import com.mandacarubroker.request.RequestUserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * User Model.
 */
@Table(name = "\"user\"")
@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String username;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private LocalDate birthDate;
  private double balance;

  /**
   * Criar User.
   */
  public User(RequestUserDTO requestUserdto) {
    this.username = requestUserdto.username();
    this.password = requestUserdto.password();
    this.email = requestUserdto.email();
    this.firstName = requestUserdto.firstName();
    this.lastName = requestUserdto.lastName();
    this.birthDate = requestUserdto.birthDate();
    this.balance = 0;
  }
}
