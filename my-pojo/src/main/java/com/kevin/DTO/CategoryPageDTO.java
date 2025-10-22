package com.kevin.DTO;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoryPageDTO {

    Integer page;
    Integer pageSize;
    String name;
    Integer type;


}
