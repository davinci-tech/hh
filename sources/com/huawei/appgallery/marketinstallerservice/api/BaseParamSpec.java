package com.huawei.appgallery.marketinstallerservice.api;

/* loaded from: classes3.dex */
public class BaseParamSpec {
    private String b = null;
    private String e = null;

    /* renamed from: a, reason: collision with root package name */
    private String f1875a = null;
    private boolean d = false;

    public void setUpdate(boolean z) {
        this.d = z;
    }

    public void setSubsystem(String str) {
        this.e = str;
    }

    public void setServerUrl(String str) {
        this.b = str;
    }

    public void setMarketPkg(String str) {
        this.f1875a = str;
    }

    public boolean isUpdate() {
        return this.d;
    }

    public String getSubsystem() {
        return this.e;
    }

    public String getServerUrl() {
        return this.b;
    }

    public String getMarketPkg() {
        return this.f1875a;
    }
}
