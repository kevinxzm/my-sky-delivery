package com.kevin.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
//    private Integer id;
    private Long id;
    private String name;
    private String username;
    private String token;
}
