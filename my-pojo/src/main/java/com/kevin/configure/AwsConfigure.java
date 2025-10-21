package com.kevin.configure;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "aws-config")
@Data
@ToString
public class AwsConfigure {
    String accessKey;
    String secretKey;
    String region;
    String bucketName;
}
