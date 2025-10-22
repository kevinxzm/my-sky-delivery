package com.kevin.controller.imp;

import com.kevin.DTO.CategoryPageDTO;
import com.kevin.controller.CategoryController;
import com.kevin.ResultEntity.Result;
import com.kevin.entity.Category;
import com.kevin.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@RequestMapping("/api/category")
@RequestMapping("/category")
public class CategoryControllerImp implements CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryControllerImp.class);
    @Autowired
    CategoryService categoryService;

    @PostMapping("")
    public Result createCategory(@RequestBody Category category) {
        Category categoryRes = categoryService.createCategory(category);
        return Result.success(categoryRes);
    }

    @PutMapping("")
    public Result updateCategory(@RequestBody Category category) {
        Category categoryRes = categoryService.updateCategory(category);
        return Result.success(categoryRes);
    }

    @DeleteMapping("")
    public Result updateCategory(Long id) {
        return categoryService.deleteCategory(id);
    }


    @GetMapping("/page")
    public Result getCategoryPage(CategoryPageDTO categoryPageDTO) {
        return categoryService.getCategoryPage(categoryPageDTO);

    }

    @PostMapping("/status/{status}")
    public Result updateCategoryStatus(@PathVariable("status") Integer status, Long id) {
        Category categoryRes = categoryService.updateCategoryStatusById(id, status);
        return Result.success(categoryRes);
    }


    @GetMapping("/list")
    public Result updateCategoryStatus(Integer type) {
        log.info("GET method '/list' is executed. type is {}", type);
        List<Category> categoryList =  categoryService.getCategoryByType(type);
        return Result.success(categoryList );
    }


}






















