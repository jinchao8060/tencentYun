package com.sin.controller;


import com.sin.api.IDCardOCRApi;
import com.sin.dto.IDCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("idCard")
public class IDCardController {

    @Autowired
    private IDCardOCRApi idCardOCRApi;


    @PostMapping("idCardOcr")
    public String idCardOcr(HttpServletRequest request) {

        if (request instanceof StandardMultipartHttpServletRequest) {

            String base64 = null;
            StandardMultipartHttpServletRequest multipartRequest = (StandardMultipartHttpServletRequest) request;
            MultiValueMap<String, MultipartFile> fileMultiValueMap = multipartRequest.getMultiFileMap();
            if (fileMultiValueMap != null && !fileMultiValueMap.isEmpty()) {
                for (Map.Entry<String, List<MultipartFile>> fileEntry : fileMultiValueMap.entrySet()) {
                    if (fileEntry.getValue() != null && !fileEntry.getValue().isEmpty()) {
                        MultipartFile file = fileEntry.getValue().get(0);
                        String filename = file.getOriginalFilename();
                        BASE64Encoder base64Encoder = new BASE64Encoder();
                        try {
                            base64 = base64Encoder.encode(file.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                 }
            }

            Map<String, String[]> paramterMap = multipartRequest.getParameterMap();
            String[] cardsides = paramterMap.get("cardside");

            IDCardRequest idCardRequest = new IDCardRequest();
            idCardRequest.setImagebase64(base64);
            idCardRequest.setCardside(cardsides[0]);

            String s = idCardOCRApi.getDistinguishIDCard(idCardRequest);
            System.out.println(" s = " + s);
            return s;


        }
        return null;
    }
}