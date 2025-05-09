package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice;

import java.util.List;

/* loaded from: classes5.dex */
public class WatchFaceParamsResult {
    private List<CompatibleDialStruct> compatibleDialList;
    private int currentMode;
    private List<Integer> maxFirmware;
    private int powerLevel;
    private int screenHeight;
    private int screenWidth;
    private int supportCapability;
    private List<Integer> supportEarlyFirmwareVersion;
    private int supportFileType;
    private String supportGaussianColor;
    private String supportOneTouch;
    private int supportSort;
    private int supportSync;

    public int getSupportSync() {
        return this.supportSync;
    }

    public void setSupportSync(int i) {
        this.supportSync = i;
    }

    public int getCurrentMode() {
        return this.currentMode;
    }

    public void setCurrentMode(int i) {
        this.currentMode = i;
    }

    public String getSupportGaussianColor() {
        return this.supportGaussianColor;
    }

    public void setSupportGaussianColor(String str) {
        this.supportGaussianColor = str;
    }

    public List<Integer> getMaxFirmware() {
        return this.maxFirmware;
    }

    public void setMaxFirmware(List<Integer> list) {
        this.maxFirmware = list;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public void setScreenHeight(int i) {
        this.screenHeight = i;
    }

    public int getSupportFileType() {
        return this.supportFileType;
    }

    public void setSupportFileType(int i) {
        this.supportFileType = i;
    }

    public int getSupportSort() {
        return this.supportSort;
    }

    public void setSupportSort(int i) {
        this.supportSort = i;
    }

    public List<Integer> getSupportEarlyFirmwareVersion() {
        return this.supportEarlyFirmwareVersion;
    }

    public void setSupportEarlyFirmwareVersion(List<Integer> list) {
        this.supportEarlyFirmwareVersion = list;
    }

    public List<CompatibleDialStruct> getCompatibleDialList() {
        return this.compatibleDialList;
    }

    public void setCompatibleDialList(List<CompatibleDialStruct> list) {
        this.compatibleDialList = list;
    }

    public int getSupportCapability() {
        return this.supportCapability;
    }

    public void setSupportCapability(int i) {
        this.supportCapability = i;
    }

    public String getSupportOneTouch() {
        return this.supportOneTouch;
    }

    public void setSupportOneTouch(String str) {
        this.supportOneTouch = str;
    }

    public int getPowerLevel() {
        return this.powerLevel;
    }

    public void setPowerLevel(int i) {
        this.powerLevel = i;
    }
}
