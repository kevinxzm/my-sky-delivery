package com.kevin.controller.imp;

import com.kevin.ResultEntity.Result;
import com.kevin.controller.ShopController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shop")
public class ShopControllerImp implements ShopController {
    private static final Logger log = LoggerFactory.getLogger(ShopControllerImp.class);
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/status")
    public Result getShopStatus() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object status = valueOperations.get("status");

        if (status == null) {
            return Result.error("Status not found in Redis");
        }
        // 1 是营业，0是打烊
        return Result.success(Integer.parseInt(status.toString()));
//        return Result.success(status.toString());
    }


    @PutMapping("/{status}")
    public Result getShopStatus(@PathVariable String status) {
        log.info("shops status is set as '{}' ", status);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("status", status);
        return Result.success(status);
    }


}
