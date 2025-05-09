package com.huawei.ui.thirdpartservice.interactors.thirdpartyservice.qqhealth.mgr;

/* loaded from: classes8.dex */
public class QqHealthTable {
    private String mExpireTime;
    private int mIdTable;
    private String mNickName;
    private String mOpenId;
    private String mQqLogoPath;
    private String mToken;

    public String getExpireTime() {
        return this.mExpireTime;
    }

    public void setExpireTime(String str) {
        this.mExpireTime = str;
    }

    public int getId() {
        return this.mIdTable;
    }

    public void setId(int i) {
        this.mIdTable = i;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String str) {
        this.mNickName = str;
    }

    public String getOpenId() {
        return this.mOpenId;
    }

    public void setOpenId(String str) {
        this.mOpenId = str;
    }

    public String getQqLogoPath() {
        return this.mQqLogoPath;
    }

    public void setQqLogoPath(String str) {
        this.mQqLogoPath = str;
    }

    public String getToken() {
        return this.mToken;
    }

    public void setToken(String str) {
        this.mToken = str;
    }

    public String toString() {
        return "QqHealthTable [id=" + this.mIdTable + ", mOpenId = " + this.mOpenId + ", mExpireTime = " + this.mExpireTime + ", mNickName = " + this.mNickName + ", mQqLogoPath = " + this.mQqLogoPath + "]";
    }
}
