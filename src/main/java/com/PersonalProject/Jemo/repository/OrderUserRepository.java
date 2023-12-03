package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.OrderUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderUserRepository extends JpaRepository<OrderUser, Long> {

    List<OrderUser> findAllByUserId(Long id);


}
