package com.kevin.config;


import com.kevin.configure.AwsConfigure;
import com.kevin.util.GlobalFnClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalFnConfiguration {
    @Bean
    public GlobalFnClass globalFnClass(AwsConfigure awsConfigure) {
        System.out.println("配置globalFnClass 这个方法成为bean");
        return new GlobalFnClass(awsConfigure.getAccessKey(), awsConfigure.getSecretKey(), awsConfigure.getBucketName(),awsConfigure.getRegion());
    }
}
