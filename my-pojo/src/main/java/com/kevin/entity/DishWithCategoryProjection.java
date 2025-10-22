package com.kevin.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public interface DishWithCategoryProjection {
    Long getId();
    String getName();
    BigDecimal getPrice();
    Long getCategoryId();
    String getCategoryName(); // 来自 Category
    String getImage();
    String getDescription();
    Integer getStatus();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getCreateTime();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime getUpdateTime();
    Long getCreateUser();
    Long getUpdateUser();
}
