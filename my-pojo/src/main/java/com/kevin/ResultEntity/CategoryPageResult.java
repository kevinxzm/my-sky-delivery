package com.kevin.ResultEntity;
import com.kevin.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPageResult {
    Long total;
    List<Category> records;

}
