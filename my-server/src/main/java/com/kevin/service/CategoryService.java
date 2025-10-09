package com.kevin.service;
import com.kevin.DTO.CategoryDTO;
import com.kevin.ResultEntity.Result;
import com.kevin.entity.Category;


public interface CategoryService {
    Result getCategoryPage(CategoryDTO categoryDTO);
    Category createCategory(Category category);
    Category updateCategoryStatusById(Long id, Integer status);
    Category updateCategory(Category category);
    Result deleteCategory(Long id);
    Category updateCategoryFields(Category categoryFE);
}
