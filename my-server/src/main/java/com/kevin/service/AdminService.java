package com.kevin.service;

import com.kevin.DTO.EmployeeDTO;
import com.kevin.entity.Emp;
import com.kevin.entity.Employee;
import com.kevin.entityJPA.EmployeeJPA;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface AdminService {
    public String login();

    ArrayList searchAllEmp();

    Employee insertEmp(EmployeeDTO emp);

    Employee insertEmp(EmployeeDTO emp, HttpServletRequest request);

    Emp adminLogin(String username, String password);

    List<EmployeeJPA> searchEmp();
}
