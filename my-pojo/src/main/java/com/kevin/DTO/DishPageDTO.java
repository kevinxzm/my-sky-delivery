package com.kevin.DTO;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DishPageDTO {
    private Integer page;
    private Integer pageSize;
    private String name;
    private Long categoryId;
    private Integer status;
}
