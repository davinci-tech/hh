package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

/* loaded from: classes.dex */
public class InstalledWatchFace {
    private String iconPicture;
    private boolean isCurrent;
    private boolean isOnTrial;
    private boolean isSupportDelete;
    private String jumpUrl;
    private String latestVersion;
    private int status;
    private String watchFaceId;
    private String watchFaceName;
    private int watchFaceType;
    private String watchFaceVersion;

    public boolean isOnTrial() {
        return this.isOnTrial;
    }

    public void setOnTrial(boolean z) {
        this.isOnTrial = z;
    }

    public String getJumpUrl() {
        return this.jumpUrl;
    }

    public void setJumpUrl(String str) {
        this.jumpUrl = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
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

    public String getWatchFaceName() {
        return this.watchFaceName;
    }

    public void setWatchFaceName(String str) {
        this.watchFaceName = str;
    }

    public boolean isCurrent() {
        return this.isCurrent;
    }

    public void setCurrent(boolean z) {
        this.isCurrent = z;
    }

    public String getIconPicture() {
        return this.iconPicture;
    }

    public void setIconPicture(String str) {
        this.iconPicture = str;
    }

    public boolean isSupportDelete() {
        return this.isSupportDelete;
    }

    public void setSupportDelete(boolean z) {
        this.isSupportDelete = z;
    }

    public String getLatestVersion() {
        return this.latestVersion;
    }

    public void setLatestVersion(String str) {
        this.latestVersion = str;
    }

    public int getWatchFaceType() {
        return this.watchFaceType;
    }

    public void setWatchFaceType(int i) {
        this.watchFaceType = i;
    }
}
