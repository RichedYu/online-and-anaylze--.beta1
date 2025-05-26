package com.exam.service.impl;


import com.exam.config.minio.MinioProperties;
import com.exam.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private MinioClient minioClient;
    @Resource
    private MinioProperties properties;

    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(properties.getBucketName()).build());
        if (!bucketExists) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(properties.getBucketName()).build());
            // 设置桶的权限
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                    .bucket(properties.getBucketName())
                    .config(createBucketPolicyConfig(properties.getBucketName()))
                    .build());
        }
        // 设置文件名
        String filename = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
        // 上传文件
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(properties.getBucketName())
                .stream(file.getInputStream(), file.getSize(), -1)// 已知文件总大小，partSize可以传入-1，Minio会自动进行一个合理的分片传输大小
                .object(filename)
                .contentType(file.getContentType())// 设置文件类型
                .build());

        // 返回url
        //String url = properties.getEndpoint() + "/" + properties.getBucketName() + "/" + filename;
        return String.join("/",properties.getEndpoint(),properties.getBucketName(),filename);
    }

    @Override
    public boolean delete(String url) {
        // 从minio中删除文件
        try {
            url = url.replace(properties.getEndpoint() + "/", "");
           String bucketName = url.substring(0,url.indexOf("/"));
           String objectName = url.substring(url.indexOf("/")+1);
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String createBucketPolicyConfig(String bucketName) {

        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }
}
