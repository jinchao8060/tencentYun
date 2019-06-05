package com.sin.controller;

import com.sin.api.IDCardOCRApi;
import com.sin.dto.IDCardRequest;
import com.sin.pic.TestReduce;
import com.sin.utils.Base64;
import com.sin.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@CrossOrigin
@RequestMapping("userTall")
public class TestCotroller {


    private final Logger logger = LoggerFactory.getLogger(TestCotroller.class);
    private final IDCardOCRApi idCardOCRApi;

    @Autowired
    public TestCotroller(IDCardOCRApi idCardOCRApi) {
        this.idCardOCRApi = idCardOCRApi;
    }

    String fileUrl = "/upload/";
    String uploadFolder = "c://upload//";
    String ip = "127.0.0.1";
    String wpost = "8080";

    @PostMapping(value = "idCardOcr")
    public String idCardOcr(MultipartFile file, @RequestParam("cardsides") String cardsides) {

        /*拼装图片地址(用于生成在服务器地址)
        String url = FileUtil.getUrl(ip, wpost, fileUrl);*/
        try {
            //判断文件夹是否存在，不存在则创建。
            File file1 = new File(uploadFolder);
            if (!file1.exists() && !file1.isDirectory()) {
                logger.info("文件夹不存在");
                file1.mkdir();
                logger.info("但是已被创建");
            }
            //将上传的图片处理：随机图片名称
            String s1 = FileUtil.uploadFile(uploadFolder, file);
            //压缩图片，将上传的图片和压缩后的图片存到本地
            TestReduce.reduceImg(uploadFolder + "//" + s1, uploadFolder + "//aa" + s1, 1000, 1000, null);
            logger.info("压缩前图片名称：{}", s1);
            logger.info("压缩后图片名称：{}", "aa" + s1);
            IDCardRequest idCardRequest = new IDCardRequest();
            String imgFileToBase64 = Base64.getImgFileToBase64(uploadFolder + "//aa" + s1);
            idCardRequest.setImagebase64(imgFileToBase64);
            idCardRequest.setCardside(cardsides);
            String s = idCardOCRApi.getDistinguishIDCard(idCardRequest);
            logger.info(" 正确输出 = {}", s);
            return s;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }

    }

    @RequestMapping(value = "idCard")
    public String idCard() {
        return "/platform/userPageRequest/upload.htm";
    }
}
