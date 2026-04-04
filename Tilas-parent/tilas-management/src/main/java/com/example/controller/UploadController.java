package com.example.controller;

import com.example.tilas.pojo.Result;
import com.example.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传: {}",file.getOriginalFilename());
        //将文件上传阿里云oss
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传的url:{}",url);
        return  Result.success(url);
    }

    @DeleteMapping("/upload")
    public Result delete(@RequestParam String url) throws Exception {
        log.info("删除OSS文件: {}", url);
        aliyunOSSOperator.delete(url);
        return Result.success();
    }

}
