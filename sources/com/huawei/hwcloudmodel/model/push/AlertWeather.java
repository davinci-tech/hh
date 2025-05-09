package com.huawei.hwcloudmodel.model.push;

/* loaded from: classes5.dex */
public class AlertWeather {
    private String mAlertAreaName;
    private String mAlertContent;
    private int mAlertLevel;
    private String mAlertLevelName;
    private long mAlertPublicTime;
    private String mAlertTitle;
    private int mAlertType;
    private String mAlertTypeName;

    public String getAlertTitle() {
        return this.mAlertTitle;
    }

    public void setAlertTitle(String str) {
        this.mAlertTitle = str;
    }

    public String getAlertAreaName() {
        return this.mAlertAreaName;
    }

    public void setAlertAreaName(String str) {
        this.mAlertAreaName = str;
    }

    public int getAlertLevel() {
        return this.mAlertLevel;
    }

    public void setAlertLevel(int i) {
        this.mAlertLevel = i;
    }

    public String getAlertLevelName() {
        return this.mAlertLevelName;
    }

    public void setAlertLevelName(String str) {
        this.mAlertLevelName = str;
    }

    public int getAlertType() {
        return this.mAlertType;
    }

    public void setAlertType(int i) {
        this.mAlertType = i;
    }

    public String getAlertTypeName() {
        return this.mAlertTypeName;
    }

    public void setAlertTypeName(String str) {
        this.mAlertTypeName = str;
    }

    public String getAlertContent() {
        return this.mAlertContent;
    }

    public void setAlertContent(String str) {
        this.mAlertContent = str;
    }

    public long getAlertPublicTime() {
        return this.mAlertPublicTime;
    }

    public void setAlertPublicTime(long j) {
        this.mAlertPublicTime = j;
    }

    public String toString() {
        return "AlertWeather{mAlertTitle='" + this.mAlertTitle + "', mAlertAreaName='" + this.mAlertAreaName + "', mAlertLevel=" + this.mAlertLevel + ", mAlertLevelName='" + this.mAlertLevelName + "', mAlertType=" + this.mAlertType + ", mAlertTypeName='" + this.mAlertTypeName + "', mAlertContent='" + this.mAlertContent + "', mAlertPublicTime=" + this.mAlertPublicTime + '}';
    }
}
