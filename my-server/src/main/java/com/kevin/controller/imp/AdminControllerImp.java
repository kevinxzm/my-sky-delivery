package com.kevin.controller.imp;

import com.kevin.DTO.EmployeeDTO;
import com.kevin.DTO.EmployeeLoginDTO;
import com.kevin.controller.AdminController;
import com.kevin.entity.Dish;
import com.kevin.entity.Emp;
import com.kevin.entity.Employee;
import com.kevin.entity.Result;
import com.kevin.mapper.AdminMapper;
import com.kevin.service.AdminService;
//import com.kevin.util.PasswordUtil;
import com.kevin.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/employee")
@Slf4j
public class AdminControllerImp implements AdminController {

    //    c46d0270-6421-487a-a8f2-cda2a49e82a1
    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;


    //员工登陆 (pending: 返回token)
    @PostMapping("/login")
    public Result<Emp> adminLogin(@RequestBody EmployeeLoginDTO employee) {
        String username = employee.getUsername();
        String password = employee.getPassword();
        Emp employeeRes = adminService.adminLogin(username, password);
        Result<Emp> res = employeeRes == null ? Result.error("username or password is wrong") : Result.success(employeeRes);
        return res;
    }

    //增加员工
    @PostMapping("")
    public Result insertEmp(@RequestBody EmployeeDTO empDTO, HttpServletRequest request) {
        System.out.println("controller" + request.getHeader("token"));
        Employee e1 = adminService.insertEmp(empDTO);
        return new Result(1, "haha", "msg");
    }


    @GetMapping("/getAllDish")
    public ArrayList<Dish> test() {
        log.info("getAllDish controller");
        return adminMapper.searchDish();
    }


//    @PostMapping("")
//    public Result insertEmp(String username, String password) {
//        System.out.println(username);
//        System.out.println(password);
//
//        return new Result(1, "res", "msg");
//    }


    // test api
    @GetMapping("/getAllDish1")
    public String test2(@RequestParam(required = false) String name) {
        return "receive " + (name != null ? name : "nothing");
    }

    @PostMapping("/getAllEmp")
    public Result getAllEmp(String testName) {
        ArrayList x = adminService.searchAllEmp();
        System.out.println(x);

        return new Result(1, testName, "msg");
    }


    @PostMapping("/postTest/{id}")
    public String ppostTest(@RequestBody Emp emp, @PathVariable int id) {
        System.out.println(emp);
        System.out.println(id);

        return "res" + emp.getName() + id;
    }


}
