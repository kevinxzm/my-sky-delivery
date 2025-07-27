package com.kevin.controller.imp;

import com.kevin.controller.AdminController;
import com.kevin.entity.Dish;
import com.kevin.mapper.AdminMapper;
import com.kevin.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@RequestMapping("/api")
@Slf4j
public class AdminControllerImp implements AdminController {


    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;


    @GetMapping("/getAllDish")
    public ArrayList<Dish> test() {
        log.info("getAllDish controller");


        return adminMapper.searchDish();
    }

    @GetMapping("/getAllDish1")
    public String test2() {
        return "bbbb";
    }


    @PostMapping("/employee/login")
    public Result test2(String username, String password) {
        Emp emp = new Emp(1, "kevin", "xu", "123");
        Result res = new Result(1, emp, "msg");
        return res;
    }

}
