package com.huawei.health.h5pro.jsbridge.system.share;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class ShareImageObj extends ShareObj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("uri")
    public String f2423a = "";

    @SerializedName("isReport")
    public boolean c = false;
    public String d = "";

    public void setUri(String str) {
        this.f2423a = str;
    }

    public void setIsReport(boolean z) {
        this.c = z;
    }

    public void setFilePath(String str) {
        this.d = str;
    }

    public boolean isReport() {
        return this.c;
    }

    public String getUri() {
        return this.f2423a;
    }

    public String getFilePath() {
        return this.d;
    }
}
