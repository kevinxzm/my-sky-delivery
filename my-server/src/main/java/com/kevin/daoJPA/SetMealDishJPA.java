package com.kevin.daoJPA;

import com.kevin.entity.Category;
import com.kevin.entity.SetMealDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetMealDishJPA extends JpaRepository<SetMealDish, Long> {

    public List<SetMealDish> findByDishId(Long dishId);
}
