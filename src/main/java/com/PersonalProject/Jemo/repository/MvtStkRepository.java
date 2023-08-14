package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStkRepository extends JpaRepository<MvtStk, Long> {

    @Query("select sum (m.quantity) from MvtStk m where m.product.id = :id")
    BigDecimal stockReelProduct(@Param("id") Long id);

    List<MvtStk> findAllByProductId(Long id);
}
