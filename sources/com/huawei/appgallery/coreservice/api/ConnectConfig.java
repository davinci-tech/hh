package com.huawei.appgallery.coreservice.api;

import defpackage.afu;
import java.io.Serializable;

/* loaded from: classes2.dex */
public class ConnectConfig implements Cloneable, Serializable {

    /* renamed from: a, reason: collision with root package name */
    private String f1866a;
    private String b;
    private String c;
    private String d;
    private String e;

    public void setInstallAppName(String str) {
        this.b = str;
    }

    public void setConnectServiceAction(String str) {
        this.f1866a = str;
    }

    public void setConnectAppPkg(String str) {
        this.d = str;
    }

    public void setAppSignCertchain(String str) {
        this.c = str;
    }

    public void setAppFingerprintSignature(String str) {
        this.e = str;
    }

    public String getInstallAppName() {
        return this.b;
    }

    public String getConnectServiceAction() {
        return this.f1866a;
    }

    public String getConnectAppPkg() {
        return this.d;
    }

    public String getAppSignCertchain() {
        return this.c;
    }

    public String getAppFingerprintSignature() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ConnectConfig m127clone() {
        try {
            return (ConnectConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            afu.a("ConnectConfig", "ConnectConfig Clone error:" + e.getMessage());
            return null;
        }
    }
}
