package com.kevin.service.imp;

import com.kevin.DTO.DishDTO;
import com.kevin.Enum.UpdateEnum;
import com.kevin.aspect.AutoFillDateUser;
import com.kevin.daoJPA.DishFlavorJPA;
import com.kevin.daoJPA.DishJPA;
import com.kevin.entity.Dish;
import com.kevin.entity.DishFlavor;
import com.kevin.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class DishServiceImp implements DishService {
    @Autowired
    private DishJPA dishJPA;
    @Autowired
    private DishFlavorJPA dishFlavorJPA;

    @Override
    @AutoFillDateUser(UpdateEnum.ADD)
    @Transactional(rollbackFor = Exception.class)
    public void saveDish(DishDTO dishDTO) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        // 第一个数据库请求；
        dishJPA.save(dish);

        ArrayList<DishFlavor> dishFlavors = dishDTO.getFlavors();

        for (DishFlavor dishFlavor : dishFlavors) {
            dishFlavor.setDishId(dish.getId());
        }

        if (true) {
            throw new RuntimeException("手动测试事务回滚！");
        }

        dishFlavorJPA.saveAll(dishFlavors);

    }
}
