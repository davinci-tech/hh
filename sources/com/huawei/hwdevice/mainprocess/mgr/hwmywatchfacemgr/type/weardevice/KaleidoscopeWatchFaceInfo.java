package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class KaleidoscopeWatchFaceInfo {

    @SerializedName("currentMaterialImageIndex")
    private int currentMaterialImageIndex;

    @SerializedName("handsStyleIndex")
    private int handsStyleIndex;

    @SerializedName("kaleidoscopeType")
    private int kaleidoscopeType;

    @SerializedName("materialImageList")
    private ArrayList<MaterialImage> materialImageList = new ArrayList<>(16);

    @SerializedName("materialImageType")
    private int materialImageType;

    @SerializedName("maxMaterialImages")
    private int maxMaterialImages;

    public int getMaxMaterialImages() {
        return this.maxMaterialImages;
    }

    public void setMaxMaterialImages(int i) {
        this.maxMaterialImages = i;
    }

    public int getMaterialImageType() {
        return this.materialImageType;
    }

    public void setMaterialImageType(int i) {
        this.materialImageType = i;
    }

    public ArrayList<MaterialImage> getMaterialImageList() {
        return this.materialImageList;
    }

    public void setMaterialImageList(ArrayList<MaterialImage> arrayList) {
        this.materialImageList = arrayList;
    }

    public int getCurrentMaterialImageIndex() {
        return this.currentMaterialImageIndex;
    }

    public void setCurrentMaterialImageIndex(int i) {
        this.currentMaterialImageIndex = i;
    }

    public int getHandsStyleIndex() {
        return this.handsStyleIndex;
    }

    public void setHandsStyleIndex(int i) {
        this.handsStyleIndex = i;
    }

    public int getKaleidoscopeType() {
        return this.kaleidoscopeType;
    }

    public void setKaleidoscopeType(int i) {
        this.kaleidoscopeType = i;
    }

    public static class MaterialImage {

        @SerializedName("isPreset")
        private boolean isPreset;

        @SerializedName("materialImageIndex")
        private int materialImageIndex;

        @SerializedName("materialImageName")
        private String materialImageName;

        public int getMaterialImageIndex() {
            return this.materialImageIndex;
        }

        public void setMaterialImageIndex(int i) {
            this.materialImageIndex = i;
        }

        public String getMaterialImageName() {
            return this.materialImageName;
        }

        public void setMaterialImageName(String str) {
            this.materialImageName = str;
        }

        public boolean isPreset() {
            return this.isPreset;
        }

        public void setPreset(boolean z) {
            this.isPreset = z;
        }
    }
}
