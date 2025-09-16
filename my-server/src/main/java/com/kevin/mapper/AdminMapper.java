package com.kevin.mapper;
import com.kevin.entity.Dish;
import com.kevin.entity.Employee;
import org.apache.ibatis.annotations.Mapper;


import java.util.ArrayList;



@Mapper
public interface AdminMapper {
    public void insertEmp(Employee emp) ;

    public ArrayList<Dish> searchDish();

    public ArrayList<Employee> searchAllEmp();

    Employee adminLogin(String username);
}
