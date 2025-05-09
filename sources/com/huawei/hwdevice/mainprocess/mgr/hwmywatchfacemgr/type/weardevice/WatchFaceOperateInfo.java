package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice;

/* loaded from: classes5.dex */
public class WatchFaceOperateInfo {
    private int errorCode;
    private int needReceive;
    private int operate;
    private int synctype;
    private int watchfaceHeight;
    private String watchfaceId;
    private String watchfaceVersion;
    private int watchfaceWidth;

    public int getSynctype() {
        return this.synctype;
    }

    public void setSynctype(int i) {
        this.synctype = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public String getWatchfaceId() {
        return this.watchfaceId;
    }

    public void setWatchfaceId(String str) {
        this.watchfaceId = str;
    }

    public String getWatchfaceVersion() {
        return this.watchfaceVersion;
    }

    public void setWatchfaceVersion(String str) {
        this.watchfaceVersion = str;
    }

    public int getOperate() {
        return this.operate;
    }

    public void setOperate(int i) {
        this.operate = i;
    }

    public int getNeedReceive() {
        return this.needReceive;
    }

    public void setNeedReceive(int i) {
        this.needReceive = i;
    }

    public int getWatchfaceWidth() {
        return this.watchfaceWidth;
    }

    public void setWatchfaceWidth(int i) {
        this.watchfaceWidth = i;
    }

    public int getWatchfaceHeight() {
        return this.watchfaceHeight;
    }

    public void setWatchfaceHeight(int i) {
        this.watchfaceHeight = i;
    }
}
