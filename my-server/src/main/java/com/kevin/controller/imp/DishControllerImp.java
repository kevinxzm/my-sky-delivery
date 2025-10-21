package com.kevin.controller.imp;


import com.kevin.DTO.DishDTO;
import com.kevin.ResultEntity.Result;
import com.kevin.controller.DishController;
import com.kevin.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish")
public class DishControllerImp implements DishController {

    @Autowired
    private DishService dishService;

    @PostMapping("")
    public Result saveDish(@RequestBody DishDTO dishDTO) {

        dishService.saveDish(dishDTO);





//        return Result.success("dish & dish flavor successfull saved");
        return null;
    }




}
