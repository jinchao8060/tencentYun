package com.sin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionSequenceRequest {

    /**
     * 公共参数，本接口取值：GetActionSequence
     */
    @JsonProperty("Action")
    private String action = "GetActionSequence";

    /**
     * 公共参数，本接口取值：2018-03-01
     */
    @JsonProperty("Version")
    private String version = "2018-03-01";

    /**
     * 公共参数，详见产品支持的地域列表
     */
    @JsonProperty("Region")
    private String region = "ap-beijing";

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

    @Override
    public String toString() {
        return "ActionSequenceRequest{" +
                "action='" + action + '\'' +
                ", version='" + version + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
