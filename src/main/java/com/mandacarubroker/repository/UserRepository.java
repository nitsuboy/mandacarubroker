package com.mandacarubroker.repository;

import com.mandacarubroker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Repositório do User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

  User findByUsername(String username);
}
