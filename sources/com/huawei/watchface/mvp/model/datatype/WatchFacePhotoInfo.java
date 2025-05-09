package com.huawei.watchface.mvp.model.datatype;

import java.util.ArrayList;

/* loaded from: classes7.dex */
public class WatchFacePhotoInfo {
    public static final int BACKGROUND_TYPE_BIN = 2;
    public static final int BACKGROUND_TYPE_PNG = 1;
    public static final int LIST_MAX_LENGTH = 20;
    private int mBackgroundType;
    private int mDataIndex;
    private boolean mIsSupportIntellectColor;
    private int mMaxBackgroundImage;
    private int mPortPositionIndex;
    private int mPortraitMode;
    private int mPositionIndex;
    private int mStyleIndex;
    private ArrayList<String> mBackgroundList = new ArrayList<>(20);
    private ArrayList<Integer> mPortPositionIndexList = new ArrayList<>(20);
    private ArrayList<Integer> mPortraitModeList = new ArrayList<>(20);
    private int mBgImageOption = 0;

    public int getMaxBackgroundImage() {
        return this.mMaxBackgroundImage;
    }

    public void setMaxBackgroundImage(int i) {
        this.mMaxBackgroundImage = i;
    }

    public ArrayList<Integer> getPortPositionIndexList() {
        return this.mPortPositionIndexList;
    }

    public void setPortPositionIndexList(ArrayList<Integer> arrayList) {
        this.mPortPositionIndexList = arrayList;
    }

    public ArrayList<Integer> getPortraitModeList() {
        return this.mPortraitModeList;
    }

    public void setPortraitModeList(ArrayList<Integer> arrayList) {
        this.mPortraitModeList = arrayList;
    }

    public int getBgImageOption() {
        return this.mBgImageOption;
    }

    public void setBgImageOption(int i) {
        this.mBgImageOption = i;
    }

    public int getDataIndex() {
        return this.mDataIndex;
    }

    public void setDataIndex(int i) {
        this.mDataIndex = i;
    }

    public int getMaxBackgroundImages() {
        return this.mMaxBackgroundImage;
    }

    public void setMaxBackgroundImages(int i) {
        this.mMaxBackgroundImage = i;
    }

    public boolean isSupportIntellectColor() {
        return this.mIsSupportIntellectColor;
    }

    public void setSupportIntellectColor(boolean z) {
        this.mIsSupportIntellectColor = z;
    }

    public int getBackgroundType() {
        return this.mBackgroundType;
    }

    public void setBackgroundType(int i) {
        this.mBackgroundType = i;
    }

    public ArrayList<String> getBackgroundList() {
        return this.mBackgroundList;
    }

    public void setBackgroundList(ArrayList<String> arrayList) {
        this.mBackgroundList = arrayList;
    }

    public int getPositionIndex() {
        return this.mPositionIndex;
    }

    public void setPositionIndex(int i) {
        this.mPositionIndex = i;
    }

    public int getStyleIndex() {
        return this.mStyleIndex;
    }

    public void setStyleIndex(int i) {
        this.mStyleIndex = i;
    }

    public int getPortraitMode() {
        return this.mPortraitMode;
    }

    public void setPortraitMode(int i) {
        this.mPortraitMode = i;
    }

    public int getPortPositionIndex() {
        return this.mPortPositionIndex;
    }

    public void setPortPositionIndex(int i) {
        this.mPortPositionIndex = i;
    }
}
