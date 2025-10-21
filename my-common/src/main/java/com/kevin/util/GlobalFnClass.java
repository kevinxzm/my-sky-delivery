package com.kevin.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;


@Data
@ToString
@AllArgsConstructor
public class GlobalFnClass {
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String region;

    public String updateFile(MultipartFile file) {
        String key = file.getOriginalFilename(); // S3中存储的文件名和“路径”
        // 1️⃣ 初始化 S3 Client
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        S3Client s3 = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();
        PutObjectResponse response;
        try {
            response = s3.putObject(putRequest, software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
            System.out.println("上传成功，ETag: " + response.eTag());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String fileUrl = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
        return fileUrl;
    }
}
