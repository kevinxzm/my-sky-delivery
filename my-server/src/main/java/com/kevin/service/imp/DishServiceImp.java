package com.kevin.service.imp;

import com.kevin.DTO.DishDTO;
import com.kevin.DTO.DishPageDTO;
import com.kevin.Enum.UpdateEnum;
import com.kevin.ResultEntity.DishPageResult;
import com.kevin.ResultEntity.Result;
import com.kevin.aspect.AutoFillDateUser;
import com.kevin.daoJPA.DishFlavorJPA;
import com.kevin.daoJPA.SetMealDishJPA;
import com.kevin.entity.Dish;
import com.kevin.entity.DishFlavor;
import com.kevin.entity.DishWithCategoryProjection;
import com.kevin.entity.SetMealDish;
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
import java.util.Optional;


@Service
public class DishServiceImp implements DishService {
    @Autowired
    private com.kevin.daoJPA.DishJPA dishJPA;
    @Autowired
    private DishFlavorJPA dishFlavorJPA;

    @Autowired
    private SetMealDishJPA setMealDishJPA;

    private static final Logger log = LoggerFactory.getLogger(DishServiceImp.class);

    @Override
    @AutoFillDateUser(UpdateEnum.ADD)
    @Transactional(rollbackFor = Exception.class)
    public Result<String> saveDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        // 第一个数据库请求；
        this.dishJPA.save(dish);
        ArrayList<DishFlavor> dishFlavors = dishDTO.getFlavors();

        for (DishFlavor dishFlavor : dishFlavors) {
            dishFlavor.setDishId(dish.getId());
        }
        dishFlavorJPA.saveAll(dishFlavors);
        return Result.success("save successfully");

    }

    @Override
    public Result<DishPageResult> getDishPage(DishPageDTO dishPageDTO) {
        Pageable pageable = PageRequest.of(dishPageDTO.getPage() - 1, dishPageDTO.getPageSize());

        Page<DishWithCategoryProjection> dishPage = dishJPA.findDishPageByCondition(dishPageDTO.getName(), dishPageDTO.getCategoryId(), dishPageDTO
                .getStatus(), pageable);

        DishPageResult dishPageResult = new DishPageResult();

        List<DishWithCategoryProjection> records = dishPage.getContent();

        dishPageResult.setRecords(records);

        dishPageResult.setTotal(dishPage.getTotalElements());

        return Result.success(dishPageResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> deleteDishBatch(List<Long> ids) throws Exception {
        for (Long id : ids) {
            Optional<String> validationResult = validateDishBeforeDelete(id);
            if (validationResult.isPresent()) {
                return Result.error(validationResult.get());
            }
        }
        log.info("all the ids are able to be deleted");
        dishJPA.deleteAllById(ids);
        dishFlavorJPA.deleteAllByDishNames(ids);

        return Result.success("Dishes deleted successfully");
    }

    private Optional<String> validateDishBeforeDelete(Long id) {
        Dish dish = dishJPA.findById(id).orElseThrow(() -> new RuntimeException("can not find"));
        if (dish.getStatus() == 1) {
            return Optional.of(dish.getName() + " is active, so can not delete");
        }
        List<SetMealDish> setMealDishListForDishId = setMealDishJPA.findByDishId(dish.getId());
        if (!setMealDishListForDishId.isEmpty()) {
            return Optional.of(dish.getName() + " is in the set meal dish, so can not delete");
        }
        return Optional.empty();
    }


}
