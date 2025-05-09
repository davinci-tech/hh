package com.huawei.basefitnessadvice.model;

/* loaded from: classes8.dex */
public class AccountInfo {
    private String mHuid;
    private boolean mIsLogin;
    private String mServiceToken;
    private int mSiteId;

    public AccountInfo(String str, boolean z, String str2, int i) {
        this.mHuid = str;
        this.mIsLogin = z;
        this.mServiceToken = str2;
        this.mSiteId = i;
    }

    public String acquireHuid() {
        return this.mHuid;
    }

    public boolean isLogin() {
        return this.mIsLogin;
    }

    public String acquireServiceToken() {
        return this.mServiceToken;
    }

    public int acquireSiteId() {
        return this.mSiteId;
    }
}
