package com.kevin.daoJPA;

import com.kevin.entity.Dish;
import com.kevin.entity.DishWithCategoryProjection;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DishJPA extends JpaRepository<Dish, Long> {
    List<Dish> findByCategoryId(Long id);

    Optional<Dish> findById(Long id);

    @Query("SELECT d.id AS id, d.name AS name, d.price AS price, d.categoryId AS categoryId, " +
            "c.name AS categoryName, d.image AS image, d.description AS description, d.status AS status, " +
            "d.createTime AS createTime, d.updateTime AS updateTime, d.createUser AS createUser, d.updateUser AS updateUser " +
            "FROM Dish d JOIN Category c ON d.categoryId = c.id " +
            "WHERE (:name IS NULL OR d.name LIKE CONCAT('%', :name, '%')) " +
            "AND (:status IS NULL OR d.status = :status) " +
            "AND (:categoryId IS NULL OR d.categoryId = :categoryId)")
    Page<DishWithCategoryProjection> findDishPageByCondition(@Param("name") String name, @Param("categoryId") Long categoryId, @Param("status") Integer status, Pageable pageable);
}
