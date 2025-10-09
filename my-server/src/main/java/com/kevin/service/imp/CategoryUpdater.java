package com.kevin.service.imp;

import com.kevin.Enum.UpdateEnum;
import com.kevin.aspect.AutoFillDateUser;
import com.kevin.context.BaseContext;
import com.kevin.daoJPA.CategoryJPA;
import com.kevin.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

// 暂时没用
@Component
public class CategoryUpdater {
    private static final Logger log = LoggerFactory.getLogger(CategoryUpdater.class);
    @Autowired
    CategoryJPA categoryJPA;

    @AutoFillDateUser(UpdateEnum.UPDATE)
    public Category updateCategoryFields(Category categoryFE) {
        log.info("CategoryServiceImp.updateCategoryFields!!!：{}", categoryFE.toString());
        System.out.println("!!!" + BaseContext.getTokenId());

        Category categoryDB = categoryJPA.findById(categoryFE.getId()).orElseThrow(() -> new EntityNotFoundException("Category not found for id: " + categoryFE.getId()));
        ;

        if (categoryFE.getStatus() != null) {
            categoryDB.setStatus(categoryFE.getStatus());
        }

        if (categoryFE.getName() != null) {
            categoryDB.setName(categoryFE.getName());
        }
        if (categoryFE.getSort() != null) {
            categoryDB.setSort(categoryFE.getSort());
        }
//        categoryDB.setUpdateTime(LocalDateTime.now());
//        categoryDB.setUpdateUser(BaseContext.getTokenId());
        return categoryJPA.save(categoryDB);
    }
}
