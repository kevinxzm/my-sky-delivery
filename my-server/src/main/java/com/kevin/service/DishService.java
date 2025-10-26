package com.kevin.service;

import com.kevin.DTO.DishDTO;
import com.kevin.DTO.DishPageDTO;
import com.kevin.ResultEntity.Result;

import java.util.List;

public interface DishService {
    Result saveDish(DishDTO dishDTO);
    Result getDishPage(DishPageDTO dishPageDTO);
    Result deleteDishBatch(List<Long> ids) throws Exception;
    Result<DishDTO> getDishById(Long id);
    Result updateDish(DishDTO dishDTO);
    Result updateDishStatusById(Long id, Integer status);
}
