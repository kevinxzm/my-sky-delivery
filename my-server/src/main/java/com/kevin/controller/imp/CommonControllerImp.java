package com.kevin.controller.imp;

import com.kevin.configure.AwsConfigure;
import com.kevin.ResultEntity.Result;
import com.kevin.configure.Test;
import com.kevin.controller.CommonController;
import com.kevin.util.GlobalFnClass;
import com.kevin.util.GlobalFnStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CommonControllerImp implements CommonController {
//    @Value("${aws-config.access-key}")
//    private String accessKey;

    @Autowired
    private Test test;

    @Autowired
    private AwsConfigure awsConfigure;

    @Autowired
    private GlobalFnClass globalFnClass;

    @PostMapping("/common/upload")
    public Result<Object> fileUpload(MultipartFile file) {
//        String fileUrl = GlobalFnStatic.updateFile(file);
        String fileUrl = globalFnClass.updateFile(file);
        return Result.success((Object) fileUrl);
    }
}
