package com.kevin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public static void main(String[] args) {

        String[] array = new String[]{"a", "b", "c"};
        array[0] = "e";
        List<String> list = Arrays.asList(array);
        list.set(0, "d");
        System.out.println(list);
        System.out.println(Arrays.toString(array));

    }
}
