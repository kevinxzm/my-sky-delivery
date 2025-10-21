package com.kevin.daoJPA;

import com.kevin.entity.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryJPA extends JpaRepository<Category, Long> {
    // 这种写法必须要有name，如果是null就会查不到数据
    Page<Category> findByNameContainingAndType(String name, Integer type, Pageable pageable);

    @Query("select c from Category c " +
            "where (:name1 is null or c.name like concat('%', :name1, '%')) " +
            "and (:type is null or c.type = :type)")
    Page<Category> getCategoryPage(@Param("name1") String name1,
                                   @Param("type") Integer type,
                                   Pageable pageable);


    List<Category> findByType(Integer type);


}
