package com.kevin.controller.imp;


import com.kevin.DTO.DishDTO;
import com.kevin.DTO.DishPageDTO;
import com.kevin.ResultEntity.Result;
import com.kevin.controller.DishController;
import com.kevin.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishControllerImp implements DishController {

    private static final Logger log = LoggerFactory.getLogger(DishControllerImp.class);
    @Autowired
    private DishService dishService;

    @GetMapping("/{id}")
    public Result<DishDTO> getDishById(@PathVariable Long id) {
        return dishService.getDishById(id);

    }


    @PostMapping("")
    public Result saveDish(@RequestBody DishDTO dishDTO) {
        return dishService.saveDish(dishDTO);
    }


    @GetMapping("/page")
    public Result getDishPage(DishPageDTO dishPageDTO) {
        return dishService.getDishPage(dishPageDTO);
    }


    @DeleteMapping("")
    public Result deleteDish(@RequestParam List<Long> ids) throws Exception {
        return dishService.deleteDishBatch(ids);
    }


    @PutMapping("")
    public Result updateDish(@RequestBody DishDTO dishDTO) {
        return dishService.updateDish(dishDTO);
    }

    @PostMapping("/status/{status}")
    public Result updateDishStatus(@PathVariable Integer status, @RequestParam Long id) {
        return dishService.updateDishStatusById(id, status);
    }


}
