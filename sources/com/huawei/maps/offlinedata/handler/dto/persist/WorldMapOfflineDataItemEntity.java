package com.huawei.maps.offlinedata.handler.dto.persist;

/* loaded from: classes5.dex */
public class WorldMapOfflineDataItemEntity {
    private String cityId;
    private String cityName;
    private String countryId;
    private String countryName;
    private String fileCheck;
    private String fileId;
    private long id;
    private String lastModifiedTime;
    private String originalSize;
    private String packageSize;
    private String political;
    private String regionId;
    private String regionName;
    private String version;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String str) {
        this.countryId = str;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String str) {
        this.countryName = str;
    }

    public String getRegionId() {
        return this.regionId;
    }

    public void setRegionId(String str) {
        this.regionId = str;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String str) {
        this.regionName = str;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String str) {
        this.cityId = str;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public String getPolitical() {
        return this.political;
    }

    public void setPolitical(String str) {
        this.political = str;
    }

    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getPackageSize() {
        return this.packageSize;
    }

    public void setPackageSize(String str) {
        this.packageSize = str;
    }

    public String getOriginalSize() {
        return this.originalSize;
    }

    public void setOriginalSize(String str) {
        this.originalSize = str;
    }

    public String getFileCheck() {
        return this.fileCheck;
    }

    public void setFileCheck(String str) {
        this.fileCheck = str;
    }

    public String getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime(String str) {
        this.lastModifiedTime = str;
    }
}
