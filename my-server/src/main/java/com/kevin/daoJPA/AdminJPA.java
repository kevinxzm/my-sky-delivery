package com.kevin.daoJPA;

import com.kevin.entityJPA.EmployeeJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminJPA extends JpaRepository<EmployeeJPA, Long> {

    Page<EmployeeJPA> findByUsernameContaining(String username, Pageable pageable);
    Page<EmployeeJPA> findByPhoneContaining(String id, Pageable pageable);
}