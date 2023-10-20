package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.ItemOrderUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrderUserRepository extends JpaRepository<ItemOrderUser, Long> {

    List<ItemOrderUser> findAllByProductId(Long id);

    List<ItemOrderUser> findAllByOrderUserId(Long id);

}
