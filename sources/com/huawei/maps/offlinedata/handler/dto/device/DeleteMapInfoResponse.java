package com.huawei.maps.offlinedata.handler.dto.device;

/* loaded from: classes5.dex */
public class DeleteMapInfoResponse {
    private String cityId;
    private Integer mapDeleteState;
    private Integer mapType;

    public Integer getMapDeleteState() {
        return this.mapDeleteState;
    }

    public void setMapDeleteState(Integer num) {
        this.mapDeleteState = num;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String str) {
        this.cityId = str;
    }

    public int getMapType() {
        return this.mapType.intValue();
    }

    public void setMapType(int i) {
        this.mapType = Integer.valueOf(i);
    }
}
