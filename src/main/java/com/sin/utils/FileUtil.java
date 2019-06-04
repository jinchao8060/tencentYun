package com.sin.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class FileUtil {

    public static String  uploadFile(String path, MultipartFile file) throws Exception{
        String base64 = null;
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传后的路径
        fileName = UUID.randomUUID() + suffixName;
        String uppath=path+fileName;
        File targetFile = new File(uppath);
        file.transferTo(targetFile);
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        try {
//            base64 = base64Encoder.encode(file.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return  fileName;
    }
    public static String   getUrl(String ip,String wpost,String staticAccessPath) {
        String url="http://"+ip+":"+wpost+staticAccessPath;
        return  url;
    }



}
