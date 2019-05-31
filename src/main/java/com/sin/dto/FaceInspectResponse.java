package com.sin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 人脸核身返回类
 */
public class FaceInspectResponse {

    /**
     * 验证通过后的视频最佳截图照片，照片为BASE64编码后的值，jpg格式。
     */
    @JsonProperty("BestFrameBase64")
    private String bestFrameBase64;

    /**
     * 相似度，取值范围 [0.00, 100.00]。推荐相似度大于等于70时可判断为同一人，
     * 可根据具体场景自行调整阈值（阈值70的误通过率为千分之一，阈值80的误通过率是万分之一）
     */
    @JsonProperty("Sim")
    private Float sim;

    /**
     * 业务错误码，成功情况返回Success, 错误情况请参考下方错误码 列表中FailedOperation部分
     */
    @JsonProperty("Result")
    private String result;

    /**
     * 业务错误描述
     */
    @JsonProperty("Description")
    private String description;

    /**
     * 唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     */
    @JsonProperty("RequestId")
    private String requestId;


    public String getBestFrameBase64() {
        return bestFrameBase64;
    }

    public void setBestFrameBase64(String bestFrameBase64) {
        this.bestFrameBase64 = bestFrameBase64;
    }

    public Float getSim() {
        return sim;
    }

    public void setSim(Float sim) {
        this.sim = sim;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "FaceInspectResponse{" +
                "bestFrameBase64='" + bestFrameBase64 + '\'' +
                ", sim=" + sim +
                ", result='" + result + '\'' +
                ", description='" + description + '\'' +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}
