package com.huawei.hwcloudmodel.model;

/* loaded from: classes7.dex */
public class ThirdAuthTokenI {
    private long huid = 0;
    private int thirdAccountType = 0;
    private String openId = "";
    private int thirdTokenType = 0;
    private String thirdToken = "";
    private int expireTime = 0;

    public long getHuid() {
        return this.huid;
    }

    public void setHuid(long j) {
        this.huid = j;
    }

    public int getThirdAccountType() {
        return this.thirdAccountType;
    }

    public void setThirdAccountType(int i) {
        this.thirdAccountType = i;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }

    public int getThirdTokenType() {
        return this.thirdTokenType;
    }

    public void setThirdTokenType(int i) {
        this.thirdTokenType = i;
    }

    public String getThirdToken() {
        return this.thirdToken;
    }

    public void setThirdToken(String str) {
        this.thirdToken = str;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(int i) {
        this.expireTime = i;
    }

    public String toString() {
        return "ThirdUserToken [huid = " + this.huid + ", thirdAccountType = " + this.thirdAccountType + ", thirdTokenType = " + this.thirdTokenType + ", thirdToken = " + this.thirdToken + ", expireTime = " + this.expireTime + "]";
    }
}
