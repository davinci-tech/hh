package com.huawei.watchface.mvp.model.datatype;

import java.util.List;

/* loaded from: classes9.dex */
public class WatchFaceInfoForH5 {
    private String aarVersion;
    private AbilityListForH5 abilityList;
    private String appVersion;
    private String buildNumber;
    private List<ScreenInfo> compatibleInfo;
    private String currentWatchFaceId;
    private int deviceMode = -1;
    private String deviceName;
    private String filetype;
    private String firmware;
    private String hideWatchFaceIdStore;
    private boolean isHuaweiPhone;
    private String isoCode;
    private String locale;
    private String phoneType;
    private int powerLevel;
    private String preWatchFaceIdStore;
    private List<ScreenInfo> purchasedInfo;
    private String screen;
    private String themeVersion;
    private String watchFaceIdStore;

    public String getHideWatchFaceIdStore() {
        return this.hideWatchFaceIdStore;
    }

    public void setHideWatchFaceIdStore(String str) {
        this.hideWatchFaceIdStore = str;
    }

    public String getAarVersion() {
        return this.aarVersion;
    }

    public void setAarVersion(String str) {
        this.aarVersion = str;
    }

    public String getFirmware() {
        return this.firmware;
    }

    public void setFirmware(String str) {
        this.firmware = str;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public String getScreen() {
        return this.screen;
    }

    public void setScreen(String str) {
        this.screen = str;
    }

    public String getPhoneType() {
        return this.phoneType;
    }

    public void setPhoneType(String str) {
        this.phoneType = str;
    }

    public String getIsoCode() {
        return this.isoCode;
    }

    public void setIsoCode(String str) {
        this.isoCode = str;
    }

    public String getBuildNumber() {
        return this.buildNumber;
    }

    public void setBuildNumber(String str) {
        this.buildNumber = str;
    }

    public String getFiletype() {
        return this.filetype;
    }

    public void setFiletype(String str) {
        this.filetype = str;
    }

    public String getThemeVersion() {
        return this.themeVersion;
    }

    public void setThemeVersion(String str) {
        this.themeVersion = str;
    }

    public String getCurrentWatchFaceId() {
        return this.currentWatchFaceId;
    }

    public void setCurrentWatchFaceId(String str) {
        this.currentWatchFaceId = str;
    }

    public String getPreWatchFaceIdStore() {
        return this.preWatchFaceIdStore;
    }

    public void setPreWatchFaceIdStore(String str) {
        this.preWatchFaceIdStore = str;
    }

    public String getWatchFaceIdStore() {
        return this.watchFaceIdStore;
    }

    public void setWatchFaceIdStore(String str) {
        this.watchFaceIdStore = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public boolean isHuaweiPhone() {
        return this.isHuaweiPhone;
    }

    public void setHuaweiPhone(boolean z) {
        this.isHuaweiPhone = z;
    }

    public List<ScreenInfo> getCompatibleInfo() {
        return this.compatibleInfo;
    }

    public void setCompatibleInfo(List<ScreenInfo> list) {
        this.compatibleInfo = list;
    }

    public List<ScreenInfo> getPurchasedInfo() {
        return this.purchasedInfo;
    }

    public void setPurchasedInfo(List<ScreenInfo> list) {
        this.purchasedInfo = list;
    }

    public AbilityListForH5 getAbilityList() {
        return this.abilityList;
    }

    public void setAbilityList(AbilityListForH5 abilityListForH5) {
        this.abilityList = abilityListForH5;
    }

    public int getPowerLevel() {
        return this.powerLevel;
    }

    public void setPowerLevel(int i) {
        this.powerLevel = i;
    }

    public int getDeviceMode() {
        return this.deviceMode;
    }

    public void setDeviceMode(int i) {
        this.deviceMode = i;
    }
}
