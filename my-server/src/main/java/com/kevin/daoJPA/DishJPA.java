package com.kevin.daoJPA;

import com.kevin.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DishJPA extends JpaRepository<Dish, Long> {
    List<Dish> findByCategoryId(Long id);


}
