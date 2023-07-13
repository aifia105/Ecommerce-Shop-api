package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
