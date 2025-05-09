package com.huawei.hwcloudmodel.model.samplepoint;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: classes.dex */
public class SamplePoint implements Serializable {
    private Long endTime;

    @SerializedName("fieldsMetadata")
    private String fieldsMetadata;

    @SerializedName("fieldsModifyTime")
    private String fieldsModifyTime;
    private String key;
    private Long startTime;
    private String unit;
    private String value;

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getFieldsModifyTime() {
        return this.fieldsModifyTime;
    }

    public void setFieldsModifyTime(String str) {
        this.fieldsModifyTime = str;
    }

    public String toString() {
        return "SamplePoint{startTime=" + this.startTime + ", endTime=" + this.endTime + ", key='" + this.key + "', value='" + this.value + "', modifyTime='" + this.fieldsModifyTime + "', unit='" + this.unit + "'}";
    }

    public String getFieldsMetadata() {
        return this.fieldsMetadata;
    }

    public void setFieldsMetadata(String str) {
        this.fieldsMetadata = str;
    }
}
