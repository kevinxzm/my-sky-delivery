package com.kevin.service.imp;

import com.kevin.DTO.CategoryDTO;
import com.kevin.Enum.UpdateEnum;
import com.kevin.ResultEntity.CategoryPageResult;
import com.kevin.aspect.AutoFillDateUser;
import com.kevin.context.BaseContext;
import com.kevin.daoJPA.CategoryJPA;
import com.kevin.daoJPA.DishJPA;
import com.kevin.entity.Category;
import com.kevin.ResultEntity.Result;
import com.kevin.entity.Dish;
import com.kevin.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImp.class);

    @Autowired
    CategoryJPA categoryJPA;

    @Autowired
    @Lazy
    CategoryService selfProxy;

    @Autowired
    DishJPA dishJPA;

//    @Autowired
//    CategoryUpdater categoryUpdater;


    @Override
    public Result getCategoryPage(CategoryDTO categoryDTO) {
        log.info("Service: get category page, name={}, type={}, page={}, pageSize={}",
                categoryDTO.getName(), categoryDTO.getType(), categoryDTO.getPage(), categoryDTO.getPageSize());

        int pageNum = categoryDTO.getPage() - 1;
        int pageSizeNum = categoryDTO.getPageSize();

        Pageable pageable = PageRequest.of(pageNum, pageSizeNum);
        Page<Category> listPage = categoryJPA.getCategoryPage(categoryDTO.getName(), categoryDTO.getType(), pageable);
        List<Category> list = listPage.getContent();
        CategoryPageResult categoryPageResult = new CategoryPageResult();
        categoryPageResult.setTotal(listPage.getTotalElements());
        categoryPageResult.setRecords(list);
        return Result.success(categoryPageResult);
    }

    @Override
    @AutoFillDateUser(UpdateEnum.ADD)
    public Category createCategory(Category category) {
        category.setStatus(0);
//        category.setCreateTime(LocalDateTime.now());
//        category.setUpdateTime(LocalDateTime.now());
//        category.setCreateUser(BaseContext.getTokenId());
//        category.setUpdateUser(BaseContext.getTokenId());
        return categoryJPA.save(category);
    }


    @Override
    public Category updateCategoryStatusById(Long id, Integer status) {
        Category category = Category.builder().id(id).status(status).build();

        System.out.println("???" + BaseContext.getTokenId());
        return selfProxy.updateCategoryFields(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return selfProxy.updateCategoryFields(category);
    }

    @Override
    public Result deleteCategory(Long id) {
        List<Dish> dishList = dishJPA.findByCategoryId(id);
        if (dishList.size() > 0) {
            return Result.error("有数据不能删");
        }
        categoryJPA.deleteById(id);
        return Result.success("已经删除");
    }



    @AutoFillDateUser(UpdateEnum.UPDATE)
    public Category updateCategoryFields(Category categoryFE) {
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
        categoryDB.setUpdateTime(LocalDateTime.now());
        categoryDB.setUpdateUser(BaseContext.getTokenId());
        return categoryJPA.save(categoryDB);
    }
}
