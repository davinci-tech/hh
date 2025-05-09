package com.huawei.wearengine.auth;

/* loaded from: classes8.dex */
public class AuthInfo {

    /* renamed from: a, reason: collision with root package name */
    private int f11225a;
    private String b;
    private String c;
    private int d;
    private int e;
    private String g;

    public int getOpenStatus() {
        return this.d;
    }

    public void setOpenStatus(int i) {
        this.d = i;
    }

    public String getKey() {
        return this.b;
    }

    public void setKey(String str) {
        this.b = str;
    }

    public String getUserId() {
        return this.g;
    }

    public void setUserId(String str) {
        this.g = str;
    }

    public int getAppId() {
        return this.f11225a;
    }

    public void setAppId(int i) {
        this.f11225a = i;
    }

    public String getPermission() {
        return this.c;
    }

    public void setPermission(String str) {
        this.c = str;
    }

    public int getAppUid() {
        return this.e;
    }

    public void setAppUid(int i) {
        this.e = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AuthInfo:{key=");
        stringBuffer.append(this.b);
        stringBuffer.append(", appUid=").append(this.e);
        stringBuffer.append(", appId=").append(this.f11225a);
        stringBuffer.append(", userId=").append(this.g);
        stringBuffer.append(", permission=").append(this.c);
        stringBuffer.append(", openStatus=").append(this.d);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
