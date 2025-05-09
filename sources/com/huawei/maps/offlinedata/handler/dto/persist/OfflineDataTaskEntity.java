package com.huawei.maps.offlinedata.handler.dto.persist;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes5.dex */
public class OfflineDataTaskEntity {
    private String cityId;
    private String cityName;
    private String countryId;
    private String countryName;
    private String dataType;
    private Integer downloadProgress;
    private String id;
    private String lastModifyTime;
    private String political;
    private String regionId;
    private String regionName;
    private String taskId;
    private Integer transmitProgress;
    private String version;
    private String taskState = "";
    private String itemState = "";

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
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

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getDataType() {
        return this.dataType;
    }

    public int getMapType() {
        if (isGlobal()) {
            return 2;
        }
        return (!TextUtils.equals("watch_map", this.dataType) && TextUtils.equals("watch_map_contour", this.dataType)) ? 1 : 0;
    }

    public void setDataType(String str) {
        this.dataType = str;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public String getTaskState() {
        return this.taskState;
    }

    public void setTaskState(String str) {
        this.taskState = str;
    }

    public String getItemState() {
        return this.itemState;
    }

    public void setItemState(String str) {
        this.itemState = str;
    }

    public Integer getDownloadProgress() {
        return this.downloadProgress;
    }

    public void setDownloadProgress(Integer num) {
        this.downloadProgress = num;
    }

    public String getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(String str) {
        this.lastModifyTime = str;
    }

    public Integer getTransmitProgress() {
        return this.transmitProgress;
    }

    public void setTransmitProgress(Integer num) {
        this.transmitProgress = num;
    }

    public String getIndexId() {
        if (isGlobal()) {
            return "global";
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(this.countryId)) {
            arrayList.add(this.countryId);
        }
        if (!TextUtils.isEmpty(this.regionId)) {
            arrayList.add(this.regionId);
        }
        if (!TextUtils.isEmpty(this.cityId)) {
            arrayList.add(this.cityId);
        }
        return TextUtils.join("_", arrayList);
    }

    public String getPrintId() {
        if (isGlobal()) {
            return "global";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.cityId);
        arrayList.add(this.dataType);
        return TextUtils.join("_", arrayList);
    }

    public boolean isGlobal() {
        return TextUtils.isEmpty(this.countryId) && TextUtils.isEmpty(this.regionId) && TextUtils.isEmpty(this.cityId) && !TextUtils.isEmpty(this.countryName);
    }

    public boolean equals(Object obj) {
        if (obj instanceof OfflineDataTaskEntity) {
            OfflineDataTaskEntity offlineDataTaskEntity = (OfflineDataTaskEntity) obj;
            if (Objects.equals(this.cityId, offlineDataTaskEntity.getCityId()) && Objects.equals(this.regionId, offlineDataTaskEntity.getRegionId()) && Objects.equals(this.countryId, offlineDataTaskEntity.getCountryId()) && Objects.equals(this.dataType, offlineDataTaskEntity.getDataType())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(getCountryId(), getRegionId(), getCityId(), getDataType());
    }

    public static final class Builder {
        private String cityId;
        private String countryId;
        private String dataType;
        private String id;
        private String political;
        private String regionId;
        private String version;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder fromMapInfo(MapOfflineDataItemEntity mapOfflineDataItemEntity) {
            this.id = String.valueOf(mapOfflineDataItemEntity.getId());
            this.dataType = mapOfflineDataItemEntity.getDataType();
            this.countryId = mapOfflineDataItemEntity.getCountryId();
            this.regionId = mapOfflineDataItemEntity.getRegionId();
            this.cityId = mapOfflineDataItemEntity.getCityId();
            this.political = mapOfflineDataItemEntity.getPolitical();
            this.version = mapOfflineDataItemEntity.getVersion();
            return this;
        }

        public OfflineDataTaskEntity build() {
            OfflineDataTaskEntity offlineDataTaskEntity = new OfflineDataTaskEntity();
            offlineDataTaskEntity.setId(this.id);
            offlineDataTaskEntity.setDataType(this.dataType);
            offlineDataTaskEntity.setCountryId(this.countryId);
            offlineDataTaskEntity.setRegionId(this.regionId);
            offlineDataTaskEntity.setCityId(this.cityId);
            offlineDataTaskEntity.setPolitical(this.political);
            offlineDataTaskEntity.setVersion(this.version);
            return offlineDataTaskEntity;
        }
    }
}
