package com.sin.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sin.dto.FaceInspectRequest;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.GetActionSequenceRequest;
import com.tencentcloudapi.faceid.v20180301.models.GetActionSequenceResponse;
import com.tencentcloudapi.faceid.v20180301.models.LivenessRecognitionRequest;
import com.tencentcloudapi.faceid.v20180301.models.LivenessRecognitionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 腾讯接口sdk相关
 */
@Component
public class FaceApi {

    /**
     * 必须参数,个人秘钥id
     */
    @Value("${tencent.secretId}")
    private static String TENCENT_SECRETID = "AKIDI0efZP3mLqDRYajZRn4DEVhlklyZqTvF";

    /**
     * 必须参数,个人秘钥key
     */
    @Value("${tencent.secretKey}")
    private static String TENCENT_SECRETKEY = "u55ItgBxz33zzBNmJymp4T2yDvXPGMN4";

    /**
     * 必须参数,地区
     */
    @Value("${tencent.region}")
    private static String TENCENT_REGION = "ap-beijing";

    /**
     * 接口
     */
    @Value("${tencent.api}")
    private static String TENCENT_API = "faceid.tencentcloudapi.com";


    /**
     * 请求获取动作顺序接口
     *
     * @return
     */
    public String getActionSequence() {
        try {

            Credential cred = new Credential("AKIDI0efZP3mLqDRYajZRn4DEVhlklyZqTvF", "u55ItgBxz33zzBNmJymp4T2yDvXPGMN4");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-beijing", clientProfile);

            String params = "{}";
            GetActionSequenceRequest req = GetActionSequenceRequest.fromJsonString(params, GetActionSequenceRequest.class);

            GetActionSequenceResponse resp = client.GetActionSequence(req);

            System.out.println(GetActionSequenceRequest.toJsonString(resp));
            return GetActionSequenceRequest.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return null;

    }

    /**
     * 请求人脸核身接口
     *
     * @return
     */
    public String FaceInspect(FaceInspectRequest faceInspectRequest) {

        LivenessRecognitionResponse resp = null;
        try {
            Credential cred = new Credential(TENCENT_SECRETID, TENCENT_SECRETKEY);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint(TENCENT_API);

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setSignMethod("TC3-HMAC-SHA256"); // 指定签名算法（默认为 HmacSHA256）
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, TENCENT_REGION, clientProfile);

            ObjectMapper mapper = new ObjectMapper();
            String params = mapper.writeValueAsString(faceInspectRequest);
            //String params = "{\"IdCard\":\"130726\",\"Name\":\"name\",\"VideoBase64\":\"bsae64\",\"LivenessType\":\"ACTION\",\"ValidateData\":\"1,2\"}";
            LivenessRecognitionRequest req = LivenessRecognitionRequest.fromJsonString(params, LivenessRecognitionRequest.class);

            resp = client.LivenessRecognition(req);

            System.out.println(LivenessRecognitionRequest.toJsonString(resp));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return LivenessRecognitionRequest.toJsonString(resp);
    }

    /**
     * 携带参数发送请求
     *
     * @return
     * @throws TencentCloudSDKException
     */
//    private static String request(String params,String url) throws TencentCloudSDKException {
//        Credential cred = new Credential(TENCENT_SECRETID, TENCENT_SECRETKEY);
//
//        HttpProfile httpProfile = new HttpProfile();
//        httpProfile.setEndpoint(url );
//
//        ClientProfile clientProfile = new ClientProfile();
//        clientProfile.setHttpProfile(httpProfile);
//
//        FaceidClient client = new FaceidClient(cred, TENCENT_REGION, clientProfile);
//
//        //String params = "{\"IdCard\":\"130726\",\"Name\":\"name\",\"VideoBase64\":\"bsae64\",\"LivenessType\":\"ACTION\",\"ValidateData\":\"1,2\"}";
//        LivenessRecognitionRequest req = LivenessRecognitionRequest.fromJsonString(params, LivenessRecognitionRequest.class);
//
//        LivenessRecognitionResponse resp = client.LivenessRecognition(req);
//
//        System.out.println(LivenessRecognitionRequest.toJsonString(resp));
//        return LivenessRecognitionRequest.toJsonString(resp);
//    }

}
