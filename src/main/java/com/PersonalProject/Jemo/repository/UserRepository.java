package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


  User findByEmail(String email);
  Optional<User> findUserByEmail(String email);
}
