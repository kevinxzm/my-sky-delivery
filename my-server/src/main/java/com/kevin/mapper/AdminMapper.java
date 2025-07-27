package com.kevin.mapper;
import com.kevin.entity.Dish;
import org.apache.ibatis.annotations.Mapper;


import java.util.ArrayList;



@Mapper
public interface AdminMapper {
    public ArrayList<Dish> searchDish();
}
