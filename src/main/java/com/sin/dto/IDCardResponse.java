package com.sin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 身份证验证输出参数实体类
 * @author zk
 */
public class IDCardResponse implements Serializable {

    /**
     * 姓名（人像面）
     */
    @JsonProperty("Name")
    private String name;

    /**
     * 性别（人像面）
     */
    @JsonProperty("Sex")
    private String sex;

    /**
     * 民族（人像面）
     */
    @JsonProperty("Nation")
    private String nation;

    /**
     * 出生日期（人像面）
     */
    @JsonProperty("Birth")
    private String birth;

    /**
     * 地址（人像面）
     */
    @JsonProperty("Address")
    private String address;

    /**
     * 身份证号（人像面）
     */
    @JsonProperty("IdNum")
    private String idnum;

    /**
     * 发证机关（国徽面）
     */
    @JsonProperty("Authority")
    private String authority;

    /**
     * 证件有效期（国徽面）
     */
    @JsonProperty("ValidDate")
    private String validdate;

    /**
     * IdCard，身份证照片，请求 CropIdCard 时返回；
     * Portrait，人像照片，请求 CropPortrait 时返回；
     * WarnInfos，告警信息（Code - 告警码，Msg - 告警信息内容），识别出翻拍件或复印件时返回。
     *
     * Code 告警码列表和释义：
     * -9103 身份证翻拍告警，
     * -9102 身份证复印件告警。
     */
    @JsonProperty("AdvancedInfo")
    private String advancedinfo;

    /**
     * 唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     */
    @JsonProperty("RequestId")
    private String requestid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getValiddate() {
        return validdate;
    }

    public void setValiddate(String validdate) {
        this.validdate = validdate;
    }

    public String getAdvancedinfo() {
        return advancedinfo;
    }

    public void setAdvancedinfo(String advancedinfo) {
        this.advancedinfo = advancedinfo;
    }

    public String getRequestid() {
        return requestid;
    }

    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }

    public IDCardResponse() {
    }

    public IDCardResponse(String name, String sex, String nation, String birth, String address, String idnum, String authority, String validdate, String advancedinfo, String requestid) {
        this.name = name;
        this.sex = sex;
        this.nation = nation;
        this.birth = birth;
        this.address = address;
        this.idnum = idnum;
        this.authority = authority;
        this.validdate = validdate;
        this.advancedinfo = advancedinfo;
        this.requestid = requestid;
    }

    @Override
    public String toString() {
        return "IDCardResponse{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", birth='" + birth + '\'' +
                ", address='" + address + '\'' +
                ", idnum='" + idnum + '\'' +
                ", authority='" + authority + '\'' +
                ", validdate='" + validdate + '\'' +
                ", advancedinfo='" + advancedinfo + '\'' +
                ", requestid='" + requestid + '\'' +
                '}';
    }
}
