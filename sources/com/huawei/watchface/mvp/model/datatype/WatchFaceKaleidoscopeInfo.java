package com.huawei.watchface.mvp.model.datatype;

import java.util.ArrayList;

/* loaded from: classes7.dex */
public class WatchFaceKaleidoscopeInfo {
    public static final int BACKGROUND_TYPE_BIN = 2;
    public static final int LIST_MAX_LENGTH = 20;
    private int currentMaterialImageIndex;
    private int handsStyleIndex;
    private int kaleidoscopeType;
    private int mBgImageOption = 0;
    private ArrayList<KaleidoscopeStruct> materialImageList;
    private int materialImageNum;
    private int materialImageType;
    private int maxMaterialImages;

    public int getBgImageOption() {
        return this.mBgImageOption;
    }

    public void setBgImageOption(int i) {
        this.mBgImageOption = i;
    }

    public int getMaterialImageType() {
        return this.materialImageType;
    }

    public void setMaterialImageType(int i) {
        this.materialImageType = i;
    }

    public int getMaxMaterialImages() {
        return this.maxMaterialImages;
    }

    public void setMaxMaterialImages(int i) {
        this.maxMaterialImages = i;
    }

    public int getMaterialImageNum() {
        return this.materialImageNum;
    }

    public void setMaterialImageNum(int i) {
        this.materialImageNum = i;
    }

    public ArrayList<KaleidoscopeStruct> getMaterialImageList() {
        return this.materialImageList;
    }

    public void setMaterialImageList(ArrayList<KaleidoscopeStruct> arrayList) {
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
}
