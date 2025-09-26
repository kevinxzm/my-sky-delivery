package com.kevin.service.imp;

import com.kevin.DTO.EmployeeDTO;
import com.kevin.GlobalBean;
import com.kevin.constant.StatusConstant;
import com.kevin.context.BaseContext;
import com.kevin.daoJPA.AdminJPA;
import com.kevin.entity.PageResult;
import com.kevin.entityJPA.EmployeeJPA;
import com.kevin.entity.Emp;
import com.kevin.entity.Employee;
import com.kevin.mapper.AdminMapper;
import com.kevin.service.AdminService;
import com.kevin.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class AdminServiceImp implements AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminServiceImp.class);
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminJPA adminJPA;

    @Autowired
    GlobalBean globalBean;

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


    // 3.查询员工
    @Override
    public List<EmployeeJPA> searchEmp() {

        Pageable pageable = PageRequest.of(1, 3);
//        Page<EmployeeJPA> empListPage = adminJPA.findAll(pageable);
//        List<EmployeeJPA> empList= adminJPA.findByUsernameContaining("bbb");
        Page<EmployeeJPA> empListPage = adminJPA.findByPhoneContaining("1370", pageable);


        List<EmployeeJPA> empList = empListPage.getContent();
        long total = empListPage.getTotalElements();
        int totalPage = empListPage.getTotalPages();

        System.out.println("total:" + total);
        System.out.println("totalPage:" + totalPage);


        return empList;
    }

    @Override
    public Employee insertEmp(EmployeeDTO empDTO) {
        return doInsertEmp(empDTO, null);
    }


    //    1.
    @Override
    public Employee insertEmp(EmployeeDTO empDTO, HttpServletRequest request) {
        return doInsertEmp(empDTO, request);
    }

    //    2.
    public Employee doInsertEmp(EmployeeDTO empDTO, HttpServletRequest request) {

        //   (1)     mybatis
        Employee emp = new Employee();
        BeanUtils.copyProperties(empDTO, emp);
        emp.setPassword(PasswordUtil.encryptPassword("666666"));
        emp.setStatus(StatusConstant.ENABLE);
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateUser(BaseContext.getTokenId());
        emp.setUpdateUser(BaseContext.getTokenId());
//        adminMapper.insertEmp(emp);

//        (2) JPA
        EmployeeJPA empJPA = new EmployeeJPA();
        BeanUtils.copyProperties(empDTO, empJPA);
        empJPA.setPassword(PasswordUtil.encryptPassword("666666"));
        empJPA.setStatus(StatusConstant.ENABLE);
        empJPA.setCreateTime(LocalDateTime.now());
        empJPA.setUpdateTime(LocalDateTime.now());
        empJPA.setCreateUser(BaseContext.getTokenId());
        empJPA.setUpdateUser(BaseContext.getTokenId());

        EmployeeJPA empRes = adminJPA.save(empJPA);
        BeanUtils.copyProperties(empRes, emp);

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
