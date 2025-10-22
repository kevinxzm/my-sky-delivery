package com.kevin.ResultEntity;
import com.kevin.entity.DishWithCategoryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishPageResult {
    Long total;
    List<DishWithCategoryProjection> records;

}
