package com.huawei.openalliance.ad.beans.metadata;

/* loaded from: classes5.dex */
public class CheckResult {
    private String contentDownMethod;
    private String filePath;
    private String realPath;
    private boolean valid;

    public String d() {
        return this.contentDownMethod;
    }

    public String c() {
        return this.realPath;
    }

    public String b() {
        return this.filePath;
    }

    public boolean a() {
        return this.valid;
    }

    public CheckResult(boolean z, String str, String str2, String str3) {
        this.valid = z;
        this.filePath = str;
        this.realPath = str2;
        this.contentDownMethod = str3;
    }

    public CheckResult(boolean z, String str, String str2) {
        this.valid = z;
        this.filePath = str;
        this.realPath = str2;
    }

    public CheckResult() {
    }
}
