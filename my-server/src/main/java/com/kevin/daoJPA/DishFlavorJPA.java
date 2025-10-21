package com.kevin.daoJPA;

import com.kevin.entity.DishFlavor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishFlavorJPA extends JpaRepository<DishFlavor, Long> {

}
