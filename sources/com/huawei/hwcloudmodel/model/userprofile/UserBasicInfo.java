package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.watchface.mvp.model.webview.JsNetwork;
import java.io.Serializable;

/* loaded from: classes7.dex */
public class UserBasicInfo implements Serializable {

    @SerializedName("serialVersionUID")
    private static final long serialVersionUID = -3185409940237567052L;

    @SerializedName("birthday")
    private Integer birthday;

    @SerializedName("email")
    private String email;

    @SerializedName(CommonConstant.KEY_GENDER)
    private Integer gender;

    @SerializedName("height")
    private Integer height;

    @SerializedName(JsNetwork.NetworkType.MOBILE)
    private String mobile;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private Long modifyTime;

    @SerializedName("name")
    private String name;

    @SerializedName("portraitUrl")
    private String portraitUrl;

    @SerializedName("unitType")
    private Integer unitType;

    @SerializedName("weight")
    private Integer weight;

    @SerializedName("weightDb")
    private Double weightDb;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer num) {
        this.gender = num;
    }

    public Integer getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Integer num) {
        this.birthday = num;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer num) {
        this.weight = num;
    }

    public Double getWeightDb() {
        return this.weightDb;
    }

    public void setWeightDb(Double d) {
        this.weightDb = d;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer num) {
        this.height = num;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getPortraitUrl() {
        return this.portraitUrl;
    }

    public void setPortraitUrl(String str) {
        this.portraitUrl = str;
    }

    public Integer getUnitType() {
        return this.unitType;
    }

    public void setUnitType(Integer num) {
        this.unitType = num;
    }

    public Long getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Long l) {
        this.modifyTime = l;
    }

    public String toString() {
        return "UserBasicInfo{name='" + this.name + "', gender=" + this.gender + ", birthday=" + this.birthday + ", weight=" + this.weight + ", weightDb=" + this.weightDb + ", height=" + this.height + ", email=" + this.email + ", mobile='" + this.mobile + "', portraitUrl='" + this.portraitUrl + "', unitType='" + this.unitType + "', modifyTime=" + this.modifyTime + '}';
    }
}
