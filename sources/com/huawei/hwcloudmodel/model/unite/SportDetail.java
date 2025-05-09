package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcloudmodel.model.samplepoint.SamplePoint;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes7.dex */
public class SportDetail {
    private Integer appType;
    private Long deviceCode;
    private Long endTime;
    private List<String> mergedFields;
    private Integer mergedFlag;
    private String metadata;
    private String recordId;
    private List<SamplePoint> samplePoints;
    private SportBasicInfo[] sportBasicInfos;
    private Integer sportType;
    private Long startTime;
    private String timeZone;
    private Long version;

    public List<String> getMergedFields() {
        return this.mergedFields;
    }

    public void setMergedFields(List<String> list) {
        this.mergedFields = list;
    }

    public Integer getMergedFlag() {
        return this.mergedFlag;
    }

    public void setMergedFlag(Integer num) {
        this.mergedFlag = num;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String str) {
        this.recordId = str;
    }

    public Integer getSportType() {
        return this.sportType;
    }

    public void setSportType(Integer num) {
        this.sportType = num;
    }

    public Integer getAppType() {
        return this.appType;
    }

    public void setAppType(Integer num) {
        this.appType = num;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public SportBasicInfo[] getSportBasicInfos() {
        SportBasicInfo[] sportBasicInfoArr = this.sportBasicInfos;
        return sportBasicInfoArr == null ? new SportBasicInfo[0] : (SportBasicInfo[]) sportBasicInfoArr.clone();
    }

    public void setSportBasicInfos(SportBasicInfo[] sportBasicInfoArr) {
        this.sportBasicInfos = sportBasicInfoArr == null ? null : (SportBasicInfo[]) sportBasicInfoArr.clone();
    }

    public List<SamplePoint> getSamplePoints() {
        return this.samplePoints;
    }

    public void setSamplePoints(List<SamplePoint> list) {
        this.samplePoints = list;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String str) {
        this.metadata = str;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public String toString() {
        return "SportDetail{startTime=" + this.startTime + ", endTime=" + this.endTime + ", recordId='" + this.recordId + "', sportType=" + this.sportType + ", appType=" + this.appType + ", deviceCode=" + this.deviceCode + ", sportBasicInfos=" + Arrays.toString(this.sportBasicInfos) + ", samplePoints=" + this.samplePoints + ", metadata='" + this.metadata + "', timeZone='" + this.timeZone + "', version=" + this.version + ", mergedFields=" + HiJsonUtil.e(this.mergedFields) + ", mergedFlag=" + this.mergedFlag + '}';
    }
}
