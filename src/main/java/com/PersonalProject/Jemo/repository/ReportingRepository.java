package com.PersonalProject.Jemo.repository;

import com.PersonalProject.Jemo.model.Reporting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportingRepository extends JpaRepository<Reporting , String> {

    List<Reporting> findAllByRapporteurId(String id);

}
