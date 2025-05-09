package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice;

import com.huawei.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class WearWatchFaceInfo {

    @SerializedName("clockTypeCapability")
    private long clockTypeCapability;

    @SerializedName("curStyleIndex")
    private long curStyleIndex;

    @SerializedName("curWearImageIndex")
    private long curWearImageIndex;

    @SerializedName("maxStyleNum")
    private int maxStyleNum;

    @SerializedName("rectRadius")
    private int rectRadius;

    @SerializedName("tintClockOption")
    private int tintClockOption;

    @SerializedName("wearImageOption")
    private int wearImageOption;

    @SerializedName("wearStyleList")
    private List<WearStyleStruct> wearStyleList = new ArrayList(16);

    @SerializedName("wearStyleNum")
    private int wearStyleNum;

    @SerializedName("wearStyleType")
    private int wearStyleType;

    @SerializedName("wearTypeCapability")
    private long wearTypeCapability;

    public int getMaxStyleNum() {
        return this.maxStyleNum;
    }

    public void setMaxStyleNum(int i) {
        this.maxStyleNum = i;
    }

    public int getWearStyleNum() {
        return this.wearStyleNum;
    }

    public void setWearStyleNum(int i) {
        this.wearStyleNum = i;
    }

    public int getWearStyleType() {
        return this.wearStyleType;
    }

    public void setWearStyleType(int i) {
        this.wearStyleType = i;
    }

    public long getWearTypeCapability() {
        return this.wearTypeCapability;
    }

    public void setWearTypeCapability(long j) {
        this.wearTypeCapability = j;
    }

    public long getClockTypeCapability() {
        return this.clockTypeCapability;
    }

    public void setClockTypeCapability(long j) {
        this.clockTypeCapability = j;
    }

    public List<WearStyleStruct> getWearStyleList() {
        return this.wearStyleList;
    }

    public void setWearStyleList(List<WearStyleStruct> list) {
        this.wearStyleList = list;
    }

    public long getCurStyleIndex() {
        return this.curStyleIndex;
    }

    public void setCurStyleIndex(long j) {
        this.curStyleIndex = j;
    }

    public int getWearImageOption() {
        return this.wearImageOption;
    }

    public void setWearImageOption(int i) {
        this.wearImageOption = i;
    }

    public int getTintClockOption() {
        return this.tintClockOption;
    }

    public void setTintClockOption(int i) {
        this.tintClockOption = i;
    }

    public int getRectRadius() {
        return this.rectRadius;
    }

    public void setRectRadius(int i) {
        this.rectRadius = i;
    }

    public long getCurWearImageIndex() {
        return this.curWearImageIndex;
    }

    public void setCurWearImageIndex(long j) {
        this.curWearImageIndex = j;
    }

    public static class WearStyleStruct {

        @SerializedName("bgImageName")
        private String bgImageName;

        @SerializedName("clockStyleColor")
        private String clockStyleColor;

        @SerializedName("clockStyleIndex")
        private int clockStyleIndex;

        @SerializedName("isPreset")
        private boolean isPreset;

        @SerializedName("previewName")
        private String previewName;

        @SerializedName("wearStyleId")
        private long wearStyleId;

        @SerializedName("wearType")
        private int wearType = 1;

        public long getWearStyleId() {
            return this.wearStyleId;
        }

        public void setWearStyleId(long j) {
            this.wearStyleId = j;
        }

        public String getPreviewName() {
            return this.previewName;
        }

        public void setPreviewName(String str) {
            this.previewName = str;
        }

        public int getWearType() {
            return this.wearType;
        }

        public void setWearType(int i) {
            this.wearType = i;
        }

        public String getBgImageName() {
            return this.bgImageName;
        }

        public void setBgImageName(String str) {
            this.bgImageName = str;
        }

        public int getClockStyleIndex() {
            return this.clockStyleIndex;
        }

        public void setClockStyleIndex(int i) {
            this.clockStyleIndex = i;
        }

        public String getClockStyleColor() {
            return this.clockStyleColor;
        }

        public void setClockStyleColor(String str) {
            this.clockStyleColor = str;
        }

        public boolean isPreset() {
            return this.isPreset;
        }

        public void setPreset(boolean z) {
            this.isPreset = z;
        }
    }
}
