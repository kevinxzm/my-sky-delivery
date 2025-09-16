package com.kevin.service.imp;

import com.kevin.DTO.EmployeeDTO;
import com.kevin.constant.StatusConstant;
import com.kevin.entity.Emp;
import com.kevin.entity.Employee;
import com.kevin.mapper.AdminMapper;
import com.kevin.service.AdminService;
import com.kevin.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Service
public class AdminServiceImp implements AdminService {


    private static final Logger log = LoggerFactory.getLogger(AdminServiceImp.class);
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Emp adminLogin(String username, String password) {
        Employee employee = adminMapper.adminLogin(username);
        Emp result = null;
        if (employee != null) {
            boolean flag = PasswordUtil.matchPassword(password, employee.getPassword());
            if (flag) {
                Emp emp = new Emp();
                BeanUtils.copyProperties(employee, emp);
                emp.setToken("token123");
                result = emp;
            }
        }
        return result;


//        Chatgpt版本
//        Employee employee = adminMapper.adminLogin(username, password);
//        if (employee != null && PasswordUtil.matchPassword(password, employee.getPassword())) {
//            return employee;
//        }
//        return null;
    }

    @Override
    public Employee insertEmp(EmployeeDTO empDTO) {
        Employee emp = new Employee();
        BeanUtils.copyProperties(empDTO, emp);
//        defaultpassword: 123
        emp.setPassword(PasswordUtil.encryptPassword("123"));
        emp.setStatus(StatusConstant.ENABLE);


        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());


        System.out.println("empDto = " + empDTO.toString());
        System.out.println("emp = " + emp.toString());

//        adminMapper.insertEmp(emp);
        return emp;

    }


    public String login() {
        return "login service";
    }


    public ArrayList searchAllEmp() {

        ArrayList empList = adminMapper.searchAllEmp();


        return empList;
    }


}
