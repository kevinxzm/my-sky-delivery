package com.kevin.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString


@Entity
@Table(name = "dish_flavor")
public class DishFlavor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dishId;
    private String name;
    private String value;

}
