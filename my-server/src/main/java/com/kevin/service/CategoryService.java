package com.kevin.service;
import com.kevin.DTO.CategoryPageDTO;
import com.kevin.ResultEntity.Result;
import com.kevin.entity.Category;

import java.util.List;


public interface CategoryService {
    Result getCategoryPage(CategoryPageDTO categoryPageDTO);
    Category createCategory(Category category);
    Category updateCategoryStatusById(Long id, Integer status);
    Category updateCategory(Category category);
    Result deleteCategory(Long id);
    Category updateCategoryFields(Category categoryFE);

    List<Category> getCategoryByType(Integer type);
}
