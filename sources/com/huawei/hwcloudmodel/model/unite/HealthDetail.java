package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import java.util.List;

/* loaded from: classes.dex */
public class HealthDetail {
    private Long deviceCode;
    private Long endTime;
    private Integer mergedFlag;
    private String metadata;
    private String recordId;
    private List<SamplePoint> samplePoints;
    private Long startTime;
    private String timeZone;
    private Integer type;
    private long version;

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String str) {
        this.recordId = str;
    }

    public List<SamplePoint> getSamplePoints() {
        return this.samplePoints;
    }

    public void setSamplePoints(List<SamplePoint> list) {
        this.samplePoints = list;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String str) {
        this.metadata = str;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(long j) {
        this.version = j;
    }

    public Integer getMergedFlag() {
        return this.mergedFlag;
    }

    public void setMergedFlag(Integer num) {
        this.mergedFlag = num;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HealthDetail{startTime=");
        stringBuffer.append(this.startTime);
        stringBuffer.append(", endTime=").append(this.endTime);
        stringBuffer.append(", mergedFlag=").append(this.mergedFlag);
        stringBuffer.append(", type=").append(this.type);
        stringBuffer.append(", recordId='").append(this.recordId);
        stringBuffer.append("', deviceCode=").append(this.deviceCode);
        stringBuffer.append(", samplePoints=").append(this.samplePoints);
        stringBuffer.append(", metadata='").append(this.metadata);
        stringBuffer.append("', timeZone='").append(this.timeZone);
        stringBuffer.append("', version=").append(this.version).append('}');
        return stringBuffer.toString();
    }
}
