package com.kevin.daoJPA;

import com.kevin.entity.DishFlavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DishFlavorJPA extends JpaRepository<DishFlavor, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM DishFlavor d WHERE d.dishId IN :ids")
    void deleteAllByDishIds(List<Long> ids);

    void deleteByDishId(Long id);

    List<DishFlavor> findByDishId(Long dishId);


}
