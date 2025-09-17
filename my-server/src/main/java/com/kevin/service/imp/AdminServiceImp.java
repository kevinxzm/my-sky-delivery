package com.kevin.service.imp;

import com.kevin.DTO.EmployeeDTO;
import com.kevin.constant.StatusConstant;
import com.kevin.entity.Emp;
import com.kevin.entity.Employee;
import com.kevin.mapper.AdminMapper;
import com.kevin.service.AdminService;
import com.kevin.util.GlobalFn;
import com.kevin.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
                System.out.println("get emp from data" + emp);
                emp.setToken("tokenid:" + emp.getId());
                result = emp;
            }
        }
        return result;
    }

    @Override
    public Employee insertEmp(EmployeeDTO empDTO) {
        System.out.println(1111111);
        return doInsertEmp(empDTO, null);
    }


    @Override
    public Employee insertEmp(EmployeeDTO empDTO, HttpServletRequest request) {
        System.out.println(2222222);
        return doInsertEmp(empDTO, request);
    }

    public Employee doInsertEmp(EmployeeDTO empDTO, HttpServletRequest request) {

        System.out.println("request111111" + request);
        System.out.println("request!=null!!!!   " + (request != null));
        System.out.println("2222" + request.getHeader("token"));
        Employee emp = new Employee();
        BeanUtils.copyProperties(empDTO, emp);

        if (request != null) {
            Long loginUserId = GlobalFn.extractId(request.getHeader("token"));
            System.out.println(loginUserId);
            emp.setCreateUser(loginUserId);
            emp.setUpdateUser(loginUserId);
        }
        emp.setPassword(PasswordUtil.encryptPassword("666666"));
        emp.setStatus(StatusConstant.ENABLE);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        System.out.println(emp);
        adminMapper.insertEmp(emp);
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
