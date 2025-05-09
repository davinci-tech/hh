package com.huawei.healthcloud.plugintrack.trackanimation.bgm.choosedownload;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class MusicInformation {

    @SerializedName("default")
    private boolean mIsDefault;
    private boolean mIsDownloaded;
    private boolean mIsDownloading;
    private boolean mIsMute;
    private boolean mIsSelected;
    private String mMusicBackgroundPath;

    @SerializedName("name")
    private String mMusicName;
    private String mMusicResourcePath;

    @SerializedName("uuid")
    private String mUuid;

    public void setIsSelected(boolean z) {
        this.mIsSelected = z;
    }

    public void setIsDownloaded(boolean z) {
        this.mIsDownloaded = z;
    }

    public void setIsDownloading(boolean z) {
        this.mIsDownloading = z;
    }

    public void setIsMute(boolean z) {
        this.mIsMute = z;
    }

    public void setMusicBackgroundPath(String str) {
        this.mMusicBackgroundPath = str;
    }

    public void setMusicResourcePath(String str) {
        this.mMusicResourcePath = str;
    }

    public void setMusicName(String str) {
        this.mMusicName = str;
    }

    public void setUuid(String str) {
        this.mUuid = str;
    }

    public void setIsDefault(boolean z) {
        this.mIsDefault = z;
    }

    public boolean getIsSelected() {
        return this.mIsSelected;
    }

    public boolean getIsDownloaded() {
        return this.mIsDownloaded;
    }

    public boolean getIsDownloading() {
        return this.mIsDownloading;
    }

    public boolean getIsMute() {
        return this.mIsMute;
    }

    public String getMusicBackgroundPath() {
        return this.mMusicBackgroundPath;
    }

    public String getMusicResourcePath() {
        return this.mMusicResourcePath;
    }

    public String getMusicName() {
        return this.mMusicName;
    }

    public String getUuid() {
        return this.mUuid;
    }

    public boolean getIsDefault() {
        return this.mIsDefault;
    }
}
