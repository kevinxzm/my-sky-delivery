package com.kevin.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@ToString
public class CategoryDTO {

    Integer page;
    Integer pageSize;
    String name;
    Integer type;


}
