package com.exam.controller;



import com.exam.service.FileService;
import com.exam.utils.result.Result;
import com.exam.utils.result.ResultCodeEnum;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


@Tag(name = "文件管理")
@RequestMapping("/exam/file")
@RestController
public class FileUploadController {

    @Resource
    private FileService fileService;

    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result<HashMap<String, String>> upload(@RequestParam MultipartFile file, @RequestParam(value = "url",required = false) String url) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        System.out.println("要删除的url为："+url);
        if (url!= null &&!url.isEmpty() && !url.equals("undefined")){
            if (fileService.delete(url)){
                System.out.println("删除成功！");
            }else {
                System.out.println("删除失败！");
            }
        }
        String newUrl = fileService.upload(file);
        if (newUrl == null){
            return Result.fail();
        }
        HashMap<String, String> result = new HashMap<>();
        result.put("url", newUrl);
        System.out.println("上传成功！url为："+newUrl);
        return Result.ok(result);
    }

}
