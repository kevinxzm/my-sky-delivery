package com.kevin.service.imp;

import com.kevin.DTO.EmployeeDTO;
import com.kevin.GlobalBean;
import com.kevin.constant.StatusConstant;
import com.kevin.context.BaseContext;
import com.kevin.daoJPA.AdminJPA;
import com.kevin.ResultEntity.PageResult;
import com.kevin.entity.EmployeeJPA;
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

    //1. 登录员工
    @Override
    public Emp adminLogin(String username, String password) {
        Employee employee = adminMapper.adminLogin(username);
        Emp result = null;
        if (employee != null) {
            boolean flag = PasswordUtil.matchPassword(password, employee.getPassword());
            if (flag) {
                Emp emp = new Emp();
                BeanUtils.copyProperties(employee, emp);
                emp.setToken("tokenid:" + emp.getId());
                result = emp;
            }
        }
        return result;
    }

    // 2. 插入员工
    @Override
    public Employee insertEmp(EmployeeDTO empDTO) {
        return doInsertEmp(empDTO, null);
    }

    // 2.1
    @Override
    public Employee insertEmp(EmployeeDTO empDTO, HttpServletRequest request) {
        return doInsertEmp(empDTO, request);
    }

    // 2.2
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

    // 3.查询员工
    @Override
    public PageResult searchEmp(String page, String pageSize, String name) {
        int pageNum = Integer.parseInt(page) - 1;
        int pageSizeNum = Integer.parseInt(pageSize);
        Pageable pageable = PageRequest.of(pageNum, pageSizeNum);
        Page<EmployeeJPA> empListPage = null;
        if (name != null && !"".equals(name)) {
            empListPage = adminJPA.findByUsernameContaining(name, pageable);
        } else {
            empListPage = adminJPA.findAll(pageable);
        }
        long total = empListPage.getTotalElements();

        List<EmployeeJPA> empList = empListPage.getContent();
        PageResult pageResult = new PageResult();
        pageResult.setRecords(empList);
        pageResult.setTotal(total);
        return pageResult;
        //        Page<EmployeeJPA> empListPage = adminJPA.findByPhoneContaining("130", pageable);
    }

    // 4. 查询员工(根据id，回显）
    @Override
    public EmployeeJPA searchEmpById(Long id) {
        return adminJPA.findById(id)
                .orElseThrow(() -> new RuntimeException("员工不存在，id=" + id));
    }


    //5. 编辑员工信息
    @Override
    public EmployeeJPA updateEmpById(EmployeeJPA employeeFE) {
        EmployeeJPA employeeDB = adminJPA.findById(employeeFE.getId()).orElse(null);
        log.info("DB" + adminJPA.toString());
        if (employeeFE.getName() != null) {
            employeeDB.setName(employeeFE.getName());
        }
        if (employeeFE.getPhone() != null) {
            employeeDB.setPhone(employeeFE.getPhone());
        }
        if (employeeFE.getSex() != null) {
            employeeDB.setStatus(employeeFE.getStatus());
        }

        employeeDB.setUpdateTime(LocalDateTime.now());
        employeeDB.setUpdateUser(BaseContext.getTokenId());

        EmployeeJPA employeeRes = adminJPA.save(employeeDB);

        return employeeRes;
    }


    //6. 编辑员工状态
    @Override
    public EmployeeJPA updateEmpStatus(Long id, Integer status) {
        EmployeeJPA employee = this.searchEmpById(id);
        employee.setStatus(status);
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(BaseContext.getTokenId());

//        EmployeeJPA employeeNew = new EmployeeJPA();
//        BeanUtils.copyProperties(employee, employeeNew);
//        employeeNew.setId(55L);
//        employeeNew.setUsername("new real name");
        EmployeeJPA employeeRes = adminJPA.save(employee);


        return employeeRes;
    }


}
