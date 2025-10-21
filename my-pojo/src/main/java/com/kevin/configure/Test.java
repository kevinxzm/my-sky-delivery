package com.kevin.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class Test {
    @Value("${aws-config.accessKey}")
    public String accessKey;
}
