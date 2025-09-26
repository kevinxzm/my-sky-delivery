package com.kevin;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GlobalBean {
    private String id;
    private String name;
}

