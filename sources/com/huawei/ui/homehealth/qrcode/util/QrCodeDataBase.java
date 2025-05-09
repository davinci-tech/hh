package com.huawei.ui.homehealth.qrcode.util;

/* loaded from: classes6.dex */
public abstract class QrCodeDataBase {
    protected String action;

    public abstract int parser(Object obj);

    public QrCodeDataBase(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }
}
