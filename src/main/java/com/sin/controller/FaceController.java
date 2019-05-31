package com.sin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sin.api.FaceApi;
import com.sin.dto.ActionSequenceResponse;
import com.sin.dto.FaceInspectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("face")
public class FaceController {

    private String validateData = "1,2" ;

    @Autowired
    private FaceApi faceApi;

    /**
     * 人脸核身
     * @param request
     * @return
     */
    @RequestMapping("faceInspect")
    public String faceInspect(HttpServletRequest request){

        if(request instanceof StandardMultipartHttpServletRequest) {

            String base64 = null;
            StandardMultipartHttpServletRequest multipartRequest=(StandardMultipartHttpServletRequest)request;
            MultiValueMap<String, MultipartFile> fileMultiValueMap = multipartRequest.getMultiFileMap();
            if(fileMultiValueMap!=null&&!fileMultiValueMap.isEmpty()) {
                for (Map.Entry<String, List<MultipartFile>> fileEntry: fileMultiValueMap.entrySet()) {
                    if(fileEntry.getValue()!=null&&!fileEntry.getValue().isEmpty()) {
                        MultipartFile file=fileEntry.getValue().get(0);
                        String filename = file.getOriginalFilename();

                        BASE64Encoder base64Encoder =new BASE64Encoder();
                        try {
                            base64 = base64Encoder.encode(file.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            Map<String, String[]> paramterMap=multipartRequest.getParameterMap();
            String[] idCards = paramterMap.get("idCard");
            String[] names = paramterMap.get("name");
            String[] livenessTypes = paramterMap.get("livenessType");

            FaceInspectRequest faceInspectRequest = new FaceInspectRequest();
            faceInspectRequest.setIdCard(idCards[0]);
            faceInspectRequest.setName(names [0]);
            faceInspectRequest.setLivenessType(livenessTypes [0]);
            faceInspectRequest.setValidateData(validateData);
            faceInspectRequest.setVideoBase64(base64);

            String s = faceApi.FaceInspect(faceInspectRequest);
            System.out.println("s = " + s);

            return s;


        }

        return null;
    }

    /**
     * 获取动作顺序
     * @return
     */
    @RequestMapping("getActionSequence")
    public String getActionSequence(){
        //res.FaceInspectResponse.ActionSequence==null ? alert(res.FaceInspectResponse.Error.Message) : alert(res.FaceInspectResponse.ActionSequence)
        String actionSequence = faceApi.getActionSequence();

        //赋值使用
        if (!StringUtils.isEmpty(actionSequence)) {
            ObjectMapper mapper = new ObjectMapper();
            ActionSequenceResponse actionSequenceResponse = null;
            try {
                actionSequenceResponse = mapper.readValue(actionSequence.toLowerCase(), ActionSequenceResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (actionSequenceResponse != null) {
                validateData= actionSequenceResponse.getActionSequence();
            }
        }
        return actionSequence;
    }


    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ActionSequenceResponse actionSequenceResponse = mapper.readValue("{\"ActionSequence\":\"2,1\",\"RequestId\":\"2bd07c95-5367-4d4c-8f8a-7f48eb4dc6e7\"}", ActionSequenceResponse.class);
        System.out.println("actionSequenceResponse = " + actionSequenceResponse);
    }

}
