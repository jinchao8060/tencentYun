package com.sin.controller;

import com.sin.api.IDCardOCRApi;
import com.sin.dto.IDCardRequest;
import com.sin.pic.TestReduce;
import com.sin.utils.Base64;
import com.sin.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@CrossOrigin
@RequestMapping("userTall")
public class TestCotroller {

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

        //拼装图片地址
        String url = FileUtil.getUrl(ip, wpost, fileUrl);
        String base64 = null;
        try {
            File file1 = new File(uploadFolder);
            //判断文件夹是否存在，不存在则创建。
            //随机图片名称
            String s1 = FileUtil.uploadFile(uploadFolder, file);
            //压缩图片，将上传的图片和压缩后的图片存到本地
            TestReduce.reduceImg(uploadFolder+"//"+s1,uploadFolder+"//aa"+s1,1000, 1000,null);
            IDCardRequest idCardRequest = new IDCardRequest();
            String imgFileToBase64 = Base64.getImgFileToBase64(uploadFolder + "//aa" + s1);
            idCardRequest.setImagebase64(imgFileToBase64);
            idCardRequest.setCardside(cardsides);
            String s = idCardOCRApi.getDistinguishIDCard(idCardRequest);
            System.out.println(" s = " + s);
            return s;
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
            return e.getMessage();
        }

    }

    @RequestMapping(value = "idCard")
    public String idCard() {
        return "/platform/userPageRequest/upload.htm";
    }
}
