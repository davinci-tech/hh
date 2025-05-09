package com.huawei.hms.update.download.api;

/* loaded from: classes9.dex */
public class UpdateInfo {
    public String mHash;
    public int mNewVersionCode;
    public String mPackageName;
    public int mSize;
    public String mUri;

    public UpdateInfo() {
        this.mNewVersionCode = 0;
        this.mPackageName = "";
        this.mUri = "";
        this.mSize = 0;
        this.mHash = "";
    }

    public boolean isValid() {
        String str;
        return this.mNewVersionCode > 0 && this.mSize > 0 && (str = this.mUri) != null && !str.isEmpty();
    }

    public UpdateInfo(String str, int i, String str2, int i2, String str3) {
        this.mPackageName = str;
        this.mNewVersionCode = i;
        this.mUri = str2;
        this.mSize = i2;
        this.mHash = str3;
    }
}
