package com.kevin;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.*;


@SpringBootTest
public class redisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisString() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("name", "valueName");

        Object object = valueOperations.get("name");
        String string = String.valueOf(object);
        System.out.println("!!!" + string);
    }

    @Test
    public void testRedisHash() {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();

        hashOperations.put("person1", "name", "reese");
        hashOperations.put("person1", "age", "35");
        hashOperations.put("person1", "career", "it");

        Object person1Keys = hashOperations.keys("person1");

        Object person1Values = hashOperations.values("person1");
        Object person1Age = hashOperations.get("person1", "age");

        Integer age = Integer.valueOf((String) person1Age);
        Object person1 = hashOperations.entries("person1");


        System.out.println("person keys" + person1Keys);
        System.out.println("person values" + person1Values);
        System.out.println("person1Age: " + age);
        System.out.println("person1" + person1);


        Map<String, Object> map = new HashMap<>();
        map.put("name", "Perlie");
        map.put("age", "30");
        map.put("email", "aa.com");
        hashOperations.putAll("person2", map);

        Map<String, Object> person2 = hashOperations.entries("person2");
        System.out.println("person2" + person2);
    }


    @Test
    public void testRedisList() {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        redisTemplate.delete("list");
        listOperations.leftPush("list", "a");
        listOperations.leftPushAll("list", "b", "c", "d");
        listOperations.leftPush("list", "c", "c_c");

        List list = listOperations.range("list", 0, -1);
        System.out.println("list length is : " + listOperations.size("list"));
        System.out.println(list);
    }


    @Test
    public void testRedisSet() {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        redisTemplate.delete("setList");
        setOperations.add("setList", "a");
        setOperations.add("setList", "b");
        setOperations.add("setList", "c");
        setOperations.add("setList", "d");
        setOperations.add("setList", "a");

        Set<String> setList = setOperations.members("setList");

        for (String list : setList) {
            System.out.println(list);
        }


        System.out.println(setList);
        System.out.println("list length is : " + setOperations.size("setList"));
//        setOperations.remove("setList", "a", "b");
        Set<String> setList2 = setOperations.members("setList");
        System.out.println(setList2);


        setOperations.add("setList2", "a");
        setOperations.add("setList2", "b");
        setOperations.add("setList2", "1");
        setOperations.add("setList2", "2");


        Set<String> intersect = setOperations.intersect("setList", "setList2");
        Set<String> union = setOperations.union("setList", "setList2");

        System.out.println(intersect);
        System.out.println(union);
    }


    @Test
    public void testRedisZset() {
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        redisTemplate.delete("zset");
        zSetOperations.add("zlist", "z", 100);
        zSetOperations.add("zlist", "y", 99);
        zSetOperations.add("zlist", "x", 98);

        Set<String> zSet = zSetOperations.range("zlist", 0, -1);
        Set<ZSetOperations.TypedTuple<String>> zSetWithScore = zSetOperations.rangeWithScores("zlist", 0, -1);
        System.out.println(zSet);
        System.out.println(zSetWithScore);

        for (ZSetOperations.TypedTuple<String> x : zSetWithScore) {
            System.out.println(x.getValue());
            System.out.println(x.getScore());
        }
    }
}









