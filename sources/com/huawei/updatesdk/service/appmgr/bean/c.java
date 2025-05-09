package com.huawei.updatesdk.service.appmgr.bean;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public class c extends com.huawei.updatesdk.a.b.c.c.b {

    @SDKNetTransmission
    private String appName;

    @SDKNetTransmission
    private String downUrl;

    @SDKNetTransmission
    private long fileSize;

    @SDKNetTransmission
    private String pkgName;

    @SDKNetTransmission
    private String sha256;

    @SDKNetTransmission
    private long versionCode;

    @SDKNetTransmission
    private String versionName;

    public boolean f() {
        return (TextUtils.isEmpty(this.sha256) || TextUtils.isEmpty(this.downUrl) || this.fileSize <= 0) ? false : true;
    }

    public String e() {
        return this.sha256;
    }

    public String d() {
        return this.pkgName;
    }

    public long c() {
        return this.fileSize;
    }

    public String b() {
        return this.downUrl;
    }

    public String a() {
        return this.appName;
    }
}
