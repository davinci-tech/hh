package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class WearWatchFaceParam {

    @SerializedName("deviceId")
    private String deviceId;

    @SerializedName("deviceType")
    private String deviceType;

    @SerializedName("preWearWatchFaceInfo")
    private ArrayList<PresetWatchFace> preWearWatchFaceInfo;

    @SerializedName("watchFaceId")
    private String watchFaceId;

    @SerializedName("watchFaceVersion")
    private String watchFaceVersion;

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getWatchFaceId() {
        return this.watchFaceId;
    }

    public void setWatchFaceId(String str) {
        this.watchFaceId = str;
    }

    public String getWatchFaceVersion() {
        return this.watchFaceVersion;
    }

    public void setWatchFaceVersion(String str) {
        this.watchFaceVersion = str;
    }

    public ArrayList<PresetWatchFace> getPreWearWatchFaceInfo() {
        return this.preWearWatchFaceInfo;
    }

    public void setPreWearWatchFaceInfo(ArrayList<PresetWatchFace> arrayList) {
        this.preWearWatchFaceInfo = arrayList;
    }

    public static class PresetWatchFace {

        @SerializedName("index")
        private long index;

        @SerializedName("resPreview")
        private String resPreview;

        public long getIndex() {
            return this.index;
        }

        public void setIndex(long j) {
            this.index = j;
        }

        public String getResPreview() {
            return this.resPreview;
        }

        public void setResPreview(String str) {
            this.resPreview = str;
        }
    }
}
