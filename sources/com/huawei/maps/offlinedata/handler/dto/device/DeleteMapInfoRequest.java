package com.huawei.maps.offlinedata.handler.dto.device;

/* loaded from: classes9.dex */
public class DeleteMapInfoRequest {
    private Long cityId;
    private Integer mapType;

    public DeleteMapInfoRequest() {
    }

    public DeleteMapInfoRequest(long j, int i) {
        this.cityId = Long.valueOf(j);
        this.mapType = Integer.valueOf(i);
    }

    public long getCityId() {
        return this.cityId.longValue();
    }

    public void setCityId(long j) {
        this.cityId = Long.valueOf(j);
    }

    public int getMapType() {
        return this.mapType.intValue();
    }

    public void setMapType(int i) {
        this.mapType = Integer.valueOf(i);
    }
}
