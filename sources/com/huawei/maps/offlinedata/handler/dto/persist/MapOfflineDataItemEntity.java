package com.huawei.maps.offlinedata.handler.dto.persist;

/* loaded from: classes5.dex */
public class MapOfflineDataItemEntity {
    private String cityId;
    private String cityName;
    private String countryCode;
    private String countryId;
    private String countryName;
    private String dataType;
    private String fileCheck;
    private String fileId;
    private long id;
    private String lastModifiedTime;
    private String lat;
    private String lon;
    private String maxLat;
    private String maxLon;
    private String minLat;
    private String minLon;
    private String originalSize;
    private String packageSize;
    private String political;
    private String regionId;
    private String regionName;
    private String version;

    public MapOfflineDataItemEntity() {
    }

    public MapOfflineDataItemEntity(WorldMapOfflineDataItemEntity worldMapOfflineDataItemEntity) {
        this.id = worldMapOfflineDataItemEntity.getId();
        this.countryId = worldMapOfflineDataItemEntity.getCountryId();
        this.countryName = worldMapOfflineDataItemEntity.getCountryName();
        this.regionId = worldMapOfflineDataItemEntity.getRegionId();
        this.regionName = worldMapOfflineDataItemEntity.getRegionName();
        this.cityId = worldMapOfflineDataItemEntity.getCityId();
        this.cityName = worldMapOfflineDataItemEntity.getCityName();
        this.fileCheck = worldMapOfflineDataItemEntity.getFileCheck();
        this.originalSize = worldMapOfflineDataItemEntity.getOriginalSize();
        this.packageSize = worldMapOfflineDataItemEntity.getPackageSize();
        this.version = worldMapOfflineDataItemEntity.getVersion();
        this.political = worldMapOfflineDataItemEntity.getPolitical();
        this.fileId = worldMapOfflineDataItemEntity.getFileId();
        this.lastModifiedTime = worldMapOfflineDataItemEntity.getLastModifiedTime();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String str) {
        this.dataType = str;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String str) {
        this.countryId = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
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

    public String getLon() {
        return this.lon;
    }

    public void setLon(String str) {
        this.lon = str;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String str) {
        this.lat = str;
    }

    public String getMaxLon() {
        return this.maxLon;
    }

    public void setMaxLon(String str) {
        this.maxLon = str;
    }

    public String getMaxLat() {
        return this.maxLat;
    }

    public void setMaxLat(String str) {
        this.maxLat = str;
    }

    public String getMinLon() {
        return this.minLon;
    }

    public void setMinLon(String str) {
        this.minLon = str;
    }

    public String getMinLat() {
        return this.minLat;
    }

    public void setMinLat(String str) {
        this.minLat = str;
    }

    public String getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime(String str) {
        this.lastModifiedTime = str;
    }
}
