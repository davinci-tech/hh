package com.huawei.hms.framework.network.restclient.dnkeeper;

import android.text.TextUtils;

/* loaded from: classes4.dex */
public final class RequestHost {

    /* renamed from: a, reason: collision with root package name */
    private String f4566a;
    private String b;
    private String c = "Unknown Reason";
    private String d = "UNKNOWN";
    private int e = 0;
    private String f = "ALL";
    private long g;

    public String toString() {
        return this.f4566a + "";
    }

    public void setType(String str) {
        this.f = str;
    }

    public void setTime(long j) {
        this.g = j;
    }

    public void setFailIP(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.d = str;
    }

    public void setDomainName(String str) {
        this.f4566a = str;
    }

    public void setDnsFailType(String str) {
        this.c = str;
    }

    public void setApkName(String str) {
        this.b = str;
    }

    public int hashCode() {
        String str = this.f4566a;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String getType() {
        return this.f;
    }

    public long getTime() {
        return this.g;
    }

    public String getFailIP() {
        return this.d;
    }

    public String getDomainName() {
        return this.f4566a;
    }

    public String getDnsFailType() {
        return this.c;
    }

    public String getApkName() {
        return this.b;
    }

    public int getAccelerate() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RequestHost) {
            return this.f4566a.equals(((RequestHost) obj).f4566a);
        }
        return false;
    }

    public void enableAccelerate(boolean z) {
        this.e = z ? 1 : 0;
    }

    public RequestHost(String str) {
        setDomainName(str);
    }
}
