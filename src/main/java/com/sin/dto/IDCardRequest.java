package com.sin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 身份证识别请求参数实体
 *
 * @author zk
 */

public class IDCardRequest implements Serializable {

    /**
     * 公共参数，本接口取值：IDCardOCR
     */
    @JsonProperty("Action")
    private String action = "IDCardOCR";

    /**
     * 公共参数，本接口取值：2018-11-19
     */
    @JsonProperty("Version")
    private String version = "2018-11-19";

    /**
     * 公共参数，详见产品支持的 地域列表。
     */
    @JsonProperty("Region")
    private String region = "ap-beijing";

    /**
     * 图片的 Base64 值。
     * 支持的图片格式：PNG、JPG、JPEG，暂不支持 GIF 格式。
     * 支持的图片大小：所下载图片经Base64编码后不超过 3M。图片下载时间不超过 3 秒。
     * 图片的 ImageUrl、ImageBase64 必须提供一个，如果都提供，只使用 ImageUrl。
     */
    @JsonProperty("ImageBase64")
    private String imagebase64;

    /**
     * 图片的 Url 地址。
     * 支持的图片格式：PNG、JPG、JPEG，暂不支持 GIF 格式。
     * 支持的图片大小：所下载图片经 Base64 编码后不超过 3M。图片下载时间不超过 3 秒。
     * 图片存储于腾讯云的 Url 可保障更高的下载速度和稳定性，建议图片存储于腾讯云。
     * 非腾讯云存储的 Url 速度和稳定性可能受一定影响。
     */
    @JsonProperty("ImageUrl")
    private String imageurl;

    /**
     * FRONT 为身份证有照片的一面（人像面），
     * BACK 为身份证有国徽的一面（国徽面）。
     */
    @JsonProperty("CardSide")
    private String cardside;

    /**
     * 可选字段，根据需要选择是否请求对应字段。
     * 目前包含的字段为：
     * CropIdCard，身份证照片裁剪，bool 类型，
     * CropPortrait，人像照片裁剪，bool 类型，
     * CopyWarn，复印件告警，bool 类型，
     * ReshootWarn，翻拍告警，bool 类型。
     */
    @JsonProperty("Config")
    private String config;
//    private String config = "{\"CopyWarn\":true,\"ReshootWarn\":true}";

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getImagebase64() {
        return imagebase64;
    }

    public void setImagebase64(String imagebase64) {
        this.imagebase64 = imagebase64;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getCardside() {
        return cardside;
    }

    public void setCardside(String cardside) {
        this.cardside = cardside;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public IDCardRequest() {
    }

    public IDCardRequest(String action, String version, String region, String imagebase64, String imageurl, String cardside, String config) {
        this.action = action;
        this.version = version;
        this.region = region;
        this.imagebase64 = imagebase64;
        this.imageurl = imageurl;
        this.cardside = cardside;
        this.config = config;
    }

    @Override
    public String toString() {
        return "IDCardRequest{" +
                "action='" + action + '\'' +
                ", version='" + version + '\'' +
                ", region='" + region + '\'' +
                ", imagebase64='" + imagebase64 + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", cardside='" + cardside + '\'' +
                ", config='" + config + '\'' +
                '}';
    }
}
