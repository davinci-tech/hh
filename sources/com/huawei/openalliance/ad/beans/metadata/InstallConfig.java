package com.huawei.openalliance.ad.beans.metadata;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class InstallConfig implements Serializable {
    private static final long serialVersionUID = 546555599564481045L;
    private String appBtnInstallWay;
    private String contentBtnInstallWay;
    private String creativeInstallWay;

    public void c(String str) {
        this.contentBtnInstallWay = str;
    }

    public String c() {
        return TextUtils.isEmpty(this.contentBtnInstallWay) ? this.appBtnInstallWay : this.contentBtnInstallWay;
    }

    public void b(String str) {
        this.appBtnInstallWay = str;
    }

    public String b() {
        return this.appBtnInstallWay;
    }

    public void a(String str) {
        this.creativeInstallWay = str;
    }

    public String a() {
        return this.creativeInstallWay;
    }
}
