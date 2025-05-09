package com.huawei.ui.homehealth.device.sitting;

/* loaded from: classes6.dex */
public class RecommendedItem {
    private String mContent;
    private String mDeviceNameTitle;
    private int mDeviceType;
    private int mId;
    private String mMac = "";
    private boolean mIsShowRedDot = false;

    public String getDeviceNameTitle() {
        return this.mDeviceNameTitle;
    }

    public void setDeviceNameTitle(String str) {
        this.mDeviceNameTitle = str;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setContent(String str) {
        this.mContent = str;
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public String getMac() {
        return this.mMac;
    }

    public void setMac(String str) {
        this.mMac = str;
    }

    public boolean isShowRedDot() {
        return this.mIsShowRedDot;
    }

    public void setIsShowRedDot(boolean z) {
        this.mIsShowRedDot = z;
    }
}
