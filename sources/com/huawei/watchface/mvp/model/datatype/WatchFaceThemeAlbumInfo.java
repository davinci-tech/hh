package com.huawei.watchface.mvp.model.datatype;

import java.util.List;

/* loaded from: classes7.dex */
public class WatchFaceThemeAlbumInfo {
    private List<String> mBgPathList;
    private String mDefaultTimePosLabel;
    private String mDefaultTimeTextFont;
    private String mDpi;
    private String mSupportMaxBgAmount = "5";
    private List<String> mSvgPathList;
    private String mTextAmbientColor;
    private List<TimePositionRect> mTimePositionList;

    public List<String> getSvgPathList() {
        return this.mSvgPathList;
    }

    public void setSvgPathList(List<String> list) {
        this.mSvgPathList = list;
    }

    public List<String> getBgPathList() {
        return this.mBgPathList;
    }

    public void setBgPathList(List<String> list) {
        this.mBgPathList = list;
    }

    public String getDpi() {
        return this.mDpi;
    }

    public void setDpi(String str) {
        this.mDpi = str;
    }

    public String getDefaultTimeTextFont() {
        return this.mDefaultTimeTextFont;
    }

    public void setDefaultTimeTextFont(String str) {
        this.mDefaultTimeTextFont = str;
    }

    public String getSupportMaxBgAmount() {
        return this.mSupportMaxBgAmount;
    }

    public void setSupportMaxBgAmount(String str) {
        this.mSupportMaxBgAmount = str;
    }

    public List<TimePositionRect> getTimePositionList() {
        return this.mTimePositionList;
    }

    public void setTimePositionList(List<TimePositionRect> list) {
        this.mTimePositionList = list;
    }

    public String getDefaultTimePosLabel() {
        return this.mDefaultTimePosLabel;
    }

    public void setDefaultTimePosLabel(String str) {
        this.mDefaultTimePosLabel = str;
    }

    public String getTextAmbientColor() {
        return this.mTextAmbientColor;
    }

    public void setTextAmbientColor(String str) {
        this.mTextAmbientColor = str;
    }

    public String toString() {
        return "WatchFaceThemeAlbumInfo{mDpi='" + this.mDpi + "', mTimePositionList=" + this.mTimePositionList + ", mDefaultTimePosLabel='" + this.mDefaultTimePosLabel + "', mTextAmbientColor='" + this.mTextAmbientColor + "', mDefaultTimeTextFont='" + this.mDefaultTimeTextFont + "', mSupportMaxBgAmount='" + this.mSupportMaxBgAmount + "', mSvgPathList=" + this.mSvgPathList + ", mBgPathList=" + this.mBgPathList + '}';
    }
}
