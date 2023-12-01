package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.ItemOrderUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemOrderUserRepository extends JpaRepository<ItemOrderUser, String> {

    List<ItemOrderUser> findAllByProductId(String id);

    List<ItemOrderUser> findAllByOrderUserId(String id);

}
