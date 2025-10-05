package com.kevin.controller.imp;

import com.kevin.DTO.EmployeeDTO;
import com.kevin.DTO.EmployeeLoginDTO;
import com.kevin.ResultEntity.PageResult;
import com.kevin.ResultEntity.Result;
import com.kevin.context.BaseContext;
import com.kevin.controller.AdminController;
import com.kevin.entity.*;
import com.kevin.entity.EmployeeJPA;
import com.kevin.mapper.AdminMapper;
import com.kevin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/employee")
@Slf4j
public class AdminControllerImp implements AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;

    // 1.员工登陆 (pending: 返回token)
    @PostMapping("/login")
    public Result<Emp> adminLogin(@RequestBody EmployeeLoginDTO employeeDTO) {
        log.info("controller:  controller员工登陆");
        String username = employeeDTO.getUsername();
        String password = employeeDTO.getPassword();
        Emp employeeRes = adminService.adminLogin(username, password);
        return employeeRes == null ? Result.error("username or password is wrong") : Result.success(employeeRes);
    }

    // 2.增加员工
    @PostMapping("")
    public Result insertEmp(@RequestBody EmployeeDTO empDTO, HttpServletRequest request) {
        log.info("controller:  插入员工 insert");
        Employee e1 = adminService.insertEmp(empDTO, request);
        BaseContext.threadLocal.set(123L);
        return Result.success(e1);
    }

    // 3.查询员工
    @GetMapping("/page")
    public Result searchEmpPage(String page, String pageSize, String name) {
        log.info("controller:  查询员工");
        PageResult empListPageRes = adminService.searchEmp(page, pageSize, name);
        return Result.success(empListPageRes);
    }


    // 4. 查询员工(根据id，回显）
    @GetMapping("/{id}")
    public Result searchEmpById(@PathVariable Long id) {
        log.info("controller:  查询员工(根据id，回显） id:{}", id);
        EmployeeJPA employee = adminService.searchEmpById(id);
        return Result.success(employee);
    }

    //5. 编辑员工信息
    @PutMapping("")
    public Result updateEmpById(@RequestBody EmployeeJPA employeeDTO) {
        log.info("controller:  编辑员工信息 员工:{}", employeeDTO);
        EmployeeJPA employee = adminService.updateEmpById(employeeDTO);
        return Result.success(employee);
    }


    // 6. 编辑员工状态
    @PostMapping("/status/{status}")
    public Result updateEmpStatus(@PathVariable Integer status, Long id) {
        log.info("controller:  编辑员工状态 id:{} to status:{}", id, status);
        EmployeeJPA employeeRes = adminService.updateEmpStatus(id, status);
        return Result.success(employeeRes);
    }
}
