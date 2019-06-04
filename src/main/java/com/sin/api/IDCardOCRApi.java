package com.sin.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sin.dto.IDCardRequest;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 腾讯SDK接口
 */
@Component
public class IDCardOCRApi {


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
    private static String TENCENT_API = "ocr.tencentcloudapi.com";


    /**
     * 身份证识别接口
     *
     * @return
     */
    public String getDistinguishIDCard(IDCardRequest idCardOCRRequest) {

        IDCardOCRResponse resp = null;

        try {
            Credential cred = new Credential(TENCENT_SECRETID, TENCENT_SECRETKEY);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint(TENCENT_API);

            ClientProfile clientProfile = new ClientProfile();
            // 指定签名算法（默认为 HmacSHA256）
            clientProfile.setSignMethod("TC3-HMAC-SHA256");
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, TENCENT_REGION, clientProfile);

            ObjectMapper mapper = new ObjectMapper();

            String params = mapper.writeValueAsString(idCardOCRRequest);

            /* String params = ""; //{"ImageBase64":"123454","ImageUrl":"123"} */
            IDCardOCRRequest req = IDCardOCRRequest.fromJsonString(params, IDCardOCRRequest.class);

            resp = client.IDCardOCR(req);

            System.out.println(IDCardOCRRequest.toJsonString(resp));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        if (resp != null) {
            return IDCardOCRRequest.toJsonString(resp);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
