package com.kevin.service;

import com.kevin.DTO.EmployeeDTO;
import com.kevin.entity.Emp;
import com.kevin.entity.Employee;
import com.kevin.ResultEntity.PageResult;
import com.kevin.entity.EmployeeJPA;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    Emp adminLogin(String username, String password);

    Employee insertEmp(EmployeeDTO emp);
    Employee insertEmp(EmployeeDTO emp, HttpServletRequest request);

    PageResult searchEmp(String page, String pageSize, String name);

    EmployeeJPA searchEmpById(Long id);

    EmployeeJPA updateEmpById(EmployeeJPA employeeDTO);

    EmployeeJPA updateEmpStatus(Long id, Integer status);

}
