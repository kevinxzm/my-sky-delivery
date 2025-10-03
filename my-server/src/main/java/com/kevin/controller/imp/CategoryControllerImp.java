package com.kevin.controller.imp;

import com.kevin.DTO.CategoryDTO;
import com.kevin.controller.CategoryController;
import com.kevin.ResultEntity.Result;
import com.kevin.entity.Category;
import com.kevin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/category")
public class CategoryControllerImp implements CategoryController {

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
    public Result getCategoryPage(CategoryDTO categoryDTO) {
        return categoryService.getCategoryPage(categoryDTO);

    }

    @PostMapping("/status/{status}")
    public Result updateCategoryStatus(@PathVariable("status") Integer status, Long id) {
        Category categoryRes = categoryService.updateCategoryStatusById(id, status);
        return Result.success(categoryRes);
    }


}






















