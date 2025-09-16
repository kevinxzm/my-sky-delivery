package com.kevin.service;

import com.kevin.DTO.EmployeeDTO;
import com.kevin.entity.Emp;
import com.kevin.entity.Employee;

import java.util.ArrayList;

public interface AdminService {
    public String login();

    ArrayList searchAllEmp();

    Employee insertEmp(EmployeeDTO emp);

    Emp adminLogin(String username, String password);
}
