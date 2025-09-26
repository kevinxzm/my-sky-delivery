package com.kevin.daoJPA;

import com.kevin.entityJPA.EmployeeJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminJPA extends JpaRepository<EmployeeJPA, Long> {

    List<EmployeeJPA> findByUsernameContaining (String username);
    Page<EmployeeJPA> findByPhoneContaining(String id, Pageable pageable);
}