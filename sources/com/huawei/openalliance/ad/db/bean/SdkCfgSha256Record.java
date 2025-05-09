package com.huawei.openalliance.ad.db.bean;

/* loaded from: classes5.dex */
public class SdkCfgSha256Record extends a {
    public static final String PKGNAME = "pkgName";
    public static final String SHA256 = "sha256";
    String pkgName;
    String sha256;

    public void b(String str) {
        this.sha256 = str;
    }

    public String b() {
        return this.sha256;
    }

    public void a(String str) {
        this.pkgName = str;
    }

    public String a() {
        return this.pkgName;
    }
}
