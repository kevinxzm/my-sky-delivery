package com.kevin.service;

import com.kevin.DTO.DishDTO;
import com.kevin.DTO.DishPageDTO;
import com.kevin.ResultEntity.Result;

public interface DishService {
    void saveDish(DishDTO dishDTO);
    Result getDishPage(DishPageDTO dishPageDTO);
}
