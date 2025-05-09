package com.huawei.maps.offlinedata.service.cloud.dto;

import com.huawei.maps.offlinedata.health.init.OfflineMapPoint;
import java.util.List;

/* loaded from: classes5.dex */
public class OfflineMapRequestDto extends BaseRequestDTO {
    private String countryIds;
    private String dataType;
    private String deviceType;
    private String fileId;
    private String language;
    private String p;
    private List<OfflineMapPoint> points;

    public List<OfflineMapPoint> getPoints() {
        return this.points;
    }

    public void setPoints(List<OfflineMapPoint> list) {
        this.points = list;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getP() {
        return this.p;
    }

    public void setP(String str) {
        this.p = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String str) {
        this.dataType = str;
    }

    public String getCountryIds() {
        return this.countryIds;
    }

    public void setCountryIds(String str) {
        this.countryIds = str;
    }

    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }
}
