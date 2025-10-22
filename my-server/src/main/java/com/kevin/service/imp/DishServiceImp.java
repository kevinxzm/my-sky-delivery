package com.kevin.service.imp;

import com.kevin.DTO.DishDTO;
import com.kevin.DTO.DishPageDTO;
import com.kevin.Enum.UpdateEnum;
import com.kevin.ResultEntity.DishPageResult;
import com.kevin.ResultEntity.Result;
import com.kevin.aspect.AutoFillDateUser;
import com.kevin.controller.imp.DishControllerImp;
import com.kevin.daoJPA.DishFlavorJPA;
import com.kevin.entity.Dish;
import com.kevin.entity.DishFlavor;
import com.kevin.entity.DishWithCategoryProjection;
import com.kevin.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class DishServiceImp implements DishService {
    @Autowired
    private com.kevin.daoJPA.DishJPA dishJPA;
    @Autowired
    private DishFlavorJPA dishFlavorJPA;

    private static final Logger log = LoggerFactory.getLogger(DishControllerImp.class);

    @Override
    @AutoFillDateUser(UpdateEnum.ADD)
    @Transactional(rollbackFor = Exception.class)
    public void saveDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        // 第一个数据库请求；
        this.dishJPA.save(dish);

        ArrayList<DishFlavor> dishFlavors = dishDTO.getFlavors();

        for (DishFlavor dishFlavor : dishFlavors) {
            dishFlavor.setDishId(dish.getId());
        }

        dishFlavorJPA.saveAll(dishFlavors);

    }

    @Override
    public Result getDishPage(DishPageDTO dishPageDTO) {
        Pageable pageable = PageRequest.of(dishPageDTO.getPage() - 1, dishPageDTO.getPageSize());

        Page<DishWithCategoryProjection> dishPage = dishJPA.findDishPageByCondition(dishPageDTO.getName(), dishPageDTO.getCategoryId(), dishPageDTO
                .getStatus(), pageable);

        DishPageResult dishPageResult = new DishPageResult();

        List<DishWithCategoryProjection> records = dishPage.getContent();

        dishPageResult.setRecords(records);

        dishPageResult.setTotal(dishPage.getTotalElements());

        return Result.success(dishPageResult);
    }
}
